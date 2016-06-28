package dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import classes.Result;
import classes.Answer;
import factory.ClassFactory;

public class ResultDAOImplTest {

	static ResultDAO resultDAO;
	static ClassFactory classFactory;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DAOInstances daoFactory = new DAOInstances();
		daoFactory.init();
		resultDAO = daoFactory.getResultDAO();
		classFactory = new ClassFactory();
	}

	@Test
	public void test() {
		Result result = classFactory.getResult("vaja", 1);
		result.setFinalGrade(98);
		result.setTimeStarted(2005);
		result.setTimeTaken(5);
		List<Answer> answers = new ArrayList<Answer> ();
		Answer answer1 = classFactory.getAnswer(Arrays.asList("drogba", "gadavida", "chinetshi"));
		
		Answer answer2 = classFactory.getAnswer(Arrays.asList("messi", "aris", "sauketeso"));
		answers.add(answer1);
		answers.add(answer2);
		result.setAnswers(answers);
		resultDAO.insertResult(result);
	}

}
