package dao;

import java.util.List;

import javax.sql.DataSource;

import classes.question.QuestionMC;
import classes.question.QuestionQR;
import classes.question.Abstract.Question;

public class QuestionDAOImpl implements QuestionDAO {

	private DataSource dataSource;
	
	public QuestionDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void addQR(int quizId, QuestionQR qr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addMC(int quizId, QuestionMC mc) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Question> getQuestions(int quizId) {
		// TODO Auto-generated method stub
		return null;
	}

}
