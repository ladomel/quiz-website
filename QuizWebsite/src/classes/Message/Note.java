package classes.Message;

public final class Note extends Message {

	private final String note;
	
	public Note(String senderUserName, long dateSent, String note, String receiverUserName, boolean seen) {
		super(senderUserName, dateSent, receiverUserName, seen);
		this.note = note;
	}

	public String getNote() {
		return note;
	}
	
	@Override
	public String toString() {
		return super.toString() + " | " +
				"note: " + this.getNote();
	}
	
}
