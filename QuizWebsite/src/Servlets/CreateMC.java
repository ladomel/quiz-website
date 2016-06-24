package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String problem = request.getParameter("statement");
		List<String> answers = new ArrayList<String>();
		answers.add(request.getParameter("correctanswer"));

		String nextAnswer = "";
		for(int i = 0; nextAnswer != null; i++)
		{
			nextAnswer = request.getParameter("answer" + i);
			answers.add(nextAnswer);
		}
		
		QuestionMC questionMC = new QuestionMC(problem, answers);
		
		HttpSession session = request.getSession();
		ArrayList<Question> questions = (ArrayList<Question>)session.getAttribute("QuestionList");
		questions.add(questionMC);
		
	}

}
