package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.PasswordHasher;
import classes.User;
import dao.UserDAO;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(userName);
		
		ServletContext ctx= getServletContext();  
		UserDAO userDAO = (UserDAO)ctx.getAttribute("userDAO");
		PasswordHasher hasher = (PasswordHasher)ctx.getAttribute("hasher");
		User user = userDAO.getUser(userName);
		
		if(user != null)  // If user exists return.
			{	
				out.println("used");
				return;
			}
		
		String salt = hasher.getRandomSalt();
		String hashedPassword = hasher.hashPassword(password + salt);

		User newUser = new User(userName, hashedPassword, salt);
		userDAO.addUser(newUser.getUserName(), newUser.getHashedPassword(), newUser.getSalt());  // Put in base.
		out.println("free");
	}
}
