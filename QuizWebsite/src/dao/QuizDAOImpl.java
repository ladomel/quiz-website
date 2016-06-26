package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import classes.Quiz;
import classes.Result;
import factory.ClassFactory;

public class QuizDAOImpl implements QuizDAO {

	private DataSource dataSource;
	private ClassFactory modelFactory;
	
	public QuizDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
		modelFactory = new ClassFactory();	// TODO: should get this via ctor
	}

	@Override
	public Quiz getQuiz(int quizId) {
		Quiz quiz = null;
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(getterCommand());
			preparedStatement.setInt(1, quizId);
			ResultSet rs = preparedStatement.executeQuery();
			// we need only one row (there should not be more)
			if(rs.next()) quiz = loadIntoQuiz(rs);
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return quiz;
	}

	private Quiz loadIntoQuiz(ResultSet rs) throws SQLException {
		Quiz quiz = modelFactory.getQuiz(rs.getString("username"),
				rs.getString("name"),
				null);
		quiz.setDateCreated(rs.getLong("creation_time"));
		return quiz;
	}

	private String getterCommand() {
		return "SELECT quizzes.id, username, name, is_random, is_one_page, immediate_correction, practice_mode, creation_time, time, max_score FROM quizzes INNER JOIN users ON quizzes.creator_id = users.id WHERE quizzes.id = ?;";
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
