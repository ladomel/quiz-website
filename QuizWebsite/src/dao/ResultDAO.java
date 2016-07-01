package dao;

import java.util.List;
import java.util.Set;

import classes.Result;

/**
 * Interface which handles result/answer storing/retrieving.
 * 
 * @author sergi
 *
 */
public interface ResultDAO {
	
	/**
	 * Stores result.
	 * 
	 * @param result
	 */
	public void insertResult(Result result);
	
	/**
	 * Ordered by grade>time.
	 * 
	 * @param userName - quiz taker
	 * @param quizId - quiz taken
	 * @return list of quiz results taken by given user
	 */
	public List<Result> getResult(String userName, int quizId);
	
	/**
	 * @param userName - String user name
	 * @param n - int limit number
	 * @return list of recent results by given user
	 */
	public List<Result> getRecentResults(String userName, int n);
	
	public List<Result> getRecentResults(Set<String> userName, int n);
	
	/**
	 * @param quizId - id of quiz
	 * @param n - int limit number
	 * @return list of recent results by given quiz
	 */
	public List<Result> getRecentResults(int quizId, int n);
	
	/**
	 * @param userName - String user name
	 * @param n - int limit number
	 * @return best of recent results by given user
	 */
	public List<Result> getBestResults(String userName, int n, long interval);
	
	/**
	 * @param quizId - id of quiz
	 * @param n - int limit number
	 * @return list of best results by given quiz
	 */
	public List<Result> getBestResults(int quizId, int n, long interval);
	
	/**
	 * @param n - limit number
	 * @param interval - time interval in ms
	 * @return list of most popular quiz ids
	 */
	public List<Integer> getPopularQuizzes(int n, long interval);
	
}
