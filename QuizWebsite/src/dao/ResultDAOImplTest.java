package dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import classes.Result;
import classes.User;
import classes.Answer;
import classes.Quiz;
import factory.ClassFactory;

public class ResultDAOImplTest {

	static ResultDAO resultDAO;
	static UserDAO userDAO;
	static QuizDAO quizDAO;
	static ClassFactory classFactory;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DAOInstances daoFactory = new DAOInstances();
		daoFactory.init();
		resultDAO = daoFactory.getResultDAO();
		userDAO = daoFactory.getUserDAO();
		quizDAO = daoFactory.getQuizDAO();
		classFactory = new ClassFactory();
	}

	@Test
	public void test() {
		Result result = classFactory.getResult("vaja", 1);
		result.setFinalGrade(98);
		result.setTimeStarted(2005);
		result.setTimeTaken(5);
		List<Answer> answers = new ArrayList<Answer> ();
		Answer answer1 = classFactory.getAnswer(Arrays.asList("drogba", "gadavida", "chinetshi"));
		
		Answer answer2 = classFactory.getAnswer(Arrays.asList("messi", "aris", "sauketeso"));
		answers.add(answer1);
		answers.add(answer2);
		result.setAnswers(answers);
		resultDAO.insertResult(result);
	}

	
	@Test
	public void test2() {
		Set<String> friends = new HashSet<String>(Arrays.asList("b", "c"));
		User testUser = new User("a", "a1234", "a123");
		testUser.setFriends(friends);
		userDAO.deleteUser("a");
		userDAO.addUser("a", "a123", "a12");	
		userDAO.updateUser(testUser);  // Aq test user maq ukve setiani.
		
		Quiz quiz = new Quiz("vaja", "Future Vulture", "quiz about 2034");
		quiz.setDateCreated(20);
		quiz.setDescription("desc");
		quiz.setQuizName("name");
		quiz.setQuizTime(10);
		quiz.setMaxScore(100);
		quiz.setRandom(true);
		quiz.setOnePage(true);
		quiz.setImmediatelyCorrected(false);
		quiz.setHasPracticeMode(false);
		quiz.setAverageRating(50);
		quiz.setAverageScore(70);
		quiz.setAverageTimeMillis(20);
		quiz.setNumTries(3);
		quiz.setCategory("Phys");
		
		int lastId = quizDAO.addQuiz(quiz);
		
		Result result1 = classFactory.getResult("a", lastId);
		result1.setFinalGrade(98);
		result1.setTimeStarted(2005);
		result1.setTimeTaken(5);
		
		List<Answer> answers1 = new ArrayList<Answer> ();
		Answer answer11 = classFactory.getAnswer(Arrays.asList("drogba", "gadavida", "chinetshi"));
		Answer answer12 = classFactory.getAnswer(Arrays.asList("messi", "aris", "sauketeso"));
		
		answers1.add(answer11);
		answers1.add(answer12);
		
		result1.setAnswers(answers1);
		resultDAO.insertResult(result1);
		
		Result result2 = classFactory.getResult("a", lastId);
		result2.setFinalGrade(98);
		result2.setTimeStarted(2005);
		result2.setTimeTaken(5);
		
		List<Answer> answers2 = new ArrayList<Answer> ();
		Answer answer21 = classFactory.getAnswer(Arrays.asList("drogba", "gadavida", "chinetshi"));
		Answer answer22 = classFactory.getAnswer(Arrays.asList("messi", "aris", "sauketeso"));
		
		answers2.add(answer21);
		answers2.add(answer22);
		
		result2.setAnswers(answers2);
		resultDAO.insertResult(result2);
		
		Result result3 = classFactory.getResult("a", lastId);
		result3.setFinalGrade(98);
		result3.setTimeStarted(2005);
		result3.setTimeTaken(5);
		
		List<Answer> answers3 = new ArrayList<Answer> ();
		Answer answer31 = classFactory.getAnswer(Arrays.asList("drogba", "gadavida", "chinetshi"));
		Answer answer32 = classFactory.getAnswer(Arrays.asList("messi", "aris", "sauketeso"));
		
		answers3.add(answer31);
		answers3.add(answer32);
		
		result3.setAnswers(answers3);
		resultDAO.insertResult(result3);
		
		List<Result> results = resultDAO.getResult("a", lastId);
		
		System.out.println(results.toString());
		System.out.println("Number of results: " + results.size());
	}
}
