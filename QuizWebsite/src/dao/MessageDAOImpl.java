package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import classes.Message.Announcement;
import classes.Message.Challenge;
import classes.Message.FriendRequest;
import classes.Message.Note;
import factory.ClassFactory;

public class MessageDAOImpl implements MessageDAO{

	DataSource dataSource;
	ClassFactory classFactory;
	
	public MessageDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
		classFactory = new ClassFactory();	// TODO: pass as parameter
	}
	
	@Override
	public void addFriendRequest(FriendRequest friendRequest) {
		try{
		Connection con = dataSource.getConnection();
		PreparedStatement preparedStatement =
				con.prepareStatement(addFriendRequestCommand());
		preparedStatement.setString(1, friendRequest.getSenderUserName());
		preparedStatement.setString(2, friendRequest.getGetterUserName());
		preparedStatement.setString(3, friendRequest.getStatus());
		preparedStatement.setDouble(4, friendRequest.getDateSent());
		preparedStatement.setBoolean(5, friendRequest.isSeen());
		preparedStatement.executeUpdate();
		con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String addFriendRequestCommand() {
		return "INSERT INTO friendrequests (sender_username, receiver_username, status, time, seen) VALUES(?, ?, ?, ?, ?);";
	}

	@Override
	public void addNote(Note note) {
		try{
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(addNoteCommand());
			preparedStatement.setString(1, note.getSenderUserName());
			preparedStatement.setString(2, note.getGetterUserName());
			preparedStatement.setString(3, note.getNote());
			preparedStatement.setDouble(4, note.getDateSent());
			preparedStatement.setBoolean(5, note.isSeen());
			preparedStatement.executeUpdate();
			con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	private String addNoteCommand() {
		return "INSERT INTO notes (sender_username, receiver_username, note, time, seen) VALUES(?, ?, ?, ?, ?);";
	}

	@Override
	public void addChallenges(Challenge challenge) {
		try{
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(addChallengeCommand());
			preparedStatement.setString(1, challenge.getSenderUserName());
			preparedStatement.setString(2, challenge.getGetterUserName());
			preparedStatement.setInt(3, challenge.getQuizId());
			preparedStatement.setString(4, challenge.getStatus());
			preparedStatement.setDouble(5, challenge.getDateSent());
			preparedStatement.setBoolean(6, challenge.isSeen());
			preparedStatement.executeUpdate();
			con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	private String addChallengeCommand() {
		return "INSERT INTO challenges (sender_username, receiver_username, quiz, status, time, seen) VALUES(?, ?, ?, ?, ?, ?);";
	}
	
	@Override
	public void addAnnouncement(Announcement announcement) {
		try{
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(addAnnouncementCommand());
			preparedStatement.setString(1, announcement.getAnnouncer());
			preparedStatement.setString(2, announcement.getAnnouncement());
			preparedStatement.setLong(3, announcement.getDate());
			preparedStatement.executeUpdate();
			con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	private String addAnnouncementCommand() {
		return "INSERT INTO announcements (announcer_username, announcement, time) VALUES(?, ?, ?);";
	}

	@Override
	public List<Announcement> getAnnouncements() {
		List<Announcement> answer = new ArrayList<Announcement>();
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement = 
					con.prepareStatement("SELECT *"
							+ "FROM announcements "
							+ "ORDER BY time DESC;");
			ResultSet rs = preparedStatement.executeQuery();
			fillAnnouncementAnswer(answer, rs);
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}

	private void fillAnnouncementAnswer(List<Announcement> answer, ResultSet rs)
	{
		try {
			while(rs.next())
			{
				Announcement nextAnnouncement = classFactory.getAnnouncement
						(rs.getString("announcer_username"), rs.getString("announcement"), rs.getLong("time"));
				nextAnnouncement.setId(rs.getInt("id"));
				answer.add(nextAnnouncement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<FriendRequest> getFriendRequests(String username) {
		List<FriendRequest> answer = new ArrayList<FriendRequest>();
		
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement = 
					con.prepareStatement("SELECT *"
							+ "FROM friendrequests "
							+ "WHERE friendrequests.receiver_username = ? ORDER BY time DESC;");
			preparedStatement.setString(1, username);

			ResultSet rs = preparedStatement.executeQuery();
			fillRequestAnswer(answer, rs);
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}

	private void fillRequestAnswer(List<FriendRequest> answer, ResultSet rs)
	{
		try {
			while(rs.next())
			{
				FriendRequest nextRequest = classFactory.getFriendRequest
						(rs.getString("sender_username"), rs.getLong("time"), rs.getString("receiver_username"), rs.getBoolean("seen"));
				nextRequest.setStatus(rs.getString("status"));
				nextRequest.setId(rs.getInt("id"));
				answer.add(nextRequest);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Challenge> getChallenges(String username) {
	List<Challenge> answer = new ArrayList<Challenge>();
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement = 
					con.prepareStatement("SELECT *"
							+ "FROM challenges "
							+ "WHERE receiver_username = ?  ORDER BY time DESC;");
			preparedStatement.setString(1, username);

			ResultSet rs = preparedStatement.executeQuery();
			fillChallengeAnswer(answer, rs);
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	private void fillChallengeAnswer(List<Challenge> answer, ResultSet rs)
	{
		try {
			while(rs.next())
			{
				Challenge nextChallenge = classFactory.getChallenge
						(rs.getString("sender_username"), rs.getLong("time"), rs.getString("receiver_username"), rs.getInt("quiz"), rs.getBoolean("seen"));
				nextChallenge.setStatus(rs.getString("status"));
				nextChallenge.setId(rs.getInt("id"));
				answer.add(nextChallenge);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Note> getNotes(String username) {
		List<Note> answer = new ArrayList<Note>();
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement = 
					con.prepareStatement("SELECT *"
							+ "FROM notes "
							+ "WHERE receiver_username = ?  ORDER BY time DESC;");
			preparedStatement.setString(1, username);

			ResultSet rs = preparedStatement.executeQuery();
			fillNoteAnswer(answer, rs);
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}

	private void fillNoteAnswer(List<Note> answer, ResultSet rs)
	{
		try {
			while(rs.next())
			{
				Note nextNote = classFactory.getNote
						(rs.getString("sender_username"), rs.getLong("time"), rs.getString("note"), rs.getString("receiver_username"), rs.getBoolean("seen"));
				nextNote.setId(rs.getInt("id"));
				answer.add(nextNote);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public FriendRequest getFriendRequest(int id)
	{
		FriendRequest answer = null;
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement = 
					con.prepareStatement("SELECT *"
							+ "FROM friendrequests "
							+ "WHERE id = ? ;");
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next())
			{
				answer = classFactory.getFriendRequest
						(rs.getString("sender_username"), rs.getLong("time"), rs.getString("receiver_username"), rs.getBoolean("seen"));
				answer.setStatus(rs.getString("status"));
				answer.setId(rs.getInt("id"));
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	public Note getNote(int id)
	{
		Note answer = null;
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement = 
					con.prepareStatement("SELECT *"
							+ "FROM notes "
							+ "WHERE id = ? ;");
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next())
			{
				answer = classFactory.getNote
						(rs.getString("sender_username"), rs.getLong("time"), rs.getString("note"), rs.getString("receiver_username"), rs.getBoolean("seen"));
				answer.setId(rs.getInt("id"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	public Challenge getChallenge(int id)
	{
		Challenge answer = null;
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement = 
					con.prepareStatement("SELECT *"
							+ "FROM challenges "
							+ "WHERE id = ? ;");
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next())
			{
				answer = classFactory.getChallenge
						(rs.getString("sender_username"), rs.getLong("time"), rs.getString("receiver_username"), rs.getInt("quiz"), rs.getBoolean("seen"));
				answer.setStatus(rs.getString("status"));
				answer.setId(rs.getInt("id"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}

}
