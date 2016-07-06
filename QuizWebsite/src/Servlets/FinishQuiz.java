package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Main.Constants;
import classes.Answer;
import classes.Result;
import classes.User;
import dao.AchievementDAO;
import dao.QuizDAO;
import dao.ResultDAO;
import dao.UserDAO;
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
		
		
		
    	Quiz takenQuiz = (Quiz)session.getAttribute("Quiz");
    	
    	if (takenQuiz == null) return;
    	
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
			rD.insertResult(result);
		}
		
		checkAchievements(request, userName, date);
		setAttributesToNulls(session);
		
		ResultDAO resultDao =  (ResultDAO) request.getServletContext().getAttribute("resultDAO");
		UserDAO userDao =  (UserDAO) request.getServletContext().getAttribute("userDAO");
		 
		List<Result> rece = (List<Result>) resultDao.getRecentResults(userDao.getFriends(userName), Constants.MAX_DISPLAY);
		request.setAttribute("FriendsRecentResults", rece);
		List<String> frnds = new ArrayList<String>();
		for (Result r : rece){
			frnds.add(r.getUserName());
		}
		request.setAttribute("FriendsRecentUsers", frnds);
		
		request.setAttribute("Ranking", resultDao.rankInQuiz(userName, takenQuiz.getId()));
		
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
	
	private void checkAchievements(HttpServletRequest request, String userName, Date date)
	{	
		Quiz takenQuiz = (Quiz)request.getSession().getAttribute("Quiz");
		AchievementDAO achievementDAO = (AchievementDAO)request.getServletContext().getAttribute("achievementDAO");
		ResultDAO resultDAO = (ResultDAO)request.getServletContext().getAttribute("resultDAO");

		if(!achievementDAO.hasAchievement(userName,3) && resultDAO.getRecentResults(userName, 10).size() == 10) 	
			achievementDAO.achievementEarned(userName, 3, date.getTime()); // User took 10 quizzes.

		if(!achievementDAO.hasAchievement(userName,4) && 
				resultDAO.getBestResults(takenQuiz.getId(), 1, 0).get(0).getUserName().equals(userName))
			achievementDAO.achievementEarned(userName, 4, date.getTime()); // Best result.

		if(!achievementDAO.hasAchievement(userName,5) && 
				(boolean)request.getSession().getAttribute("PracticeMode"))
			achievementDAO.achievementEarned(userName, 5, date.getTime()); // Practice mode
	}
	
	
	private void setAttributesToNulls(HttpSession session)
	{
		session.setAttribute("questionPositions", null);
		session.setAttribute("Result", null); 
		session.setAttribute("Questions", null); 
		session.setAttribute("Quiz", null);
	}
}