package classes.Message;

public class Challenge extends Message {
	
	private String receiverUserName;
	private int quizId;
	
	public Challenge(String senderUserName, long dateSent, String message, String receiverUserName, int quizId) {
		super(senderUserName, dateSent, message);
		setReceiverUserName(receiverUserName);
		setQuizId(quizId);
	}

	public String getReceiverUserName() {
		return receiverUserName;
	}

	public void setReceiverUserName(String receiverUserName) {
		this.receiverUserName = receiverUserName;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

}
