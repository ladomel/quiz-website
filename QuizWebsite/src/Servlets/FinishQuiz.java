package Servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.Answer;
import classes.Result;
import classes.User;
import dao.AchievementDAO;
import dao.QuizDAO;
import dao.ResultDAO;
import classes.Quiz;

/**
 * Servlet implementation class FinishQuiz
 */
@WebServlet("/FinishQuiz")
public class FinishQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinishQuiz() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	User masterUser = (User)session.getAttribute("MasterUser");
		String userName = masterUser.getUserName();
		AchievementDAO achievementDAO = (AchievementDAO)request.getServletContext().getAttribute("achievementDAO");
		ResultDAO resultDAO = (ResultDAO)request.getServletContext().getAttribute("resultDAO");
		
    	Quiz takenQuiz = (Quiz)session.getAttribute("Quiz");
    	Date date = new Date();
    	Result result = (Result)session.getAttribute("Result");
    	result.setTimeTaken(date.getTime() - result.getTimeStarted());

    	int finalGrade = 0;
    	List<Answer> answers = result.getAnswers();
    	for(Answer ans : answers) if (ans!=null) finalGrade += ans.getGrade();
    	result.setFinalGrade(finalGrade);

    	
    	request.setAttribute("Result", result);
		request.setAttribute("Quiz", takenQuiz); 
		
		if (!(boolean)session.getAttribute("PracticeMode")){
			ResultDAO rD = (ResultDAO)request.getServletContext().getAttribute("resultDAO");
			achievementDAO.achievementEarned(userName, 5, date.getTime()); // Practice mode
			rD.insertResult(result);
		}
		
		if(resultDAO.getRecentResults(userName, 10).size() == 10) 	
			achievementDAO.achievementEarned(userName, 3, date.getTime()); // User took 10 quizzes.
		
		if(resultDAO.getBestResults(takenQuiz.getId(), 1, 0).get(0).getUserName().equals(userName))
			achievementDAO.achievementEarned(userName, 4, date.getTime()); // Best result.
		
		session.setAttribute("questionPositions", null);
		session.setAttribute("Result", null); 
		session.setAttribute("Questions", null); 
		session.setAttribute("Quiz", null);

    	RequestDispatcher requestDispatcher = request.getRequestDispatcher("quizResult.jsp");
    	requestDispatcher.forward(request, response);	
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     * 
     * This servlet gets called at the end of each quiz.
     * Takes finish time.
     * 
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}