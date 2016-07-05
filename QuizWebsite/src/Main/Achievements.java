package Main;

import java.util.ArrayList;
import java.util.List;

import classes.Achievement;
import factory.ClassFactory;

public class Achievements {	
	private static List<Achievement> achievements;
	private ClassFactory factory;
	
	private static final String ACHIEVEMENT_URL_DEFAULT = "https://i.kinja-img.com/gawker-media/image/upload/s--ZGfvhqgX--/18161pd0zsg3xjpg.jpg";
	
	private static final String ACHIEVEMENT_NAME_0 = "Amateur Author";
	private static final String ACHIEVEMENT_URL_0 = "http://comps.gograph.com/bronze-3d-question-mark_gg57847833.jpg";
	private static final String ACHIEVEMENT_DESCRIPTION_0 = "The user created a quiz";

	private static final String ACHIEVEMENT_NAME_1 = "Prolifie Author";
	private static final String ACHIEVEMENT_URL_1 = "http://cdn.xl.thumbs.canstockphoto.com/canstock5930397.jpg";
	private static final String ACHIEVEMENT_DESCRIPTION_1 = "The user created five quizzes";
		
	private static final String ACHIEVEMENT_NAME_2 = "Prodigious Author";
	private static final String ACHIEVEMENT_URL_2 = "http://fscomps.fotosearch.com/compc/CSP/CSP373/k3739360.jpg";
	private static final String ACHIEVEMENT_DESCRIPTION_2 = "The user created ten quizzes";

	private static final String ACHIEVEMENT_NAME_3 = "Quiz Machine";
	private static final String ACHIEVEMENT_URL_3 = "http://cdn.playbuzz.com/cdn/3fac4ca0-bc77-4426-a22f-53b56147d26e/0010ce1c-b81a-4f7d-9d30-fa099af311c1.png";
	private static final String ACHIEVEMENT_DESCRIPTION_3 = "The user took ten quizzes";

	private static final String ACHIEVEMENT_NAME_4 = "I am the Greatest";
	private static final String ACHIEVEMENT_URL_4 = "http://thumb101.shutterstock.com/display_pic_with_logo/2813065/355898243/stock-photo--d-golden-star-number-one-with-gold-metal-frame-render-355898243.jpg";
	private static final String ACHIEVEMENT_DESCRIPTION_4 = "The user had the highest score on a quiz";

	private static final String ACHIEVEMENT_NAME_5 = "Practice Makes Perfect";
	private static final String ACHIEVEMENT_URL_5 = "http://previews.123rf.com/images/aquir/aquir1410/aquir141000015/32121851-practice-blue-grunge-stamp-isolated-on-white-Stock-Photo.jpg";
	private static final String ACHIEVEMENT_DESCRIPTION_5 = "The user took a quiz in practice mode";
	
	public Achievements()
	{
		factory = new ClassFactory();
		setAchievements(new ArrayList<Achievement>());
		addAchievement(ACHIEVEMENT_NAME_0, ACHIEVEMENT_URL_0, ACHIEVEMENT_DESCRIPTION_0);
		addAchievement(ACHIEVEMENT_NAME_1, ACHIEVEMENT_URL_1, ACHIEVEMENT_DESCRIPTION_1);
		addAchievement(ACHIEVEMENT_NAME_2, ACHIEVEMENT_URL_2, ACHIEVEMENT_DESCRIPTION_2);
		addAchievement(ACHIEVEMENT_NAME_3, ACHIEVEMENT_URL_3, ACHIEVEMENT_DESCRIPTION_3);
		addAchievement(ACHIEVEMENT_NAME_4, ACHIEVEMENT_URL_4, ACHIEVEMENT_DESCRIPTION_4);
		addAchievement(ACHIEVEMENT_NAME_5, ACHIEVEMENT_URL_5, ACHIEVEMENT_DESCRIPTION_5);
	};
	
	/**
	 * Returns i'th achievement in the list.
	 * 
	 * @param id - id of the achievement to return
	 * @return achievement on the i'th index
	 */
	public static Achievement getAchievement(int id)
	{
		return getAchievements().get(id);
	}
	
	private void addAchievement(String name, String url, String description)
	{
		getAchievements().add(factory.getAchievement(name, url, description));
	}
	
	public int getNumAchievements()
	{
		return getAchievements().size();
	}

	public static List<Achievement> getAchievements() {
		return achievements;
	}

	public static void setAchievements(List<Achievement> achievements) {
		Achievements.achievements = achievements;
	}
	
	/**
	 * Returns default url of achievement image (if it is not unlocked yet) 
	 * 
	 * @return default url of achievement image (if it is not unlocked yet) 
	 */
	public static String getDefaultURL()
	{
		return ACHIEVEMENT_URL_DEFAULT;
	}
}
