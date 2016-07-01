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

	@Override
	public List<Result> getResult(String userName, int quizId) {
		List<Result> results = new ArrayList<Result> ();
		try {
			Connection con = dataSource.getConnection();
			ResultSet rs = getResultSortGradeFirst(con, userName, quizId);
			while(rs.next()) {
				Result result = classFactory.getResult(userName, quizId);
				// set 1-1 values in result:
				result.setFinalGrade(rs.getInt("final_grade"));
				result.setQuizId(rs.getInt("quiz_id"));
				result.setTimeStarted(rs.getLong("start_time"));
				result.setTimeTaken(rs.getLong("time_taken"));
				result.setUserName(rs.getString("username"));
				results.add(result);
//				int resultId = rs.getInt("id");
//				rs.previous();
//				loadAnswersIntoResult(result, rs, resultId);
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	
	// obsolete since we no longer hold answers
	private void loadAnswersIntoResult(Result result, ResultSet rs, 
			int resultId) throws SQLException {
		List<Answer> answers = new ArrayList<Answer> ();
		while(rs.next() && rs.getInt("id") == resultId) {
			int questionId = rs.getInt("question_id");
			rs.previous();
			answers.add(getAnswer(rs, questionId));
		}
		rs.previous();
		result.setAnswers(answers);
	}

	private Answer getAnswer(ResultSet rs, int questionId) 
			throws SQLException {
		List<String> userAnswers = new ArrayList<String> ();
		while(rs.next() && rs.getInt("question_id") == questionId) {
			String userAnswer = rs.getString("user_answer");
			userAnswers.add(userAnswer);
		}
		// TODO: it gets funky if no empty user answers are allowed,
		// but this is more than enough soice it is not.
		rs.previous();
		Answer answer = classFactory.getAnswer(userAnswers);
		answer.setGrade(rs.getInt("grade"));
		return answer ;
	}

	private ResultSet getResultSortGradeFirst(Connection con, String userName, 
			int quizId)	throws SQLException {
		PreparedStatement preparedStatement =
				con.prepareStatement(
						"SELECT * "
						+ "FROM results "
						+ "WHERE results.quiz_id = ?"
						+ " AND "
						+ "results.user_id = (SELECT id FROM users WHERE username LIKE ?)" 
						);
		preparedStatement.setString(2, userName);
		preparedStatement.setInt(1, quizId);
		ResultSet rs = preparedStatement.executeQuery();
		preparedStatement.close();
		return rs;
	}

	@Override
	public List<Result> getRecentResults(String userName, int n) {
		List<Result> results = new ArrayList<Result> ();
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"SELECT *"
							+ "FROM results "
							+ "WHERE user_id = (SELECT id FROM users WHERE username LIKE ?) "
							+ "ORDER BY start_time DESC "
							+ "LIMIT ?;"
							);
			preparedStatement.setString(1, userName);
			preparedStatement.setInt(2, n);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Result result = 
						classFactory.getResult(userName, rs.getInt("quiz_id"));
				result.setTimeStarted(rs.getLong("start_time"));
				result.setTimeTaken(rs.getLong("time_taken"));
				result.setFinalGrade(rs.getInt("final_grade"));
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
	public List<Result> getRecentResults(int quizId, int n) {
		List<Result> results = new ArrayList<Result> ();
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"SELECT username, start_time, time_taken, final_grade "
							+ "FROM results "
							+ "JOIN users "
							+ "ON users.id = results.user_id "
							+ "WHERE quiz_id = ? "
							+ "ORDER BY start_time DESC "
							+ "LIMIT ?;"
							);
			preparedStatement.setInt(1, quizId);
			preparedStatement.setInt(2, n);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Result result = 
						classFactory.getResult(rs.getString("username"), quizId);
				result.setTimeStarted(rs.getLong("start_time"));
				result.setTimeTaken(rs.getLong("time_taken"));
				result.setFinalGrade(rs.getInt("final_grade"));
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Result> getBestResults(String userName, int n, long interval) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Result> getBestResults(int quizId, int n, long interval) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getPopularQuizzes(int n, long interval) {
		// TODO Auto-generated method stub
		return null;
	}

}
