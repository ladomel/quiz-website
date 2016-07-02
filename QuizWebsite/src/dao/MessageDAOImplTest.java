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
	
	@Test 
	public void testNumPendingFriendRequests()
	{
		FriendRequest request1 = classFactory.getFriendRequest("test1", 20, "test2", false);
		FriendRequest request2 = classFactory.getFriendRequest("test3", 20, "test2", false);
		FriendRequest request3 = classFactory.getFriendRequest("test4", 20, "test2", true);
		FriendRequest request4 = classFactory.getFriendRequest("test2", 20, "test1", true);
		
		messageDAO.addFriendRequest(request1);
		messageDAO.addFriendRequest(request2);
		messageDAO.addFriendRequest(request3);
		messageDAO.addFriendRequest(request4);
		
		System.out.println("Number of pending by test 2: " + messageDAO.numPendingFriendRequests("test2"));
	}
	
	@Test
	public void testNumPendingChallenges()
	{
		Challenge chal1 = classFactory.getChallenge("test1", 10, "test2", 2, false);
		Challenge chal2 = classFactory.getChallenge("test3", 20, "test2", 3, true);
		Challenge chal3 = classFactory.getChallenge("test4", 30, "test2", 2, false);
		Challenge chal4 = classFactory.getChallenge("test2", 10, "test1", 2, false);
		
		messageDAO.addChallenges(chal1);
		messageDAO.addChallenges(chal2);
		messageDAO.addChallenges(chal3);
		messageDAO.addChallenges(chal4);
		
		messageDAO.updateChallengeStatus(6, "Not pending");
		
		System.out.println("Number of pending by test 2: " + messageDAO.numPendingChallenges("test2"));
	}
	
	@Test 
	public void testNumUnseenNotes()
	{
		Note note1 = classFactory.getNote("test1", 20, "hi", "test2", false);
		Note note2 = classFactory.getNote("test1", 20, "ho", "test2", true);
		Note note3 = classFactory.getNote("test1", 20, "hu", "test2", false);
		Note note4 = classFactory.getNote("test2", 20, "ha", "test3", false);
		
		messageDAO.addNote(note1);
		messageDAO.addNote(note2);
		messageDAO.addNote(note3);
		messageDAO.addNote(note4);
			
		System.out.println("Number of unseen by test 2: " + messageDAO.numUnseenNotes("test2"));	
	}
	

	@Test
	public void testFriendRequestExists()
	{
		FriendRequest request1 = classFactory.getFriendRequest("test13", 20, "test23", false);
		messageDAO.addFriendRequest(request1);
		System.out.println("Exists? has to be true: " + messageDAO.friendRequestExists("test13", "test23"));

		messageDAO.updateFriendRequestStatus(33, "Seen");
		System.out.println("Exists? has to be false: " + messageDAO.friendRequestExists("test13", "test23"));
		
		FriendRequest request2 = classFactory.getFriendRequest("test13", 20, "test23", false);
		messageDAO.addFriendRequest(request2);
		System.out.println("Exists? has to be true: " + messageDAO.friendRequestExists("test13", "test23"));
	}
}