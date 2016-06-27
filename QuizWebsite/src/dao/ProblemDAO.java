package dao;

import java.util.List;

import classes.question.QuestionMC;
import classes.question.QuestionQR;

public interface ProblemDAO {
	
	public void addQR(int quizId, QuestionQR qr);
	
	public void addMC(int quizId, QuestionMC mc);
	
	public List<QuestionQR> getQR(int quizId);
	
	public List<QuestionMC> getMC(int quizId);

}
