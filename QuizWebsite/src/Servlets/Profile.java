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
		System.out.println("Profile.java-Start");
		String userName = request.getParameter("username");
		RequestDispatcher dispatcher = request.getRequestDispatcher("notFound.jsp");
		ServletContext ctx= getServletContext();  
		UserDAO userDAO = (UserDAO)ctx.getAttribute("userDAO");
		User master = (User) request.getSession().getAttribute("MasterUser");
		System.out.println("Profile.java-BeforeFetch-" + userName);
		User user = userDAO.getUser(userName);
		System.out.println("Profile.java-FetchUser");
		
		if(user != null){
			System.out.println("Profile.java-If");
			request.setAttribute("User", user);
			
			QuizDAO quizDao= (QuizDAO)ctx.getAttribute("quizDAO");
			ResultDAO resultDao= (ResultDAO)ctx.getAttribute("resultDAO");
			MessageDAO messageDao= (MessageDAO)ctx.getAttribute("messageDAO");
			
			Set<String> friends = userDAO.getFriends(userName);
			request.setAttribute("Friends", friends);
			System.out.println("Profile.java-Friends");
			
			
			request.setAttribute("createdQuizzes", quizDao.getCreatedQuizzes(userName));
			System.out.println("Profile.java-Created");
			
			
			if (master != null){
				request.setAttribute("FriendRequestSent", messageDao.friendRequestExists(master.getUserName(), userName));
				request.setAttribute("FriendRequestReceived", messageDao.friendRequestExists(userName, master.getUserName()));
			}
			
			
			List<Result> recent = resultDao.getRecentResults(userName, Constants.MAX_DISPLAY);
			System.out.println("Profile.java-Results");
			
			
			List<classes.Quiz> recQuiz= new ArrayList<classes.Quiz>();
			if (recent!= null){
				for (Result res : recent){
					recQuiz.add(quizDao.getQuiz(res.getQuizId()));
				}
			}
			request.setAttribute("recentResults", recent);
			request.setAttribute("recentQuizzes", recQuiz);
			System.out.println("Profile.java-Recents");
			
			List<Result> rece = (List<Result>) resultDao.getRecentResults(friends, Constants.MAX_DISPLAY);
			request.setAttribute("FriendsRecentResults", rece);
			List<String> frnds = new ArrayList<String>();
			List<classes.Quiz> quizzes = new ArrayList<classes.Quiz>();
			for (Result r : rece){
				frnds.add(r.getUserName());
				quizzes.add(quizDao.getQuiz(r.getQuizId()));
			}
			request.setAttribute("FriendsRecentQuizzes", quizzes);
			request.setAttribute("FriendsRecentUsers", frnds);
			
			List<classes.Quiz> fqq = quizDao.getQuizzesOfUsers(friends, Constants.MAX_DISPLAY);
			request.setAttribute("FriendsCreatedQuizzes", fqq);
			List<String> usrq = new ArrayList<String>();
			for (classes.Quiz q : fqq){
				usrq.add(q.getUserName());
			}
			request.setAttribute("FriendsCreatedUsers",usrq);
			
			System.out.println("Profile.java-Friendsandstuff");
			
			dispatcher = request.getRequestDispatcher("Profile.jsp");
		}
		System.out.println("Profile.java-End"); 
		dispatcher.forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Takes user Id as parameter and goes to Profile.jsp if found and 
	 * 
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
