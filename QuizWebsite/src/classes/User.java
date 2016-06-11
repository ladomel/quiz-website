package classes;

import java.util.HashSet;

public class User {

	public enum Status{REGULAR, ADMINISTRATION} 
	
	private String userName;
	private String hashedPassword;
	private String salt;

	private Status status;
	private String description;
	private HashSet<Integer> friends; // Set of user's friends.
	private HashSet<Integer> createdQuizes;	
	private HashSet<Integer> takenQuizes;
	private HashSet<Integer> achievements;
	private HashSet<Integer> messages; // Messages stored here. might change, depends on implementation	
	
	public User(String userName, String hashedPassword)
	{
		setUserName(userName);
		setHashedPassword(hashedPassword); 
		setSalt(salt);
		
		setStatus(Status.REGULAR);  // By default, user is regular.
		setFriends(new HashSet<Integer>());
		setCreatedQuizes(new HashSet<Integer>());
		setTakenQuizes(new HashSet<Integer>());
		setAchievements(new HashSet<Integer>());
		setMessages(new HashSet<Integer>());
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

	public HashSet<Integer> getCreatedQuizes() {
		return createdQuizes;
	}

	public void setCreatedQuizes(HashSet<Integer> createdQuizes) {
		this.createdQuizes = createdQuizes;
	}

	public HashSet<Integer> getTakenQuizes() {
		return takenQuizes;
	}

	public void setTakenQuizes(HashSet<Integer> takenQuizes) {
		this.takenQuizes = takenQuizes;
	}

	public HashSet<Integer> getAchievements() {
		return achievements;
	}

	public void setAchievements(HashSet<Integer> achievements) {
		this.achievements = achievements;
	}

	public HashSet<Integer> getMessages() {
		return messages;
	}

	public void setMessages(HashSet<Integer> messages) {
		this.messages = messages;
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
}
