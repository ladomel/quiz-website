package classes.question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import classes.question.Abstract.Question;

public class QuestionQR extends Question{
	
	private Set<String> answers;
	
	/**	
	 * @param newProblem - problem of the question. Not null
	 * @param grade - grade of the question, positive
	 * @param newAnswers - Set of strings with all correct answers, has to contain at least 1 element
	*/
	public QuestionQR(String newProblem, int grade, Set<String> newAnswers)
	{
		super(newProblem, grade);
		setAnswers(newAnswers);
	}

	/**
	 * Returns correct answer in List.	
	 * 
	 * @return correctAnswers - List with one element - correct answer
	*/
	@Override
	public List<String> getCorrectAnswers()
	{
		ArrayList<String> answer = new ArrayList<String>();
		Iterator<String> answerIterator = getAnswers().iterator();
		answer.add(answerIterator.next());
		return answer;
	}
	
	/**
	 * 	Returns grade of user's answer. List's first element is checked
	 *  so it has to contain at least 1 element.
	 * 
	 *  @param answer List with user's answer. 
	 *  @return grade of the answer.
	 */
	@Override
	public Integer getGrade(List<String> answer) 
	{
		if(answer == null || answer.isEmpty())  
			throw new IllegalArgumentException("Answer has to contain at least 1 element!");
		if(getAnswers().contains(answer.get(0))) return super.getGrade();
		return 0;
	}

	/**
	 * Returns set of all correct answers for the question.
	 * 
	 * @return answers - set of all correct answers for the question
	 */
	public Set<String> getAnswers() 
	{
		return answers;
	}

	/**
	 * Sets a set of all correct answers for the question.
	 * Throws IllegalArgumentException answers does not contaion at least 1 element.
	 * 
	 * @param answers - set of all correct answers for the question
	 */
	public void setAnswers(Set<String> answers) 
	{
		if(answers == null || answers.isEmpty())  
			throw new IllegalArgumentException("Answers must contain at least 1 element!");
		this.answers = answers;
	}

	/**
	 * Returns maximum grade user can get in the question.
	 * 
	 * @return maxGrade - maximum grade user can get in the question
	 */
	@Override
	public int getMaxGrade() {
		return getGrade();
	}
}

