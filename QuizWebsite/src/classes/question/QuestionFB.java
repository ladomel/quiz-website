package classes.question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import classes.question.Abstract.Question;

public class QuestionFB extends Question {

	private ArrayList<Set<String>> answers;
	
	public QuestionFB(String newProblem, ArrayList<Set<String>> answers) {
		super(newProblem);
		setAnswers(answers);
	}

	@Override
	public Integer getGrade(ArrayList<String> answer) {
		Integer grade = 0;
		Iterator<String> answerIterator = answer.iterator();
		Iterator<Set<String>> correctAnswersIterator = getAnswers().iterator();
		
		while(answerIterator.hasNext())
			if(correctAnswersIterator.next().contains(answerIterator.next())) grade++;
		
		//for(int i = 0; i < answer.size(); i++)
		//	if(getAnswers().get(i).contains(answer.get(i))) grade++;
		return grade;
	}

	@Override
	public ArrayList<String> getCorrectAnswers() {
		ArrayList<String> correctAnswers = new ArrayList<String>();
		Iterator<Set<String>> answersIterator = getAnswers().iterator();
		
		while(answersIterator.hasNext())
			correctAnswers.add(answersIterator.next().iterator().next()); // Take one element from each set.
		
		return correctAnswers;
	}

	public ArrayList<Set<String>> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<Set<String>> answers) {
		this.answers = answers;
	}

}
