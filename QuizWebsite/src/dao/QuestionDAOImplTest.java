package dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import classes.Quiz;
import classes.question.QuestionFB;
import classes.question.QuestionMA;
import classes.question.QuestionMC;
import classes.question.QuestionMCMA;
import classes.question.QuestionPR;
import classes.question.QuestionQR;
import classes.question.Abstract.Question;
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
		Set<String> qrAns = new HashSet<String> ();
		qrAns.add("qra1");
		qrAns.add("qra2");
		QuestionQR qr = classFactory.getQuestionQR("qr1", 4, qrAns);
		Set<String> qrAns2 = new HashSet<String> ();
		qrAns2.add("qra12");
		qrAns2.add("qra22");
		QuestionQR qr2 = classFactory.getQuestionQR("qr2", 5, qrAns2);
		
		Set<String> prAns = new HashSet<String> ();
		prAns.add("pra1");
		prAns.add("pra2");
		QuestionPR pr = classFactory.getQuestionPR("pr1", 2, "pic123", prAns);
		
		questionDAO.addQR(1, qr);
		questionDAO.addQR(1, qr2);
		questionDAO.addPR(1, pr);
		
		/////////
		
		HashSet<String> a1 = new HashSet<String> ();
		a1.add("a11");
		a1.add("a12");
		a1.add("a13");
		HashSet<String> b1 = new HashSet<String> ();
		b1.add("b11");
		b1.add("b12");
		b1.add("b13");
		List<Set<String>> c1 = new ArrayList<Set<String>>();
		c1.add(a1);
		c1.add(b1);
		QuestionFB fb1 = classFactory.getQuestionFB("fb1", 3, c1);
		questionDAO.addFB(1, fb1);
		
		HashSet<String> a2 = new HashSet<String> ();
		a2.add("a21");
		a2.add("a22");
		a2.add("a23");
		HashSet<String> b2 = new HashSet<String> ();
		b2.add("b21");
		b2.add("b22");
		b2.add("b23");
		List<Set<String>> c2 = new ArrayList<Set<String>>();
		c2.add(a2);
		c2.add(b2);
		QuestionMA fb2 = classFactory.getQuestionMA("ma1", 2, true, c2, 6);
		questionDAO.addMA(1, fb2);
		
		List<String> mcmaAnsC = new ArrayList<String> ();
		mcmaAnsC.add("mcma1");
		mcmaAnsC.add("mcma2");
		List<String> mcmaAnsW = new ArrayList<String> ();
		mcmaAnsW.add("mcmaw12");
		mcmaAnsW.add("mcmaw22");
		QuestionMCMA mcma = classFactory.getQuestionMCMA("mcma1", 2, mcmaAnsC, mcmaAnsW);
		questionDAO.addMCMA(1, mcma);
		
		Set<String> mcWrongs = new HashSet<String> ();
		mcWrongs.add("mcWrongs1");
		mcWrongs.add("mcWrongs2");
		QuestionMC mc = classFactory.getQuestionMC("mcProb", 8, "mcCorrect", mcWrongs);
		questionDAO.addMC(1, mc);
		
		List<Question> questions = questionDAO.getQuestions(1);
		System.out.println(questions);
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
		quiz.setCategory("Phys");

		System.out.println("Before:              " + quiz.toString());
		return quizDAO.addQuiz(quiz);
	}
}
