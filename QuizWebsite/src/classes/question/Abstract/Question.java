package classes.question.Abstract;

import java.util.List;

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
	 * Returns List's grade, or null if wrong format.	
	 * @param answer List of user's filled fields.
	 * @return grade of answer.
	*/
	public abstract Integer getGrade(List <String> answer);
	
	/**
	 * This function returns List of correct answers to show user.
	 * @return List of correct answers.
	*/
	public abstract List<String> getCorrectAnswers();
}
