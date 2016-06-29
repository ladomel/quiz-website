package classes.Message;

public final class Note extends Message {

	private final String note;
	
	public Note(String senderUserName, long dateSent, String note, String receiverUserName) {
		super(senderUserName, dateSent, receiverUserName);
		this.note = note;
	}

	public String getNote() {
		return note;
	}

}
