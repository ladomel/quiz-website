package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
	
	/*
	 * Because fuck public. 
	 */
	private QuizWebsiteModel() {
		init();
	}
	
	private void init() {
		dbConfig = DBConfig.getInstance();
		setUpDataSource();
		runInitialSQLSource();
	}

	private void runInitialSQLSource() {
		String initCommands = getSourceAsString();
		try {
			Connection con = dataSource.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " +
					dbConfig.DB_DATABASE_NAME +
					";");
			stmt.executeUpdate("USE " +
					dbConfig.DB_DATABASE_NAME +
					";");
			stmt.executeUpdate(initCommands);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getSourceAsString() {
		String sqlSource = null;
		try(BufferedReader br = new BufferedReader(
				new FileReader(dbConfig.DB_INIT_SOURCE)) ) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    sqlSource = sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlSource;
	}

	private void setUpDataSource() {
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(dbConfig.DB_DRIVER);
		dataSource.setUsername(dbConfig.DB_USER_NAME);
		dataSource.setPassword(dbConfig.DB_PASSWORD);
		dataSource.setUrl(dbConfig.DB_URL);
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
	
	private ResultSet executeQuery(String command) {
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
	
	private void executeDM(String command) {
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
		ResultSet rs = executeQuery(
				"SELECT * FROM user WHERE user_name LIKE '" +
				userName +
				"';");
		try {
			// assuming user name is unique, therefore result set has
			// at most 1 entry
			if(!rs.next())
				user = null;
			else {
				user = new User(rs.getString("user_name"),
						rs.getString("hex_password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void deleteUser(String userName) {
		executeDM("DELETE FROM user WHERE user_name LIKE '" +
				userName + "';");
	}

	@Override
	public void putUser(User user) {
		executeDM("INSERT INTO user (user_name, hex_password) VALUES('" +
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
	
	/**
	 * rough check
	 * 
	 * 
	 * @param arg0
	 */
	public static void main(String [] arg0) {
		User levana = new User("levana", "yle");
		QuizWebsiteModel qwm = QuizWebsiteModel.getInstance();
		System.out.println(qwm.getUser("levana").getUserName());
		qwm.deleteUser("levana");
		System.out.println(qwm.getUser("levana"));
		qwm.putUser(levana);
	}

}