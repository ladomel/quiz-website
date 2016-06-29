package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import classes.question.QuestionMC;
import classes.question.QuestionPR;
import classes.question.QuestionQR;
import classes.question.Abstract.Question;
import factory.ClassFactory;

public class QuestionDAOImpl implements QuestionDAO {

	private DataSource dataSource;
	private ClassFactory classFactory;
	
	public QuestionDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
		classFactory = new ClassFactory();
	}
	
	@Override
	public void addQR(int quizId, QuestionQR qr) {
		// keep ID of where question was saved to add answers
		int lastId = commonFields(quizId, qr.getProblem(),
				qr.getGrade(), qr.getType());
		correctAnswers(lastId, qr.getAnswers());
	}
	
	@Override
	public void addPR(int quizId, QuestionPR pr) {
		// keep ID of where question was saved to add answers
		int lastId = commonFields(quizId, pr.getProblem(),
				pr.getGrade(), pr.getType());
		correctAnswers(lastId, pr.getAnswers());
	}
	
	@Override
	public void addMC(int quizId, QuestionMC mc) {
		// keep ID of where question was saved to add answers
		int lastId = commonFields(quizId, mc.getProblem(),
				mc.getGrade(), mc.getType());
		correctAnswers(lastId, mc.getCorrectAnswers());
		wrongAnswers(lastId, mc.getWrongAnswers());
	}

	private void wrongAnswers(int lastId, Collection<String> wrongAnswers) {
		int fieldId = 0;
		for(String s : wrongAnswers) 
		{ 
			putWrongAnswer(s, lastId, fieldId); 
			fieldId++; 
		}
	}

	private void putWrongAnswer(String s, int lastId, int fieldId) {
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement("INSERT INTO answers_wrong " + 
							"(question_id, answer_wrong, field_id) " + 
							"VALUES(?, ?, ?);");
			preparedStatement.setInt(1, lastId);
			preparedStatement.setString(2, s);
			preparedStatement.setInt(3, fieldId);
			preparedStatement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void correctAnswers(int lastId, Collection<String> answers) {
		for(String s : answers) putAnswer(s, lastId);
	}

	// TODO: one can insert all in single query.
	// but to save time we leave it as is
	private void putAnswer(String s, int lastId) {
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement("INSERT INTO answers " + 
							"(question_id, answer, field_id) " + 
							"VALUES(?, ?, ?);");
			preparedStatement.setInt(1, lastId);
			preparedStatement.setString(2, s);
			preparedStatement.setInt(3, 0);
			preparedStatement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int commonFields(int quizId, String problem,
			int grade, String type) {
		Connection con;
		int lastId = 0;
		try {
			con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(commonCommand());
			preparedStatement.setInt(1, quizId);
			preparedStatement.setString(2, problem);
			preparedStatement.setString(3, type);
			preparedStatement.setInt(4, grade);
			preparedStatement.executeUpdate();
			lastId = MySQLUtil.getLastInsertId(con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lastId;
	}

	private String commonCommand() {
		return "INSERT INTO questions (quiz_id, problem, type, grade) " + 
				"VALUES(?, ?, ?, ?);";
	}

	@Override
	public List<Question> getQuestions(int quizId) {
		List<Question> questions = null;
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement = 
					con.prepareStatement(getQuestionsWithAnswersCommand());
			preparedStatement.setInt(1, quizId);
			ResultSet rs = preparedStatement.executeQuery();
			questions = loadQuestions(rs);
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questions;
	}

	// here question list is filled
	private List<Question> loadQuestions(ResultSet rs) throws SQLException {
		List<Question> questions = new ArrayList<Question> ();
		rs.next();
		while(true) {
			Question q = readQuestionFromResultSet(rs);
			if(q == null) break;
			questions.add(q);
		}
		return questions;
	}

	private Question readQuestionFromResultSet(ResultSet rs)
			throws SQLException {
		if(rs.isAfterLast()) return null;
		Question question = null;
		String type = rs.getString("type");
		switch(type) {
		case "QR": question = loadQR(rs); break;
		case "PR": question = loadPR(rs); break;
		// TODO: add other types as well
		}
		return question;
	}

	private Question loadPR(ResultSet rs) throws SQLException {
		/*
		 * COPIED FROM loadQR() TO DO IT FAST
		 */
		int questionId = rs.getInt("id");
		String problem = rs.getString("problem");
		int grade = rs.getInt("grade");
		Set<String> answers = new HashSet<String> ();	// now load this set
		answers.add(rs.getString("answer"));
		while(rs.next() && rs.getInt("id") == questionId) {
			answers.add(rs.getString("answer"));
		}
		Question q = classFactory.getQuestionQR(problem, grade, answers);
		return q;
	}

	// if this is called rs.isAfterLast() is false
	private Question loadQR(ResultSet rs) 
			throws SQLException {
		// memorize question id we are working with
		// and keep moving until we collect all the answers
		// NOTE: SAME QUESTION ARE ADJACENT IN RS
		int questionId = rs.getInt("id");
		String problem = rs.getString("problem");
		int grade = rs.getInt("grade");
		Set<String> answers = new HashSet<String> ();	// now load this set
		answers.add(rs.getString("answer"));
		while(rs.next() && rs.getInt("id") == questionId) {
			answers.add(rs.getString("answer"));
		}
		Question q = classFactory.getQuestionQR(problem, grade, answers);
		return q;
	}

	private String getQuestionsWithAnswersCommand() {
		return "SELECT * FROM questions INNER JOIN answers ON " + 
				"questions.id = answers.question_id " + 
				"WHERE questions.quiz_id = ? ORDER BY questions.id;";
	}

}
