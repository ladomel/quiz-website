package classes.question;

import java.util.List;

import classes.question.Abstract.Question;

public class QuestionMC extends Question {
	
	private List<String> answers;
	
	/** 
	 * @param newProblem Description of the problem.
	 * @param answers - List of Strings, where first string is the correct answer.
	 */
	public QuestionMC(String newProblem, List<String> answers)
	{
		super(newProblem);
		setAnswers(answers);
	}

	/**
	 * Returns List with correct answer at 0th place;
	 * @return List with correct answer at 0th place;
	 */
	@Override
	public List<String> getCorrectAnswers() {
		return getAnswers();
	}

	/**
	 * @param answer List with user's answer at 0th position.
	 * @return grade of user.
	 */
	@Override
	public Integer getGrade(List<String> answer) {
		Integer grade = 0;
		if(answer.get(0).equals(getAnswers().get(0))) grade = 1; // If answer's 0th is same as correct answer.
		return grade;
	}

	private List<String> getAnswers() {
		return answers;
	}

	private void setAnswers(List<String> answers) {
		this.answers = answers;
	}
}
