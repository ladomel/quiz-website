package classes.question.Abstract;

import java.util.List;

public abstract class Question{

	private String problem;
	private int grade; 
	private Type type;
	protected enum Type { QR, FB, MC, PR, MA, MCMA, MCH, TF };
	
	/**
	 * @param newProblem - problem description of the class
	 * @param grade - grade for each correct answer
	 */
	public Question(String newProblem, int grade, Type type)
	{
		setProblem(newProblem);
		setGrade(grade);
		setType(type);
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
	 * Sets problem. Throws IllegalArgumentException if newProblem is null.
	 * 
	 * @param newProblem - problem for user to see
	*/
	private void setProblem(String newProblem)
	{
		if (newProblem == null) throw new IllegalArgumentException("Problem cannot be null!");
		problem = newProblem;
	}
	
	/**
	 * Returns List's grade, or throws IllegalArgumentException if answer's format is not correct.
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
	 * Throws IllegalArgumentException if grade is negative.
	 * 
	 * @param grade - grade for each correct answer
	*/
	public void setGrade(int grade) {
		if (grade < 1) throw new IllegalArgumentException("Grade must be positive!");
		this.grade = grade;
	}
	
	/**
	 * This function returns maximum grade user can get in the question.
	 * 
	 * @return grade - maximum grade user can get in the question
	 */
	public abstract int getMaxGrade();

	public String getType() {
		return type.toString();
	}

	protected void setType(Type type) {
		this.type = type;
	}
}
