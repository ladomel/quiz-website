package model;

import java.util.List;

import classes.Quiz;
import classes.Result;
import classes.User;

/**
 * This interface provides model functionality for quiz website controller.
 * 
 * @author sergi
 *
 */
public interface QuizWebsiteModelInterface {

	public boolean userExists(String username);
	
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
	 * @return user - if exists, else null
	 */
	public User updateUser(String user);
	
	/**
	 *  Adds user to database if user name is vacant and returns User object,
	 *  else returns null.
	 * 
	 * @param userName - user name
	 * @param hashedPassword - hashed Password
	 * @param salt - password's salt
	 * @return user - user object if vacant, else null
	 */
	public void addUser(User user);
	
	/**
	 * Returns Quiz object for given id.
	 * TODO: other means to get quiz to be provided later.
	 * 
	 * @param quizId - int quiz id
	 * @return quiz - Quiz object
	 */
	public Quiz getQuiz(int quizId);
	
	/**
	 * Update quiz of quiz id - quizID with Quiz object - quiz.
	 * 
	 * @param quiz - Quiz object
	 * @param quizId - int quiz id to be replaced
	 */
	public void updateQuiz(Quiz quiz, int quizId);
	
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
	
	// AQ daviwyeT
	public List<Quiz> getPopularQuizzes(int num);
	
	public List<Quiz> getRecentQuizzes(int num);
	
	public List<Result> getRecentResults(String username, int num);
	
	public List<Quiz> getCreatedQuizzes(String username, int num);	
	
	public List<Result> getResults(String username, Integer quizId);
	
	public List<Result> getHighestPerformance(Integer quizId, int num);
	
	public List<Result> getHighestPerformanceLastDay(Integer quizId, int num, long startTime);
	
	public List<Result> getRecentResults(Integer quizId, int num);
	
}

