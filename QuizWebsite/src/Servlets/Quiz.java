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
import classes.Result;
import classes.User;
import dao.QuestionDAO;
import dao.QuizDAO;
import dao.ResultDAO;

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
		ResultDAO resultDAO = (ResultDAO) request.getServletContext().getAttribute("resultDAO");
		
		if (id != null) {
			int quizId = Integer.parseInt(id);
			classes.Quiz quiz = quizDAO.getQuiz(quizId);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("Quiz.jsp");
			if (quiz == null) requestDispatcher = request.getRequestDispatcher("notFound.jsp");
			request.setAttribute("Quiz", quiz);
			
			List<Result> res =resultDAO.getRecentResults(quizId, Constants.MAX_DISPLAY);
			request.setAttribute("RecentTakersQuizzes", res);
			List<String> users = new ArrayList<String>();
			for (Result r : res){
				users.add(r.getUserName());
			}
			request.setAttribute("RecentTakersUsers", users);
			
			users = new ArrayList<String>();
			res = resultDAO.getBestResults(quizId, Constants.MAX_DISPLAY, Constants.ALLTIME_IN_MS);
			request.setAttribute("BestResults", res);
			for (Result r : res){
				users.add(r.getUserName());
			}
			request.setAttribute("BestResultsUsers", users);
			
			User master = (User) request.getSession().getAttribute("MasterUser");
			if (master!=null){
			if (request.getParameter("result") == null) {
				request.setAttribute("PastResults", resultDAO.getResult(master.getUserName(), quizId));
			} else if (request.getParameter("result").equals("score")) {
				// sort score
			} else {
				// sort time
			}}
			
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
