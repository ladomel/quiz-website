package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import classes.Answer;
import classes.Result;

public class ResultDAOImpl implements ResultDAO {

	private DataSource dataSource;
	
	public ResultDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insertResult(Result result) {
		try {
			Connection con = dataSource.getConnection();
			insertResultWithoutAnswers(result, con);
			int resultId = MySQLUtil.getLastInsertId(con);
			insertAnswers(resultId, result.getAnswers(), con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/*
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
				con.prepareStatement(inserResultCommand());
		preparedStatement.setString(1, result.getUserName());
		preparedStatement.setInt(2, result.getQuizId());
		preparedStatement.setInt(3, result.getFinalGrade());
		preparedStatement.setLong(4, result.getTimeStarted());
		preparedStatement.setLong(5, result.getTimeTaken());
		preparedStatement.executeUpdate();
	}

	private String inserResultCommand() {
		return "INSERT INTO results " + 
				"(user_id, quiz_id, final_grade, start_time, time_taken) " + 
				"VALUES" + 
				"((SELECT id FROM users WHERE username LIKE ?), ?, ?, ?, ?);";
	}

	@Override
	public List<Result> getResult(String userName, int quizId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Result> getRecentResults(String userName, int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Result> getRecentResults(int quizId, int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Result> getBestResults(String userName, int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Result> getBestResults(int quizId, int n) {
		// TODO Auto-generated method stub
		return null;
	}

}
