package dao;

import java.util.List;

import classes.question.QuestionFB;
import classes.question.QuestionMA;
import classes.question.QuestionMC;
import classes.question.QuestionMCH;
import classes.question.QuestionMCMA;
import classes.question.QuestionPR;
import classes.question.QuestionQR;
import classes.question.QuestionTF;
import classes.question.Abstract.Question;

public interface QuestionDAO {
	
	public void addQR(int quizId, QuestionQR qr);
	
	public void addPR(int quizId, QuestionPR pr);
	
	public void addMC(int quizId, QuestionMC mc);
	
	public void addFB(int quizId, QuestionFB fb);
	
	public void addMA(int quizId, QuestionMA ma);
	
	public void addMCMA(int quizId, QuestionMCMA mcma);
	
	public void addMCH(int quizId, QuestionMCH mch);
	
	public void addTF(int quizId, QuestionTF tf);
	
	public List<Question> getQuestions(int quizId);

}
