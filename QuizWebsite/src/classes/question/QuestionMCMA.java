package classes.question;

import java.util.Iterator;
import java.util.List;

import classes.question.Abstract.Question;

public class QuestionMCMA extends Question {

	private List<String> correctAnswers;
	private List<String> incorrectAnswers;
	
	/**
	 * @param newProblem - description of the question
	 * @param grade - grade for each correct answer
	 * @param correctAnswers - List with correct answers
	 * @param incorrectAnswers -  List with incorrect answers
	 */
	public QuestionMCMA(String newProblem, int grade, List<String> correctAnswers, List<String> incorrectAnswers) {
		super(newProblem, grade);
		setCorrectAnswers(correctAnswers);
		setIncorrectAnswers(incorrectAnswers);
	}
	
	@Override
	public List<String> getCorrectAnswers() {
		return correctAnswers;
	}

	/**
	 * Sets coorectAnswers. Throws IllegalArgumentException on wrong format. 
	 * 
	 * @param correctAnswers - List with correct answers
	 */
	public void setCorrectAnswers(List<String> correctAnswers) {
		if(correctAnswers == null || correctAnswers.contains(null)) 
			throw new IllegalArgumentException("Answer cannot be null!");
		this.correctAnswers = correctAnswers;
	}

	/**
	 * Returns incorrectAnswers.
	 * 
	 * @return incorrectAnswers - List with incorrect answers
	 */
	public List<String> getIncorrectAnswers() {
		
		return incorrectAnswers;
	}
	
	/**
	 * Sets incoorectAnswers. Throws IllegalArgumentException on wrong format. 
	 * 
	 * @param incorrectAnswers - List with incorrect answers
	 */
	public void setIncorrectAnswers(List<String> incorrectAnswers) {
		if(incorrectAnswers == null || incorrectAnswers.contains(null)) 
			throw new IllegalArgumentException("Answer cannot be null!");
		this.incorrectAnswers = incorrectAnswers;
	}

	@Override
	public Integer getGrade(List<String> answer) {
		int numCorrect = 0;
		int numIncorrect = 0;
		Iterator<String> answerIterator = answer.iterator();
		String nextAnswer;
		
		while(answerIterator.hasNext())
		{
			nextAnswer = answerIterator.next();
			if(getCorrectAnswers().contains(nextAnswer)) numCorrect++;
			if(getIncorrectAnswers().contains(nextAnswer)) numIncorrect++;
		}
		return getGrade()*(numCorrect + (getIncorrectAnswers().size() - numIncorrect));
	}

	@Override
	public int getMaxGrade() {
		return getGrade()*(getCorrectAnswers().size() + getIncorrectAnswers().size());
	}
}
