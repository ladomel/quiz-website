package factory;
import java.util.List;
import java.util.Set;

import classes.Quiz;
import classes.User;
import classes.question.QuestionFB;
import classes.question.QuestionPR;
import classes.question.QuestionQR;

public class ClassFactory {
	// Each method returns corresponding class.
	
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
	
	public QuestionPR getQuestionPR(String newProblem, int grade, String pictureURL, Set<String> newAnswers)
	{
		return new QuestionPR(newProblem, grade, pictureURL, newAnswers);
	}
	
	public QuestionFB getQuestionFB(String newProblem, int grade, List<Set<String>> newAnswers)
	{
		return new QuestionFB(newProblem, grade, newAnswers);
	}
}
