package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.question.QuestionPR;
import classes.question.Abstract.Question;

/**
 * Servlet implementation class CreatePR
 */
@WebServlet("/CreatePR")
public class CreatePR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePR() {
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
		String problem = request.getParameter("statement");
		String pictureURL = request.getParameter("externalURL");
		HashSet<String> answers = new HashSet<String>();
		
		String nextAnswer = "";
		for(int i = 0; nextAnswer != null; i++)
		{
			nextAnswer = request.getParameter("answer" + i);
			answers.add(nextAnswer);
		}
		
		QuestionPR questionPR = new QuestionPR(problem, 1 ,pictureURL, answers);
		
		HttpSession session = request.getSession();
		ArrayList<Question> questions = (ArrayList<Question>)session.getAttribute("QuestionList");
		questions.add(questionPR);
	}
}
