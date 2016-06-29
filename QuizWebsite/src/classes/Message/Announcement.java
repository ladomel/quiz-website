package classes.Message;

public final class Announcement {

	private final String announcer,announcement;
	private final long date;
	private int id;
	
	public Announcement(String announcer, long date, String announcement) {
		this.announcement = announcement;
		this.announcer = announcer;
		this.date = date;
	}

	public String getAnnouncer() {
		return announcer;
	}

	public String getAnnouncement() {
		return announcement;
	}

	public long getDate() {
		return date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
