package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import classes.User;

public class UserDAOImpl implements UserDAO {

	DataSource dataSource;
	
	private static final String USER_NAME = "username";
	private static final String HEX_PASSWORD = "hash_password";
	private static final String USER_TABLE = "users";
	private static final String SALT = "salt";
	private static final String DESCRIPTION = "description";
	private static final String IMAGE = "image";
	
	public UserDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;	// TODO: needs singleton data source
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
			if(rs.next()) loadIntoUser(rs, user);
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	private void loadIntoUser(ResultSet rs, User user) throws SQLException {
		user = new User(rs.getString(USER_NAME),
				rs.getString(HEX_PASSWORD),
				rs.getString(SALT));
		// TODO: additional info goes here (user class does not support image)
	}

	private String selectCommand() {
		return "SELECT * FROM " + 
				USER_TABLE + " WHERE " + 
				USER_NAME + " LIKE ?;";
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
		return "DELETE FROM " + 
				USER_TABLE + " WHERE " + 
				USER_NAME + " LIKE ?;";
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
			preparedStatement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oldUser;
	}

	private String updateCommand() {
		return "UPDATE " + USER_TABLE + 
				"SET " + IMAGE + " = ? WHERE " + 
				USER_NAME + " LIKE ?;";
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
		return "INSERT INTO " + 
				USER_TABLE + " (" + 
				USER_NAME + ", " + 
				HEX_PASSWORD + ", " + 
				SALT + ") VALUES(?, ?, ?);";
	}

}
