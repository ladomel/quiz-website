<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/header.css">
<link rel="stylesheet" type="text/css" href="../css/createmessages.css">
<title></title>
</head>
<body>	
<%
		String toppanel; 
		if (request.getSession().getAttribute("MasterUser") == null) toppanel = "toppanel-loggedout.jsp";
		else toppanel = "toppanel-loggedin.jsp";
	%>
	<form action="SendChallenge" method="post">
		<input type="hidden" name="quizId" value='<%= request.getParameter("quizId") %>'>	
		Choose Friends: <br>
		<div id="friends">
			<input type="checkbox" name="0" value="Jandaba"> <a href='Profile?username='>Jandaba</a> <br>
			<input type="checkbox" name="1"  value="Jandaba?"> <a href='Profile?username='>Jandaba?</a> <br>
		</div>
		<input type="submit" id="send" value="Send">
	</form>
	
	<div id="toppanel">
		<jsp:include page='<%= toppanel %>' />
	</div>
	
</body>
</html>