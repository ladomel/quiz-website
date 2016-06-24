package classes.question;

import java.util.ArrayList;
import java.util.Iterator;

import classes.question.Abstract.Question;

public class QuestionMCMA extends Question {

	private ArrayList<String> correctAnswers;
	private ArrayList<String> incorrectAnswers;
	
	public QuestionMCMA(String newProblem, ArrayList<String> correctAnswers, ArrayList<String> incorrectAnswers) {
		super(newProblem);
		setCorrectAnswers(correctAnswers);
		setIncorrectAnswers(incorrectAnswers);
	}

	@Override
	public Integer getGrade(ArrayList<String> answer) {
		Integer grade = 0;
		Iterator<String> answerIterator = answer.iterator();
		while(answerIterator.hasNext())
			if(getCorrectAnswers().contains(answerIterator.next())) grade++;
		return grade;
	}

	@Override
	public ArrayList<String> getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(ArrayList<String> correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public ArrayList<String> getIncorrectAnswers() {
		return incorrectAnswers;
	}

	public void setIncorrectAnswers(ArrayList<String> incorrectAnswers) {
		this.incorrectAnswers = incorrectAnswers;
	}
	
}
