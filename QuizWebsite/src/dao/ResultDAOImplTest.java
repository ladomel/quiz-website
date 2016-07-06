package dao;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
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

	// this is scratching surface barely
	// needs better check
	@Test
	public void test() {
		
		userDAO.addUser("vaja", "vajaspass", "vajassalt");
		userDAO.addUser("vajab", "vajaspass", "vajassalt");
		userDAO.addUser("vajabe", "vajaspass", "vajassalt");
		userDAO.addUser("vajaber", "vajaspass", "vajassalt");
		userDAO.addUser("vajabere", "vajaspass", "vajassalt");
		userDAO.addUser("vajaberej", "vajaspass", "vajassalt");
		userDAO.addUser("vajabereja", "vajaspass", "vajassalt");
		
		userDAO.addFriend("vaja", "vajab");
		userDAO.addFriend("vaja", "vajabe");
		
		Result result1 = buildResult("vaja", 1, 90, 2005, 1);
		Result result2 = buildResult("vajab", 2, 91, 2006, 2);
		Result result21 = buildResult("vajabe", 2, 71, 2034, 9);
		Result result3 = buildResult("vajabe", 3, 92, 2007, 3);
		Result result4 = buildResult("vajaber", 4, 93, 2008, 4);
		Result result41 = buildResult("vajaber", 2, 46, 201, 9);
		Result result5 = buildResult("vajabere", 5, 94, 2008, 5);
		Result result6 = buildResult("vajaberej", 2, 95, 2009, 6);
		Result result7 = buildResult("vajabereja", 1, 96, 2010, 7);
		
		resultDAO.insertResult(result1);
		resultDAO.insertResult(result2);
		resultDAO.insertResult(result21);
		resultDAO.insertResult(result3);
		resultDAO.insertResult(result4);
		resultDAO.insertResult(result41);
		resultDAO.insertResult(result5);
		resultDAO.insertResult(result6);
		resultDAO.insertResult(result7);
		
		System.out.println("avg score quiz 2: " + resultDAO.averageScore(2));
		
		System.out.println("best by quiz id: " + resultDAO.getBestResults(2, 3, 2000));
		resultDAO.removeHistory(4);
		System.out.println("removed 4: " + resultDAO.getRecentResults(4, 3));
		
		System.out.println("recent q_id n	:" + resultDAO.getRecentResults(2, 2));
		System.out.println("recent u_nm n	:" + resultDAO.getRecentResults("vajaber", 3));
		
		System.out.println("pop quizes	202	:" + resultDAO.getPopularQuizzes(5, 202));
		
		System.out.println("friends quizzes" + resultDAO.getRecentResults(userDAO.getFriends("vaja"), 3));
		
		System.out.println("rank: " + resultDAO.rankInQuiz("god", 1)); 
		
	}

	// result construction wrapper
	private Result buildResult(String userName, int q_id, int grade, int start, int duration) {
		Result result = classFactory.getResult(userName, q_id);
		result.setFinalGrade(grade);
		result.setTimeStarted(start);
		result.setTimeTaken(duration);
		return result;
	}

	@Test
	public void insertAndGetResult()
	{
		User newUser = classFactory.getUser("testUser", "a", "a");
		userDAO.addUser("testUser", "a", "a");
		int quizId = getNewQuizId();
		Result res = classFactory.getResult("testUser", quizId);
		res.setFinalGrade(90);
		res.setTimeStarted(10);
		res.setTimeTaken(20);
		//res.setUserName("test2");
		System.out.println(res.toString());
		resultDAO.insertResult(res);
		System.out.println(resultDAO.getResult("testUser", quizId));
		
	}
	
	@Test
	public void getPopularQuizzesTest()
	{
		userDAO.deleteUser("testUser");
		userDAO.addUser("testUser", "a", "a");
		int quizId = getNewQuizId();
		for(int i = 0; i < 3; i++)	resultDAO.insertResult(classFactory.getResult("testUser", quizId));
		System.out.println("Quiz1 id:" + quizId);
		
		int quizId2 = getNewQuizId();
		for(int i = 0; i < 5; i++)	resultDAO.insertResult(classFactory.getResult("testUser", quizId2));
		System.out.println("Quiz2 id:" + quizId2);
		
		int quizId3 = getNewQuizId();
		for(int i = 0; i < 2; i++)	resultDAO.insertResult(classFactory.getResult("testUser", quizId3));
		System.out.println("Quiz3 id:" + quizId3);

		System.out.println(resultDAO.getPopularQuizzes(3, 21));	
	}
	
	@Test
	public void getBestResultsTest()
	{
		userDAO.deleteUser("testUser");
		userDAO.addUser("testUser", "a", "a");
		int quizId = getNewQuizId();
		resultDAO.insertResult(buildResult("testUser", quizId, 10, 10, 6));
		resultDAO.insertResult(buildResult("testUser", quizId, 11, 1, 7));
		resultDAO.insertResult(buildResult("testUser", quizId, 12, 19, 11));
		resultDAO.insertResult(buildResult("testUser", quizId, 10, 12, 12));
		resultDAO.insertResult(buildResult("testUser", quizId, 10, 11, 13));
		
		System.out.println(resultDAO.getBestResults(quizId, 2, 5));
	}
	
	@Test
	public void getRecentResultsQuizTest()
	{
		userDAO.deleteUser("testUser");
		userDAO.addUser("testUser", "a", "a");
		int quizId = getNewQuizId();
		resultDAO.insertResult(buildResult("testUser", quizId, 10, 1, 6));
		resultDAO.insertResult(buildResult("testUser", quizId, 11, 5, 7));
		resultDAO.insertResult(buildResult("testUser", quizId, 12, 13, 11));
		resultDAO.insertResult(buildResult("testUser", quizId, 10, 4, 12));
		resultDAO.insertResult(buildResult("testUser", quizId, 10, 7, 13));
		
		System.out.println(resultDAO.getRecentResults(quizId, 2));
	}
	
	@Test
	public void getRecentResultsUserTest()
	{
		userDAO.deleteUser("testUser");
		userDAO.addUser("testUser", "a", "a");
		int quizId = getNewQuizId();
		resultDAO.insertResult(buildResult("testUser", quizId, 10, 1, 6));
		resultDAO.insertResult(buildResult("testUser", quizId, 11, 5, 7));
		
		System.out.println(resultDAO.getRecentResults("testUser", 3));
	}
	
	
	private int getNewQuizId()
	{
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
		quiz.setCategory("Phys");
		return quizDAO.addQuiz(quiz);
	}
}
