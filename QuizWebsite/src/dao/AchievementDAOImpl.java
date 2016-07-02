package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import factory.ClassFactory;

public class AchievementDAOImpl implements AchievementDAO {

	DataSource dataSource;
	ClassFactory classFactory;

	public AchievementDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
		classFactory = new ClassFactory();	 
	}

	@Override
	public List<Integer> getEarnedAchievements(int userId) {
		List<Integer> answer = new ArrayList<Integer>();
		try{
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(getEarnedAchievementsCommand());
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
				answer.add(rs.getInt("achievement_id"));
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}

	private String getEarnedAchievementsCommand(){
	return "SELECT * FROM earned_achievements WHERE user_id = ? ORDER BY unlock_time DESC;";
	}
	
	@Override
	public void achievementEarned(int userId, int achievementId, long date) {
		try{
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(addAchievementCommand());
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, achievementId);
			preparedStatement.setLong(3, date);
			preparedStatement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String addAchievementCommand() {
		return "INSERT INTO earned_achievements (user_id, achievement_id, unlock_time) VALUES(?, ?, ?);";
	}

	@Override
	public boolean hasAchievement(int userId, int achievementId) {
		boolean answer = false;
		try{
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(hasAchievementCommand());
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, achievementId);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) return true;
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	private String hasAchievementCommand() {
		return "SELECT * FROM earned_achievements WHERE user_id = ? AND achievement_id = ?;";
	}
}

