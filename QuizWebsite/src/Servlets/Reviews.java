package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Review;
import dao.MessageDAO;
import dao.QuizDAO;
import dao.UserDAO;

/**
 * Servlet implementation class Reviews
 */
@WebServlet("/Reviews")
public class Reviews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reviews() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QuizDAO qD = (QuizDAO) request.getServletContext().getAttribute("quizDAO");
		Integer quizId = Integer.parseInt(request.getParameter("quizId"));
		RequestDispatcher rd = request.getRequestDispatcher("Reviews.jsp?quizId=" + quizId);
		System.out.println(quizId);
		if (quizId != null){
			List<Review> re = qD.getReviews(quizId);
			request.setAttribute("Quiz", qD.getQuiz(quizId));
			request.setAttribute("List", re);
			request.setAttribute("Average", qD.getAverageRating(quizId));
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
