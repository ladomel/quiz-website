package dao;

import static org.junit.Assert.*;

import org.apache.tomcat.jni.Time;
import org.junit.BeforeClass;
import org.junit.Test;

import classes.Quiz;
import classes.User;
import factory.ClassFactory;


public class QuizDAOImplTest {

	private static final String QUIZ_AUTHOR = "quiz author";
	
	private static DAOInstances factory;
	private static QuizDAO quizDAO;
	private static UserDAO userDAO;
	private static ClassFactory classFactory;
	
	private static User quizAuthor;

	@BeforeClass
	public static void setUp() throws Exception {
		factory = new DAOInstances();
		factory.init();
		quizDAO = factory.getQuizDAO();
		userDAO = factory.getUserDAO();
		classFactory = new ClassFactory();
		
		quizAuthor = classFactory.getUser(QUIZ_AUTHOR, "1234", "salt123");
		userDAO.deleteUser(QUIZ_AUTHOR);
		userDAO.addUser(QUIZ_AUTHOR, "1234", "124");
	}
	
	@Test
	public void testEquals() {
		Quiz quiz1 = createQuiz(QUIZ_AUTHOR, "girls", "quiz about girls", "woman", 0, false, 5, false, 100, false, 60);
		Quiz quiz2 = createQuiz(QUIZ_AUTHOR, "girls", "quiz about girls", "woman", 0, false, 5, false, 100, false, 60);
		
		assertTrue(quiz1.equals(quiz2));
		quiz2.setId(1);
		assertFalse(quiz1.equals(quiz2));
	}
	
	private Quiz createQuiz(String author, String qName, String desc, String category, long dateCreated, boolean practice, int id, boolean immed, int max, boolean onep, int time) {
		Quiz quiz = classFactory.getQuiz(author, qName, desc);
		quiz.setCategory(category);
		quiz.setDateCreated(dateCreated);
		quiz.setHasPracticeMode(practice);
		quiz.setId(id);
		quiz.setImmediatelyCorrected(immed);
		quiz.setMaxScore(max);
		quiz.setOnePage(onep);
		quiz.setQuizTime(time);
		return quiz;
	}

	@Test
	public void test() {
		Quiz quiz = createQuiz(QUIZ_AUTHOR, "a", "adesc", "apl", 1995, false, 2, false, 500, true, 120);
		
		assertTrue(quizDAO.updateQuiz(quiz) == null);

		int lastId = quizDAO.addQuiz(quiz);
		quiz.setId(lastId);
		assertTrue(quiz.equals(quizDAO.getQuiz(lastId)));		
		
		assertEquals(lastId + 1, quizDAO.addQuiz(quiz));
		
		assertTrue(quiz.equals(quizDAO.deleteQuiz(lastId)));
		assertTrue(quizDAO.getQuiz(lastId) == null);
		
		Quiz quiz1 = quizDAO.getQuiz(lastId + 1);
		quiz1.setHasPracticeMode(true);
		assertFalse(quizDAO.updateQuiz(quiz1).hasPracticeMode());
		
	}
	
	@Test
	public void testRecent() {
	
		for(int date = 1; date < 8; date++) { 
			Quiz quiz = createQuiz(QUIZ_AUTHOR, "rec test", "testing recents", "rec", date, false, 0, false, 0, false, 0);
			quizDAO.addQuiz(quiz);
		}
		
		// can not assert anything just observe
		System.out.println(quizDAO.getRecentQuizzes(4));
		
	}
	
	@Test
	public void testCreated() {
		Quiz quiz = createQuiz(QUIZ_AUTHOR, "a", "a", "a", System.currentTimeMillis(), false, 0, false, 0, false, 0);
		Quiz quiz1 = createQuiz(QUIZ_AUTHOR, "a", "a", "a", 							1, false, 0, false, 0, false, 0);
		quizDAO.addQuiz(quiz);
		quizDAO.addQuiz(quiz1);
		System.out.println(QUIZ_AUTHOR + " " + quizDAO.getCreatedQuizzes(QUIZ_AUTHOR));
		System.out.println(QUIZ_AUTHOR + "something" + " " + quizDAO.getCreatedQuizzes(QUIZ_AUTHOR + "something"));
	}

	// search works from server testing
	
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