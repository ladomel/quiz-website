package classes.question.Abstract;

import java.util.List;

public abstract class Question{

	private String problem;
	private int grade; // Grade for each correct answer.
	
	/**
	 * @param newProblem problem description of the class.
	 */
	protected Question(String newProblem, int grade)
	{
		setProblem(newProblem);
		setGrade(grade);
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

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
}
