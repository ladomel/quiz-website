package classes.Message;

public abstract class Message{
	
	private final String senderUserName;
	private final String getterUserName;
	private final long dateSent;
	
	public Message(String senderUserName, long dateSent, String getterUserName)
	{
		this.senderUserName = senderUserName;
		this.dateSent = dateSent;
		this.getterUserName = getterUserName;
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

}
