package classes.Message;

public abstract class Message{
	
	private final String senderUserName;
	private final String getterUserName;
	private final long dateSent;
	private boolean seen;
	
	public Message(String senderUserName, long dateSent, String getterUserName, boolean seen)
	{
		this.senderUserName = senderUserName;
		this.dateSent = dateSent;
		this.getterUserName = getterUserName;
		this.setSeen(seen);
	}

	public String getSenderUserName() {
		return senderUserName;
	}

	public long getDateSent() {
		return dateSent;
	}

	public String getGetterUserName() {
		return getterUserName;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

}
