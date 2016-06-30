package dao;

import org.apache.catalina.ant.ResourcesTask;
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
		request.setStatus("Pending");
		messageDAO.addFriendRequest(request);

		System.out.println(messageDAO.getFriendRequests("b").size());
		System.out.println(messageDAO.getFriendRequests("b").toString());
		System.out.println(messageDAO.getFriendRequest(2).toString());
	}
	
	@Test
	public void testNote() {
		Note note = classFactory.getNote("a", 11, "haa", "cd", false);
		messageDAO.addNote(note);
		
		System.out.println(messageDAO.getNotes("c").size());
		System.out.println(messageDAO.getNotes("c").toString());
		System.out.println(messageDAO.getNotes("a").size());
		System.out.println(messageDAO.getNotes("a").toString());
		//System.out.println(messageDAO.getNote(2).toString());

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
	
	@Test
	public void seenChallengeTest() {
		System.out.println(messageDAO.getChallenge(5).toString());
		messageDAO.seenChallenge(5);
		System.out.println(messageDAO.getChallenge(5).toString());
	}
	
	@Test
	public void seenFriendRequestTest() {
		System.out.println(messageDAO.getFriendRequest(4).toString());
		messageDAO.seenFriendRequest(4);
		System.out.println(messageDAO.getFriendRequest(4).toString());
	}
	
	@Test
	public void seenNoteTest() {
		System.out.println(messageDAO.getNote(4).toString());
		messageDAO.seenNote(4);
		System.out.println(messageDAO.getNote(4).toString());
	}
	
	@Test
	public void unseenTest() {
		System.out.println(messageDAO.getNumUnseen("haa"));
		Challenge challenge = classFactory.getChallenge("a", 11, "haa", 1, false);
		messageDAO.addChallenges(challenge);
		Note note = classFactory.getNote("sen", 12, "ih", "haa", false);
		messageDAO.addNote(note);
		FriendRequest request = classFactory.getFriendRequest("send", 12, "haa", false);
		messageDAO.addFriendRequest(request);
		System.out.println(messageDAO.getNumUnseen("haa"));
	}
	
	@Test
	public void updateFriendRequestTest() {
		System.out.println(messageDAO.getFriendRequest(4).toString());
		messageDAO.updateFriendRequestStatus(4, "I am new");
		System.out.println(messageDAO.getFriendRequest(4).toString());
	}
	
	@Test
	public void updateChallengeTest() {
		Challenge challenge = classFactory.getChallenge("a", 11, "haa", 1, false);
		messageDAO.addChallenges(challenge);
		
		System.out.println(messageDAO.getChallenge(1).toString());
		messageDAO.updateChallengeStatus(1, "I am new");
		System.out.println(messageDAO.getChallenge(1).toString());
	}
}
