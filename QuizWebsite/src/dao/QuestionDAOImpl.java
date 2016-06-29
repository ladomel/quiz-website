package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import classes.question.QuestionFB;
import classes.question.QuestionMA;
import classes.question.QuestionMC;
import classes.question.QuestionMCMA;
import classes.question.QuestionPR;
import classes.question.QuestionQR;
import classes.question.Abstract.Question;
import factory.ClassFactory;

public class QuestionDAOImpl implements QuestionDAO {

	DataSource dataSource;
	ClassFactory classFactory;
	
	public QuestionDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
		classFactory = new ClassFactory();	// TODO: should receive as parameter
	}

	
	
	
	
	
	
	
	
	///////////////// done
	@Override
	public void addQR(int quizId, QuestionQR qr) {
		try {
			Connection con = dataSource.getConnection();
			loadCommonFields(con, qr, quizId);
			int questionId = MySQLUtil.getLastInsertId(con);
			loadAnswersOfField(con, qr.getAnswers(), questionId, 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//////////////// done
	@Override
	public void addPR(int quizId, QuestionPR pr) {
		try {
			Connection con = dataSource.getConnection();
			loadCommonFields(con, pr, quizId);
			int questionId = MySQLUtil.getLastInsertId(con);
			loadAnswersOfField(con, pr.getAnswers(), questionId, 0);
			loadPictureUrl(con, questionId, pr.getPictureURL());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addMC(int quizId, QuestionMC mc) {
		try {
			Connection con = dataSource.getConnection();
			loadCommonFields(con, mc, quizId);
			int questionId = MySQLUtil.getLastInsertId(con);
			Set<String> answers = new HashSet<String>  ();
			answers.add(mc.getCorrectAnswer());
			loadAnswersOfField(con, answers, questionId, 0);
			loadWrongAnswersOfField(con, mc.getWrongAnswers(), questionId, 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	////////////// done
	@Override
	public void addFB(int quizId, QuestionFB fb) {
		try {
			Connection con = dataSource.getConnection();
			loadCommonFields(con, fb, quizId);
			int questionId = MySQLUtil.getLastInsertId(con);
			loadFieldAnswers(con, questionId, fb.getAnswers());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//////////////// done
	@Override
	public void addMA(int quizId, QuestionMA ma) {
		try {
			Connection con = dataSource.getConnection();
			loadCommonFields(con, ma, quizId);
			int questionId = MySQLUtil.getLastInsertId(con);
			loadFieldAnswers(con, questionId, ma.getAnswers());
			loadMAMetadata(con, questionId, ma.getNumAnswers(), ma.isOrdered());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//////////////// done
	@Override
	public void addMCMA(int quizId, QuestionMCMA mcma) {
		try {
			Connection con = dataSource.getConnection();
			loadCommonFields(con, mcma, quizId);
			int questionId = MySQLUtil.getLastInsertId(con);
			Set<String> answers = new HashSet<String> (mcma.getCorrectAnswers());
			Set<String> answersWrong = new HashSet<String> (mcma.getIncorrectAnswers());
			loadAnswersOfField(con, answers, questionId, 0);
			loadWrongAnswersOfField(con, answersWrong, questionId, 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
































	@Override
	public List<Question> getQuestions(int quizId) {
		List<Question> questions = null;
		try {
			Connection con = dataSource.getConnection();
			// list with correct size:
			questions = new ArrayList<Question> ();
			for(int i = 0; i < numberOfQuestions(con, quizId); i++)  questions.add(null);
			
			// TODO: hard
			retrieveQR(con, quizId, questions);
			
			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questions;
	}

	
	
	
	
	
	
	/*
	 * this method finds and inserts all QRs into list of questions
	 */
	private void retrieveQR(Connection con, int quizId, List<Question> questions) 
			throws SQLException {
		
		PreparedStatement preparedStatement=
				con.prepareStatement(	// QR only needs sort by q_id
						"SELECT * FROM questions "
						+ "LEFT JOIN answers ON answers.question_id = questions.id "
						+ "WHERE quiz_id = ? AND type LIKE 'QR';"
						);
		preparedStatement.setInt(1, quizId);
		ResultSet rs = preparedStatement.executeQuery();
		
		retrieveQRFromRS(rs, questions);
		
		rs.close();
	}


	private void retrieveQRFromRS(ResultSet rs, List<Question> questions) 
			throws SQLException {
		
		while(rs.next()) {
			String problem = rs.getString("problem");
			int grade = rs.getInt("grade");
			int currentQuestionId = rs.getInt("id");
			rs.previous();
			Set<String> answers = 
					collectAnswersUntilQuestionIsSame(rs, currentQuestionId);
			QuestionQR qr = classFactory.getQuestionQR(problem, grade, answers);
			// SQL runs +1 idx:
			questions.set(currentQuestionId - 1, qr);
		}
	}


	private Set<String> collectAnswersUntilQuestionIsSame(ResultSet rs, int currentQuestionId) 
			throws SQLException {
		
		Set<String> answers = new HashSet<String> ();
		while(rs.next() && rs.getInt("id") == currentQuestionId) {
			answers.add(rs.getString("answer"));
		}
		rs.previous();
		return answers;
	}























	private int numberOfQuestions(Connection con, int quizId) 
			throws SQLException {
		int n = 0;
		PreparedStatement preparedStatement =
				con.prepareStatement(
						"SELECT COUNT(1) FROM questions WHERE quiz_id = ?;"
						);
		preparedStatement.setInt(1, quizId);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		n = rs.getInt("COUNT(1)");
		rs.close();
		return n;
	}









	////////////////// type 1 utils
	
	/*
	 * this method loads quiz id, problem, type and grade
	 */
	private void loadCommonFields(Connection con, Question q, int quizId) 
			throws SQLException {
		
		PreparedStatement preparedStatement =
				con.prepareStatement(
						"INSERT INTO questions "
						+ "(quiz_id, problem, type, grade) "
						+ "VALUES(?, ?, ?, ?);");
		preparedStatement.setLong(1, quizId);
		preparedStatement.setString(2, q.getProblem());
		preparedStatement.setString(3, q.getType());	// sorry no enum
		preparedStatement.setLong(4, q.getGrade());
		preparedStatement.executeUpdate();
	}
	
	/*
	 * this will load answers set as fields possible correct answers
	 */
	private void loadAnswersOfField(Connection con, Set<String> answers,
			int questionId, int fieldId)
			throws SQLException {
		
		for(String answer : answers) loadAnswerIntoField(con, answer, 
				questionId, fieldId);
	}
	
	private void loadWrongAnswersOfField(Connection con, Set<String> answers, 
			int questionId, int fieldId) 
					throws SQLException {
		
		for(String answer : answers) loadWrongAnswerIntoField(con, answer, 
				questionId, fieldId);
	}











	// single queries are preferred, but hey, do we have time for that?
	private void loadAnswerIntoField(Connection con, String answer, 
			int questionId, int fieldId)
					throws SQLException {
		
		PreparedStatement preparedStatement =
				con.prepareStatement("INSERT INTO answers "
						+ "(question_id, answer, field_id) "
						+ "VALUES(?, ?, ?);");
		preparedStatement.setInt(1, questionId);
		preparedStatement.setString(2, answer);
		preparedStatement.setInt(3, fieldId);
		preparedStatement.executeUpdate();
	}
	
	private void loadWrongAnswerIntoField(Connection con, String answer, 
			int questionId, int fieldId) 
					throws SQLException {
		
		PreparedStatement preparedStatement =
				con.prepareStatement("INSERT INTO answers_wrong "
						+ "(question_id, answer_wrong, field_id) "
						+ "VALUES(?, ?, ?);");
		preparedStatement.setInt(1, questionId);
		preparedStatement.setString(2, answer);
		preparedStatement.setInt(3, fieldId);
		preparedStatement.executeUpdate();
	}
	
	/*
	 * load picture url
	 */
	private void loadPictureUrl(Connection con, int questionId, 
			String pictureURL) throws SQLException {
		
		PreparedStatement preparedStatement =
				con.prepareStatement("INSERT INTO images (question_id, image) VALUES(?, ?);");
		preparedStatement.setInt(1, questionId);
		preparedStatement.setString(2, pictureURL);
		preparedStatement.executeUpdate();
	}










	
	/////////////////////////////////// type 2 utils
	
	private void loadFieldAnswers(Connection con, int questionId, 
			List<Set<String>> answers) throws SQLException {
		
		for(int fieldId = 0; fieldId < answers.size(); fieldId++) {
			loadAnswersOfField(con, answers.get(fieldId), questionId, fieldId);
		}
	}

	private void loadMAMetadata(Connection con, 
			int questionId, int numAnswers, boolean ordered) 
					throws SQLException {

		PreparedStatement preparedStatement =
				con.prepareStatement("INSERT INTO multiple_choice_metadata "
						+ "(question_id, nfields, ordered) "
						+ "VALUES(?, ?, ?);");
		preparedStatement.setInt(1, questionId);
		preparedStatement.setInt(2, numAnswers);
		preparedStatement.setBoolean(3, ordered);
		preparedStatement.executeUpdate();
	}




















	
	
	
	
	
	
	
	
	


}
