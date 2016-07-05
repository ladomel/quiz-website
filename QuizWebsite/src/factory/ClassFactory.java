package factory;
import java.util.List;
import java.util.Set;

import classes.Achievement;
import classes.Answer;
import classes.Quiz;
import classes.User;
import classes.Message.Announcement;
import classes.Message.Challenge;
import classes.Message.FriendRequest;
import classes.Message.Note;
import classes.question.QuestionFB;
import classes.question.QuestionMA;
import classes.question.QuestionMC;
import classes.question.QuestionMCH;
import classes.question.QuestionMCMA;
import classes.question.QuestionPR;
import classes.question.QuestionQR;
import classes.question.QuestionTF;

public class ClassFactory {
	// Each method returns corresponding class.
	
	public User getUser(String userName, String password, String salt)
	{
		return new User(userName, password, salt);
	}
	
	public Quiz getQuiz(String userName, String quizName, String description)
	{
		return new Quiz(userName, quizName, description);
	}
	
	public QuestionQR getQuestionQR(String newProblem, int grade, Set<String> newAnswers)
	{
		return new QuestionQR(newProblem, grade, newAnswers);
	}
	
	public QuestionPR getQuestionPR(String newProblem, int grade, String pictureURL, Set<String> newAnswers)
	{
		return new QuestionPR(newProblem, grade, pictureURL, newAnswers);
	}
	
	public QuestionFB getQuestionFB(String newProblem, int grade, List<Set<String>> newAnswers)
	{
		return new QuestionFB(newProblem, grade, newAnswers);
	}
	
	public QuestionMA getQuestionMA(String newProblem, int grade, boolean ordered, List<Set<String>> newAnswers, int numAnswers)
	{
		return new QuestionMA(newProblem, grade, ordered, newAnswers, numAnswers);
	}
	
	public QuestionMC getQuestionMC(String newProblem, int grade, String correctAnswer, Set<String> wrongAnswers)
	{
		return new QuestionMC(newProblem, grade, correctAnswer, wrongAnswers);
	}
	
	public QuestionMCMA getQuestionMCMA(String newProblem, int grade,  List<String> correctAnswers, List<String> wrongAnswers)
	{
		return new QuestionMCMA(newProblem, grade, correctAnswers, wrongAnswers);
	}
	
	public QuestionTF getQuestionTF(String newProblem, int grade,  List<String> propositions, List<String> answers)
	{
		return new QuestionTF(newProblem, grade, propositions, answers);
	}
	
	public QuestionMCH getQuestionMCH(String newProblem, int grade, List<String> questions, List<String> rightAnswers, List<String> wrongAnswers)
	{
		return new QuestionMCH(newProblem, grade, questions, rightAnswers, wrongAnswers);
	}
	
	public classes.Result getResult(String userName, int quizId)
	{
		return new classes.Result(userName, quizId);
	}
	
	public Answer getAnswer(List<String> userAnswer)
	{
		return new Answer(userAnswer);
	}
	
	public Announcement getAnnouncement(String announcer, String announcement, long date){
		return new Announcement(announcer, date, announcement); 
	}
	
	public Note getNote(String sender,  long date, String note, String getter, boolean seen){
		return new Note(sender,date,note,getter, seen); 
	}
	
	public Challenge getChallenge(String sender,  long date, String getter, int quizId, boolean seen){
		return new Challenge(sender,date,getter,quizId, seen); 
	}
	
	public FriendRequest getFriendRequest(String sender,  long date, String getter, boolean seen){
		return new FriendRequest(sender,date,getter, seen); 
	}
	
	public Achievement getAchievement(String name, String pictureURL, String description)
	{
		return new Achievement(name, pictureURL, description);
	}
}
