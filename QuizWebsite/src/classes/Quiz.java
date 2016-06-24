package classes;

public class Quiz {
	private String creatorUserName; // Creator
	private long dateCreated; 
	private String description; 
	private String quizName;
	
	// Not necessary, might delete.
	private int maxScore; // Max Score User can get.
	private long quizTime; // Time to take this quiz.
	
	private boolean isRandom;
	private boolean isOnePage;
	private boolean isImmediatelyCorrected;
	private boolean hasPracticeMode;
	
	public Quiz(String userName, String quizName, String description)
	{
		setUserName(userName);
		setDescription(description);
		setQuizName(quizName);
	}
	
	public String getUserName() {
		return creatorUserName;
	}
	public void setUserName(String userName) {
		this.creatorUserName = userName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public boolean isRandom() {
		return isRandom;
	}

	public void setRandom(boolean isRandom) {
		this.isRandom = isRandom;
	}

	public boolean isOnePage() {
		return isOnePage;
	}

	public void setOnePage(boolean isOnePage) {
		this.isOnePage = isOnePage;
	}

	public boolean isImmediatelyCorrected() {
		return isImmediatelyCorrected;
	}

	public void setImmediatelyCorrected(boolean isImmediatelyCorrected) {
		this.isImmediatelyCorrected = isImmediatelyCorrected;
	}

	public boolean isHasPracticeMode() {
		return hasPracticeMode;
	}

	public void setHasPracticeMode(boolean hasPracticeMode) {
		this.hasPracticeMode = hasPracticeMode;
	}

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	public long getQuizTime() {
		return quizTime;
	}

	public void setQuizTime(long quizTime) {
		this.quizTime = quizTime;
	}

	public long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}
}
