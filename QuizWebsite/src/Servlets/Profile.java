package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Main.Constants;
import classes.Result;
import classes.User;
import dao.AchievementDAO;
import dao.MessageDAO;
import dao.QuizDAO;
import dao.ResultDAO;
import dao.UserDAO;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		RequestDispatcher dispatcher = request.getRequestDispatcher("notfound.jsp");
		ServletContext ctx= getServletContext();  
		UserDAO userDAO = (UserDAO)ctx.getAttribute("userDAO");
		User master = (User) request.getSession().getAttribute("MasterUser");
		
		User user = userDAO.getUser(userName);
		if(user != null){
			QuizDAO quizDao= (QuizDAO)ctx.getAttribute("quizDAO");
			ResultDAO resultDao= (ResultDAO)ctx.getAttribute("resultDAO");
			MessageDAO messageDao= (MessageDAO)ctx.getAttribute("messageDAO");
			
			Set<String> friends = userDAO.getFriends(userName);
			
			request.setAttribute("Friends", friends);
			request.setAttribute("createdQuizzes", quizDao.getCreatedQuizzes(userName));
			
			request.setAttribute("User", user);
			if (master != null){
				request.setAttribute("FriendRequestSent", messageDao.friendRequestExists(master.getUserName(), userName));
				request.setAttribute("FriendRequestReceived", messageDao.friendRequestExists(userName, master.getUserName()));
			}
			
			
			List<Result> recent = resultDao.getRecentResults(userName, Constants.MAX_DISPLAY);
			// add sort by time date and score
			
			List<classes.Quiz> recQuiz= new ArrayList<classes.Quiz>();
			if (recent!= null){
				for (Result res : recent){
					recQuiz.add(quizDao.getQuiz(res.getQuizId()));
				}
			}
			request.setAttribute("recentResults", recent);
			request.setAttribute("recentQuizzes", recQuiz);
			
			
			request.setAttribute("FriendsRecent", resultDao.getRecentResults(friends, Constants.MAX_DISPLAY));
			request.setAttribute("FriendsCreated", null);
			request.setAttribute("FriendsAchievements", null);
			
			
			//achievements
			AchievementDAO aD = (AchievementDAO) ctx.getAttribute("achievementDAO");
			
			
			dispatcher = request.getRequestDispatcher("Profile.jsp");
		}
		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Takes user Id as parameter and goes to Profile.jsp if found and 
	 * 
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
