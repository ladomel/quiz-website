package dao;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import classes.question.QuestionMC;
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

}
