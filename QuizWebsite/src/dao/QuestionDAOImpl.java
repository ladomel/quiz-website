package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import classes.question.QuestionFB;
import classes.question.QuestionMC;
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addFB(int quizId, QuestionFB fb) {
		try {
			Connection con = dataSource.getConnection();
			loadCommonFields(con, fb, quizId);
			int questionId = MySQLUtil.getLastInsertId(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Question> getQuestions(int quizId) {
		List<Question> questions = null;
		// TODO: this is hard
		return questions;
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
	
	
	
	
	
	
	
	
	
	
	


}
