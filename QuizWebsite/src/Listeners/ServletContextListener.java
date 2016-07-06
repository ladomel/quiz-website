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
import dao.ResultDAO;
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
	private ResultDAO resultDAO;
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
    	ServletContext context = arg0.getServletContext();
    	userDAO = null;quizDAO= null;resultDAO= null;messageDAO= null;questionDAO= null;achievementDAO= null;factory= null;hasher= null;achievements= null;
    	context.removeAttribute("userDAO");
    	context.removeAttribute("quizDAO");
    	context.removeAttribute("resultDAO");
    	context.removeAttribute("messageDAO");
    	context.removeAttribute("questionDAO");
    	context.removeAttribute("achievementDAO");
    	context.removeAttribute("factory");
    	context.removeAttribute("hasher");
    	context.removeAttribute("achievements");
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
    	resultDAO = daoFactory.getResultDAO();
    	
    	achievements = new Achievements();
    	factory = new ClassFactory();
    	hasher = new PasswordHasher();
    	
    	context.setAttribute("userDAO", userDAO);  
    	context.setAttribute("quizDAO", quizDAO);  
    	context.setAttribute("resultDAO", resultDAO);  
    	context.setAttribute("messageDAO", messageDAO);  
    	context.setAttribute("questionDAO", questionDAO);  
    	context.setAttribute("achievementDAO", achievementDAO);  
    	context.setAttribute("factory", factory);  
    	context.setAttribute("hasher", hasher);  
    	context.setAttribute("achievements", achievements);  
    }
}
