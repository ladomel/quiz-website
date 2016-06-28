package classes;

import java.util.List;

public class Answer {
	private int grade = -1; // not graded yet
	private List<String> userAnswer;

	/**
	 *  Constructor stores problem's id number and user's answer object.
	 */
	public Answer(List<String> userAnswer) 
	{
		this.userAnswer = userAnswer;
	}

	/**
	 * Returns a list with all information about Answer.
	 * @return answer.
	 */
	public List<String> getAnswer()
	{	
		return userAnswer;
	}	

	/**
	 * Sets the grade user in object.
	 */
	public void setGrade(int newGrade)
	{
		grade = newGrade;
	}

	/**
	 * Returns grade of user or null if answer was not graded yet.
	 * @return grade or null.
	 */
	public int getGrade()
	{
		return grade;
	}
}
