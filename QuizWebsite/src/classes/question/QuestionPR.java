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
	 * @param newProblem problem of the question.
	 * @param pictureURL URL of the picture in the question.
	 * @param newAnswers Set of strings with all correct answers.
	*/
	public QuestionPR(String newProblem, String pictureURL, Set<String> newAnswers) {
		super(newProblem);
		setPictureURL(pictureURL);
		setAnswers(newAnswers);
	}

	/**
	 * 	Returns grade of user's answer.
	 *  @param answer List with user's answer. 
	 *  @return grade of the answer.
	 */
	@Override
	public Integer getGrade(List<String> answer) {
		Integer grade = 0;
		if(getAnswers().contains(answer.get(0))) grade = 1;
		return grade;
	}

	/**
	 * Returns correct answer in List.	
	 * @return List with one element - correct answer.
	*/
	@Override
	public List<String> getCorrectAnswers() {
		ArrayList<String> answer = new ArrayList<String>();
		Iterator<String> answerIterator = getAnswers().iterator();
		answer.add(answerIterator.next());
		return answer;
	}

	private Set<String> getAnswers() {
		return answers;
	}

	private void setAnswers(Set<String> answers) {
		this.answers = answers;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	private void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}
}
