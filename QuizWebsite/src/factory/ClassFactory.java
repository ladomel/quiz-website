package factory;
import java.util.ArrayList;
import java.util.Set;

import classes.Quiz;
import classes.User;
import classes.question.QuestionQR;

public class ClassFactory {
	public User getUser(String userName, String password, String salt)
	{
		return new User(userName, password, salt);
	}
	
	public Quiz getQuiz(String userName, String quizName, String description)
	{
		return new Quiz(userName, quizName, description);
	}
	
	public QuestionQR getQuestionQR(String newProblem, int grade, Set<String> newAnswers)
	{
		return new QuestionQR(newProblem, grade, newAnswers);
	}
}
