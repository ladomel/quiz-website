<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="classes.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/header.css">
<link rel="stylesheet" type="text/css" href="css/messages.css">
<title>Messages</title>
</head>
<body>
	<%
		String toppanel; 
		if (request.getSession().getAttribute("MasterUser") == null) toppanel = "toppanel-loggedout.jsp";
		else toppanel = "toppanel-loggedin.jsp";
		User user = (User) session.getAttribute("MasterUser");
		String text = "ra jandaba ginda to?";
	%>
	<div id="centerpanel">
		<div id="notebox">
		<div class="title">Notes:</div>
			<div class="noteSeen" onclick="alert('<%= text %>');">
				<div class="from">FROM: jandaba TO: jandabaajsdasd(10.10.10)</div>
				<div class="text">NOTE: ra jandaba ginda to?</div>
			</div>
			<div class="note" onclick="alert('<%= text %>');">
			</div>
		</div>
		<div id="friendrequestbox">
		<div class="title">Friend Requests:</div>
			<div class="requestSeen">
				Friend Request FROM: <a href="">USER</a> <br>
				<form action="AcceptFriendRequest" method="post">
					<input name="requestId" value="5" type="hidden">
					<input name="status" class="accept" type="submit" value="Accept">
				</form>
				<form action="AcceptFriendRequest" method="post">
					<input name="requestId" value="5" type="hidden">
					<input name="status" class="accept" type="submit"  value="Decline">
				</form>
			</div>
			<div class="request">
				
			</div>
		</div>
		<div id="challengebox">
		<div class="title">Challenges:</div>
			<div class="challengeSeen">
				FROM: <a href="">USER</a> <br>
				QUIZ: <a href="">QUIZ</a> <br>
				<form action="AcceptChallenge" method="post">
					<input name="challengeId" value="5" type="hidden">
					<input name="status" class="accept" type="submit" value="Accept">
				</form>
				<form action="AcceptChallenge" method="post">
					<input name="challengeId" value="5" type="hidden">
					<input name="status" class="accept" type="submit"  value="Decline">
				</form>
			</div>
			<div class="challenge">
				
			</div>
		</div>
	</div>
	
	<div id="toppanel">
		<jsp:include page='<%= toppanel %>' />
	</div>
</body>
</html>