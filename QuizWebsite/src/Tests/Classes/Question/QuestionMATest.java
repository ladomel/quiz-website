package Tests.Classes.Question;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import classes.question.QuestionMA;


public class QuestionMATest {

	private List<Set<String>> answers;
	private String description;
	private int grade;
	private boolean ordered;
	private List<String> userAnswer;
	private QuestionMA question;
	
	@Before
	public void init()
	{
		userAnswer = new ArrayList<String>(); 	
		answers = new ArrayList<Set<String>>();
		
		answers.add(new HashSet<String>(Arrays.asList("a", "b")));
		answers.add(new HashSet<String>(Arrays.asList("c", "d", "e")));
		answers.add(new HashSet<String>(Arrays.asList("f" , "g")));
		answers.add(new HashSet<String>(Arrays.asList("h")));
		
		description = "Problem";
		grade = 5;
		ordered = true;
		
		question = new QuestionMA(description, grade, ordered, answers);
	}

	@Test
	public void testCorrectAnswers() {
		assertEquals((int)question.getGrade(question.getCorrectAnswers()), (int)question.getMaxGrade());
	}
	
	@Test
	public void testCorrectAnswers2() {
		ordered = false;
		question = new QuestionMA(description, grade, ordered, answers);
		assertEquals((int)question.getGrade(question.getCorrectAnswers()), (int)question.getMaxGrade());
	}
	
	@Test
	public void testBasic() {
		assertEquals(4, question.getCorrectAnswers().size());
		for(int i = 0; i < 4; i++)
		assertTrue(answers.get(i).contains(question.getCorrectAnswers().get(i))); // Correct answers is in the answers Set.
		assertEquals(grade, question.getGrade());	
		assertEquals(ordered, question.isOrdered());
		assertEquals(grade*answers.size(), question.getMaxGrade());
		assertEquals(description, question.getProblem());
		assertTrue(question.getAnswers().equals(answers));
	}
	
	@Test
	public void testGetGrade1() { 
		userAnswer.add("b");	
		userAnswer.add("e");
		userAnswer.add("g");
		userAnswer.add("h");
		assertEquals(4*grade, (int)question.getGrade(userAnswer));
	}
	
	@Test
	public void testGetGrade2() { 
		userAnswer.add("e");	
		userAnswer.add("b");
		userAnswer.add("h");
		userAnswer.add("g");
		assertEquals(0*grade, (int)question.getGrade(userAnswer));
	}
	
	@Test
	public void testGetGrade3() { 
		userAnswer.add("b");	
		userAnswer.add("b");
		userAnswer.add("g");
		userAnswer.add("g");
		assertEquals(2*grade, (int)question.getGrade(userAnswer));
	}
	
	// Testing unordered.
	@Test
	public void testGetGrade4() { 
		ordered = false;
		question = new QuestionMA(description, grade, ordered, answers);
		userAnswer.add("h");	
		userAnswer.add("g");
		userAnswer.add("c");
		userAnswer.add("a");
		assertEquals(4*grade, (int)question.getGrade(userAnswer));
	}
	
	@Test
	public void testGetGrade5() { 
		ordered = false;
		question = new QuestionMA(description, grade, ordered, answers);
		userAnswer.add("a");	
		userAnswer.add("a");
		userAnswer.add("a");
		userAnswer.add("a");
		assertEquals(1*grade, (int)question.getGrade(userAnswer));
	}
	
	@Test
	public void testGetGrade6() { 
		ordered = false;
		question = new QuestionMA(description, grade, ordered, answers);
		userAnswer.add("a");	
		userAnswer.add("b");
		userAnswer.add("f");
		userAnswer.add("g");
		assertEquals(2*grade, (int)question.getGrade(userAnswer));
	}
	
	// Null answers.
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest1() { 	
		answers = null;
		new QuestionMA(description, grade, ordered, answers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest2() { 	
		answers.add(null);
		new QuestionMA(description, grade, ordered, answers);
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest3() { 	
		answers.get(2).add(null);
		new QuestionMA(description, grade, ordered, answers);
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest4() { 	
		answers.get(2).clear();
		new QuestionMA(description, grade, ordered, answers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest5() { 	
		answers.clear();
		new QuestionMA(description, grade, ordered, answers);
	}
	
	// null userAnswers.
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest7() {
		userAnswer = null;
		question.getGrade(userAnswer);
	}

	// userAnswers with null.
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest9() {
		userAnswer.add(null);
		question.getGrade(userAnswer);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest11() {
		userAnswer.add("a");
		userAnswer.add("a");
		userAnswer.add("a");
		userAnswer.add("a");
		userAnswer.add("a");
		question.getGrade(userAnswer);
	}	
}
