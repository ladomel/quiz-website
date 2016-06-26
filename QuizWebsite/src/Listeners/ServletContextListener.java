package Listeners;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

import Main.PasswordHasher;
import dao.DAOInstances;
import dao.QuizDAO;
import dao.UserDAO;
import factory.ClassFactory;

/**
 * Application Lifecycle Listener implementation class ServletContextListener
 *
 */
@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {
	
	private DAOInstances daoFactory;
	private UserDAO userDAO;
	private QuizDAO quizDAO;
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
    	hasher = null;
    	factory = null;
    	
    	daoFactory = null;	
    	userDAO = null;	
    	quizDAO = null;
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     * Initializes model.
     */
    public void contextInitialized(ServletContextEvent event)  {
    	
    	ServletContext context = event.getServletContext();
    	
    	daoFactory = new DAOInstances();	// TODO: add to context
    	userDAO = daoFactory.getUserDAO();	// TODO: add to context
    	quizDAO = daoFactory.getQuizDAO();	// TODO: add to context
    	
    	factory = new ClassFactory();
    	hasher = new PasswordHasher();
    	
    	context.setAttribute("factory", factory);  
    	context.setAttribute("hasher", hasher);  
    }
}
