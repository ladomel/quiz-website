package Servlets;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.User;
import classes.Message.Challenge;
import factory.*;

/**
 * Servlet implementation class SendChallenge
 */
@WebServlet("/SendChallenge")
public class SendChallenge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendChallenge() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String[]> map = request.getParameterMap();
		int quizId = Integer.parseInt(request.getParameter("quizId"));
		User master = (User) request.getSession().getAttribute("MasterUser");
		ClassFactory factory = (ClassFactory) request.getServletContext().getAttribute("factory");
		long date = (new Date()).getTime();
		int i = 0;
		while(true){
			if (map.containsKey(Integer.toString(i))){
				Challenge req = factory.getChallenge(master.getUserName(), date, map.get(Integer.toString(i))[0], quizId);
				// database 
				i++;
			} else break;
		}
	}

}
