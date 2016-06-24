package Tests.Classes.Question;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import classes.question.QuestionQR;
import factory.ClassFactory;

public class QuestionQRTest {

	private HashSet<String> answers;
	private String description;
	private int grade;
	private List<String> userAnswer;
	private QuestionQR question;
	private ClassFactory factory;
	
	@Before
	public void init()
	{
		factory = new ClassFactory();
		description = "Problem";
		grade = 5;
		
		answers = new HashSet<String>();
		answers.add("a");
		answers.add("b");
		
		question = factory.getQuestionQR(description , grade, answers);
		userAnswer = new ArrayList<String>(); 	
	}
	
	@Test
	public void testBasic() {
		assertEquals(1, question.getCorrectAnswers().size());
		assertTrue(answers.contains(question.getCorrectAnswers().get(0))); // Correct answers is in the answers Set.
		assertEquals(grade, question.getGrade());
		assertEquals(description, question.getProblem());
		assertTrue(question.getAnswers().equals(answers));
	}
	
	@Test
	public void test2() { // Checking user's wrong answer.
		assertEquals(0, (int)question.getGrade(userAnswer));
		userAnswer.add("S");
		assertEquals(0, (int)question.getGrade(userAnswer));
		userAnswer.add("a");
		assertEquals(0, (int)question.getGrade(userAnswer));
	}
	
	@Test
	public void test3() { 	// Checking user's right answer.
		List<String> userAnswer = new ArrayList<String>();
		userAnswer.add("a");
		assertEquals(grade, (int)question.getGrade(userAnswer));
		userAnswer.add(0, "b");
		assertEquals(grade, (int)question.getGrade(userAnswer));
	}
}
