package dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
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
		InitialContext ic;
		try {
			ic = new InitialContext();
			// TODO: take this out as a constant
			dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/oop");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public UserDAO getUserDAO() {
		return new UserDAOImpl(dataSource);
	}
	
	public QuizDAO getQuizDAO() {
		return new QuizDAOImpl(dataSource);
	}
	
}
