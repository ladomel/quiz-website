package classes.question.Abstract;

import java.util.ArrayList;

public abstract class Question{
	protected Question(String newProblem)
	{
		setProblem(newProblem);
	}
	
	private String problem;

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
	 * Returns Collection's grade, or null if wrong format.	
	 * @param answer Collection of user's filled fields.
	 * @return grade of answer.
	*/
	public abstract Integer getGrade(ArrayList <String> answer);
	
	/**
	 * This function returns collection of correct answers to show user.
	 * @return Collection of correct answers.
	*/
	public abstract ArrayList<String> getCorrectAnswers();
}
