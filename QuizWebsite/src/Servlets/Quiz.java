package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quizId = Integer.parseInt(request.getParameter("id"));
		QuizDAO quizDAO = (QuizDAO)request.getServletContext().getAttribute("quizDAO");
		classes.Quiz quiz = quizDAO.getQuiz(quizId);
		// ArrayList<Questions> questions = quizDAO.getQuestions(quizId);
		// 	request.setAttribute("Questions", questions);
		System.out.println("Taken out quiz: " + quiz.toString());
		request.setAttribute("Quiz", quiz);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("Quiz.jsp");
		requestDispatcher.forward(request, response);	
	}
}
