package Listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import classes.Quiz;
import classes.User;
import classes.question.QuestionMA;
import classes.question.QuestionMC;
import classes.question.QuestionMCMA;
import classes.question.QuestionPR;
import classes.question.QuestionQR;
import classes.question.Abstract.Question;
import dao.DAOInstances;
import dao.UserDAO;

//createdQuestions = List with user's created questions.
//MasterUser - user currently logged in.

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
@WebListener
public class SessionListener implements HttpSessionListener {
    /**
     * Default constructor. 
     */
    public SessionListener() {  }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
    	
    	HttpSession s = arg0.getSession();
    	
    	s.setAttribute("isAdmin", false);
    	s.setAttribute("PracticeMode", false);
    	s.setAttribute("createdQuestions", null);
    	s.setAttribute("questionPositions", null);
    	s.setAttribute("MasterUser", null);
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	HttpSession s = arg0.getSession();
    	if (s.getAttribute("MasterUser") != null) {
    		System.out.println("Session Log Out");
    		UserDAO userDAO = new DAOInstances().getUserDAO();
    		userDAO.logOutUser(((User)s.getAttribute("MasterUser")).getUserName());
    	}
    	
    	s.removeAttribute("isAdmin");
    	s.removeAttribute("PracticeMode");
        s.removeAttribute("MasterUser");
        s.removeAttribute("createdQuestions");
        s.removeAttribute("Questions");
        s.removeAttribute("questionPositions");
    }
}
