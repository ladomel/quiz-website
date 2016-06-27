package dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import classes.User;

public class UserDAOImplTest{

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
		userDAO.deleteUser("a"); // Clear for test purpose.
		// make sure adding returns correct new object
		assertTrue(	userDAO.addUser("a", "a123", "a12").getSalt().equals("a12")	);// make sure updating returns correct old object
		assertTrue(	userDAO.addUser("a", "a123", "a12") == null); // Second add, returns null.
		
		assertTrue(	userDAO.updateUser(
						new User("a", "a1234", "a123")
						).getSalt().equals("a12")	);  
		
		assertTrue(	userDAO.updateUser(
				new User("a", "a12345", "a123")
				).getHashedPassword().equals("a1234")	);  
		
		// check updated user
		assertTrue(	userDAO.getUser("a").getHashedPassword().equals("a12345") );
		assertTrue(	userDAO.getUser("a").getSalt().equals("a123"));
		
		// check deletion
		assertTrue(	userDAO.deleteUser("a").getHashedPassword().equals("a12345")	);
		assertTrue(userDAO.deleteUser("a") == null);
	}

	@Test
	public void basicTest2() {
		userDAO.deleteUser("a"); // Clear for test purpose.
		userDAO.deleteUser("b"); // Clear for test purpose.
		
		assertTrue(	userDAO.updateUser(	
				new User("a", "a1234", "a123")
						) == null );  // Updating non existent user returns null.
		
		// make sure adding returns correct new object
		assertTrue(	userDAO.addUser("a", "a123", "a12").getUserName().equals("a")	);// make sure updating returns correct old object
		assertTrue(	userDAO.addUser("b", "b123", "b12").getHashedPassword().equals("b123")); // make sure updating returns correct old object
		
		assertTrue(	userDAO.updateUser(
						new User("a", "a1234", "a123")
						).getUserName().equals("a")	);  
		
		assertTrue(	userDAO.updateUser(
				new User("b", "b1234", "b123")
				).getSalt().equals("b12"));  
		
		// check updated user
		assertTrue(	userDAO.getUser("a").getUserName().equals("a") );
		assertTrue(	userDAO.getUser("b").getSalt().equals("b123"));
		
		// check deletion
		assertTrue(userDAO.deleteUser("a").getSalt().equals("a123"));
		assertTrue(userDAO.deleteUser("b").getUserName().equals("b"));
		assertTrue(userDAO.deleteUser("a") == null);
	}
}
