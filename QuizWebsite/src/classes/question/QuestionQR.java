package classes.question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import classes.question.Abstract.Question;

public class QuestionQR extends Question{
	
	private Set<String> answers;
	
	/**	
	 * @param newProblem problem of the question.
	 * @param newAnswers Set of strings with all correct answers.
	*/
	public QuestionQR(String newProblem, int grade, Set<String> newAnswers)
	{
		super(newProblem, grade);
		setAnswers(newAnswers);
	}

	/**
	 * Returns correct answer in List.	
	 * @return List with one element - correct answer.
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
	 * 	Returns grade of user's answer.
	 *  @param answer List with user's answer. 
	 *  @return grade of the answer.
	 */
	@Override
	public Integer getGrade(List<String> answer) 
	{
		if(getAnswers().contains(answer.get(0))) return super.getGrade();
		return 0;
	}

	// Answers getter.
	public Set<String> getAnswers() 
	{
		return answers;
	}

	// Answers setter.
	public void setAnswers(Set<String> answers) 
	{
		this.answers = answers;
	}
}

