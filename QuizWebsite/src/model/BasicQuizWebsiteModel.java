package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import classes.Quiz;
import classes.User;

public class BasicQuizWebsiteModel implements QuizWebsiteModelInterface {

	private static BasicQuizWebsiteModel singletonInstance;
	
	private Map<String, User> users;
	private List<Quiz> quizes;
	
	/*
	 * Because fuck public. 
	 */
	private BasicQuizWebsiteModel() {
		users = new HashMap<String, User> ();
		quizes = new ArrayList<Quiz> ();
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
		return users.get(userName);
	}

	@Override
	public void deleteUser(String userName) {
		users.remove(userName);
	}

	@Override
	public void addUser(User user) {
		users.put(user.getUserName(), user);
	}

	@Override
	public Quiz getQuiz(int quizId) {
		return quizes.get(quizId);
	}

	@Override
	public void replaceQuiz(Quiz quiz, int quizId) {
		quizes.remove(quizId);
		quizes.add(quizId, quiz);
	}

	@Override
	public int addQuiz(Quiz quiz) {
		quizes.add(quiz);
		return quizes.size() - 1;
	}

	@Override
	public void deleteQuiz(int quizId) {
		replaceQuiz(null, quizId);
	}

	@Override
	public User getUser(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
