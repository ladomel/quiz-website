package model;

/**
 * This class encapsulates user data.
 * 
 * @author sergi
 *
 */
public class User {

	private String userName;
	private String hexPassword;
	
	/**
	 * User ctor.
	 * 
	 * @param userName - String user name
	 */
	public User(String userName) {
		this.userName = userName;
	}
	
	/**
	 * @return the hexPassword
	 */
	public String getHexPassword() {
		return hexPassword;
	}
	
	/**
	 * @param hexPassword the hexPassword to set
	 */
	public void setHexPassword(String hexPassword) {
		this.hexPassword = hexPassword;
	}
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	
	
}
