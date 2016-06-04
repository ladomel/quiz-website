package model;

import java.util.ArrayList;

/**
 * This class encapsulates quiz.
 * 
 * @author sergi
 *
 */
public abstract class Quiz {

	/**
	 * Call this method to get a list of indexes of all problems
	 * included in this quiz.
	 * 
	 * @return
	 */
	public abstract ArrayList<Integer> getProblemIds();
	
}
