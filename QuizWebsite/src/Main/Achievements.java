package Main;

import java.util.List;

import classes.Achievement;
import factory.ClassFactory;

public class Achievements {	
	private static List<Achievement> achievements;
	private ClassFactory factory;
	
	private static final String ACHIEVEMENT_NAME_1 = "Amateur Author";
	private static final String ACHIEVEMENT_URL_1 = "firstUrl";
	private static final String ACHIEVEMENT_DESCRIPTION_1 = "The user created a quiz";

	private static final String ACHIEVEMENT_NAME_2 = "Prolifie Author";
	private static final String ACHIEVEMENT_URL_2 = "secondUrl";
	private static final String ACHIEVEMENT_DESCRIPTION_2 = "The user created five quizzes";
		
	private static final String ACHIEVEMENT_NAME_3 = "Prodigious Author";
	private static final String ACHIEVEMENT_URL_3 = "thirdUrl";
	private static final String ACHIEVEMENT_DESCRIPTION_3 = "The user created ten quizzes";

	private static final String ACHIEVEMENT_NAME_4 = "Quiz Machine";
	private static final String ACHIEVEMENT_URL_4 = "fourthUrl";
	private static final String ACHIEVEMENT_DESCRIPTION_4 = "The user took ten quizzes";

	private static final String ACHIEVEMENT_NAME_5 = "I am the Greatest";
	private static final String ACHIEVEMENT_URL_5 = "fifthUrl";
	private static final String ACHIEVEMENT_DESCRIPTION_5 = "The user had the highest score on a quiz";

	private static final String ACHIEVEMENT_NAME_6 = "Practice Makes Perfect";
	private static final String ACHIEVEMENT_URL_6 = "sixUrl";
	private static final String ACHIEVEMENT_DESCRIPTION_6 = "The user took a quiz in practice mode";
	
	public Achievements()
	{
		factory = new ClassFactory();
		addAchievement(ACHIEVEMENT_NAME_1, ACHIEVEMENT_URL_1, ACHIEVEMENT_DESCRIPTION_1);
		addAchievement(ACHIEVEMENT_NAME_2, ACHIEVEMENT_URL_2, ACHIEVEMENT_DESCRIPTION_2);
		addAchievement(ACHIEVEMENT_NAME_3, ACHIEVEMENT_URL_3, ACHIEVEMENT_DESCRIPTION_3);
		addAchievement(ACHIEVEMENT_NAME_4, ACHIEVEMENT_URL_4, ACHIEVEMENT_DESCRIPTION_4);
		addAchievement(ACHIEVEMENT_NAME_5, ACHIEVEMENT_URL_5, ACHIEVEMENT_DESCRIPTION_5);
		addAchievement(ACHIEVEMENT_NAME_6, ACHIEVEMENT_URL_6, ACHIEVEMENT_DESCRIPTION_6);
	};
	
	/**
	 * Returns i'th achievement in the list.
	 * 
	 * @param id - id of the achievement to return
	 * @return achievement on the i'th index
	 */
	public static Achievement getAchievement(int id)
	{
		return achievements.get(id);
	}
	
	private void addAchievement(String name, String url, String description)
	{
		achievements.add(factory.getAchievement(name, url, description));
	}
	
}
