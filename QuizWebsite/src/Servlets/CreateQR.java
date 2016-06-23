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

import classes.problem.Abstract.Question;
import classes.problem.QR.QuestionQR;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String statement = request.getParameter("statement");
		int answersSize = Integer.parseInt(request.getParameter("answersSize"));
		HashSet<String> answers = new HashSet<String>();
		
		for(int i = 0; i < answersSize; i++)
			answers.add(request.getParameter("answer" + i));
		
		QuestionQR problem = new QuestionQR(statement, answers);
		
		HttpSession session = request.getSession();
		ArrayList<Question> problems = (ArrayList)session.getAttribute("ProblemList");
		problems.add(problem);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("problemSubmitted.jsp");	
		dispatcher.forward(request, response);	
	}
}




