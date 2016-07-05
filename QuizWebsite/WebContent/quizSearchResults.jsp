<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="classes.*" %>

<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Result for: <%= request.getParameter("quizname") %></title>
</head>
<body>
	<div id="centerpanel">
		<ul id="list">
			<%
				List<Quiz> quizzes = (List<Quiz>)request.getAttribute("Quizzes");
				for (Quiz quiz: quizzes){
					out.print("<li> <a href='Quiz?id=" + quiz.getId() + "'> " + quiz.getQuizName() + "</a> </li>");
				}
			%>
		</ul>
	</div>
</body>
</html>