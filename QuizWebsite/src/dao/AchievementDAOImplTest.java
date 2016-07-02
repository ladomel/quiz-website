package dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import factory.ClassFactory;

public class AchievementDAOImplTest {

	private DAOInstances factory;
	private AchievementDAO achievementDAO;
	private ClassFactory classFactory;

	@Before
	public void setUp() throws Exception {
		factory = new DAOInstances();
		factory.init();
		achievementDAO = factory.getAchievementDAO();
		classFactory = new ClassFactory();
	}
	
	@Test
	public void hasAchievementTest() {
		System.out.println("Before adding: " + achievementDAO.hasAchievement("test", 2));
		achievementDAO.achievementEarned("test", 2, 10);

		achievementDAO.achievementEarned("test", 5, 5000);
		System.out.println("After adding: " + achievementDAO.hasAchievement("test", 2));
	}


	@Test
	public void gerEarnedAchievementsTest() {

		achievementDAO.achievementEarned("test", 3, 30);
		achievementDAO.achievementEarned("test", 5, 5000);
		achievementDAO.achievementEarned("test", 4, 40);
		achievementDAO.achievementEarned("test", 2, 1000);
		System.out.println("All achievements: " + achievementDAO.getEarnedAchievements("test"));
	}

	
}
