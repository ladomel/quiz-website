package classes;

public class Announcement {
	private String userName; // Who does this announcement
	private long creationDate;	// When
	private String message; // Announcement itself
	
	public Announcement(String userName, long creationDate, String message)
	{
		setUserName(userName);
		setCreationDate(creationDate);
		setMessage(message);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
