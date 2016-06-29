package dao;

import java.util.List;

import classes.Quiz; 

public interface QuizDAO {

	/**
	 * Returns Quiz object for given id.
	 * 
	 * @param quizId - int quiz id
	 * @return quiz - Quiz object, null if not found
	 */
	public Quiz getQuiz(int quizId);
	
	/**
	 * Updates quiz and returns overwritten one.
	 * 
	 * @param quiz - new Quiz object (includes own id)
	 * @return oldQuiz - overwritten Quiz object
	 */
	public Quiz updateQuiz(Quiz quiz);
	
	/**
	 * Adds given quiz into quizzes.
	 * 
	 * @param quiz - Quiz object
	 * @return id - id of added quiz
	 */
	public int addQuiz(Quiz quiz);
	
	/**
	 * Deletes quiz with given quiz id.
	 * 
	 * @param quizId - int quiz id to delete
	 * @return oldQuiz - deleted Quiz object, null if does not exist
	 */
	public Quiz deleteQuiz(int quizId);
	
	/**
	 * Returns a list with Quizzes in it sorted by popularity.
	 * 
	 * @param n - number of quizzes
	 * @return list of Quizzes sorted by popularity
	 */
	public List<Quiz> getPopularQuizzes(int n);
	
	/**
	 * Returns a list with Quizzes in it sorted by creation date.
	 * 
	 * @param n - number of quizzes
	 * @return list with Quizzes sorted by creation date
	 */
	public List<Quiz> getRecentQuizzes(int n);
	
	/**
	 * Returns a list with recent Quizzes created by user.
	 * 
	 * @return list with recent Quizzes created by user
	 */
	public List<Quiz> getCreatedQuizzes(String userName);	
}
