package classes;

import java.util.HashSet;
import java.util.Set;

public class User {

	public enum Status{REGULAR, ADMINISTRATION} 
	
	private Status status;
	private String userName;
	private String hashedPassword;
	private HashSet<Integer> friends; // Set of user's friends.
	private HashSet<Integer> createdQuizes;	
	private HashSet<Integer> takenQuizes;
	private HashSet<Integer> achievements;
	private HashSet<Integer> messages; // Messages stored here. might change, depends on implementation	
	
	public User(String userName, String hashedPassword)
	{
		setUserName(userName);
		setHashedPassword(hashedPassword); 
		setStatus(Status.REGULAR);  // By default, user is regular.
		setFriends(new HashSet<Integer>());
		createdQuizes = new HashSet<Integer>();
		takenQuizes = new HashSet<Integer>();
		achievements = new HashSet<Integer>();
		messages = new HashSet<Integer>();
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public HashSet<Integer> getFriends() {
		return friends;
	}

	public void addFriend(Integer friendId) {
		this.getFriends().add(friendId);
	}
	
	public void removeFriend(Integer friendId) {
		this.getFriends().remove(friendId);
	}

	private void setFriends(HashSet<Integer> friends) {
		this.friends = friends;
	}
}
