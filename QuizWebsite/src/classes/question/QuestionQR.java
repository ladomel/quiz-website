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
	 * @param grade - grade for each correct answer
	 * @param grade - grade of the question, positive
	 * @param newAnswers - Set of strings with all correct answers, has to contain at least 1 element
	*/
	public QuestionQR(String newProblem, int grade, Set<String> newAnswers)
	{
		super(newProblem, grade, Type.QR);
		setAnswers(newAnswers);
	}

	@Override
	public String toString() {
		String answers = "";
		for (String s : getAnswers()) {
		    answers += s + " ";
		}
		return "Problem: " + this.getProblem() + " | " +
				"Grade: " + getGrade() + " | " +
				"Answers: " + answers;
	}
	
	@Override
	public List<String> getCorrectAnswers()
	{
		ArrayList<String> answer = new ArrayList<String>();
		Iterator<String> answerIterator = getAnswers().iterator();
		answer.add(answerIterator.next());
		return answer;
	}
	
	@Override
	public Integer getGrade(List<String> answer) 
	{
		if(answer == null || answer.isEmpty() || answer.get(0) == null)  
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
	 * Throws IllegalArgumentException answers does not contain at least 1 element or contains nulls.
	 * 
	 * @param answers - set of all correct answers for the question
	 */
	public void setAnswers(Set<String> answers) 
	{
		if(answers == null || answers.isEmpty() || answers.contains(null))  
			throw new IllegalArgumentException("Answers must contain at least 1 element without nulls!");
		this.answers = answers;
	}

	@Override
	public int getMaxGrade() {
		return getGrade();
	}
}

