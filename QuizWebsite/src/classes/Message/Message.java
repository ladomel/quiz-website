package classes.Message;

public abstract class Message {
	private String senderUserName;
	private long dateSent;
	private String message;
	
	public Message(String senderUserName, long dateSent, String message)
	{
		setSenderUserName(senderUserName);
		setDateSent(dateSent);
		setMessage(message);
	}

	public String getSenderUserName() {
		return senderUserName;
	}

	public void setSenderUserName(String senderUserName) {
		this.senderUserName = senderUserName;
	}

	public long getDateSent() {
		return dateSent;
	}

	public void setDateSent(long dateSent) {
		this.dateSent = dateSent;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
