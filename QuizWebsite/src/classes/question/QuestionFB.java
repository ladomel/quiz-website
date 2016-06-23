package classes.question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import classes.question.Abstract.Question;

public class QuestionFB extends Question {

	private ArrayList<Set<String>> answers;

	/** 
	 * @param newProblem Description of the problem with special characters for blanks.
	 * @param answers - ArrayList of Sets of Strings with correct answers for each blank.
	 */
	public QuestionFB(String newProblem, ArrayList<Set<String>> answers) {
		super(newProblem);
		setAnswers(answers);
	}

	/**
	 * Returns grade according to user's answer. Each answer is counted
	 * separately and adds 1 point.
	 * @return Integer with score in it.
	 * @param answer ArrayList of Strings user entered.
	 */
	@Override
	public Integer getGrade(ArrayList<String> answer) {
		Integer grade = 0;
		Iterator<String> answerIterator = answer.iterator();
		Iterator<Set<String>> correctAnswersIterator = getAnswers().iterator();
		
		while(answerIterator.hasNext())
			if(correctAnswersIterator.next().contains(answerIterator.next())) grade++;
		return grade;
	}

	/**
	 * Return ArrayList with correct answers for user to see.
	 * @return ArrayList with correct answers for user to see.
	 */
	@Override
	public ArrayList<String> getCorrectAnswers() {
		ArrayList<String> correctAnswers = new ArrayList<String>();
		Iterator<Set<String>> answersIterator = getAnswers().iterator();
		
		while(answersIterator.hasNext())
			correctAnswers.add(answersIterator.next().iterator().next()); // Take one element from each set.
		return correctAnswers;
	}

	public ArrayList<Set<String>> getAnswers() {
		return answers;
	}

	private void setAnswers(ArrayList<Set<String>> answers) {
		this.answers = answers;
	}

}
