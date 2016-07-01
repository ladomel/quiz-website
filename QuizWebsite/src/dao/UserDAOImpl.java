package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import classes.User;
import factory.ClassFactory;

public class UserDAOImpl implements UserDAO {

	DataSource dataSource;
	ClassFactory classFactory;
	
	public UserDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
		classFactory = new ClassFactory();	// TODO: pass as parameter
	}
	
	@Override
	public User getUser(String userName) {
		User user = null;
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement = 
					con.prepareStatement(
							"SELECT * "
							+ "FROM users "
							+ "WHERE username LIKE ?;");
			preparedStatement.setString(1, userName);
			ResultSet rs = preparedStatement.executeQuery();
			user = loadIntoUser(rs);
			preparedStatement.close();
			rs.close();
			con.close();
		} catch (SQLException e) {	e.printStackTrace();}
		return user;
	}


	private User loadIntoUser(ResultSet rs) throws SQLException {
		User user = null;
		if(!rs.next())
			return null; 
		else {
			user = classFactory.getUser(
					rs.getString("username"), 
					rs.getString("hash_password"), 
					rs.getString("salt")
					);
			user.setDescription(rs.getString("description"));
			user.setImage(rs.getString("image"));
			}
		return user;
	}

	@Override
	public User deleteUser(String userName) {
		User oldUser = getUser(userName);
		// return null if there is no such user
		if(oldUser == null) return null;
		try {
			Connection con = dataSource.getConnection();
			// delete user
			deleteUser(con, userName);
			
			// delete his friends
			deleteUsersAsFriend(con, userName);
			
			con.close();
		} catch (SQLException e) {	e.printStackTrace();}
		return oldUser;
	}

	// deletes users friends and deletes him from other users friendlists
	private void deleteUsersAsFriend(Connection con, String userName) 
			throws SQLException {
		PreparedStatement preparedStatement = con.prepareStatement(
				"DELETE FROM friends "
				+ "WHERE first_user_id = "
				+ "(SELECT id FROM users WHERE username LIKE ?)"
				+ "OR second_user_id = "
				+ "(SELECT id FROM users WHERE username LIKE ?)"
				+ ";");
		preparedStatement.setString(1, userName);
		preparedStatement.setString(2, userName);
		preparedStatement.executeUpdate();	
		preparedStatement.close();
	}

	private void deleteUser(Connection con, String userName) 
			throws SQLException {
		PreparedStatement preparedStatement = 
				con.prepareStatement("DELETE FROM users WHERE username LIKE ?;");
		preparedStatement.setString(1, userName);
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	@Override
	public User updateUser(User user) {
		User oldUser = getUser(user.getUserName());
		// return null if there is no such user
		if(oldUser == null) return null;	
		
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"UPDATE users "
							+ "SET "
							+ "hash_password = ?, "
							+ "salt = ?,"
							+ "description = ?,"
							+ "image = ?"
							+ "WHERE username LIKE ?;"
							);
			preparedStatement.setString(1, user.getHashedPassword());
			preparedStatement.setString(2, user.getSalt());
			preparedStatement.setString(3, user.getDescription());
			preparedStatement.setString(4, user.getImage());
			preparedStatement.setString(5, user.getUserName());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {	e.printStackTrace();}
		return oldUser;
	}

	@Override
	public User addUser(String userName, String hexPassword, String salt) {
		User newUser = null;
		newUser = getUser(userName);
		if(newUser != null) return null;	// if there is one already - failed
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(addingCommand());
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, hexPassword);
			preparedStatement.setString(3, salt);
			preparedStatement.executeUpdate();
			con.close();
		} catch (SQLException e) {	e.printStackTrace();}
		return getUser(userName);
	}

	private String addingCommand() {
		return "INSERT INTO users (username, hash_password, salt) VALUES(?, ?, ?);";
	}

	@Override
	public boolean isAdmin(String userName) {
		boolean isAdmin = false;
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"SELECT * "
							+ "FROM admins "
							+ "WHERE user_id = (SELECT id FROM users WHERE username LIKE ?)"
							+ ";"
							);
			preparedStatement.setString(1, userName);
			ResultSet rs = preparedStatement.executeQuery();
			// if result set contains something => user is admin
			isAdmin = rs.next();
			
			preparedStatement.close();
			rs.close();
			con.close();
		} catch (SQLException e) {	e.printStackTrace();}
		
		return isAdmin;
	}

	@Override
	public void addAdmin(String userName) {
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement = 
					con.prepareStatement(
							"INSERT INTO admins "
							+ "VALUES(SELECT id FROM users WHERE username = ?)"
							+ ";"
							);
			preparedStatement.setString(1, userName);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {	e.printStackTrace();}
	}

	@Override
	public void removeAdmin(String userName) {
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement = 
					con.prepareStatement(
							"DELETE FROM adminds "
							+ "WHERE user_id = (SELECT id FROM users WHERE username = ?)"
							+ ";"
							);
			preparedStatement.setString(1, userName);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {	e.printStackTrace();}
	}

	@Override
	public void addFriend(String userName1, String userName2) {
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"INSERT INTO friends "
							+ "(first_user_id, second_user_id) "
							+ "VALUES("
							+ "SELECT id FROM user WHERE username LIKE ?"
							+ ", "
							+ "SELECT id FROM user WHERE username LIKE ?"
							+ "), ("
							+ "SELECT id FROM user WHERE username LIKE ?"
							+ ", "
							+ "SELECT id FROM user WHERE username LIKE ?);"
							);
			preparedStatement.setString(1, userName1);
			preparedStatement.setString(2, userName2);
			preparedStatement.setString(3, userName2);
			preparedStatement.setString(4, userName1);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Set<String> getFriends(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
}
