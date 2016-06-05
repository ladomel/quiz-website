package classes;

import java.util.Set;

public class User {
	public User(String userName, String hashedPassword)
	{
		setUserName(userName);
		setHashedPassword(hashedPassword); // Set registration date.
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}


	private String status; // TODO enum; status of the user. "Ordinary", "Administration" etc.
	private String userName;
	private String hashedPassword;
	private Set<Integer> friends; // Set of user's friends.
	private Set<Integer> createdQuizes;	
	private Set<Integer> takenQuizes;
	private Set<Integer> achievements;
	private Set<Integer> messages; // Messages stored here. might change, depends on implementation	
}
