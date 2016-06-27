package classes;

import java.util.Set;

public class Quiz {
	private String creatorUserName; // Creator
	private long dateCreated;  // in milliseconds
	private int id;	// Unique id for every quiz
	private String description; 
	private String quizName;
	private int quizTime; // Time to take this quiz in minutes.
	private int maxScore; // Max Score User can get.
	private boolean isRandom;
	private boolean isOnePage;
	private boolean isImmediatelyCorrected;
	private boolean hasPracticeMode;
	
	
	// Not necessary, might delete.
	
	private double averageRating; // Average rating made by users.
	private double averageScore; // Users' average score in the quiz.
	private long averageTimeMillis; // Users' average time in milliseconds.
	private int numTries; // Number of times users started this quiz.
	
	// extension
	private String category; // TODO enum
	private Set<String> tags;
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Quiz))
			return false;
		if (obj == this)
			return true;

		Quiz other = (Quiz) obj;
		return this.getUserName().equals(other.getUserName())  &&
				this.getDateCreated() == other.getDateCreated() &&
				this.getId() == other.getId() &&
				this.getDescription().equals(other.getDescription())  &&
				this.getQuizName().equals(other.getQuizName()) &&
				this.getMaxScore() == other.getMaxScore() &&
				this.getQuizTime() == other.getQuizTime() &&
				this.isRandom() == other.isRandom() &&
				this.isOnePage() == other.isOnePage() &&
				this.isImmediatelyCorrected() == other.isImmediatelyCorrected() &&
				this.isHasPracticeMode() == other.isHasPracticeMode();
	}
	
	@Override
	public String toString() {
		return this.getId() + " | " + this.getUserName() + " | " +
				this.getDateCreated() + " | " + this.getDescription()
				+ " | " + this.getQuizName() + " | "
				+ this.getMaxScore() + " | " + this.getQuizTime() + " | "
				+ this.isRandom() + " | " + this.isOnePage() + " | "
				+ this.isImmediatelyCorrected() + " | "
				+ this.isHasPracticeMode();
	}
	
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

	public int getQuizTime() {
		return quizTime;
	}

	public void setQuizTime(int quizTime) {
		this.quizTime = quizTime;
	}

	public long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public int getNumTries() {
		return numTries;
	}

	public void setNumTries(int numTries) {
		this.numTries = numTries;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}

	public long getAverageTimeMillis() {
		return averageTimeMillis;
	}

	public void setAverageTimeMillis(long averageTimeMillis) {
		this.averageTimeMillis = averageTimeMillis;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
}
