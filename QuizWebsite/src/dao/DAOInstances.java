package dao;

import java.util.Timer;
import java.util.TimerTask;

import database.DBInfo;


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
		
		// set up user dao to use for pooling
		userDAO = new UserDAOImpl(dataSource);

		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// set connection pool size here:
				dataSource.setMinIdle( 
						(int) userDAO.getNumberOfLoggedInUsers() / 10
						);
				// TODO: make finer adjustments
			}
		}, 0, POOL_UPDATE_INTERVAL);

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
