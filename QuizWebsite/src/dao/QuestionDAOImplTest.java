package dao;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import classes.Quiz;
import classes.question.QuestionMC;
import classes.question.QuestionPR;
import classes.question.QuestionQR;
import factory.ClassFactory;

public class QuestionDAOImplTest {

	private DAOInstances factory;
	private QuestionDAO questionDAO;
	private QuizDAO quizDAO;
	private ClassFactory classFactory;

	@Before
	public void setUp() throws Exception {
		factory = new DAOInstances();
		factory.init();
		questionDAO = factory.getQuestionDAO();
		quizDAO = factory.getQuizDAO();
		classFactory = new ClassFactory();
	}

	@Test
	public void test() {
//		Set<String> set = new HashSet<String>();
//		set.add("bonapart");
//		set.add("neipl");
//		questionDAO.addQR(4, classFactory.getQuestionQR("didie", 2, set));
//		questionDAO.getQuestions(4);
//		System.out.println(questionDAO.getQuestions(4));
//		Set<String> set = new HashSet<String>();
//		set.add("yes");
//		set.add("bird");
//		questionDAO.addQR(4, classFactory.getQuestionQR("pic", 2, set));
//		questionDAO.getQuestions(4);
//		System.out.println(questionDAO.getQuestions(4));
//		Set<String> set = new HashSet<String>();
//		set.add("bad1");
//		set.add("bad bic");
//		QuestionMC mc = classFactory.getQuestionMC("mc1", 2, "le", set);
//		questionDAO.addMC(4, mc);
//		System.out.println(questionDAO.getQuestions(4));
	}

	@Test
	public void testQR() {
		Set<String> answers = new HashSet<String>(Arrays.asList("a", "b"));
		QuestionQR question = classFactory.getQuestionQR("problem", 1, answers);

		Set<String> answers2 = new HashSet<String>(Arrays.asList("c", "b"));
		QuestionQR question2 = classFactory.getQuestionQR("problem2", 2, answers2);
		
		int quizId = getNewQuizId();
		questionDAO.addQR(quizId, question);
		questionDAO.addQR(quizId, question2);
	//	System.out.println(questionDAO.getQuestions(quizId).toString());
	}	
	
	@Test
	public void testPR() {
		Set<String> answers = new HashSet<String>(Arrays.asList("a", "b"));
		QuestionPR question = classFactory.getQuestionPR("problem", 1, "url1", answers);

		Set<String> answers2 = new HashSet<String>(Arrays.asList("c", "b"));
		QuestionPR question2 = classFactory.getQuestionPR("problem2", 2, "url2", answers2);
		
		int quizId = getNewQuizId();
		questionDAO.addPR(quizId, question);
		questionDAO.addPR(quizId, question2);
	//	System.out.println(questionDAO.getQuestions(quizId).get(0).getClass());	
	}	
	
	@Test
	public void testMC() {
		Set<String> answers = new HashSet<String>(Arrays.asList("a", "b"));
		QuestionMC question = classFactory.getQuestionMC("problem", 1, "corr", answers);

		Set<String> answers2 = new HashSet<String>(Arrays.asList("null", "b"));
		QuestionMC question2 = classFactory.getQuestionMC("problem2", 2, "correct2", answers2);
		
		int quizId = getNewQuizId();
		questionDAO.addMC(quizId, question);
		questionDAO.addMC(quizId, question2);
		System.out.println(questionDAO.getQuestions(quizId).toString());
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
		quiz.setAverageRating(50);
		quiz.setAverageScore(70);
		quiz.setAverageTimeMillis(20);
		quiz.setNumTries(3);
		quiz.setCategory("Phys");

		System.out.println("Before:              " + quiz.toString());
		return quizDAO.addQuiz(quiz);
	}
}
