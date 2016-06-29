package classes.Message;

public final class FriendRequest extends Message{
	
	private String status = "Pending";

	public FriendRequest(String senderUserName, long dateSent, String receiverUserName) {
		super(senderUserName, dateSent,receiverUserName);
	}
	
	public String generateMessage(String senderUserName){
		return "User " + senderUserName + " wants to be your friend!";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
