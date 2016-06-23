package classes;

import java.util.ArrayList;

public class Answer {
	private Integer grade = null; // Null if not graded.
	private ArrayList<String> userAnswer;

	/**
	 *  Constructor stores problem's id number and user's answer object.
	 */
	public Answer(ArrayList<String> userAnswer) 
	{
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
	public void setGrade(Integer newGrade)
	{
		grade = newGrade;
	}

	/**
	 * Returns grade of user or null if answer was not graded yet.
	 * @return grade or null.
	 */
	public Integer getGrade()
	{
		return grade;
	}
}
