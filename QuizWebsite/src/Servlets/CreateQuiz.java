package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.User;
import classes.question.QuestionMA;
import classes.question.QuestionMC;
import classes.question.QuestionMCH;
import classes.question.QuestionMCMA;
import classes.question.QuestionPR;
import classes.question.QuestionQR;
import classes.question.Abstract.Question;
import dao.QuestionDAO;
import dao.QuizDAO;
import factory.ClassFactory;

/**
 * Servlet implementation class CreateQuiz
 */
@WebServlet("/CreateQuiz")
public class CreateQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuiz() {
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
		HttpSession session = request.getSession();
		QuizDAO quizDAO = (QuizDAO)request.getServletContext().getAttribute("quizDAO");
		classes.Quiz newQuiz = createQuiz(request, session);
		ArrayList<Question> createdQuestions = (ArrayList<Question>)session.getAttribute("createdQuestions");
		
		System.out.println(newQuiz.toString());
		for(int i = 0; i < createdQuestions.size(); i++)
			System.out.println(createdQuestions.get(i).toString());	
		//int id = quizDAO.addQuiz(newQuiz);
		//createdQuestions.clear();
		//RequestDispatcher requestDispatcher = request.getRequestDispatcher("Quiz?id=" + id);
		//requestDispatcher.forward(request, response);	
	}
	
	
	public classes.Quiz createQuiz(HttpServletRequest request, HttpSession session)
	{
		Date date = new Date();   
		User masterUser = (User)session.getAttribute("MasterUser");
		ClassFactory factory = (ClassFactory)request.getServletContext().getAttribute("factory");
		//classes.Quiz newQuiz = factory.getQuiz(masterUser.getUserName(), name, description);
		classes.Quiz newQuiz = factory.getQuiz("Ado", request.getParameter("name"), request.getParameter("description"));
		newQuiz.setDateCreated(date.getTime());
		newQuiz.setHasPracticeMode(request.getParameter("practice") != null);
		newQuiz.setImmediatelyCorrected(request.getParameter("correction") != null);
		newQuiz.setOnePage(request.getParameter("onepage") != null);
		newQuiz.setRandom((request.getParameter("random") != null));
		newQuiz.setQuizTime(Integer.parseInt(request.getParameter("time")));
		return newQuiz;
			System.out.println(createdQuestions.get(i).toString());
			
		QuizDAO quizDAO = (QuizDAO)request.getServletContext().getAttribute("quizDAO");
		QuestionDAO questionDAO = (QuestionDAO) request.getServletContext().getAttribute("questionDAO");
	
		int id = quizDAO.addQuiz(newQuiz);
		
		for (int i=0;i<createdQuestions.size();i++){
			System.out.println(createdQuestions.get(i).toString());
			switch(createdQuestions.get(i).getType()){
				case "QR": questionDAO.addQR(id, (QuestionQR) createdQuestions.get(i)); break;
				case "PR": questionDAO.addPR(id,(QuestionPR) createdQuestions.get(i)); break;
				case "MC": questionDAO.addMC(id,(QuestionMC) createdQuestions.get(i)); break;
				case "MA": questionDAO.addMA(id, (QuestionMA)createdQuestions.get(i)); break;
				case "MCMA": questionDAO.addMCMA(id, (QuestionMCMA)createdQuestions.get(i)); break;
				
			}
		}

		createdQuestions.clear();
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("Quiz?id=" + id);
		requestDispatcher.forward(request, response);	
	}
}
