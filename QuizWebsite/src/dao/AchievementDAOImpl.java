package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import classes.Quiz;
import factory.ClassFactory;

public class AchievementDAOImpl implements AchievementDAO {

	DataSource dataSource;
	ClassFactory classFactory;

	public AchievementDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
		classFactory = new ClassFactory();	 
	}

	@Override
	public List<Integer> getEarnedAchievements(String userName) {
		List<Integer> answer = new ArrayList<Integer>();
		try{
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(getEarnedAchievementsCommand());
			preparedStatement.setString(1, userName);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
				answer.add(rs.getInt("achievement_id"));
			preparedStatement.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}

	private String getEarnedAchievementsCommand(){
	return "SELECT * FROM earned_achievements WHERE username = ? ORDER BY unlock_time DESC;";
	}
	
	@Override
	public void achievementEarned(String userName, int achievementId, long date) {
		try{
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(addAchievementCommand());
			preparedStatement.setString(1, userName);
			preparedStatement.setInt(2, achievementId);
			preparedStatement.setLong(3, date);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String addAchievementCommand() {
		return "INSERT INTO earned_achievements (username, achievement_id, unlock_time) VALUES(?, ?, ?);";
	}

	@Override
	public boolean hasAchievement(String userName, int achievementId) {
		boolean answer = false;
		try{
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(hasAchievementCommand());
			preparedStatement.setString(1, userName);
			preparedStatement.setInt(2, achievementId);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				rs.close();
				con.close();
				return true;
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	private String hasAchievementCommand() {
		return "SELECT * FROM earned_achievements WHERE username = ? AND achievement_id = ?;";
	}

	@Override
	public List<Integer> getFriendsRecentAchievements(Set<String> friendNames, int n) {
		List<String> friends = new ArrayList<String>();
		friends.addAll(friendNames);
		int nFriends = friends.size();
		
		List<Integer> latestFriendsAchievements = new ArrayList<Integer> (); 

		// special cases:
		if(nFriends == 0) return latestFriendsAchievements;	// in case there are no friends
		if(nFriends == 1) {	// case one friend
			latestFriendsAchievements = getEarnedAchievements(friends.get(0));
			return latestFriendsAchievements;
		}
		// case polyfriendism:
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(friendsAchievementsCommand(nFriends));
			for(int i = 0; i < nFriends; i++)
				preparedStatement.setString(i + 1, friends.get(i));
			preparedStatement.setInt(nFriends + 1, n);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) 
				latestFriendsAchievements.add(rs.getInt("achievement_id"));
			preparedStatement.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private String friendsAchievementsCommand(int nFriends) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"SELECT "
				+ "achievement_id "
				+ "FROM earned_achievements "
				+ "WHERE "
				);
		for(int i = 0; i < nFriends - 1; i++)
			sb.append("username LIKE ? OR ");
		sb.append(
				"username LIKE ? "
				+ "ORDER BY unlock_time DESC "
				+ "LIMIT ?;"
				);
		return sb.toString();
	}
}

