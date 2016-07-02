package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.question.QuestionMA;
import classes.question.Abstract.Question;

/**
 * Servlet implementation class CreateMA
 */
@WebServlet("/CreateMA")
public class CreateMA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateMA() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * Reads one MAQuestion information and adds it in createdQuestions list in session.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String problem = request.getParameter("statement");
		int grade = Integer.parseInt(request.getParameter("grade"));
		int numAnswers = Integer.parseInt(request.getParameter("answernum"));
		boolean ordered = (null != request.getParameter("order"));
		List<Set<String>> answers = new ArrayList<Set<String>>();
		System.out.println(ordered);
		
		String delimeter = "/";
		String nextAnswer;
		for(int i = 0; request.getParameter("answer" + i + delimeter + 0) != null; i++)
		{
			nextAnswer = "";
			Set<String> possibleAnswersSet= new HashSet<String>();
			for(int j = 0; ;j++)
			{
				nextAnswer = request.getParameter("answer" + i + delimeter + j);
				if(nextAnswer == null) break;
				possibleAnswersSet.add(nextAnswer);
			}
			answers.add(possibleAnswersSet);
		}
		
		QuestionMA questionMA = new QuestionMA(problem, grade, ordered, answers, numAnswers); 
		ArrayList<Question> createdQuestions = (ArrayList<Question>)request.getSession().getAttribute("createdQuestions");
		createdQuestions.add(questionMA);
	}
}
