package Tests.Classes.Question;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import classes.question.QuestionMC;
import classes.question.QuestionMCMA;

public class QuestionMCMATest {

	private List<String> incorrectAnswers;
	private List<String> correctAnswers;
	private String description;
	private int grade;
	private List<String> userAnswer;
	private QuestionMCMA question;
	
	@Before
	public void init()
	{
		userAnswer = new ArrayList<String>(); 	
		correctAnswers = new ArrayList<String>();
		
		correctAnswers.add("b");
		correctAnswers.add("c");
		correctAnswers.add("d");
		
		incorrectAnswers = new ArrayList<String>();
		incorrectAnswers.add("e");
		incorrectAnswers.add("f");
		
		description = "Problem";
		grade = 5;
		
		question = new QuestionMCMA(description, grade, correctAnswers, incorrectAnswers);
	}

	@Test
	public void testCorrectAnswers() {
		assertEquals((int)question.getGrade(question.getCorrectAnswers()), (int)question.getMaxGrade());
	}
	
	
	@Test
	public void testBasic() {
		assertEquals(grade, question.getGrade());	
		assertEquals(grade*(correctAnswers.size() + incorrectAnswers.size()), question.getMaxGrade());
		assertEquals(description, question.getProblem());
		assertTrue(question.getIncorrectAnswers().equals(incorrectAnswers));
		assertTrue(question.getCorrectAnswers().equals(correctAnswers)); 
	}
	
	@Test
	public void testGetGrade1() { 
		userAnswer.add("a");	
		assertEquals(2*grade, (int)question.getGrade(userAnswer));
	}
	
	@Test
	public void testGetGrade2() { 
		userAnswer.add("e");	
		userAnswer.add("f");	
		assertEquals(0*grade, (int)question.getGrade(userAnswer));
	}
	
	@Test
	public void testGetGrade3() { 
		userAnswer.add("a");	
		userAnswer.add("f");	
		userAnswer.add("c");	
		userAnswer.add("d");	
		assertEquals(3*grade, (int)question.getGrade(userAnswer));
	}
	
	@Test
	public void testGetGrade4() { 
		userAnswer.add("a");	
		userAnswer.add("b");	
		userAnswer.add("c");	
		userAnswer.add("d");	
		assertEquals(question.getMaxGrade(), (int)question.getGrade(userAnswer));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest1() { 	
		correctAnswers = null;
		new QuestionMCMA(description, grade, correctAnswers, incorrectAnswers);
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest2() { 	
		correctAnswers.add(null);
		new QuestionMCMA(description, grade, correctAnswers, incorrectAnswers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest3() { 	
		incorrectAnswers.add(null);
		new QuestionMCMA(description, grade, correctAnswers, incorrectAnswers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest4() { 	
		incorrectAnswers = null;
		new QuestionMCMA(description, grade, correctAnswers, incorrectAnswers);
	}
	
	
	
}
