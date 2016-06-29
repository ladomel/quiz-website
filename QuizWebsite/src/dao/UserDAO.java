package dao;

import classes.User;

/**
 * User data access object.
 * Is approximately map implementation.
 * 
 * @author sergi
 *
 */
public interface UserDAO {

	/**
	 * User getter.
	 * 
	 * @param userName - String user name
	 * @return user - User object
	 */
	public User getUser(String userName);
	
	/**
	 * Deletes and returns what was deleted.
	 * 
	 * @param userName - String user name
	 * @return user - deleted User object, null if nothing was deleted
	 */
	public User deleteUser(String userName);
	
	/**
	 * Update user with user name user.getUserName().
	 * Returns old User object.
	 * 
	 * @param user - User object to overwrite over older one
	 * @return userOld - overwritten User object
	 */
	public User updateUser(User user);
	
	/**
	 * Adds and returns new user.
	 * 
	 * @param userName - new user name String
	 * @param hexPassword - hexed password String
	 * @param salt - password salt String
	 * @return user - newly created User object, null if could not create
	 */
	public User addUser(String userName, String hexPassword, String salt);	
}
