package dao;

import java.util.List;

import classes.Quiz;
import classes.Result;

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


	////////////////////////////////////////
	// new functions
	
	public List<Quiz> getPopularQuizzes(int n);
	
	public List<Quiz> getRecentQuizzes(int n);
	
	/**
	 * Quizes ordered by date (new to old).
	 */
	public List<Quiz> getCreatedQuizzes(String userName);	
	
}
