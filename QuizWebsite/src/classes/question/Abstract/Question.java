package classes.question.Abstract;

import java.util.ArrayList;

public abstract class Question{

	private String problem;

	/**
	 * @param newProblem problem description of the class.
	 */
	protected Question(String newProblem)
	{
		setProblem(newProblem);
	}
	
	/**
	 * Returns problem for user to see.	
	 * @return problem.
	*/
	public String getProblem()
	{
		return problem;
	}
	
	private void setProblem(String newProblem)
	{
		problem = newProblem;
	}
	
	/**
	 * Returns ArrayList's grade, or null if wrong format.	
	 * @param answer ArrayList of user's filled fields.
	 * @return grade of answer.
	*/
	public abstract Integer getGrade(ArrayList <String> answer);
	
	/**
	 * This function returns ArrayList of correct answers to show user.
	 * @return ArrayList of correct answers.
	*/
	public abstract ArrayList<String> getCorrectAnswers();
}
