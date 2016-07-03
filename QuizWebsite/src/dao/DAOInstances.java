package dao;

//import org.apache.commons.dbcp2.BasicDataSource;  // Usual.

import database.DBInfo;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource; // Online.

/**
 * This class is probably not quite factory, hence the name.
 * 
 * @author sergi
 *
 */
public class DAOInstances {

	private BasicDataSource dataSource;
	
	public DAOInstances() {
		dataSource = null;
	}
	
	/**
	 * Call this before using any of the methods.
	 */
	public void init() {
		// TODO: move all these strings as constants
		dataSource = new BasicDataSource();
		
		dataSource.setDriverClassName(DBInfo.DRIVER_CLASS_NAME);
		dataSource.setUsername(DBInfo.USER_NAME);
		dataSource.setPassword(DBInfo.PASSWORD);
		dataSource.setUrl(DBInfo.DB_URL);
		// we can control how connection pool behaves
		dataSource.setMaxIdle(20);
	}
	
	public UserDAO getUserDAO() {
		return new UserDAOImpl(dataSource);
	}
	
	public AchievementDAO getAchievementDAO() {
		return new AchievementDAOImpl(dataSource);
	}
	
	public MessageDAO getMessageDAO() {
		return new MessageDAOImpl(dataSource);
	}
	
	public QuizDAO getQuizDAO() {
		return new QuizDAOImpl(dataSource);
	}
	
	public QuestionDAO getQuestionDAO() {
		return new QuestionDAOImpl(dataSource);
	}
	
	public ResultDAO getResultDAO() {
		return new ResultDAOImpl(dataSource);
	}
	
}
