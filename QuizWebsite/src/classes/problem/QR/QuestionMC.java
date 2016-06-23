package classes.problem.QR;

import java.util.ArrayList;

import classes.problem.Abstract.Question;
import classes.problem.Abstract.Statement;

public class QuestionMC extends Question {
	
	String correctAnswer;
	/**
	 * 
	 * @param statement Statement of the problem.
	 * @param answer - ArrayList of Strings, where first string is the correct answer.
	 */
	public QuestionMC(String statement, ArrayList<String> answer)
	{
		correctAnswer = answer.get(0);
	}
	
	@Override
	public Integer getGrade(ArrayList<String> answer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement getStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAnswerLength() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getRightAnswers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}
