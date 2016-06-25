package Tests.Classes.Question;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import classes.question.QuestionFB;
import classes.question.QuestionQR;

public class QuestionFBTest {
	private List<Set<String>> answers;
	private String description;
	private int grade;
	private List<String> userAnswer;
	private QuestionFB question;
	
	@Before
	public void init()
	{
		userAnswer = new ArrayList<String>(); 	
		answers = new ArrayList<Set<String>>();
		
		answers.add(new HashSet<String>(Arrays.asList("a", "b")));
		answers.add(new HashSet<String>(Arrays.asList("c", "b", "e")));
		answers.add(new HashSet<String>(Arrays.asList("d")));
		
		description = "Problem";
		grade = 5;
		
		question = new QuestionFB(description, grade, answers);
	}
	
	@Test
	public void testCorrectAnswers() {
		assertEquals((int)question.getGrade(question.getCorrectAnswers()), (int)question.getMaxGrade());
	}

	@Test
	public void testBasic() {
		assertEquals(3, question.getCorrectAnswers().size());
		for(int i = 0; i < 3; i++)
		assertTrue(answers.get(i).contains(question.getCorrectAnswers().get(i))); // Correct answers is in the answers Set.
		assertEquals(grade, question.getGrade());	
		assertEquals(grade*answers.size(), question.getMaxGrade());
		assertEquals(description, question.getProblem());
		assertTrue(question.getAnswers().equals(answers));
	}
	
	
	// Checking user's wrong answer.
	@Test
	public void testGetGrade1() { 
		userAnswer.add("S");	
		userAnswer.add("a");
		userAnswer.add("d");
		assertEquals(grade, (int)question.getGrade(userAnswer));
	}
	
	@Test
	public void testGetGrade2() { 
		userAnswer.add("a");	
		userAnswer.add("b");
		userAnswer.add("a");
		assertEquals(2*grade, (int)question.getGrade(userAnswer));
	}

	@Test
	public void testGetGrade3() { 
		userAnswer.add("c");	
		userAnswer.add("d");
		userAnswer.add("a");
		assertEquals(0*grade, (int)question.getGrade(userAnswer));
	}
	
	@Test
	public void testGetGrade4() { 
		userAnswer.add("b");	
		userAnswer.add("b");
		userAnswer.add("d");
		assertEquals(3*grade, (int)question.getGrade(userAnswer));
	}
	
	// Null answers.
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest1() { 	
		answers = null;
		question = new QuestionFB(description, grade, answers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest2() { 	
		answers.add(null);
		question = new QuestionFB(description, grade, answers);
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest3() { 	
		answers.get(2).add(null);
		question = new QuestionFB(description, grade, answers);
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest4() { 	
		answers.get(2).clear();
		question = new QuestionFB(description, grade, answers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest5() { 	
		answers.clear();
		question = new QuestionFB(description, grade, answers);
	}
	
	// null userAnswers.
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest7() {
		userAnswer = null;
		question.getGrade(userAnswer);
	}

	// empty userAnswers.
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest8() {
		userAnswer.clear();
		question.getGrade(userAnswer);
	}

	// userAnswers with null.
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest9() {
		userAnswer.add(null);
		question.getGrade(userAnswer);
	}

	// userAnswers with different length.
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest10() {
		userAnswer.add("a");
		question.getGrade(userAnswer);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest11() {
		userAnswer.add("a");
		userAnswer.add("a");
		userAnswer.add("a");
		userAnswer.add("a");
		question.getGrade(userAnswer);
	}
}
