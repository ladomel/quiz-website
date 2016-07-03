package dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import classes.Quiz;
import factory.ClassFactory;


public class QuizDAOImplTest {

	private static DAOInstances factory;
	private static QuizDAO quizDAO;
	private static ClassFactory classFactory;

	@BeforeClass
	public static void setUp() throws Exception {
		factory = new DAOInstances();
		factory.init();
		quizDAO = factory.getQuizDAO();
		classFactory = new ClassFactory();
	}
	
	/*
	 * bad test do not use
	 */
//	@Test
//	public void testEquals() {
//		Quiz quiz1 = classFactory.getQuiz("u", "v", "w");
//		quiz1.setDateCreated(6);
//		quiz1.setId(2);
//		quiz1.setHasPracticeMode(true);
//		quiz1.setDescription("a");
//		Quiz quiz2 = classFactory.getQuiz("u", "v", "w");
//		quiz2.setDateCreated(6);
//		quiz2.setId(2);
//		quiz2.setHasPracticeMode(true);
//		quiz2.setDescription("a");
//		assertTrue(quiz1.equals(quiz2));
//		quiz2.setId(1);
//		assertFalse(quiz1.equals(quiz2));
//	}
//
//	@Test
//	public void test() {
//		Quiz quiz = new Quiz("vaja", "Future Vulture", "quiz about 2034");
//		quiz.setHasPracticeMode(true);
//		quiz.setImmediatelyCorrected(false);
//		quiz.setMaxScore(25);
//		quiz.setQuizTime(120);
//		quiz.setRandom(false);
//		quiz.setDateCreated(4);
//		quiz.setId(2);
//		
//		assertTrue(quizDAO.updateQuiz(quiz) == null);
//
//		int lastId = quizDAO.addQuiz(quiz);
//		quiz.setId(lastId);
//		assertTrue(quiz.equals(quizDAO.getQuiz(lastId)));		
//		
//		assertEquals(lastId + 1, quizDAO.addQuiz(quiz));
//		
//		assertTrue(quiz.equals(quizDAO.deleteQuiz(lastId)));		
//		assertTrue(quizDAO.getQuiz(lastId) == null);
//		System.out.println(quizDAO.getRecentQuizzes(5));
//	}
//	
//	@Test
//	public void test2() {
//		Quiz quiz = new Quiz("vaja", "Future Vulture1", "quiz about 2034");
//		quiz.setDateCreated(20);
//		quiz.setDescription("desc");
//		quiz.setQuizName("name");
//		quiz.setQuizTime(10);
//		quiz.setMaxScore(110);
//		quiz.setRandom(true);
//		quiz.setOnePage(true);
//		quiz.setImmediatelyCorrected(false);
//		quiz.setHasPracticeMode(false);
//		quiz.setCategory("Phys");
//
////		System.out.println("Before:              " + quiz.toString());
//		int lastId = quizDAO.addQuiz(quiz);
//		Quiz taken1 = quizDAO.getQuiz(lastId);
//		
////		System.out.println("Added and taken out: " + taken1.toString());
//
//		taken1.setDateCreated(30);
//		taken1.setDescription("Desc");
//		taken1.setQuizName("Name");
//		taken1.setQuizTime(20);
//		taken1.setMaxScore(200);
//		taken1.setRandom(false);
//		taken1.setOnePage(false);
//		taken1.setImmediatelyCorrected(true);
//		taken1.setHasPracticeMode(true);
//		taken1.setCategory("phys");
//		
//		quizDAO.updateQuiz(taken1);
//		
//		Quiz q2 = new Quiz("vaja", "Name2", "desc2");
//				q2.setDateCreated(20);
//		q2.setDescription("Desc");
//		q2.setQuizName("Name");
//		q2.setQuizTime(20);
//		q2.setMaxScore(200);
//		q2.setRandom(false);
//		q2.setOnePage(false);
//		q2.setImmediatelyCorrected(true);
//		q2.setHasPracticeMode(true);
//		q2.setCategory("phys");
//		quizDAO.addQuiz(q2);
//		
//
//		Quiz q3 = new Quiz("vaja", "Name2", "desc2");
//		q3.setDateCreated(10);
//		q3.setDescription("Desc");
//		q3.setQuizName("Name");
//		q3.setQuizTime(20);
//		q3.setMaxScore(200);
//		q3.setRandom(false);
//		q3.setOnePage(false);
//		q3.setImmediatelyCorrected(true);
//		q3.setHasPracticeMode(true);
//		q3.setCategory("phys");
//		quizDAO.addQuiz(q3);
//
//		
//		Quiz taken2 = quizDAO.getQuiz(lastId);		
//		//System.out.println("Updat and taken out: " + taken2.toString());
//		
//		//System.out.println(quizDAO.getCreatedQuizzes("vaja").toString());
////		System.out.println(quizDAO.getSeatchedQuizzes(100, "esc").toString());
//		
////		System.out.println(quizDAO.getRecentQuizzes(4));
//	} 
}