package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
			// list with populated with correct size:
			questions = new ArrayList<Question> ();
			for(int i = 0; i < numberOfQuestions(con, quizId); i++)  questions.add(null);
			
			// couples
			retrieveQR(con, quizId, questions);
			retrievePR(con, quizId, questions);
			// couples
			retrieveFB(con, quizId, questions);
			retrieveMA(con, quizId, questions);
			// couples
			retrieveMCMA(con, quizId, questions);
			retrieveMC(con, quizId, questions);
			
			
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
						+ "WHERE quiz_id = ? AND type LIKE 'QR' ORDER BY id;"
						);
		preparedStatement.setInt(1, quizId);
		ResultSet rs = preparedStatement.executeQuery();
		
		retrieveQRFromRS(rs, questions);
		rs.close();
	}
	
	/*
	 * mocking above method
	 */
	private void retrievePR(Connection con, int quizId, List<Question> questions) 
			throws SQLException {
		
		PreparedStatement preparedStatement=
				con.prepareStatement(	// QR only needs sort by q_id
						"SELECT * FROM questions "
						+ "LEFT JOIN answers ON answers.question_id = questions.id "
						+ "LEFT JOIN images ON images.question_id = id "
						+ "WHERE quiz_id = ? AND type LIKE 'PR' ORDER BY id;"
						);
		preparedStatement.setInt(1, quizId);
		ResultSet rs = preparedStatement.executeQuery();
		
		retrievePRFromRS(rs, questions);
		rs.close();
	}

	///////// 
	private void retrieveFB(Connection con, int quizId, List<Question> questions) 
			throws SQLException {

		PreparedStatement preparedStatement =
				con.prepareStatement("SELECT * FROM questions "
						+ "LEFT JOIN answers ON answers.question_id = questions.id "
						+ "WHERE quiz_id = ? AND type LIKE 'FB' "
						+ "ORDER BY id, answers.field_id;"
						);
		preparedStatement.setInt(1, quizId);
		ResultSet rs = preparedStatement.executeQuery();
		
		retrieveFBFromRS(rs, questions);
		rs.close();
	}
	
	// similar to above
	private void retrieveMA(Connection con, int quizId, List<Question> questions) 
			throws SQLException {

		PreparedStatement preparedStatement =
				con.prepareStatement("SELECT * FROM questions "
						+ "LEFT JOIN answers ON answers.question_id = questions.id "
						+ "LEFT JOIN multiple_choice_metadata AS mcm "
						+ "ON mcm.question_id = questions.id  "
						+ "WHERE quiz_id = ? AND type LIKE 'MA' "
						+ "ORDER BY id, answers.field_id;"
						);
		preparedStatement.setInt(1, quizId);
		ResultSet rs = preparedStatement.executeQuery();
		
		retrieveMAFromRS(rs, questions);
		rs.close();
	}
	

	private void retrieveMCMA(Connection con, int quizId, List<Question> questions) 
			throws SQLException {
		
		PreparedStatement preparedStatement =
				con.prepareStatement("SELECT * FROM questions "
						+ "LEFT JOIN answers ON answers.question_id = questions.id "
						+ "LEFT JOIN answers_wrong AS aw ON aw.question_id = questions.id "
						+ "WHERE quiz_id = ? AND type LIKE 'MCMA' "
						+ "ORDER BY id;"
						);
		preparedStatement.setInt(1, quizId);
		ResultSet rs = preparedStatement.executeQuery();
		
		retrieveMCMAFromRS(rs, questions);
		rs.close();
	}
	
	/*
	 * modified MCMA retriever  
	 */
	private void retrieveMC(Connection con, int quizId, List<Question> questions) 
			throws SQLException {
		
		PreparedStatement preparedStatement =
				con.prepareStatement("SELECT * FROM questions "
						+ "LEFT JOIN answers ON answers.question_id = questions.id "
						+ "LEFT JOIN answers_wrong AS aw ON aw.question_id = questions.id "
						+ "WHERE quiz_id = ? AND type LIKE 'MC' "
						+ "ORDER BY id;"
						);
		preparedStatement.setInt(1, quizId);
		ResultSet rs = preparedStatement.executeQuery();
		
		retrieveMCFromRS(rs, questions);
		rs.close();
	}
















































