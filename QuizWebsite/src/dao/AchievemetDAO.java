package dao;

import java.util.List;

public interface AchievemetDAO {
	/**
	 * This function returns user's earned achievements' id's sorted by date earned.
	 * 
	 * @param userId - id of user whose achievements we want
	 * @return user's earned achievements' id's sorted by date earned
	 */
	public List<Integer> getEarnedAchievements(int userId);
	
	/**
	 * This function adds earned achievement's id to the user's earned achievements.
	 * 
	 * @param userId - id of user who got the achievement
	 * @param achievementId - id of achievement user earned
	 * @param date - date of achievement unlocking
	 */
	public void achievementEarned(int userId, int achievementId, long date);
}
