package dao;

import javax.sql.DataSource;

/**
 * This class is probably not quite factory, hence the name.
 * 
 * @author sergi
 *
 */
public class DAOInstances {

	private DataSource dataSource;
	
	public DAOInstances() {
		dataSource = null;
	}
	
	/**
	 * Call this before using any of the methods.
	 */
	public void init() {
		// TODO: set up data source
	}
	
	public UserDAO getUserDAO() {
		return new UserDAOImpl(dataSource);
	}
	
	public QuizDAO getQuizDAO() {
		return new QuizDAOImpl(dataSource);
	}
	
}
