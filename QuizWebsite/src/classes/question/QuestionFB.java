package classes.question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import classes.question.Abstract.Question;

public class QuestionFB extends Question {

	private List<Set<String>> answers;

	/** 
	 * @param newProblem - Description of the problem with special characters for blanks
	 * @param grade - grade for each correct answer
	 * @param answers - List of Sets of Strings with correct answers for each blank. Must not be empty
	 */
	public QuestionFB(String newProblem, int grade, List<Set<String>> answers) {
		super(newProblem, grade, Type.FB);
		setAnswers(answers);
	}
	
	@Override
	public String toString() {
		String answers = "";
		List<Set<String>> setAnswers = getAnswers();
		Iterator iterator;
		for (int i = 0; i < setAnswers.size(); i++)
		{
			iterator = setAnswers.get(i).iterator();
			answers += i + ") ";
			while(iterator.hasNext()) answers += iterator.next() + " ";
		}
		
		return "Problem: " + this.getProblem() + " | " +
				"Grade: " + getGrade() + " | " +
				"Answers: " + answers;
	}

	@Override
	public Integer getGrade(List<String> answer) {
		if(answer == null || answer.size() != getAnswers().size() || answer.contains(null))
			throw new IllegalArgumentException("Wrong passed answer argument length");
		Integer grade = 0;
		Iterator<String> answerIterator = answer.iterator();
		Iterator<Set<String>> correctAnswersIterator = getAnswers().iterator();
		
		while(answerIterator.hasNext())
			if(correctAnswersIterator.next().contains(answerIterator.next())) grade += getGrade();
		return grade;
	}

	@Override
	public List<String> getCorrectAnswers() {
		ArrayList<String> correctAnswers = new ArrayList<String>();
		Iterator<Set<String>> answersIterator = getAnswers().iterator();
		
		while(answersIterator.hasNext())
			correctAnswers.add(answersIterator.next().iterator().next()); // Take one element from each set.
		return correctAnswers;
	}

	/**
	 * Returns list of set of correct answers for each entry.
	 * 
	 * @return answers - list of set of correct answers for each entry
	 */
	public List<Set<String>> getAnswers() {
		return answers;
	}

	/**
	 * Sets list of set of correct answers for each entry.
	 * Throws IllegalArgumentException if wrong parameters passed.
	 * 
	 * @param answers - list of set of correct answers for each entry
	 */
	private void setAnswers(List<Set<String>> answers) {
		if(answers == null || answers.isEmpty()) throw new IllegalArgumentException("Answer cannot be null or empty!");
		for(int i = 0; i < answers.size(); i++)
			if(answers.get(i) == null || answers.get(i).isEmpty() || answers.get(i).contains(null)) 
				throw new IllegalArgumentException("Wrong inner set format!");
		this.answers = answers;
	}

	@Override
	public int getMaxGrade() {
		return getGrade()*getAnswers().size();
	}
}
