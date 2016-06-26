package dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import classes.User;

public class UserDAOTest {

	private static DAOInstances factory;
	private static UserDAO userDAO;
	
	
	@BeforeClass
	public static void setUp() {
		factory = new DAOInstances();
		factory.init();
		userDAO = factory.getUserDAO();
	}
	
	@Test
	public void basicTest1() {
		userDAO.deleteUser("a");	// so that we do not get duplicate entry
		// make sure adding returns correct new object
		assertTrue(
				userDAO.addUser("a", "a123", "a12").getSalt().equals("a12")
				);
		// make sure updating returns correct old object
		assertTrue(
				userDAO.updateUser(
						new User("a", "a1234", "a123")
						).getSalt().equals("a12")	
				);
		// check updated user
		assertTrue(
				userDAO.getUser("a").getHashedPassword().equals("a1234")
				);
		assertTrue(
				userDAO.getUser("a").getSalt().equals("a123")
				);
		// check deletion
		assertTrue(
				userDAO.deleteUser("a").getHashedPassword().equals("a1234")
				);
		assertTrue(userDAO.deleteUser("a") == null);
	}

}
