package Listeners;

import java.util.ArrayList;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import classes.question.Abstract.Question;


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
    	ArrayList<Question> problemList = new ArrayList<Question>();
    	HttpSession s = arg0.getSession();
    	s.setAttribute("MasterUser", null);
    	s.setAttribute("ProblemList", problemList);
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	HttpSession s = arg0.getSession();
        s.removeAttribute("MasterUser");
        s.removeAttribute("ProblemList");
    }
	
}
