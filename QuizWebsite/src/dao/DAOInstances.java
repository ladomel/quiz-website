package dao;

import java.util.Timer;

import database.DBInfo;
import database.PoolHandler;

//import org.apache.commons.dbcp2.BasicDataSource;  // Usual.
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource; // Online.

/**
 * This class is probably not quite factory, hence the name.
 * 
 * @author sergi
 *
 */
public class DAOInstances {

	private static final long POOL_UPDATE_INTERVAL = 10000;
	private BasicDataSource dataSource;
	private Timer timer;
	private UserDAO userDAO;
	
	public DAOInstances() {
		dataSource = null;
		timer = null;
		userDAO = null;
	}
	
	/**
	 * Call this before using any of the methods.
	 */
	public void init() {
		
		dataSource = new BasicDataSource();
		
		dataSource.setDriverClassName(DBInfo.DRIVER_CLASS_NAME);
		dataSource.setUsername(DBInfo.USER_NAME);
		dataSource.setPassword(DBInfo.PASSWORD);
		dataSource.setUrl(DBInfo.DB_URL);
		dataSource.setInitialSize(10);

		// set up user dao to use for pooling
		userDAO = new UserDAOImpl(dataSource);

		timer = new Timer();
		timer.scheduleAtFixedRate(
				new PoolHandler(dataSource, userDAO.getNumberOfLoggedInUsers()), 
				0, 
				POOL_UPDATE_INTERVAL);
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
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
