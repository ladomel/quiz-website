package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Review;
import classes.User;
import dao.MessageDAO;
import dao.QuizDAO;

/**
 * Servlet implementation class MakeReview
 */
@WebServlet("/MakeReview")
public class MakeReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quizId = Integer.parseInt(request.getParameter("quizId"));
		int rating = Integer.parseInt(request.getParameter("rating"));
		String text = request.getParameter("text");
		
		Review r = new Review(text, System.currentTimeMillis(), 
				((User)request.getSession().getAttribute("MasterUser")).getUserName(), quizId, rating);
		
		QuizDAO mD = (QuizDAO) request.getServletContext().getAttribute("quizDAO");
		
		if (!mD.reviewExists(((User)request.getSession().getAttribute("MasterUser")).getUserName(), quizId)){
			mD.addReview(r);
		} else {
			mD.deleteReview(((User)request.getSession().getAttribute("MasterUser")).getUserName(), quizId);
			mD.addReview(r);
		}
		
		response.sendRedirect("Reviews?quizId=" + quizId);
		
	}

}
