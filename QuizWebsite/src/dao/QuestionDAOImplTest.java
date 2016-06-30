package dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

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
	private ClassFactory classFactory;

	@Before
	public void setUp() throws Exception {
		factory = new DAOInstances();
		factory.init();
		questionDAO = factory.getQuestionDAO();
		classFactory = new ClassFactory();
	}

	@Test
	public void test() {
//		Set<String> qrAns = new HashSet<String> ();
//		qrAns.add("qra1");
//		qrAns.add("qra2");
//		QuestionQR qr = classFactory.getQuestionQR("qr1", 4, qrAns);
//		Set<String> qrAns2 = new HashSet<String> ();
//		qrAns2.add("qra12");
//		qrAns2.add("qra22");
//		QuestionQR qr2 = classFactory.getQuestionQR("qr2", 5, qrAns2);
//		
//		Set<String> prAns = new HashSet<String> ();
//		prAns.add("pra1");
//		prAns.add("pra2");
//		QuestionPR pr = classFactory.getQuestionPR("pr1", 2, "pic123", prAns);
//		
//		questionDAO.addQR(1, qr);
//		questionDAO.addQR(1, qr2);
//		questionDAO.addPR(1, pr);
//		
//		/////////
//		
//		HashSet<String> a1 = new HashSet<String> ();
//		a1.add("a11");
//		a1.add("a12");
//		a1.add("a13");
//		HashSet<String> b1 = new HashSet<String> ();
//		b1.add("b11");
//		b1.add("b12");
//		b1.add("b13");
//		List<Set<String>> c1 = new ArrayList<Set<String>>();
//		c1.add(a1);
//		c1.add(b1);
//		QuestionFB fb1 = classFactory.getQuestionFB("fb1", 3, c1);
//		questionDAO.addFB(1, fb1);
//		
//		HashSet<String> a2 = new HashSet<String> ();
//		a2.add("a21");
//		a2.add("a22");
//		a2.add("a23");
//		HashSet<String> b2 = new HashSet<String> ();
//		b2.add("b21");
//		b2.add("b22");
//		b2.add("b23");
//		List<Set<String>> c2 = new ArrayList<Set<String>>();
//		c2.add(a2);
//		c2.add(b2);
//		QuestionMA fb2 = classFactory.getQuestionMA("ma1", 2, true, c2, 6);
//		questionDAO.addMA(1, fb2);
//		
//		List<String> mcmaAnsC = new ArrayList<String> ();
//		mcmaAnsC.add("mcma1");
//		mcmaAnsC.add("mcma2");
//		List<String> mcmaAnsW = new ArrayList<String> ();
//		mcmaAnsW.add("mcmaw12");
//		mcmaAnsW.add("mcmaw22");
//		QuestionMCMA mcma = classFactory.getQuestionMCMA("mcma1", 2, mcmaAnsC, mcmaAnsW);
//		questionDAO.addMCMA(1, mcma);
//		
//		Set<String> mcWrongs = new HashSet<String> ();
//		mcWrongs.add("mcWrongs1");
//		mcWrongs.add("mcWrongs2");
//		QuestionMC mc = classFactory.getQuestionMC("mcProb", 8, "mcCorrect", mcWrongs);
//		questionDAO.addMC(1, mc);
		
		List<Question> questions = questionDAO.getQuestions(1);
		System.out.println(questions);
	}

}
