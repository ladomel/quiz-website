package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
  move to tomcat libraries when running on server
 */
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

//import org.apache.commons.dbcp2.BasicDataSource;

import classes.Quiz;
import classes.User;

public class QuizWebsiteModel implements QuizWebsiteModelInterface {

	private static QuizWebsiteModel singletonInstance;
	
	private BasicDataSource dataSource;
	
	private DBConfig dbConfig;
	

	private static final String USER_USER_NAME = "user_name";

	private static final String USER_HEX_PASSWORD = "hex_password";

	private static final String USER_TABLE = "user";

//	private static final String USER_USER_ID = "id";

	private static final String USER_SALT = "salt";
	
	/*
	 * Because fuck public. 
	 */
	private QuizWebsiteModel() {
		init();
	}
	
	private void init() {
		dbConfig = DBConfig.getInstance();
		setUpDataSource();
	}
	
	private void setUpDataSource() {
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(dbConfig.DB_DRIVER);
		dataSource.setUsername(dbConfig.DB_USER_NAME);
		dataSource.setPassword(dbConfig.DB_PASSWORD);
		dataSource.setUrl(dbConfig.DB_URL);
		// we can control how connection pool behaves
		dataSource.setMaxIdle(dbConfig.DB_MAX_CONNECTIONS);
	}

	/**
	 * Global point of access for singleton object.
	 * 
	 * @return
	 */
	public static QuizWebsiteModel getInstance() {
		if (null == singletonInstance) {
            singletonInstance = new QuizWebsiteModel();
        }
        return singletonInstance;
	}
	
	@Override
	public User getUser(String userName) {
		User user = null;
		String command = "SELECT * FROM " + 
				USER_TABLE + " WHERE " + 
				USER_USER_NAME + " LIKE ?;";
		try {
			Connection con = dataSource.getConnection();
			java.sql.PreparedStatement preparedStatement = 
					con.prepareStatement(command);
			preparedStatement.setString(1, userName);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next())
				user = new User(rs.getString(USER_USER_NAME),
						rs.getString(USER_HEX_PASSWORD),
						rs.getString(USER_SALT));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void deleteUser(String userName) {
		String command = "DELETE FROM " + 
				USER_TABLE + " WHERE " + 
				USER_USER_NAME + " LIKE ?;";
		try {
			Connection con = dataSource.getConnection();
			java.sql.PreparedStatement preparedStatement = 
					con.prepareStatement(command);
			preparedStatement.setString(1, userName);
			preparedStatement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addUser(User user) {
		String command = "INSERT INTO " + 
				USER_TABLE + " (" + 
				USER_USER_NAME + ", " + USER_HEX_PASSWORD + ", " + USER_SALT +
				") VALUES (?, ?, ?);";
		try {
			Connection con = dataSource.getConnection();
			java.sql.PreparedStatement preparedStatement = 
					con.prepareStatement(command);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getHashedPassword());
			preparedStatement.setString(3, user.getSalt());
			preparedStatement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Quiz getQuiz(int quizId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void replaceQuiz(Quiz quiz, int quizId) {
		// TODO Auto-generated method stub

	}

	@Override
	public int addQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteQuiz(int quizId) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUser(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}