package classes.question;

import java.util.ArrayList;

import classes.question.Abstract.Question;

public class QuestionMC extends Question {
	
	private ArrayList<String> answers;
	
	/** 
	 * @param statement Statement of the problem.
	 * @param answer - ArrayList of Strings, where first string is the correct answer.
	 */
	public QuestionMC(String newProblem, ArrayList<String> answers)
	{
		super(newProblem);
		setAnswers(answers);
	}

	@Override
	public ArrayList<String> getCorrectAnswers() {
		return getAnswers();
	}

	@Override
	public Integer getGrade(ArrayList<String> answer) {
		Integer grade = 0;
		if(answer.get(0).equals(getAnswers().get(0))) grade = 1; // If answer's 0'th is same as correct answer.
		return grade;
	}

	private ArrayList<String> getAnswers() {
		return answers;
	}

	private void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}
}
