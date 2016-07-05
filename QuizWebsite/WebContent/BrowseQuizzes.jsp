<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import="classes.*" %>
    <%@ page import="dao.*" %>
    <%@ page import="classes.Message.*" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/browsequizzes.css">
<link rel="stylesheet" type="text/css" href="css/header.css">
<title>Browse Quizzes</title>
</head>
<body>
	<%
		String toppanel; 
		if (request.getSession().getAttribute("MasterUser") == null) toppanel = "toppanel-loggedout.jsp";
		else toppanel = "toppanel-loggedin.jsp";
		
		QuizDAO qD = (QuizDAO) request.getServletContext().getAttribute("quizDAO");
		Set<String> cat = qD.getCategories();
		List<Quiz> quizzes = (List<Quiz>) request.getAttribute("Quizzes");
		
		
	%>
	<div id="centerpanel">
	<div id='categories'>
		Choose Category: <br>
		<a href="BrowseQuizzes?category=all"><span class='catspan'>All</span></a> <br>
		<%
			if (cat != null){
			for (String s : cat){
				out.print("<a href='BrowseQuizzes?category=" + s + "' >");
				out.print("<span class='catspan'>" + s  + "</span>");
				out.print("</a><br>");
			}}
		%>
	</div>
	
	<div id="list">
	Quizzes: <br>
		<%
			if (quizzes != null){
			for (Quiz quiz : quizzes){
				out.print("<a href='Quiz?id=" + quiz.getId() + "' >");
				out.print("<span class='listentry'>" + quiz.getQuizName() + "  -     " + quiz.getDescription() + "</span>");
				out.print("</a><br>");
			}}
		
		%>
	</div>
	</div>
	<div id="toppanel">
		<jsp:include page='<%= toppanel %>' />
	</div>
	
	
</body>
</html>