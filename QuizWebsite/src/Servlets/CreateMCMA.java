package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.question.QuestionMCMA;
import classes.question.Abstract.Question;

/**
 * Servlet implementation class CreateMCMA
 */
@WebServlet("/CreateMCMA")
public class CreateMCMA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateMCMA() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * Reads one MCMAQuestion information and adds it in createdQuestions list in session.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String problem = request.getParameter("statement");
		int grade = Integer.parseInt(request.getParameter("grade"));
		String nextAnswer;

		List<String> correctAnswers = new ArrayList<String>();
		for(int i = 0; ;i++)
		{
			nextAnswer = request.getParameter("correctanswer" + i);
			if( nextAnswer == null) break;
			correctAnswers.add(nextAnswer);
		}

		List<String> incorrectAnswers = new ArrayList<String>();
		for(int i = 0; ;i++)
		{
			nextAnswer = request.getParameter("answer" + i);
			if(nextAnswer == null) break;
			incorrectAnswers.add(nextAnswer);
		}

		QuestionMCMA questionMCMA = new QuestionMCMA(problem, grade, correctAnswers, incorrectAnswers);
		ArrayList<Question> createdQuestions = (ArrayList<Question>)request.getSession().getAttribute("createdQuestions");
		createdQuestions.add(questionMCMA);
	}
}
