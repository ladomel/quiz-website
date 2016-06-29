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
	
	/**
	 * Returns friendRequest by id, null if not found.
	 * 
	 * @param id - id of request
	 * @return request - request with id.
	 */
	public FriendRequest getFriendRequest(int id);
	
	/**
	 * Returns note by id, null if not found.
	 * 
	 * @param id - id of note
	 * @return note - note with id
	 */
	public Note getNote(int id);
	
	/**
	 * Returns Challenge by id, null if not found.
	 * 
	 * @param id - id of Challenge
	 * @return Challenge - Challenge with id.
	 */
	public Challenge getChallenge(int id);
	
	/**
	 * Returns true if friend request was sent.
	 * 
	 * @return true if friend request was sent
	 */
	public boolean friendRequestExists(String senderUserName, String receiverUserName);
	
	/**
	 * Updates the challenge to be seen.
	 * 
	 * @param id of the challenge
	 */
	public void seenChallenge(int id);
	
	/**
	 * Updates the friend request to be seen.
	 * 
	 * @param id of the friend request
	 */
	public void seenFriendRequest(int id);
	
	/**
	 * Updates the note to be seen.
	 * 
	 * @param id of the note
	 */
	public void seenNote(int id);
	
	/**
	 * Returns number of unseen messages for user.
	 * 
	 * @param userName - username of user
	 */
	public int getNumUnseen(String username);
	
}
