package classes.question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import classes.question.Abstract.Question;

public class QuestionPR extends Question {

	private Set<String> answers;
	private String pictureURL;
	
	/**	
	 * @param newProblem - problem of the question, not null
	 * @param grade - grade for each correct answer
	 * @param pictureURL - URL of the picture in the question, not null
	 * @param newAnswers - Set of strings with all correct answers, must contain at least 1 element
	*/
	public QuestionPR(String newProblem, int grade, String pictureURL, Set<String> newAnswers) {
		super(newProblem, grade, Type.PR);
		setPictureURL(pictureURL);
		setAnswers(newAnswers);
	}

	@Override
	public Integer getGrade(List<String> answer) {
		if(answer == null || answer.isEmpty() || answer.get(0) == null)  
			throw new IllegalArgumentException("Answer has to contain at least 1 element!");
		if(getAnswers().contains(answer.get(0))) return getMaxGrade();
		return 0;
	}

	@Override
	public List<String> getCorrectAnswers() {
		ArrayList<String> answer = new ArrayList<String>();
		Iterator<String> answerIterator = getAnswers().iterator();
		answer.add(answerIterator.next());
		return answer;
	}

	/**
	 * Returns set of correct answers.
	 * 
	 * @return answers - set of correct answers
	*/
	public Set<String> getAnswers() {
		return answers;
	}
	
	/**
	 * Sets a set of all correct answers for the question.
	 * Throws IllegalArgumentException answers does not contain at least 1 element.
	 * 
	 * @param answers - set of all correct answers for the question
	 */
	public void setAnswers(Set<String> answers) 
	{
		if(answers == null || answers.isEmpty() || answers.contains(null))  
			throw new IllegalArgumentException("Answers must contain at least 1 element!");
		this.answers = answers;
	}

	/**
	 * Returns url of picture for user to see.
	 * 
	 * @return pictureURL - url of picture for user to see
	 */
	public String getPictureURL() {
		return pictureURL;
	}

	/**
	 * Sets url of picture for user to see. 
	 * Throws IllegalArgumentException if pictureURL is null.
	 * 
	 * @param pictureURL - url of picture for user to see
	 */
	private void setPictureURL(String pictureURL) {
		if(pictureURL == null) throw new IllegalArgumentException("pictureURL must not be null!");
		this.pictureURL = pictureURL;
	}

	@Override
	public int getMaxGrade() {
		return getGrade();
	}
}
