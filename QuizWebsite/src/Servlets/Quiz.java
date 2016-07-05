package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Constants;
import dao.QuestionDAO;
import dao.QuizDAO;

/**
 * Servlet implementation class Quiz
 */
@WebServlet("/Quiz")
public class Quiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Quiz() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String quizName = request.getParameter("quizname");
		
		QuizDAO quizDAO = (QuizDAO)request.getServletContext().getAttribute("quizDAO");
		
		if (id != null) {
			int quizId = Integer.parseInt(id);
			classes.Quiz quiz = quizDAO.getQuiz(quizId);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("Quiz.jsp");
			if (quiz == null) requestDispatcher = request.getRequestDispatcher("notFound.jsp");
			request.setAttribute("Quiz", quiz);
			
			requestDispatcher.forward(request, response);	
		} else {
			List<classes.Quiz> quizzes = quizDAO.getSeatchedQuizzes(Constants.MAX_DISPLAY, quizName);	
			System.out.println(quizName);
			System.out.println(quizzes.size());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("quizSearchResults.jsp");
			request.setAttribute("Quizzes", quizzes);
		
			requestDispatcher.forward(request, response);	
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
