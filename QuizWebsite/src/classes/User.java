package classes;

import java.util.HashSet;
import java.util.Set;

public class User {
	
	private String userName;
	private String hashedPassword;
	private String salt;
	private Set<String> friends;

	private String description;
	
	/*
	 * these overrides are not necessary but they might come in handy
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User))
			return false;
		if (obj == this)
			return true;

		User otherUser = (User) obj;
		
		return this.getUserName().equals(otherUser.getUserName()) &&
				this.getHashedPassword().equals(otherUser.getHashedPassword()) && 
				this.getSalt().equals(otherUser.getSalt()) &&
				this.getFriends().equals(otherUser.getFriends());
	}
	
	@Override
    public int hashCode() {
        return this.getUserName().hashCode();
    }
	
	@Override
	public String toString() {
		return "u: " + userName + ", p: " + hashedPassword + ", salt: " + salt + " , friends: " + friends + ".";	
	}
	
	public User(String userName, String hashedPassword, String salt)
	{
		setUserName(userName);
		setHashedPassword(hashedPassword); 
		setSalt(salt);
		setFriends(new HashSet<String> ());
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
	
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the friends
	 */
	public Set<String> getFriends() {
		return friends;
	}

	/**
	 * @param friends the friends to set
	 */
	public void setFriends(Set<String> friends) {
		this.friends = friends;
	}
}
