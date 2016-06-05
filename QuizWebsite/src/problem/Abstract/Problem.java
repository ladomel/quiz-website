package problem.Abstract;

import java.util.ArrayList;

public abstract class Problem{

	/**
	 * Returns ArrayList's grade, or null if wrong format.
	*/
	public abstract Integer getGrade(ArrayList<String> answer);
	
	/**
	 * This function returns statement for user to see.
	 * @return Statement;
	*/
	public abstract Statement getStatement();	
	
	/**
	 * This function returns length of user's expected Array of answers.
	 * @return answer length;
	*/
	public abstract Integer getAnswerLength();
}
