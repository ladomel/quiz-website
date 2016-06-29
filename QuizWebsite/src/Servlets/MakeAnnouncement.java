package Servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.User;
import classes.Message.Announcement;
import classes.Message.Note;
import factory.ClassFactory;

/**
 * Servlet implementation class MakeAnnouncement
 */
@WebServlet("/MakeAnnouncement")
public class MakeAnnouncement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeAnnouncement() {
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
		String announcement = request.getParameter("announcement");
		User master = (User) request.getSession().getAttribute("MasterUser");
		ClassFactory factory = (ClassFactory) request.getServletContext().getAttribute("factory");
		long date = (new Date()).getTime();
		Announcement ann = factory.getAnnouncement(master.getUserName(),announcement,date);
		// database 
	}

}
