package classes.Message;

public final class FriendRequest extends Message{
	
	private String status = "Pending";

	public FriendRequest(String senderUserName, long dateSent, String receiverUserName, boolean seen) {
		super(senderUserName, dateSent,receiverUserName, seen);
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

	@Override
	public String toString() {
		return super.toString() + " | " +
				"status: " + this.getStatus();
	}
}