/*
 * retrievals from ResultSet
 */


	private void retrieveQRFromRS(ResultSet rs, List<Question> questions) 
			throws SQLException {
		
		while(rs.next()) {
			String problem = rs.getString("problem");
			int grade = rs.getInt("grade");
			int currentQuestionId = rs.getInt("q_id");
			int currId = rs.getInt("id");
			rs.previous();
			Set<String> answers = 
					collectAnswersUntilFieldAndQuestionIsSame(rs, currId, 0);
			QuestionQR qr =
					classFactory.getQuestionQR(problem, grade, answers);
			// SQL runs +1 idx:
			questions.set(currentQuestionId - 1, qr);
		}
	}
	
	/*
	 * copy above
	 */
	private void retrievePRFromRS(ResultSet rs, List<Question> questions) 
			throws SQLException {
		
		while(rs.next()) {
			String imageURL = rs.getString("image");
			String problem = rs.getString("problem");
			int grade = rs.getInt("grade");
			int currentQuestionId = rs.getInt("q_id");
			int currId = rs.getInt("id");
			rs.previous();
			Set<String> answers = 
					collectAnswersUntilFieldAndQuestionIsSame(rs, currId, 0);
			QuestionPR question = 
					classFactory.getQuestionPR(problem, grade, imageURL, answers);
			// SQL runs +1 idx:
			questions.set(currentQuestionId - 1, question);
		}		
	}
	
	
	private void retrieveFBFromRS(ResultSet rs, List<Question> questions) 
			throws SQLException {
		
		while(rs.next()) {
			// TODO: these are common question fields but could not move to another functions
			String problem = null;
			Integer grade = null;
			Integer currentQuestionId = null;
			int currId = rs.getInt("id");
			problem = rs.getString("questions.problem");
			grade = rs.getInt("questions.grade");
			currentQuestionId = rs.getInt("questions.q_id");
			
			rs.previous();
			
			List<Set<String>> answers =  collectFBAnswersUntilNextQuestion(rs, currId);

			QuestionFB fb = classFactory.getQuestionFB(problem, grade, answers);
			// SQL runs +1 idx:
			questions.set(currentQuestionId - 1, fb);
		}		
		
	}
	
	/*
	 * copy above
	 */
	private void retrieveMAFromRS(ResultSet rs, List<Question> questions) 
			throws SQLException {
		
		while(rs.next()) {
			String problem = null;
			Integer grade = null;
			Integer currentQuestionId = null;
			int currId = rs.getInt("id");
			problem = rs.getString("questions.problem");
			grade = rs.getInt("questions.grade");
			currentQuestionId = rs.getInt("questions.q_id");
			// MA custom fields
			Boolean ordered = null;
			Integer nFields = null;
			ordered = rs.getBoolean("ordered");
			nFields = rs.getInt("nfields");
			
			rs.previous();
			
			// reusing existing collector
			List<Set<String>> answers =  collectFBAnswersUntilNextQuestion(rs, currId);

			QuestionMA ma = classFactory.getQuestionMA(problem, grade, ordered, answers, nFields);
			// SQL runs +1 idx:
			questions.set(currentQuestionId - 1, ma);
		}		
		
	}
	
	
	private void retrieveMCMAFromRS(ResultSet rs, List<Question> questions) 
			throws SQLException {

		while(rs.next()) {
			String problem = null;
			Integer grade = null;
			Integer currentQuestionId = null;
			int currId = rs.getInt("id");
			problem = rs.getString("questions.problem");
			grade = rs.getInt("questions.grade");
			currentQuestionId = rs.getInt("questions.q_id");
			
			rs.previous();

			// set of correct/wrong answers and all the conversion bullshit required to match MCMA ctor
			Set<String> correctAnswers = new HashSet<String> ();
			Set<String> wrongAnswers = new HashSet<String> ();
			collectMCMAAnswersAndWrongAnswers(rs, correctAnswers, wrongAnswers, currId);
			List<String> correctAnswersList = new ArrayList<String> ();
			List<String> wrongAnswersList = new ArrayList<String> ();
			correctAnswersList.addAll(correctAnswers);
			wrongAnswersList.addAll(wrongAnswers);
			
			
			QuestionMCMA mcma = 
					classFactory.getQuestionMCMA(problem, grade, 
							correctAnswersList, wrongAnswersList);
			
			// SQL runs +1 idx:
			questions.set(currentQuestionId - 1, mcma);
		}				
	}
	
	private void retrieveMCFromRS(ResultSet rs, List<Question> questions) 
			throws SQLException {
		

		while(rs.next()) {
			String problem = null;
			Integer grade = null;
			Integer currentQuestionId = null;
			int currId = rs.getInt("id");
			problem = rs.getString("questions.problem");
			grade = rs.getInt("questions.grade");
			currentQuestionId = rs.getInt("questions.q_id");
			
			rs.previous();

			// set of correct/wrong answers and all the conversion bullshit required to match MCMA ctor
			Set<String> correctAnswers = new HashSet<String> ();
			Set<String> wrongAnswers = new HashSet<String> ();
			collectMCMAAnswersAndWrongAnswers(rs, correctAnswers, wrongAnswers, currId);
			String correctAnswerString = null;
			
			for(String s : correctAnswers) correctAnswerString = s; 
			
			QuestionMC mc = classFactory.getQuestionMC(
					problem, 
					grade, 
					correctAnswerString, 
					wrongAnswers
					);
			
			// SQL runs +1 idx:
			questions.set(currentQuestionId - 1, mc);
		}				
		
	}












	//////////////////////////////// retriever utils1
	
	// self-explanatory
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
	
	// collect set of correct answers
	private Set<String> collectAnswersUntilFieldAndQuestionIsSame(ResultSet rs, 
			int currentQuestionId, int currentFieldId) 
			throws SQLException {
		Set<String> answers = new HashSet<String> ();
		while(rs.next() && rs.getInt("id") == currentQuestionId && rs.getInt("field_id") == currentFieldId) {
			answers.add(rs.getString("answers.answer"));
		}
		rs.previous();	// back up from next question
		return answers;
	}

	// collect list of sets
	private List<Set<String>> collectFBAnswersUntilNextQuestion(ResultSet rs, int currentQuestionId) 
			throws SQLException {
		
		List<Set<String>> answers = new ArrayList<Set<String>> ();
		while(rs.next() && rs.getInt("id") == currentQuestionId) {
			int currentFieldId = rs.getInt("field_id");
			Set<String> answersOfField = 
					collectAnswersUntilFieldAndQuestionIsSame(rs, currentQuestionId, currentFieldId);
			answers.add(answersOfField);
		}
		rs.previous();	// dont forget to back up on next question
		return answers;
	}
	
	/*
	 * NOTE: this method works because we know MCMA has only one field.
	 * generalisation for several fields is little bit tricky and not needed yet.
	 * 
	 * code is basically a double of collectAnswersUntilFieldAndQuestionIsSame(...)
	 */
	private void collectMCMAAnswersAndWrongAnswers(ResultSet rs, 
			Set<String> correctAnswers, 
			Set<String> wrongAnswers, 
			Integer currentQuestionId) 
					throws SQLException {
		
		while(rs.next() && rs.getInt("id") == currentQuestionId) {
			correctAnswers.add(rs.getString("answers.answer"));
			wrongAnswers.add(rs.getString("aw.answer_wrong"));
		}
		rs.previous();	// back up from next question

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
		preparedStatement.close();
		
		int qId = getLastQuestionId(con, quizId);
		setQuestionIdOnLastInsert(con, qId);
	}
	
	private void setQuestionIdOnLastInsert(Connection con, int qId) 
			throws SQLException {
		PreparedStatement preparedStatement = 
				con.prepareStatement(
						"UPDATE questions "
						+ "SET q_id = ? "
						+ "WHERE id = (SELECT LAST_INSERT_ID());"
						);
		preparedStatement.setInt(1, qId);
		preparedStatement.executeUpdate();
		preparedStatement.close();		
	}

	// self-explanatory
	private int getLastQuestionId(Connection con, int quizId) 
			throws SQLException {
		int id = 0;
		PreparedStatement preparedStatement =
				con.prepareStatement(
						"SELECT COUNT(1) "
						+ "FROM questions "
						+ "WHERE quiz_id = ?;"
						);
		preparedStatement.setInt(1, quizId);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		id = rs.getInt("count(1)");
		rs.close();
		return id;
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
