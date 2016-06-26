package dao;

import java.util.List;

import javax.sql.DataSource;

import classes.Quiz;
import classes.Result;

public class QuizDAOImpl implements QuizDAO {

	public QuizDAOImpl(DataSource dataSource) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Quiz getQuiz(int quizId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Quiz deleteQuiz(int quizId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Quiz> getPopularQuizzes(int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Quiz> getRecentQuizzes(int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Result> getRecentResults(String userName, int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getCreatedQuizzes(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Result> getResult(String userName, Integer quizId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Result> getHighestPerformance(Integer quizId, int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Result> getRecentResults(Integer quizId, int n) {
		// TODO Auto-generated method stub
		return null;
	}

}
