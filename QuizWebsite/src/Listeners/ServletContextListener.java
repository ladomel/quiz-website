package Listeners;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

import Main.Achievements;
import Main.PasswordHasher;
import dao.AchievementDAO;
import dao.DAOInstances;
import dao.MessageDAO;
import dao.QuestionDAO;
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
	private MessageDAO messageDAO;
	private QuestionDAO questionDAO;
	private AchievementDAO achievementDAO;
	private PasswordHasher hasher;
	private ClassFactory factory;
	private Achievements achievements;
	
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
    	messageDAO = null;	
    	questionDAO = null;
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     * Initializes model.
     */
    public void contextInitialized(ServletContextEvent event)  {
    	
    	ServletContext context = event.getServletContext();
    	
    	daoFactory = new DAOInstances();
    	daoFactory.init();
    	
    	userDAO = daoFactory.getUserDAO();	// TODO: add to context
    	messageDAO = daoFactory.getMessageDAO();	// TODO: add to context
    	quizDAO = daoFactory.getQuizDAO();	// TODO: add to context
    	questionDAO = daoFactory.getQuestionDAO();	// TODO: add to context
    	achievementDAO = daoFactory.getAchievementDAO();
    	
    	achievements = new Achievements();
    	factory = new ClassFactory();
    	hasher = new PasswordHasher();
    	
    	context.setAttribute("userDAO", userDAO);  
    	context.setAttribute("quizDAO", quizDAO);  
    	context.setAttribute("messageDAO", messageDAO);  
    	context.setAttribute("questionDAO", questionDAO);  
    	context.setAttribute("achievementDAO", achievementDAO);  
    	context.setAttribute("factory", factory);  
    	context.setAttribute("hasher", hasher);  
    	context.setAttribute("achievements", achievements);  
    }
}
