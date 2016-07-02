package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import classes.Message.FriendRequest;
import factory.ClassFactory;

public class AchievementDAOImpl implements AchievemetDAO {

	DataSource dataSource;
	ClassFactory classFactory;

	public AchievementDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
		classFactory = new ClassFactory();	 
	}

	@Override
	public List<Integer> getEarnedAchievements(int userId) {
		// TODO Auto-generated method stub
		return null;
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
		return "INSERT INTO earned_achievenets (user_id, achievement_id, unlock_time) VALUES(?, ?, ?);";
	}
}

