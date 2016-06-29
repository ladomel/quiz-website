package dao;

import org.junit.Before;
import org.junit.Test;

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
		
		//System.out.println(messageDAO.getFriendRequests("b").toString());
	}
	
	@Test
	public void testNote() {
		Note note = classFactory.getNote("a", 11, "haa", "c", false);
		messageDAO.addNote(note);
		
		System.out.println(messageDAO.getChallenges("haa").size());
		System.out.println(messageDAO.getChallenges("haa").toString());

	}
	
	@Test
	public void testChallenge() {
		Challenge challenge = classFactory.getChallenge("a", 11, "haa", 1, false);
		messageDAO.addChallenges(challenge);
		
	//	System.out.println(messageDAO.getChallenges("haa").size());
	//	System.out.println(messageDAO.getChallenges("haa").toString());
	}
	
	
	
	
}
