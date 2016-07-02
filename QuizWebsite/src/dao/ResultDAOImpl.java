package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import classes.Answer;
import classes.Result;
import factory.ClassFactory;

public class ResultDAOImpl implements ResultDAO {

	private DataSource dataSource;
	private ClassFactory classFactory;
	
	public ResultDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
		classFactory = new ClassFactory();	// TODO: get this as an argument
	}

	@Override
	public void insertResult(Result result) {
		try {
			Connection con = dataSource.getConnection();
			insertResultWithoutAnswers(result, con);
//			int resultId = MySQLUtil.getLastInsertId(con);
//			insertAnswers(resultId, result.getAnswers(), con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/*
	 * NOTE: obsolete. answer saving no longer supported.
	 * Here are used many inserts instead of single insert with several values.
	 * Performance difference should be negligible because of low
	 * numbers involved with quizzes in general.
	 */
	private void insertAnswers(int resultId, List<Answer> answers,
			Connection con) throws SQLException {
		// for each question
		for(int questionId = 0; questionId < answers.size(); questionId++) {
			int grade = answers.get(questionId).getGrade();
			insertQuestionGrade(con, resultId, questionId, grade);
			List<String> answerTexts = answers.get(questionId).getAnswer();
			insertQuestionFieldTexts(con, questionId, answerTexts);
		}
		
	}
	
	private void insertQuestionFieldTexts(Connection con, int questionId, 
			List<String> answerTexts) throws SQLException {
		// for each field
		for(int fieldId = 0; fieldId < answerTexts.size(); fieldId++) {
			PreparedStatement preparedStatement =
					con.prepareStatement("INSERT INTO user_answers "
							+ "(question_id, field_id, user_answer) "
							+ "VALUES(?, ?, ?);");
			preparedStatement.setInt(1, questionId);
			preparedStatement.setInt(2, fieldId);
			String text = answerTexts.get(fieldId);
			preparedStatement.setString(3, text );
			preparedStatement.executeUpdate();
		}
	}

	private void insertQuestionGrade(Connection con, int resultId, int i,
			Integer grade) throws SQLException {
		PreparedStatement preparedStatement = 
				con.prepareStatement("INSERT INTO answers_of_question " + 
						"(result_id, question_id, grade) " + 
						"VALUES(?, ?, ?);");
		preparedStatement.setInt(1, resultId);
		preparedStatement.setInt(2, i);
		preparedStatement.setInt(3, grade);
		preparedStatement.executeUpdate();
	}

	private void insertResultWithoutAnswers(Result result, Connection con)
			throws SQLException {
		PreparedStatement preparedStatement = 
				con.prepareStatement(
						"INSERT INTO results " + 
						"(user_id, quiz_id, final_grade, start_time, time_taken) " + 
						"VALUES" + 
						"((SELECT id FROM users WHERE username LIKE ?), ?, ?, ?, ?);");
		preparedStatement.setString(1, result.getUserName());
		preparedStatement.setInt(2, result.getQuizId());
		preparedStatement.setInt(3, result.getFinalGrade());
		preparedStatement.setLong(4, result.getTimeStarted());
		preparedStatement.setLong(5, result.getTimeTaken());
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	////////////////////// getters from here
	
	@Override
	public List<Result> getResult(String userName, int quizId) {
		List<Result> results = new ArrayList<Result> ();
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"SELECT username, quiz_id, start_time, time_taken, final_grade "
							+ "FROM results "
							+ "JOIN users "
							+ "ON users.id = results.user_id "
							+ "WHERE user_id = (SELECT id FROM users WHERE username LIKE ?) "
							+ "AND quiz_id = ? "
							+ "ORDER BY start_time DESC, final_grade DESC"
							+ " ;"
							);
			preparedStatement.setString(1, userName);
			preparedStatement.setInt(2, quizId);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Result result = buildResultFromResultSet(rs);
				results.add(result);
			}
			preparedStatement.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	
	@Override
	public List<Result> getRecentResults(String userName, int n) {
		List<Result> results = new ArrayList<Result> ();
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"SELECT username, quiz_id, start_time, time_taken, final_grade "
							+ "FROM results "
							+ "JOIN users "
							+ "ON users.id = results.user_id "
							+ "WHERE user_id = (SELECT id FROM users WHERE username LIKE ?) "
							+ "ORDER BY start_time DESC, final_grade DESC "
							+ "LIMIT ?;"
							);
			preparedStatement.setString(1, userName);
			preparedStatement.setInt(2, n);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Result result = buildResultFromResultSet(rs);
				results.add(result);
			}
			preparedStatement.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	// basically copy of above ^^^^^^
	@Override
	public List<Result> getRecentResults(int quizId, int n) {
		List<Result> results = new ArrayList<Result> ();
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"SELECT username, quiz_id, start_time, time_taken, final_grade "
							+ "FROM results "
							+ "JOIN users "
							+ "ON users.id = results.user_id "
							+ "WHERE quiz_id = ? "
							+ "ORDER BY start_time DESC, final_grade DESC "
							+ "LIMIT ?;"
							);
			preparedStatement.setInt(1, quizId);
			preparedStatement.setInt(2, n);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Result result = buildResultFromResultSet(rs);
				results.add(result);
			}
			preparedStatement.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public List<Result> getRecentResults(Set<String> userName, int n) {
		// TODO: too hard?
		return null;
	}

	// copy of above again ^^^^^^^
	@Override
	public List<Result> getBestResults(String userName, int n, long fromTimeInMs) {
		List<Result> results = new ArrayList<Result> ();
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"SELECT username, quiz_id, start_time, time_taken, final_grade "
							+ "FROM results "
							+ "JOIN users "
							+ "ON users.id = results.user_id "
							+ "WHERE user_id = (SELECT id FROM users WHERE username LIKE ?) "
							+ " AND "
							+ "start_time > ? "
							+ "ORDER BY final_grade DESC, start_time DESC "
							+ "LIMIT ?;"
							);
			preparedStatement.setString(1, userName);
			preparedStatement.setLong(2, fromTimeInMs);
			preparedStatement.setInt(3, n);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Result result = buildResultFromResultSet(rs);
				results.add(result);
			}
			preparedStatement.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	// make sureresult set has correct collumn names
	private Result buildResultFromResultSet(ResultSet rs) 
			throws SQLException {
		
		Result result = 
				classFactory.getResult(rs.getString("username"), 
						rs.getInt("quiz_id"));
		result.setTimeStarted(rs.getLong("start_time"));
		result.setTimeTaken(rs.getLong("time_taken"));
		result.setFinalGrade(rs.getInt("final_grade"));
		return result;
	}

	@Override
	public List<Result> getBestResults(int quizId, int n, long fromTimeInMs) {
		List<Result> results = new ArrayList<Result> ();
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"SELECT username, quiz_id, start_time, time_taken, final_grade "
							+ "FROM results "
							+ "JOIN users "
							+ "ON users.id = results.user_id "
							+ "WHERE quiz_id = ? "
							+ " AND "
							+ "start_time > ? "
							+ "ORDER BY final_grade DESC, start_time DESC "
							+ "LIMIT ?;"
							);
			preparedStatement.setInt(1, quizId);
			preparedStatement.setLong(2, fromTimeInMs);
			preparedStatement.setInt(3, n);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Result result = buildResultFromResultSet(rs);
				results.add(result);
			}
			preparedStatement.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public List<Integer> getPopularQuizzes(int n, long fromTimeInMs) {
		List<Integer> popularQuizIds = new ArrayList<Integer> ();
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"SELECT quiz_id, COUNT(1) AS num_tries "
							+ "FROM results "
							+ "GROUP BY quiz_id "
							+ "ORDER BY num_tries DESC"
							+ ";"
							);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) popularQuizIds.add(rs.getInt("quiz_id"));
			preparedStatement.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return popularQuizIds;
	}

}
