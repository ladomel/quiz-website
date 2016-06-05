package problem.QR;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;

import classes.Answer;

public class ProblemQRTest {

	@Test
	public void test() {
		HashSet<String> answers = new HashSet<String>();
		answers.add("A");
		answers.add("B");
		String problem = "description";
		
		ProblemQR test = new ProblemQR(problem, answers);
		
		assertTrue(test.getAnswers().equals(answers)); // Testing if information is written without mistakes.
		assertTrue(test.getStatement().getDescription().equals(problem));
		
		Answer answer1 = new Answer(0, new ArrayList<String>(){{  add("A"); }});
		Answer answer2 = new Answer(0, new ArrayList<String>(){{  add("B"); }});
		Answer answer3 = new Answer(0, new ArrayList<String>(){{  add("C"); }});
		
		test.setGrade(answer1);
		test.setGrade(answer2);
		test.setGrade(answer3);
		
		assertTrue(answer1.getGrade().equals(1));
		assertTrue(answer2.getGrade().equals(1));
		assertTrue(answer3.getGrade().equals(0));
	}


}
