package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.mysql.fabric.xmlrpc.base.Array;

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
		//System.out.println(questionDAO.getQuestions(quizId).toString());
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
	
	@Test
	public void testMA() 
	{
		QuestionMA question = classFactory.getQuestionMA("problem1", 1, true, getSetList1(), 2);
		QuestionMA question2 = classFactory.getQuestionMA("problem2", 2, false, getSetList2(), 3);
		System.out.println(question.toString());
		
		int quizId = getNewQuizId();
		questionDAO.addMA(quizId, question);
		questionDAO.addMA(quizId, question2);
		System.out.println(questionDAO.getQuestions(quizId).toString());
	}
	
	@Test 
	public void testFB()
	{
		QuestionFB question = classFactory.getQuestionFB("problem1", 1, getSetList1());
		QuestionFB question2 = classFactory.getQuestionFB("problem2", 2, getSetList2());
		System.out.println(question.toString());
		
		int quizId = getNewQuizId();
		questionDAO.addFB(quizId, question);
		questionDAO.addFB(quizId, question2);
		System.out.println(questionDAO.getQuestions(quizId).toString());
		System.out.println(questionDAO.getQuestions(quizId).get(0).getCorrectAnswers().size());
	}
	
	private ArrayList<Set<String>> getSetList1()
	{
		Set<String> answers = new HashSet<String>(Arrays.asList("z", "a", "d"));
		Set<String> answers2 = new HashSet<String>(Arrays.asList("c", "a", "f"));
		return new ArrayList<Set<String>>(Arrays.asList(answers, answers2));
		
	}
	
	private ArrayList<Set<String>> getSetList2()
	{
		Set<String> answers = new HashSet<String>(Arrays.asList("l", "a", "g"));
		Set<String> answers2 = new HashSet<String>(Arrays.asList("c", "d", "h"));
		return new ArrayList<Set<String>>(Arrays.asList(answers, answers2));
		
	}
	
	@Test
	public void testMCMA() 
	{
		QuestionMCMA question = classFactory.getQuestionMCMA("problem1", 1, getList1(), getList2());
		QuestionMCMA question2 = classFactory.getQuestionMCMA("problem2", 2, getList3(), getList4());
		System.out.println(question.toString());
		
		int quizId = getNewQuizId();
		questionDAO.addMCMA(quizId, question);
		questionDAO.addMCMA(quizId, question2);
		System.out.println(questionDAO.getQuestions(quizId).toString());
	}
	
	private List<String> getList1()
	{
		return new ArrayList<String>(Arrays.asList("c", "d", "h"));
	}
	
	private List<String> getList2()
	{
		return new ArrayList<String>(Arrays.asList("a", "b", "c"));
	}	
	
	private List<String> getList3()
	{
		return new ArrayList<String>(Arrays.asList("y", "x", "z"));
	}
	
	private List<String> getList4()
	{
		return new ArrayList<String>(Arrays.asList("l", "a", "m"));
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
