package classes.Message;

public class Challenge extends Message {
	
	private final Integer quizId;
	private String status = "Pending";
	
	public Challenge(String senderUserName, long dateSent, String receiverUserName, int quizId, boolean seen) {
		super(senderUserName, dateSent, receiverUserName, seen);
		this.quizId = quizId;
	}

	public int getQuizId() {
		return quizId;
	}
	
	public String generateMessage(String senderUserName){
		return "User " + senderUserName + " wants to challenge you on quiz " + quizId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
