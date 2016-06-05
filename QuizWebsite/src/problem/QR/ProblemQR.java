package problem.QR;

import java.util.Set;

import classes.Answer;
import problem.Abstract.Problem;

public class ProblemQR extends Problem{

	public ProblemQR(String newDescription, Set<String> newAnswers)
	{
		problem = new StatementQR(newDescription);
		answers = newAnswers;
	}
	
	private StatementQR problem;
	private Set<String> answers;
	
	@Override
	public void setGrade(Answer answer) 
	{
		String userAnswer = answer.getAnswer().get(0);   // NO check.
		if(getAnswers().contains(userAnswer)) answer.setGrade(1);
		else answer.setGrade(0);
	}

	public Set<String> getAnswers()
	{
		return answers;
	}
	
	@Override
	public StatementQR getStatement() 
	{
		return problem;
	}
}

