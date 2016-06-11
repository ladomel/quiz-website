package classes.problem.QR;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import classes.problem.Abstract.Problem;
import classes.problem.Abstract.Statement;

public class ProblemFB extends Problem {
	private StatementFB statement;
	ArrayList<HashSet<String>> answers;
	
	/**
	 * Statement and answer have to be same size.
	 * @param description description of the problem.
	 * @param statement ArrayList of strings between which user needs to write input.
	 * @param answer ArrayList of HashSets of possible answers in each place. 
	 */
	public ProblemFB(String description, ArrayList<String> statement, ArrayList<HashSet<String>> answers)
	{
		this.statement = new StatementFB(description, statement);
		this.answers = answers;
	}
	
	@Override
	public Integer getGrade(ArrayList<String> userAnswers) {
		int grade = 0;
		for(int i = 0; i < answers.size(); i++)
			if(answers.get(i).contains(userAnswers.get(i))) grade++;
		return grade;
	}

	@Override
	public Statement getStatement() {
		return statement;
	}

	@Override
	public Integer getAnswerLength() {
		return answers.size();
	}
	
	// Takes one entry from the set of right answers and constructs an arrayList from it.
	@Override
	public ArrayList<String> getRightAnswers() {
		ArrayList<String> rightAnswers = new ArrayList<String>();
		for(int i = 0; i < answers.size(); i++)
		{
			Iterator<String> answerIterator = answers.get(i).iterator();
			rightAnswers.add(answerIterator.next());
		}
		return	rightAnswers;
	}
}
