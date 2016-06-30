package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import classes.Quiz; 
import factory.ClassFactory;

public class QuizDAOImpl implements QuizDAO {

	/*
	 * NOTE: mysql commands do not use column name or table name constants.
	 * And that is ok.
	 */
	
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
			
			if(rs.next()) quiz = loadIntoQuiz(rs);	// we need only one row (there should not be more)
			
			rs.close();
			con.close();
		} catch (SQLException e) {	e.printStackTrace();}
		return quiz;
	}

	private Quiz loadIntoQuiz(ResultSet rs) throws SQLException {
		Quiz quiz = modelFactory.getQuiz(rs.getString("username"),
				rs.getString("name"),
				rs.getString("description"));
		quiz.setRandom(rs.getBoolean("is_random"));
		quiz.setQuizTime(rs.getInt("time"));
		quiz.setOnePage(rs.getBoolean("is_one_page"));
		quiz.setImmediatelyCorrected(rs.getBoolean("immediate_correction"));
		quiz.setId(rs.getInt("id"));
		quiz.setHasPracticeMode(rs.getBoolean("practice_mode"));
		quiz.setDateCreated(rs.getLong("creation_time"));
		quiz.setMaxScore(rs.getInt("max_score"));
		quiz.setAverageRating(rs.getDouble("avg_rating"));
		quiz.setAverageScore(rs.getDouble("avg_score"));
		quiz.setAverageTimeMillis(rs.getLong("avg_time"));
		quiz.setNumTries(rs.getInt("tries"));
		quiz.setCategory(rs.getString("category"));	// should have separate table but fuck it
		
		return quiz;
	}

	private String getterCommand() {
		return "SELECT " + 
				"quizzes.id, quizzes.description, username, name, " + 
				"is_random, is_one_page, immediate_correction, " + 
				"practice_mode, creation_time, time, max_score, "
				+ "avg_rating, avg_score, avg_time, tries, category " + 
				"FROM quizzes " + 
				"INNER JOIN users " + 
				"ON quizzes.creator_id = users.id WHERE quizzes.id = ?;";
		}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		Quiz oldQuiz = null;
		oldQuiz = getQuiz(quiz.getId());
		if(oldQuiz == null) return null;
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(updatingCommand());
			preparedStatement.setString(1, quiz.getUserName());
			preparedStatement.setString(2, quiz.getQuizName());
			preparedStatement.setString(3, quiz.getDescription());
			preparedStatement.setBoolean(4, quiz.isRandom());
			preparedStatement.setBoolean(5, quiz.isOnePage());
			preparedStatement.setBoolean(6, quiz.isImmediatelyCorrected());
			preparedStatement.setBoolean(7, quiz.hasPracticeMode());
			preparedStatement.setLong(8, quiz.getDateCreated());
			preparedStatement.setInt(9, quiz.getQuizTime());
			preparedStatement.setInt(10, quiz.getMaxScore());
			preparedStatement.setDouble(11, quiz.getAverageRating());
			preparedStatement.setDouble(12, quiz.getAverageScore());
			preparedStatement.setLong(13, quiz.getAverageTimeMillis());
			preparedStatement.setInt(14, quiz.getNumTries());
			preparedStatement.setString(15, quiz.getCategory());
			preparedStatement.setInt(16, quiz.getId());
			preparedStatement.executeUpdate();
			con.close();
		} catch (SQLException e) {e.printStackTrace();	}
		return oldQuiz;
	}

	private String updatingCommand() {
		return "UPDATE quizzes SET " + 
				"creator_id = (SELECT id FROM users WHERE username LIKE ?), " + 
				"name = ?, " + 
				"description = ?, " + 
				"is_random = ?, " + 
				"is_one_page = ?, " + 
				"immediate_correction = ?, " + 
				"practice_mode = ?, " + 
				"creation_time = ?, " + 
				"time = ?, " + 
				"max_score = ?, avg_rating = ?, avg_score = ?, "
				+ "avg_time = ?, tries = ?, category = ? WHERE id = ?";
	}

	@Override
	public int addQuiz(Quiz quiz) {
		int id = 0;
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(addingCommand());
			preparedStatement.setString(1, quiz.getUserName());
			preparedStatement.setString(2, quiz.getQuizName());
			preparedStatement.setString(3, quiz.getDescription());
			preparedStatement.setBoolean(4, quiz.isRandom());
			preparedStatement.setBoolean(5, quiz.isOnePage());
			preparedStatement.setBoolean(6, quiz.isImmediatelyCorrected());
			preparedStatement.setBoolean(7, quiz.hasPracticeMode());
			preparedStatement.setLong(8, quiz.getDateCreated());
			preparedStatement.setInt(9, quiz.getQuizTime());
			preparedStatement.setInt(10, quiz.getMaxScore());
			preparedStatement.setDouble(11, quiz.getAverageRating());
			preparedStatement.setDouble(12, quiz.getAverageScore());
			preparedStatement.setLong(13, quiz.getAverageTimeMillis());
			preparedStatement.setInt(14, quiz.getNumTries());
			preparedStatement.setString(15, quiz.getCategory());
			preparedStatement.executeUpdate();
			id = MySQLUtil.getLastInsertId(con);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	private String addingCommand() {
		return "INSERT INTO quizzes " + 
				"(creator_id, name, description, is_random, is_one_page, " + 
				"immediate_correction, practice_mode, creation_time, " + 
				"time, max_score, avg_rating, avg_score, avg_time, tries, category) " + 
				"VALUES(" + 
				"(SELECT id FROM users WHERE username like ?), " + 
				" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	}

	@Override
	public Quiz deleteQuiz(int quizId) {
		Quiz oldQuiz = null;
		oldQuiz = getQuiz(quizId);
		if(oldQuiz == null) return null;
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(deletingCommand());
			preparedStatement.setInt(1, quizId);
			preparedStatement.executeUpdate();
			con.close();
		} catch (SQLException e) {e.printStackTrace();}
		return oldQuiz;
	}

	private String deletingCommand() {
		return "DELETE FROM quizzes WHERE id = ?;";
	}

	@Override
	public List<Quiz> getPopularQuizzes(int n) {
		List<Quiz> popularQuizzes = new ArrayList<Quiz> (); 
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(popularCommand());
			preparedStatement.setInt(1, n);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Quiz quiz = loadIntoQuiz(rs);
				popularQuizzes.add(quiz);
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return popularQuizzes;
	}

	private String popularCommand() {
		return "SELECT *, username " + 
				"FROM quizzes INNER " + 
				"JOIN users ON users.id = quizzes.creator_id " + 
				"ORDER BY tries DESC LIMIT ?;";
	}
	
	@Override
	public List<Quiz> getRecentQuizzes(int n) {
		List<Quiz> recentQuizzes = new ArrayList<Quiz>();
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(recentCommand());
			preparedStatement.setInt(1, n);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Quiz quiz = loadIntoQuiz(rs);
				recentQuizzes.add(quiz);
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recentQuizzes;
	}

	private String recentCommand() {
		return "SELECT *, username " + 
				"FROM quizzes INNER " + 
				"JOIN users ON users.id = quizzes.creator_id " + 
				"ORDER BY creation_time DESC LIMIT ?;";
	}

	@Override
	public List<Quiz> getCreatedQuizzes(String userName) {
		List<Quiz> userQuizes = new ArrayList<Quiz>();
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(userCreatedCommand());
			preparedStatement.setString(1, userName);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Quiz quiz = loadIntoQuiz(rs);
				userQuizes.add(quiz);
			}
			rs.close();
			con.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return userQuizes;
	}

	private String userCreatedCommand() {
		return "SELECT " + 
				"quizzes.id, avg_rating, avg_score, avg_time, tries, quizzes.description, username, name, " + 
				"is_random, is_one_page, immediate_correction, " + 
				"practice_mode, creation_time, category, time, max_score " + 
				"FROM quizzes " + 
				"INNER JOIN users " + 
				"ON quizzes.creator_id = users.id WHERE username LIKE ? ORDER BY creation_time ";
	}
}
