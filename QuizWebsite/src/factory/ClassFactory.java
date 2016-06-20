package factory;
import java.util.ArrayList;

import classes.Quiz;
import classes.User;

public class ClassFactory {
	public User getUser(String userName, String password, String salt)
	{
		return new User(userName, password, salt);
	}
	
	public Quiz getQuiz(String userName, String quizName, String description, ArrayList<Integer> problems)
	{
		return new Quiz(userName, quizName, description, problems);
	}
	
	
}
