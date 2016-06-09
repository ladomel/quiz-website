package model;

import classes.Quiz;
import classes.User;

/**
 * This interface provides model functionality for quiz website controller.
 * 
 * @author sergi
 *
 */
public interface QuizWebsiteModelInterface {

	/**
	 * User getter.
	 * 
	 * @param userName - String user name
	 * @return user - User object
	 */
	public User getUser(String userName);
	
	/**
	 * Self-explanatory.
	 * 
	 * @param userName - String user name
	 */
	public void deleteUser(String userName);
	
	/**
	 * Overwrites given user object over existing one.
	 * User name which is overwritten is included in user object
	 * and is assumed that it is kept constant (no user name change).
	 * 
	 * @param user - new User object
	 */
	public void putUser(User user);
	
	/**
	 * Returns Quiz object for given id.
	 * TODO: other means to get quiz to be provided later.
	 * 
	 * @param quizId - int quiz id
	 * @return quiz - Quiz object
	 */
	public Quiz getQuiz(int quizId);
	
	/**
	 * Replace quiz of quiz id - quizID with Quiz object - quiz.
	 * 
	 * @param quiz - Quiz object
	 * @param quizId - int quiz id to be replaced
	 */
	public void replaceQuiz(Quiz quiz, int quizId);
	
	/**
	 * Adds given quiz into quizzes and returns id of newly added quiz.
	 * 
	 * @param quiz - Quiz object
	 * @return id - int id of newly added quiz
	 */
	public int addQuiz(Quiz quiz);
	
	/**
	 * Deletes quiz with given quiz id.
	 * 
	 * @param quizId - int quiz id to delete
	 */
	public void deleteQuiz(int quizId);
	
}
