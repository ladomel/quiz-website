package Listeners;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

import Main.PasswordHasher;
import factory.ClassFactory;
import model.QuizWebsiteModel; // Added for some reason, might be wrong.

/**
 * Application Lifecycle Listener implementation class ServletContextListener
 *
 */
@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {
	private QuizWebsiteModel model;
	private PasswordHasher hasher;
	private ClassFactory factory;
	
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
    	hasher = null;
    	factory = null;
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     * Initializes model.
     */
    public void contextInitialized(ServletContextEvent event)  { 
    	ServletContext context = event.getServletContext();  
    	model = QuizWebsiteModel.getInstance();
    	factory = new ClassFactory();
    	hasher = new PasswordHasher();
    	
    	context.setAttribute("model", model);  
    	context.setAttribute("factory", factory);  
    	context.setAttribute("hasher", hasher);  
    }
}
