package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.question.QuestionTF;
import classes.question.Abstract.Question;

/**
 * Servlet implementation class CreateTF
 */
@WebServlet("/CreateTF")
public class CreateTF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTF() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String problem = request.getParameter("statement");
		int grade = Integer.parseInt(request.getParameter("grade"));
		String nextAnswer;

		List<String> questions = new ArrayList<String>();
		for(int i = 0; ;i++)
		{
			nextAnswer = request.getParameter("question" + i);
			if(nextAnswer == null) break;
			questions.add(nextAnswer);
		}

		List<String> answers = new ArrayList<String>();
		for(int i = 0; ;i++)
		{
			nextAnswer = request.getParameter("answer" + i);
			if(nextAnswer == null) break;
			answers.add(nextAnswer);
		}

		
		QuestionTF questionTF = new QuestionTF(problem, grade, questions, answers);
		ArrayList<Question> createdQuestions = (ArrayList<Question>)request.getSession().getAttribute("createdQuestions");
		createdQuestions.add(questionTF);
		System.out.println(questionTF.toString());
	}
}
