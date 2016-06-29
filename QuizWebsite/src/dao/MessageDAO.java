package dao;

import java.util.*;

import classes.Message.*;

public interface MessageDAO {
	public void addFriendRequest(FriendRequest friendRequest);
	public void addNote(Note note);
	public void addChallenges(Challenge challenge);
	public void addAnnouncement(Announcement announcement);
	
	public List<Announcement> getAnnouncements();
	public List<FriendRequest> getFriendRequests(String username);
	public List<Challenge> getChallenges(String username);
	public List<Note> getNotes(String username);
	
	
}
