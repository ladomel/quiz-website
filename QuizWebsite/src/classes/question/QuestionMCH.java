package classes.question;

import java.util.List;

import classes.question.Abstract.Question;


public class QuestionMCH extends Question {

	private List<String> questions;
	private List<String> rightAnswers;
	private List<String> wrongAnswers;
	
	/** 
	 * @param newProblem Description of the problem
	 * @param grade - grade for each correct answer
	 * @param questions - list of questions on the left side
	 * @param rightAnswers - list of right answers on the right side
	 * @param wrongAnswers - set of wrong answers
	 */
	public QuestionMCH(String newProblem, int grade, List<String> questions, List<String> rightAnswers, List<String> wrongAnswers) {
		super(newProblem, grade, Type.MCH);
		setQuestions(questions);
		setRightAnswers(rightAnswers);
		setWrongAnswers(wrongAnswers);

		if(questions.size() != rightAnswers.size()) throw new IllegalArgumentException("Sizes are different!");
	}
	
	@Override
	public String toString() {
		String questions = "";
		for (String s : getQuestions()) {
			questions += s + " ";
		}
		
		String rightAnswers = "";
		for (String s : getRightAnswers()) {
			rightAnswers += s + " ";
		}
		
		String wrongAnswers = "";
		for (String s : getWrongAnswers()) {
			wrongAnswers += s + " ";
		}
		return "Problem: " + getProblem() + " | " +
				"Grade: " + getGrade() + " | " +
				"Questions: " + questions +  " | " +
				"Right Answers: " + rightAnswers +  " | " +
				"Wrong Answers: " + wrongAnswers;
	}

	@Override
	public List<String> getCorrectAnswers() {
		return rightAnswers;
	}

	@Override
	public int getMaxGrade() {
		return getGrade()*getQuestions().size();
	}

	@Override
	public Integer getGrade(List<String> answer) {
		int numCorrect = 0;
		for(int i = 0; i < answer.size(); i++)
			if(answer.get(i).equals(rightAnswers.get(i))) numCorrect++;
		return numCorrect*getGrade();
	}
	
	/**
	 * Returns list of wrong answers.
	 * 
	 * @return wrongAnswers - list of wrong answers
	 */
	public List<String> getWrongAnswers() {
		return wrongAnswers;
	}

	/**
	 * Sets list of wrong answers.
	 * 
	 * @param wrongNumbers - list of wrong answers
	 */
	public void setWrongAnswers(List<String> wrongAnswers) {
		if(wrongAnswers == null) 	throw new IllegalArgumentException("Wrong wrongAnswers format!");
		if(wrongAnswers.contains(null)) wrongAnswers.clear();
		this.wrongAnswers = wrongAnswers;
	}
	
	/**
	 * Returns list of questions.
	 * 
	 * @return questions - list of questions
	 */
	public List<String> getQuestions() {
		return questions;
	}

	/**
	 * Sets list of questions.
	 * 
	 * @param questions - list of questions
	 */
	public void setQuestions(List<String> questions) {
		if(questions == null || questions.isEmpty() || questions.contains(null)) 
			throw new IllegalArgumentException("Wrong question format!");
		this.questions = questions;
	}
	
	/**
	 * Returns list of right answers.
	 * 
	 * @return rightAnswers - list of right answers
	 */
	public List<String> getRightAnswers() {
		return rightAnswers;
	}

	/**
	 * Sets list of right answers.
	 * 
	 * @param questions - list of right answers
	 */
	public void setRightAnswers(List<String> rightAnswers) {
		if(rightAnswers == null || rightAnswers.isEmpty() || rightAnswers.contains(null)) 
			throw new IllegalArgumentException("wrong right answes format!");
		this.rightAnswers = rightAnswers;
	}
}
