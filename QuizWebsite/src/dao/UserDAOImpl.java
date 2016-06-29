package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
					con.prepareStatement(selectCommand());
			preparedStatement.setString(1, userName);
			ResultSet rs = preparedStatement.executeQuery();
			// we need only one row (there should not be more)
			if(rs.next()) user = loadIntoUser(rs);
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	
	private User loadIntoUser(ResultSet rs) throws SQLException {
		User user = classFactory.getUser(rs.getString("username"),
				rs.getString("hash_password"),
				rs.getString("salt"));
		user.setDescription(rs.getString("description"));
				return user;
	}

	private String selectCommand() {
		return "SELECT * FROM users WHERE username LIKE ?;";
	}

	@Override
	public User deleteUser(String userName) {
		User oldUser = getUser(userName);
		// return null if there is no such user
		if(oldUser == null) return null;
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement = 
					con.prepareStatement(deleteCommand());
			preparedStatement.setString(1, userName);
			preparedStatement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oldUser;
	}

	private String deleteCommand() {
		return "DELETE FROM users WHERE username LIKE ?;";
	}

	@Override
	public User updateUser(User user) {
		User oldUser = getUser(user.getUserName());
		// return null if there is no such user
		if(oldUser == null) return null;	
		// delete old, put in new and add additional data
		deleteUser(user.getUserName());
		addUser(user.getUserName(),
				user.getHashedPassword(),
				user.getSalt());
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement = 
					con.prepareStatement(updateCommand());
			preparedStatement.setString(4, user.getUserName());
			preparedStatement.setString(1, user.getHashedPassword());
			preparedStatement.setString(2, user.getSalt());
			preparedStatement.setString(3, user.getDescription());
			preparedStatement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oldUser;
	}

	private String updateCommand() {
		return "UPDATE users SET hash_password = ?, salt = ?, description = ? "
				+ "WHERE username LIKE ?;";
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return getUser(userName);
	}

	private String addingCommand() {
		return "INSERT INTO users (username, hash_password, salt, description) VALUES(?, ?, ?);";
	}

}
