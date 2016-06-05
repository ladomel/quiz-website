package classes;

import java.util.ArrayList;

public class Answer {
	private int grade;
	private boolean graded = false;
	private int problemId;
	private ArrayList<String> userAnswer;


	/**
	 *  Constructor stores problem's id number and user's answer object.
	 */
	public Answer(int problemId, ArrayList<String> userAnswer) 
	{
		this.problemId = problemId;
		this.userAnswer = userAnswer;
	}

	/**
	 * Returns an object with all information about Answer.
	 * @return answer.
	 */
	public ArrayList<String> getAnswer()
	{	
		return userAnswer;
	}

	/**
	 * Sets the grade user in object.
	 */
	public void setGrade(int newGrade)
	{
		grade = newGrade;
		graded = true;
	}

	/**
	 * Returns id of the problem.
	 * @return id of problem.
	 */
	public int getProblemId()
	{
		return problemId;
	}

	/**
	 * Returns grade of user or null if answer was not graded yet.
	 * @return grade or null.
	 */
	public Integer getGrade()
	{
		if(graded) return grade;
		return null;
	}

	/**
	 * Returns true if answer was graded.
	 * @return graded.
	 */
	public boolean isGraded()
	{
		return graded;
	}
}
