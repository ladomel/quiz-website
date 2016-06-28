package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import classes.Result;

public class ResultDAOImpl implements ResultDAO {

	private DataSource dataSource;
	
	public ResultDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insertResult(Result result) {
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement = 
					con.prepareStatement(inserResultCommand());
			preparedStatement.setString(1, result.getUserName());
			preparedStatement.setInt(2, result.getQuizId());
			preparedStatement.setInt(3, result.getFinalGrade());
			preparedStatement.setLong(4, result.getTimeStarted());
			preparedStatement.setLong(5, result.getTimeTaken());
			preparedStatement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private String inserResultCommand() {
		return "INSERT INTO results " + 
				"(user_id, quiz_id, final_grade, start_time, time_taken) " + 
				"VALUES" + 
				"((SELECT id FROM users WHERE username LIKE ?), ?, ?, ?, ?);";
	}

	@Override
	public List<Result> getResult(String userName, int quizId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Result> getRecentResults(String userName, int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Result> getRecentResults(int quizId, int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Result> getBestResults(String userName, int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Result> getBestResults(int quizId, int n) {
		// TODO Auto-generated method stub
		return null;
	}

}
