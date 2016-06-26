package classes.Message;

public class FriendRequest extends Message{

	private String receiverUserName;
	
	public FriendRequest(String senderUserName, long dateSent, String message, String receiverUserName) {
		super(senderUserName, dateSent, senderUserName);
		super.setMessage(generateMessage(senderUserName));
		setReceiverUserName(receiverUserName);
	}
	
	private String generateMessage(String senderUserName)
	{
		return "User " + senderUserName + " wants to be your friend ";
	}

	public String getReceiverUserName() {
		return receiverUserName;
	}

	public void setReceiverUserName(String receiverUserName) {
		this.receiverUserName = receiverUserName;
	}
}
