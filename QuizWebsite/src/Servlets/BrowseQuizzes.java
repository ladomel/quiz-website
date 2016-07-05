package Servlets;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Constants;
import dao.QuizDAO;

/**
 * Servlet implementation class BrowseQuizzes
 */
@WebServlet("/BrowseQuizzes")
public class BrowseQuizzes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowseQuizzes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("category");
		QuizDAO qD = (QuizDAO) request.getServletContext().getAttribute("quizDAO");
		
		List<classes.Quiz> quizzes = null; 
		
		if (category == null || category.equals("all")) {
			quizzes = qD.getRecentQuizzes(Constants.BROWSING_DISPLAY_LIMIT);
			System.out.println("jandaba" + quizzes.size());
		} else {
			//quizzes = qD.getRecentQuizzes(0);
		}
		
		request.setAttribute("Quizzes", quizzes);
		
		RequestDispatcher rd = request.getRequestDispatcher("BrowseQuizzes.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
