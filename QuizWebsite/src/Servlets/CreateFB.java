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

import classes.question.QuestionFB;
import classes.question.Abstract.Question;

/**
 * Servlet implementation class CreateFB
 */
@WebServlet("/CreateFB")
public class CreateFB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateFB() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * Reads one FBQuestion information and adds it in createdQuestions list in session.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String problem = request.getParameter("statement");
		List<Set<String>> answers = new ArrayList<Set<String>>();

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
		
		QuestionFB questionFB = new QuestionFB(problem, 1, answers);
		ArrayList<Question> createdQuestions = (ArrayList<Question>)request.getSession().getAttribute("createdQuestions");
		createdQuestions.add(questionFB);
	}
}
