package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.User;
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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Takes user Id as parameter and goes to Profile.jsp if found and 
	 * 
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		
		RequestDispatcher dispatcher;
		ServletContext ctx= getServletContext();  
		UserDAO userDAO = (UserDAO)ctx.getAttribute("userDAO");
		dispatcher = request.getRequestDispatcher("notfound.jsp");	
		
		User user = userDAO.getUser(userName);
		if(user != null)
		{
			request.setAttribute("User", user);
			dispatcher = request.getRequestDispatcher("Profile.jsp?username=" + userName);
		}
		dispatcher.forward(request, response);	
	}
}
