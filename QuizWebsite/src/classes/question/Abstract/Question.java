package classes.question.Abstract;

import java.util.List;

public abstract class Question{

	private String problem;
	private int grade; 
	
	/**
	 * @param newProblem - problem description of the class
	 * @param grade - grade for each correct answer
	 */
	public Question(String newProblem, int grade)
	{
		setProblem(newProblem);
		setGrade(grade);
	}
	
	/**
	 * Returns problem.	
	 * 
	 * @return problem - problem for user to see
	*/
	public String getProblem()
	{
		return problem;
	}
	
	/**
	 * Sets problem.	
	 * 
	 * @param newProblem - problem for user to see
	*/
	
	private void setProblem(String newProblem)
	{
		problem = newProblem;
	}
	
	/**
	 * Returns List's grade, or null if wrong format.
	 * 	
	 * @param answer - List of user's filled fields
	 * @return grade - grade of answer
	*/
	public abstract Integer getGrade(List<String> answer);
	
	/**
	 * This function returns List of correct answers to show user.
	 * 
	 * @return answers - List of correct answers
	*/
	public abstract List<String> getCorrectAnswers();
	
	/**
	 * This function returns grade for each correct answer.
	 * 
	 * @return grade - grade for each correct answer
	*/
	public int getGrade() {
		return grade;
	}

	/**
	 * This function sets grade for each correct answer.
	 * 
	 * @param grade - grade for each correct answer
	*/
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	/**
	 * This function returns maximum grade user can get in the question.
	 * 
	 * @return grade - maximum grade user can get in the question
	 */
	public abstract int getMaxGrade();
}
