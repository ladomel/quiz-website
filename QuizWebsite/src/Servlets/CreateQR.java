package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.question.QuestionQR;
import classes.question.Abstract.Question;

/**
 * Servlet implementation class CreateQR
 */
@WebServlet("/CreateQR")
public class CreateQR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQR() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String problem = request.getParameter("problem");
		HashSet<String> answers = new HashSet<String>();
		
		String nextAnswer = "";
		for(int i = 0; nextAnswer != null; i++)
		{
			nextAnswer = request.getParameter("answer" + i);
			answers.add(nextAnswer);
		}
		
		QuestionQR questionQR = new QuestionQR(problem, answers);
		
		HttpSession session = request.getSession();
		ArrayList<Question> questions = (ArrayList<Question>)session.getAttribute("QuestionList");
		questions.add(0, questionQR);
	}
}




