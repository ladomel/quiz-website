package Tests.Classes.Question;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import classes.question.QuestionTF;

public class QuestionTFTest {
	private List<String> propositions;
	private List<String> answers;
	int grade;
	private String description;
	private List<String> userAnswer;
	private QuestionTF question;
	
	@Before
	public void init()
	{
		userAnswer = new ArrayList<String>(); 	
		answers = new ArrayList<String>();
		propositions = new ArrayList<String>();
		
		description = "Problem";
		grade = 5;
		
		propositions.add("a");
		propositions.add("b");
		propositions.add("c");
		
		answers.add("True");
		answers.add("False");
		answers.add("True");
		
		question = new QuestionTF(description, grade, propositions, answers);
		System.out.println(question.toString());
	}

	@Test
	public void testCorrectAnswers() {
		assertEquals((int)question.getGrade(question.getCorrectAnswers()), (int)question.getMaxGrade());
	}
	
	@Test
	public void testBasic() {
		assertTrue(answers.contains(question.getCorrectAnswers().get(0))); // Correct answers is in the answers Set.
		assertEquals(grade, question.getGrade());	
		assertEquals(grade*answers.size(), question.getMaxGrade());
		assertEquals(description, question.getProblem());
		assertTrue(question.getAnswers().equals(answers));
		assertTrue(question.getPropositions().equals(propositions));
	}
	
	@Test
	public void testGetGrade1() { 
		userAnswer.add("True");
		userAnswer.add("True");
		userAnswer.add("True");
		assertEquals(2*grade, (int)question.getGrade(userAnswer));
	}
	
	@Test
	public void testGetGrade2() { 
		userAnswer.add("True");
		userAnswer.add("False");
		userAnswer.add("True");
		assertEquals(3*grade, (int)question.getGrade(userAnswer));
	}
	
	@Test
	public void testGetGrade3() { 
		userAnswer.add("False");
		userAnswer.add("True");
		userAnswer.add("False");
		assertEquals(0*grade, (int)question.getGrade(userAnswer));
	}
	
	@Test
	public void testGetGrade4() { 
		userAnswer.add("False");
		userAnswer.add("False");
		userAnswer.add("False");
		assertEquals(1*grade, (int)question.getGrade(userAnswer));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest1() { 	
		grade = 0;
		new QuestionTF(description, grade, propositions, answers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest2() { 	
		propositions = null;
		new QuestionTF(description, grade, propositions, answers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest3() { 	
		propositions.clear();
		new QuestionTF(description, grade, propositions, answers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest4() { 	
		propositions.clear();
		new QuestionTF(description, grade, propositions, answers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest5() { 	
		propositions.add(0, null);
		new QuestionTF(description, grade, propositions, answers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest6() { 	
		answers.clear();
		new QuestionTF(description, grade, propositions, answers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest7() { 	
		answers.clear();
		new QuestionTF(description, grade, propositions, answers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest8() { 	
		answers.add(0, null);
		new QuestionTF(description, grade, propositions, answers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest9() { 	
		answers.add("True");
		new QuestionTF(description, grade, propositions, answers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest10() { 	
		userAnswer.add("False");
		userAnswer.add("False");
		userAnswer.add("False");
		userAnswer.add("False");
		question.getGrade(userAnswer);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest11() { 	
		userAnswer.add("False");
		question.getGrade(userAnswer);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest12() { 	
		question.getGrade(userAnswer);
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest13() { 
		userAnswer.add(null);
		question.getGrade(userAnswer);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void illegalArgumentTest14() { 
		userAnswer = null;
		question.getGrade(userAnswer);
	}
}
