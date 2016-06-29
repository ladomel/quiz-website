package dao;

import org.junit.Before;
import org.junit.Test;

import classes.Message.Announcement;
import classes.Message.Challenge;
import classes.Message.FriendRequest;
import classes.Message.Note;
import factory.ClassFactory;

public class MessageDAOImplTest {

	private DAOInstances factory;
	private MessageDAO messageDAO;
	private ClassFactory classFactory;

	@Before
	public void setUp() throws Exception {
		factory = new DAOInstances();
		factory.init();
		messageDAO = factory.getMessageDAO();
		classFactory = new ClassFactory();
	}

	@Test
	public void testFriendRequest() {
		FriendRequest request = classFactory.getFriendRequest("a", 20, "b", false);
		messageDAO.addFriendRequest(request);

		System.out.println(messageDAO.getFriendRequests("b").size());
		System.out.println(messageDAO.getFriendRequests("b").toString());
		System.out.println(messageDAO.getFriendRequest(2).toString());
	}
	
	@Test
	public void testNote() {
		Note note = classFactory.getNote("a", 11, "haa", "c", false);
		messageDAO.addNote(note);
		
		System.out.println(messageDAO.getChallenges("haa").size());
		System.out.println(messageDAO.getChallenges("haa").toString());
		System.out.println(messageDAO.getNote(2).toString());

	}
	
	@Test
	public void testChallenge() {
		Challenge challenge = classFactory.getChallenge("a", 11, "haa", 1, false);
		messageDAO.addChallenges(challenge);
		
		System.out.println(messageDAO.getChallenges("haa").size());
		System.out.println(messageDAO.getChallenges("haa").toString());
		System.out.println(messageDAO.getNote(2).toString());
	}
	
	@Test
	public void testAnnouncement() {
		Announcement announcement = classFactory.getAnnouncement("a", "announ", 30);
		messageDAO.addAnnouncement(announcement);
		
		System.out.println(messageDAO.getAnnouncements().toString());
	}
	
	@Test
	public void testFriendRequest2() {
		FriendRequest request = classFactory.getFriendRequest("a", 20, "b", false);
		System.out.println(messageDAO.friendRequestExists("a", "b"));
		System.out.println(messageDAO.friendRequestExists("b", "a"));
		System.out.println(messageDAO.friendRequestExists("a", "c"));
	}
}
