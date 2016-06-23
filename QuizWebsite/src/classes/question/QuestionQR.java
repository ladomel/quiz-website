package classes.question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import classes.question.Abstract.Question;

public class QuestionQR extends Question{
	
	private Set<String> answers;
	
	/**
	 * Returns right answer in arrayList. NewAnswers must not be null and must contain at least 1 entry.	
	 * @param newProblem problem of the question.
	 * @param newAnswers Set of strings with all correct answers.
	*/
	public QuestionQR(String newProblem, Set<String> newAnswers)
	{
		super(newProblem);
		setAnswers(newAnswers);
	}

	/**
	 * Returns right answer in arrayList.	
	 * @param answer Collection of user's filled fields.
	 * @return grade of answer.
	*/
	@Override
	public ArrayList<String> getCorrectAnswers()
	{
		Iterator<String> answerIterator = getAnswers().iterator();
		ArrayList<String> answer = new ArrayList<String>();
		answer.add(answerIterator.next());
		return answer;
	}
	
	@Override
	public Integer getGrade(ArrayList<String> answer) 
	{
		Integer grade = 0;
		if(getAnswers().contains(answer.get(0))) grade = 1;
		return grade;
	}

	private Set<String> getAnswers() 
	{
		return answers;
	}

	private void setAnswers(Set<String> answers) 
	{
		this.answers = answers;
	}
}

