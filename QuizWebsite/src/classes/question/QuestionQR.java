package classes.question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import classes.question.Abstract.Question;

public class QuestionQR extends Question{
	
	private Set<String> answers;
	
	/**	
	 * @param newProblem problem of the question.
	 * @param newAnswers Set of strings with all correct answers.
	*/
	public QuestionQR(String newProblem, Set<String> newAnswers)
	{
		super(newProblem);
		setAnswers(newAnswers);
	}

	/**
	 * Returns correct answer in ArrayList.	
	 * @return ArrayList with one element - correct answer.
	*/
	@Override
	public ArrayList<String> getCorrectAnswers()
	{
		Iterator<String> answerIterator = getAnswers().iterator();
		ArrayList<String> answer = new ArrayList<String>();
		answer.add(answerIterator.next());
		return answer;
	}
	
	/**
	 * 	Returns grade of user's answer.
	 *  @param answer ArrayList with user's answer. 
	 *  @return grade of the answer.
	 */
	@Override
	public Integer getGrade(ArrayList<String> answer) 
	{
		Integer grade = 0;
		if(getAnswers().contains(answer.get(0))) grade = 1;
		return grade;
	}

	// Answers getter.
	private Set<String> getAnswers() 
	{
		return answers;
	}

	// Answers setter.
	private void setAnswers(Set<String> answers) 
	{
		this.answers = answers;
	}
}

