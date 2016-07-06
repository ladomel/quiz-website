<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="classes.*" %><%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" type="text/css" href="css/quizresults.css">
<link rel="stylesheet" type="text/css" href="css/header.css">
<title>Quiz Results</title>
</head>
<body>
	<%
	String toppanel; 
	User master = (User)request.getSession().getAttribute("MasterUser"); 
	if (master == null) toppanel = "toppanel-loggedout.jsp";
	else toppanel = "toppanel-loggedin.jsp";
	
		Result result = (Result) request.getAttribute("Result");
		Quiz quiz = (Quiz) request.getAttribute("Quiz");
	%>
	<div id="centerpanel">
	<h1>Your Score: <%= result.getFinalGrade() %>/<%= quiz.getMaxScore() %> </h1>
	<h2>Time Taken: <%= result.getTimeTaken()/(1000*60) %>mins</h2>

	<%
		if (session.getAttribute("Challenger")!=null && (int)session.getAttribute("ChallengerQuiz") == quiz.getId()) out.print("<h2>" +session.getAttribute("Challenger") + "'s Hight Score was: " +
				session.getAttribute("ChallengerScore") + "/" + quiz.getMaxScore()  +" </h2>");
	%>

	<h2>You are <%= request.getAttribute("Ranking") %>th in the rankings for this quiz!</h2>
	
	<div id="friendsrecent">
			<div class="divtitle">Friend's Recent Results:</div>
			<div class="inf">
				<span class="usrname">User</span>
				<span class="date">Date</span>
				<span class="scr" >Score</span>
				<span class="timetaken">Time Taken</span>
			</div>
			<div class="list" id="j">
			<% 
			List<classes.Result> recent = (List<classes.Result>) request.getAttribute("FriendsRecentResults");
			List<String> frnds = (List<String>) request.getAttribute("FriendsRecentUsers");
			if (recent != null && frnds!= null)
			for (int i=0;i<recent.size();i++){ 
				out.print("<div class='listentry'>");
					
				out.print("<a href='Profile?username=" + frnds.get(i) + "'><span class='entryuser'>" + frnds.get(i) + "</span><a/>");
				out.print("<span class='entrydate'>" + new Date(recent.get(i).getTimeStarted()) + "</span>");
				out.print("<span class='entryscore'>" + recent.get(i).getFinalGrade() + "</span>");
				out.print("<span class='entrytime'>" + recent.get(i).getTimeTaken()/(1000 * 60) + "mins</span>");
				
				out.print("</div>");	
			}
			%>
			</div>
		</div>
	
	
	<h3>If you want to see more about this quiz, then follow <a href="Quiz?id=<%= quiz.getId() %>">this link</a></h3>
	<br>Or <a href="index">Return to Main Page</a>
	</div>
	<div id="toppanel">
		<jsp:include page='<%= toppanel %>' />
	</div>
</body>
</html>