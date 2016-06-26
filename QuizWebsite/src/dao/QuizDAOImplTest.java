package dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class QuizDAOImplTest {

	private static DAOInstances factory;
	private static QuizDAO quizDAO;

	@BeforeClass
	public static void setUp() throws Exception {
		factory = new DAOInstances();
		factory.init();
		quizDAO = factory.getQuizDAO();
	}

	@Test
	public void test() {
		assertTrue(quizDAO.getQuiz(1).getQuizName().equals("birds"));
	}

}
