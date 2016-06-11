package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import classes.Quiz;
import classes.User;

public class QuizWebsiteModel implements QuizWebsiteModelInterface {

	private static QuizWebsiteModel singletonInstance;
	
	private BasicDataSource dataSource;
	
	private DBConfig dbConfig;
	

	private static final String USER_USER_NAME = "user_name";

	private static final String USER_HEX_PASSWORD = "hex_password";

	private static final String USER_TABLE = "user";

	private static final String USER_USER_ID = "id";
	
	/*
	 * Because fuck public. 
	 */
	private QuizWebsiteModel() {
		init();
	}
	
	private void init() {
		dbConfig = DBConfig.getInstance();
		setUpDataSource();
		setUpDB();
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

	private void setUpDB() {
		try {
			Connection con = dataSource.getConnection();
			Statement stmt = con.createStatement();
			createDB(stmt);
			useDB(stmt);
			createUserTable(stmt);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createUserTable(Statement stmt) throws SQLException {
		// to simplify creation we drop everything created prior to this
		stmt.executeUpdate("DROP TABLE IF EXISTS " + USER_TABLE + ";" );
		stmt.executeUpdate("CREATE TABLE " + USER_TABLE + 
				" ( " + USER_USER_ID +
				" INT NOT NULL AUTO_INCREMENT UNIQUE KEY);" );
		stmt.executeUpdate("ALTER TABLE " + USER_TABLE + " ADD " +
				USER_USER_NAME + " VARCHAR(20) NOT NULL PRIMARY KEY;");
		stmt.executeUpdate("ALTER TABLE "  + USER_TABLE + " ADD " +
				USER_HEX_PASSWORD + " VARCHAR(255) NOT NULL;");
	}

	private void useDB(Statement stmt) throws SQLException {
		stmt.executeUpdate("USE " +
				dbConfig.DB_DATABASE_NAME +
				";");
	}

	private void createDB(Statement stmt) throws SQLException {
			stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " +
					dbConfig.DB_DATABASE_NAME +
					";");
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
	
	private ResultSet connectAndExecuteQuery(String command) {
		ResultSet rs = null;
		try {
			Connection con = dataSource.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + dbConfig.DB_DATABASE_NAME + ";");
			rs = stmt.executeQuery(command);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	private void connectAndExecuteDM(String command) {
		try {
			Connection con = dataSource.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + dbConfig.DB_DATABASE_NAME + ";");
			stmt.executeUpdate(command);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(String userName) {
		User user = null;
		ResultSet rs = connectAndExecuteQuery(
				"SELECT * FROM " + USER_TABLE + " WHERE " + 
						USER_USER_NAME + " LIKE '" +
				userName +
				"';");
		try {
			// assuming user name is unique, therefore result set has
			// at most 1 entry
			if(!rs.next())
				user = null;
			else {
				user = new User(rs.getString(USER_USER_NAME),
						rs.getString(USER_HEX_PASSWORD));    // TODO change salt to good value
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void deleteUser(String userName) {
		connectAndExecuteDM("DELETE FROM " + USER_TABLE + 
				" WHERE " + USER_USER_NAME + " LIKE '" +
				userName + "';");
	}

	@Override
	public void addUser(User user) {
		connectAndExecuteDM("INSERT INTO " + USER_TABLE +
				" (" + USER_USER_NAME + ", " + USER_HEX_PASSWORD +
				") VALUES('" +
				user.getUserName() + "','" +
				user.getHashedPassword() + "');");
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

}