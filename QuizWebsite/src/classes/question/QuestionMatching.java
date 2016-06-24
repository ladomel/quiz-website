package classes.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import classes.question.Abstract.Question;


public class QuestionMatching extends Question {

	private HashMap<String, String> answers;
	private Set<String> wrongAnswers;

	public QuestionMatching(String newProblem, HashMap<String, String> answers, Set<String> wrongAnswers) {
		super(newProblem);
		setAnswers(answers);
		setWrongAnswers(wrongAnswers);
	}

	@Override
	public Integer getGrade(ArrayList<String> answer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getCorrectAnswers() {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<String, String> getAnswers() {
		return answers;
	}

	public void setAnswers(HashMap<String, String> answers) {
		this.answers = answers;
	}

	public Set<String> getWrongAnswers() {
		return wrongAnswers;
	}

	public void setWrongAnswers(Set<String> wrongAnswers) {
		this.wrongAnswers = wrongAnswers;
	}

}
