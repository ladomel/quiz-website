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
import classes.question.QuestionFB;
import classes.question.QuestionMA;
import classes.question.QuestionMC;
import classes.question.QuestionMCH;
import classes.question.QuestionMCMA;
import classes.question.QuestionPR;
import classes.question.QuestionQR;
import classes.question.QuestionTF;
import classes.question.Abstract.Question;
import dao.AchievementDAO;
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
		Date date = new Date();
		classes.Quiz newQuiz = createQuiz(request, date.getTime());
		
		HttpSession session = request.getSession();				
		ArrayList<Question> createdQuestions = (ArrayList<Question>)session.getAttribute("createdQuestions");
		
		for(int i = 0; i < createdQuestions.size(); i++)
			newQuiz.setMaxScore(newQuiz.getMaxScore() + createdQuestions.get(i).getMaxGrade());
			
		QuizDAO quizDAO = (QuizDAO)request.getServletContext().getAttribute("quizDAO");
		QuestionDAO questionDAO = (QuestionDAO) request.getServletContext().getAttribute("questionDAO");	
		int id = quizDAO.addQuiz(newQuiz);

		
		for (int i=0;i<createdQuestions.size();i++){
			System.out.println(createdQuestions.get(i).toString());
			switch(createdQuestions.get(i).getType()){
				case "QR": questionDAO.addQR(id, (QuestionQR) createdQuestions.get(i)); break;
				case "PR": questionDAO.addPR(id,(QuestionPR) createdQuestions.get(i)); break;
				case "MC": questionDAO.addMC(id,(QuestionMC) createdQuestions.get(i)); break;
				case "FB": questionDAO.addFB(id, (QuestionFB) createdQuestions.get(i)); break;
				case "MA": questionDAO.addMA(id, (QuestionMA)createdQuestions.get(i)); break;
				case "MCMA": questionDAO.addMCMA(id, (QuestionMCMA)createdQuestions.get(i)); break;
				case "MCH": questionDAO.addMCH(id, (QuestionMCH)createdQuestions.get(i)); break;
				case "TF": questionDAO.addTF(id, (QuestionTF)createdQuestions.get(i)); break;
			}
		}

		createdQuestions.clear();
	
		int i = 0;
		while (true){
			String tag = request.getParameter("tag" + i);
			if (tag == null) break;
			quizDAO.addTag(id, tag);
			i++;
		}
		
		checkAchievements(request, date.getTime());
		response.sendRedirect("Quiz?id=" + id);
	}
	
	/**
	 * This function creates quiz according to user's input
	 */
	private classes.Quiz createQuiz(HttpServletRequest request, long date)
	{
		HttpSession session = request.getSession();
		User masterUser = (User)session.getAttribute("MasterUser");
		ClassFactory factory = (ClassFactory)request.getServletContext().getAttribute("factory");
		classes.Quiz newQuiz = factory.getQuiz(masterUser.getUserName(),  request.getParameter("name"), request.getParameter("description"));
		newQuiz.setDateCreated(date);
		newQuiz.setHasPracticeMode(request.getParameter("practice") != null);
		newQuiz.setImmediatelyCorrected(request.getParameter("correction") != null);
		newQuiz.setOnePage(request.getParameter("onepage") != null);
		newQuiz.setRandom(request.getParameter("random") != null);
		newQuiz.setQuizTime(Integer.parseInt(request.getParameter("time")));
		newQuiz.setCategory(request.getParameter("category"));
		return newQuiz;
	}
	
	
	private void checkAchievements(HttpServletRequest request, long date)
	{
		HttpSession session = request.getSession();
		User masterUser = (User)session.getAttribute("MasterUser");
		String userName = masterUser.getUserName();
		
		AchievementDAO achievementDAO = (AchievementDAO)request.getServletContext().getAttribute("achievementDAO");
		QuizDAO quizDAO = (QuizDAO)request.getServletContext().getAttribute("quizDAO");
		int numQuizzesCreated = quizDAO.getCreatedQuizzes(userName).size();
		
		if(numQuizzesCreated >= 1 )	achievementDAO.achievementEarned(userName, 0, date);
		if(numQuizzesCreated >= 5 )	achievementDAO.achievementEarned(userName, 1, date);
		if(numQuizzesCreated >= 10 )	achievementDAO.achievementEarned(userName, 2, date);
	}
	
	
	
	
	
	
	
	
	
}