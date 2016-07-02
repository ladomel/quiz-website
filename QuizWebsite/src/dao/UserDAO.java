package dao;

import java.util.Set;

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
	
	/**
	 * Returns true iff specified user is administrator.
	 * 
	 * @param username - user name of user we want to check
	 * @return true iff specified user is administrator
	 */
	public boolean isAdmin(String userName);
	
	/**
	 * Take a userName and makes him administrator.
	 * 
	 * @param userName - user name of user we want to make administrator
	 */
	public void addAdmin(String userName);
	
	/**
	 * Take a userName and removes him from administration.
	 * 
	 * @param userName - user name of user we want to remove him from administration
	 */
	public void removeAdmin(String userName);
	
	/**
	 * Adds first user as a friend of second user and vice-versa.
	 * 
	 * @param userName1 - first user name
	 * @param userName2 - second user name
	 */
	public void addFriend(String userName1, String userName2);
	
	/**
	 * Returns set of friends for given user name.
	 * 
	 * @param userName - String user name
	 * @return set of given users friends
	 */
	public Set<String> getFriends(String userName);
	
	/**
	 * Removes friendship between given two users.
	 * 
	 * @param user name 1
	 * @param user name 2
	 */
	public void removeFriendship(String userName1, String userName2);
	
}
