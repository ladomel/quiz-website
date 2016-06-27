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
	
	@Test
	public void testEquals() {
		Quiz quiz1 = classFactory.getQuiz("u", "v", "w");
		quiz1.setDateCreated(6);
		quiz1.setId(2);
		quiz1.setHasPracticeMode(true);
		quiz1.setDescription("a");
		Quiz quiz2 = classFactory.getQuiz("u", "v", "w");
		quiz2.setDateCreated(6);
		quiz2.setId(2);
		quiz2.setHasPracticeMode(true);
		quiz2.setDescription("a");
		assertTrue(quiz1.equals(quiz2));
		quiz2.setId(1);
		assertFalse(quiz1.equals(quiz2));
	}

	@Test
	public void test() {
		Quiz quiz = new Quiz("vaja", "Future Vulture", "quiz about 2034");
		quiz.setHasPracticeMode(true);
		quiz.setImmediatelyCorrected(false);
		quiz.setMaxScore(25);
		quiz.setQuizTime(120);
		quiz.setRandom(false);
		quiz.setDateCreated(4);
		quiz.setId(1);
		assertTrue(quizDAO.updateQuiz(quiz) == null);
		assertTrue(quizDAO.getQuiz(1) == null);
		
		int lastId = quizDAO.addQuiz(quiz);
		quiz.setId(lastId);
		assertTrue(quiz.equals(quizDAO.getQuiz(lastId)));
		
		assertEquals(lastId + 1, quizDAO.addQuiz(quiz));
		
		assertTrue(quiz.equals(quizDAO.deleteQuiz(lastId)));
		
		assertTrue(quizDAO.getQuiz(lastId) == null);
		
//		List<Quiz> list = quizDAO.getRecentQuizzes(1);
//		for(Quiz q : list) System.out.println(q + "\n");
		
//		List<Quiz> list = quizDAO.getCreatedQuizzes("apa");
//		for(Quiz q : list) System.out.println(q + "\n");
		
	}

}