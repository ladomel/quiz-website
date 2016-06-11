package Listeners;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

import Main.PasswordHasher;
import model.QuizWebsiteModel; // Added for some reason, might be wrong.

/**
 * Application Lifecycle Listener implementation class ServletContextListener
 *
 */
@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {
	private QuizWebsiteModel model;
	private PasswordHasher hasher;
	
    /**
     * Default constructor. 
     */
    public ServletContextListener() {
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	model = null;
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     * Initializes model.
     */
    public void contextInitialized(ServletContextEvent event)  { 
    	ServletContext ctx=event.getServletContext();  
    	model = QuizWebsiteModel.getInstance();
    	hasher = new PasswordHasher();
    	
    	ctx.setAttribute("model", model);  
    	ctx.setAttribute("hasher", hasher);  
    }
}
