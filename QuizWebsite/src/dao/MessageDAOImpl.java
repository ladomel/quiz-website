package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addChallenges(Challenge challenge) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAnnouncement(Announcement announcement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Announcement> getAnnouncements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FriendRequest> getFriendRequests(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Challenge> getChallenges(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Note> getNotes(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
