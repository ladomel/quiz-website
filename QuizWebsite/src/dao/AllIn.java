package dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class AllIn {

	private static DAOInstances factory;
	private static UserDAO userDAO;
	
	
	@BeforeClass
	public static void setUp() {
		factory = new DAOInstances();
		factory.init();
		userDAO = factory.getUserDAO();
	}
	
	@Test
	public void test() {
		System.out.println(userDAO.addUser("a", "a1234", "a123").getUserName());
		assertTrue(userDAO.getUser("b") != null);
		assertTrue(userDAO.getUser("a").getUserName() == "a");
	}

}
