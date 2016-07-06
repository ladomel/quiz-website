package dao;

import java.util.List;
import java.util.Set;

public interface AchievementDAO {
	/**
	 * This function returns user's earned achievements' id's sorted by date earned.
	 * 
	 * @param userName - userName of user whose achievements we want
	 * @return user's earned achievements' id's sorted by date earned
	 */
	public List<Integer> getEarnedAchievements(String userName);
	
	/**
	 * This function adds earned achievement's id to the user's earned achievements.
	 * 
	 * @param userName - username of user who got the achievement
	 * @param achievementId - id of achievement user earned
	 * @param date - date of achievement unlocking
	 */
	public void achievementEarned(String userName, int achievementId, long date);
	
	
	/**
	 * This function returns true if user has earned given achievement
	 * 
	 * @param userName - userName of user
	 * @param achievementId - if of achievement
	 * @return true iff user has earned this achievement
	 */
	public boolean hasAchievement(String userName, int achievementId);
	
	public List<Integer> getFriendsRecentAchievements(Set<String> friendNames, int n);
	
}
