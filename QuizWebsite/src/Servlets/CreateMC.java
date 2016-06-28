package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.question.QuestionMC;
import classes.question.Abstract.Question;

/**
 * Servlet implementation class CreateMC
 */
@WebServlet("/CreateMC")
public class CreateMC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateMC() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * Reads one MCQuestion information and adds it in createdQuestions list in session.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Set<String> answers = new HashSet<String>();
		String problem = request.getParameter("statement");
		String correctAnswer = (request.getParameter("correctanswer"));
		
		String nextAnswer;
		for(int i = 0; ;i++)
		{
			nextAnswer = request.getParameter("answer" + i);
			if( nextAnswer == null) break;
			answers.add(nextAnswer);
		}
		
		QuestionMC questionMC = new QuestionMC(problem, 1, correctAnswer, answers);
		ArrayList<Question> createdQuestions = (ArrayList<Question>)request.getSession().getAttribute("createdQuestions");
		createdQuestions.add(questionMC);
	}
}
