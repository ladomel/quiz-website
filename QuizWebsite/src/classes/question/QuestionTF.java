package classes.question;

import java.util.List;

import classes.question.Abstract.Question;

public class QuestionTF extends Question {

	private List<String> propositions;
	private List<String> answers;
	private static final String TRUE_STRING = "True";
	
	/**
	 * QuestionTrueFalse constructor. answers is a list of answers, with "True" as true and other as false in it.
	 * 
	 * @param newProblem - problem of the question
	 * @param grade - grade of each correct answer
	 * @param propositions - list of propositions
	 * @param answers - list of correct answers
	 */
	public QuestionTF(String newProblem, int grade, List<String> propositions, List<String> answers) {
		super(newProblem, grade, Type.TF);
		setPropositions(propositions);
		setAnswers(answers);
		
		if(getPropositions().size() != getAnswers().size())
			throw new IllegalArgumentException("propositions and answers must be same size!");
	}
	
	@Override
	public String toString() {
		String propositions = "";
		for (String s : getPropositions()) {
			propositions += s + " ";
		}
		
		String answers = "";
		for (String s : getAnswers()) {
			answers += s + " ";
		}
		
		return "Problem: " + getProblem() + " | " +
				"Grade: " + getGrade() + " | " +
				"Propositions: " + propositions +  " | " +
				"Answers: " + answers;
	}
	
	@Override
	public Integer getGrade(List<String> answer) {
		if(answer == null || answer.isEmpty() || answer.contains(null) || answer.size()!= getAnswers().size())
			throw new IllegalArgumentException("answer has to contain at least 1 element and have correct size!");
		
		int numCorrect = 0;
		List<String> correctAnswers = getAnswers();
		for(int i = 0; i < correctAnswers.size(); i++)
		{
			if(correctAnswers.get(i).equals(TRUE_STRING) && answer.get(i).equals(TRUE_STRING)) numCorrect++;
			else if(!correctAnswers.get(i).equals(TRUE_STRING) && !answer.get(i).equals(TRUE_STRING)) numCorrect++;
		}
		return numCorrect*getGrade();
	}

	@Override
	public List<String> getCorrectAnswers() {
		return getAnswers();
	}

	@Override
	public int getMaxGrade() {
		return getGrade()*getAnswers().size();
	}

	/**
	 * Returns a list of propositions.
	 * 
	 * @return propositions - a list of propositions
	 */
	public List<String> getPropositions() {
		return propositions;
	}

	/**
	 * Sets a list of propositions.
	 * Throws IllegalArgumentException if propositions is null or empty or contains null.
	 * 
	 * @param propositions - a list of propositions
	 */
	public void setPropositions(List<String> propositions) {
		if(propositions == null || propositions.isEmpty() || propositions.contains(null))
			throw new IllegalArgumentException("Propositions has to contain at least 1 element!");
		this.propositions = propositions;
	}

	/**
	 * Returns a list of answers.
	 * 
	 * @return answers - a list of answers
	 */
	public List<String> getAnswers() {
		return answers;
	}

	/**
	 * Sets a list of answers.
	 * Throws IllegalArgumentException if answers is null or empty or contains null.
	 * 
	 * @param answers - a list of answers
	 */
	public void setAnswers(List<String> answers) {
		if(answers == null || answers.isEmpty() || answers.contains(null))
			throw new IllegalArgumentException("Answers have to contain at least 1 element!");
		this.answers = answers;
	}
}
