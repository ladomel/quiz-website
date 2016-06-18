package model;

import classes.Quiz;
import classes.User;

public class BasicQuizWebsiteModel implements QuizWebsiteModelInterface {

	private static BasicQuizWebsiteModel singletonInstance;
	
	/*
	 * Because fuck public. 
	 */
	private BasicQuizWebsiteModel() {
	}
	
	/**
	 * Global point of access for singleton object.
	 * 
	 * @return
	 */
	public static BasicQuizWebsiteModel getInstance() {
		if (null == singletonInstance) {
            singletonInstance = new BasicQuizWebsiteModel();
        }
        return singletonInstance;
	}
	
	@Override
	public User getUser(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(String userName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public Quiz getQuiz(int quizId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void replaceQuiz(Quiz quiz, int quizId) {
		// TODO Auto-generated method stub

	}

	@Override
	public int addQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteQuiz(int quizId) {
		// TODO Auto-generated method stub

	}

}
