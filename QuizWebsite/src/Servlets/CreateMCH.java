package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.question.QuestionMCH;
import classes.question.Abstract.Question;

/**
 * Servlet implementation class CreateMCH
 */
@WebServlet("/CreateMCH")
public class CreateMCH extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateMCH() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * Reads one MCHQuestion information and adds it in createdQuestions list in session.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String problem = request.getParameter("statement");
		String nextAnswer;

		List<String> questions = new ArrayList<String>();
		for(int i = 0; ;i++)
		{
			nextAnswer = request.getParameter("question" + i);
			if(nextAnswer == null) break;
			questions.add(nextAnswer);
		}

		List<String> rightAnswers = new ArrayList<String>();
		for(int i = 0; ;i++)
		{
			nextAnswer = request.getParameter("rightanswer" + i);
			if(nextAnswer == null) break;
			rightAnswers.add(nextAnswer);
		}
		
		List<String> wrongAnswers = new ArrayList<String>();
		for(int i = 0; ;i++)
		{
			nextAnswer = request.getParameter("wronganswer" + i);
			if(nextAnswer == null) break;
			wrongAnswers.add(nextAnswer);
		}
		
		QuestionMCH questionMCH = new QuestionMCH(problem, 1, questions, rightAnswers, wrongAnswers);
		ArrayList<Question> createdQuestions = (ArrayList<Question>)request.getSession().getAttribute("createdQuestions");
		createdQuestions.add(questionMCH);
	}
}
