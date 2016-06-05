package problem.Abstract;

import classes.Answer;

public abstract class Problem{

	/**
	 * Changes Answer's graded state to true and sets the grade with maximum grade.
	*/
	public abstract void setGrade(Answer answer);
	
	/**
	 * This function returns statement for user to see.
	 * @return Statement;
	*/
	public abstract Statement getStatement();	
}
