package classes.Message;

public class Note extends Message {

	private String receiverUserName;
	
	public Note(String senderUserName, long dateSent, String message, String receiverUserName) {
		super(senderUserName, dateSent, message);
		setReceiverUserName(receiverUserName);
	}

	public String getReceiverUserName() {
		return receiverUserName;
	}

	public void setReceiverUserName(String receiverUserName) {
		this.receiverUserName = receiverUserName;
	}
}
