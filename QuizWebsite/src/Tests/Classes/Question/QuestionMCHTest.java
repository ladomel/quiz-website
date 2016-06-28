package Tests.Classes.Question;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import classes.question.QuestionMCH;


public class QuestionMCHTest {

	private List<String> questions;
	private List<String> rightAnswers;
	private List<String> wrongAnswers;

	private List<String> userAnswer;
	private String description;
	private int grade;
	private QuestionMCH question;
	
	@Before
	public void init()
	{
		questions = new ArrayList<String>(); 	
		rightAnswers = new ArrayList<String>(); 	
		wrongAnswers = new ArrayList<String>(); 	
		userAnswer = new ArrayList<String>(); 	
		
		questions.add("a");
		questions.add("b");
		questions.add("c");
		
		rightAnswers.add("aa");
		rightAnswers.add("bb");
		rightAnswers.add("cc");
		
		wrongAnswers.add("x");
		wrongAnswers.add("z");
		
		description = "Problem";
		grade = 5;
		
		question = new QuestionMCH(description, grade, questions, rightAnswers, wrongAnswers);
		System.out.println(question.toString());
	}

	@Test
	public void testCorrectAnswers() {
		assertEquals((int)question.getGrade(question.getCorrectAnswers()), (int)question.getMaxGrade());
	}

	@Test
	public void testBasic() {
		assertEquals(grade, question.getGrade());	
		assertEquals(grade*rightAnswers.size(), question.getMaxGrade());
		assertEquals(description, question.getProblem());
		assertTrue(question.getWrongAnswers().equals(wrongAnswers));
		assertTrue(question.getRightAnswers().equals(rightAnswers));
		assertTrue(question.getQuestions().equals(questions));
	}
	
	@Test
	public void testGetGrade1() { 
		userAnswer.add("z");	
		assertEquals(0, (int)question.getGrade(userAnswer));
	}
	
	@Test
	public void testGetGrade2() { 
		userAnswer.add("aa");	
		assertEquals(grade, (int)question.getGrade(userAnswer));
	}
	
	@Test
	public void testGetGrade3() { 
		userAnswer.add("aa");	
		userAnswer.add("cc");	
		userAnswer.add("bb");	
		assertEquals(grade, (int)question.getGrade(userAnswer));
	}
	
	@Test
	public void testGetGrade4() { 
		userAnswer.add("aa");	
		userAnswer.add("bb");	
		userAnswer.add("cc");	
		assertEquals(3*grade, (int)question.getGrade(userAnswer));
	}
	
	@Test
	public void testGetGrade5() { 
		userAnswer.add("x");	
		userAnswer.add("x");	
		userAnswer.add("x");	
		assertEquals(0*grade, (int)question.getGrade(userAnswer));
	}
	
	@Test
	public void testGetGrade6() { 
		userAnswer.add("a");	
		assertEquals(0, (int)question.getGrade(userAnswer));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest1() { 	
		questions = null;
		new QuestionMCH(description, grade, questions, rightAnswers, wrongAnswers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest2() { 	
		questions.add(null);
		new QuestionMCH(description, grade, questions, rightAnswers, wrongAnswers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest3() { 	
		questions.clear();
		new QuestionMCH(description, grade, questions, rightAnswers, wrongAnswers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest4() { 	
		rightAnswers = null;
		new QuestionMCH(description, grade, questions, rightAnswers, wrongAnswers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest5() { 	
		rightAnswers.add(null);
		new QuestionMCH(description, grade, questions, rightAnswers, wrongAnswers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest6() { 	
		rightAnswers.clear();
		new QuestionMCH(description, grade, questions, rightAnswers, wrongAnswers);
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest7() { 	
		wrongAnswers = null;
		new QuestionMCH(description, grade, questions, rightAnswers, wrongAnswers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest8() { 	
		wrongAnswers.add(null);
		new QuestionMCH(description, grade, questions, rightAnswers, wrongAnswers);
	}
	
	@Test(expected=IllegalArgumentException.class) // Different size of questions and rightAnswers
	public void illegalArgumentTest9() { 	
		rightAnswers.add("A");
		new QuestionMCH(description, grade, questions, rightAnswers, wrongAnswers);
	}
}
