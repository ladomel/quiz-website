<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="classes.*" %>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/header.css">
<link rel="stylesheet" type="text/css" href="css/review.css">
<title>Quiz Reviews</title>
</head>
<body>
	<%
	String toppanel; 
	if (request.getSession().getAttribute("MasterUser") == null) toppanel = "toppanel-loggedout.jsp";
	else toppanel = "toppanel-loggedin.jsp";
	
		Quiz quiz = (Quiz) request.getAttribute("Quiz");
		List<Review> re = (List<Review> ) request.getAttribute("List");
	%>
	<div id="centerpanel">
	<h2>Quiz Average Rating: <%= request.getAttribute("Average") %></h2>
	<div id="list">
		<%
			for (int i=0;i<re.size();i++){
				out.print("<div class='ls'>");
					out.print("<div class='rating'>Rating: "  +  re.get(i).getRating() + "/10</div>");
					out.print("<div class='author'>Author: "  +  re.get(i).getUserName() + "</div>");
					out.print("<div class='text'>Review: "  +  re.get(i).getText() + "</div>");
				out.print("</div>");
			}
		%>
	</div>
	
	
	<form action="MakeReview" method="post">
		<textarea type="text" cols="60" rows="6" name="text" id="stmnt" Placeholder="Write a review here"></textarea> <br>
		<input name="quizId" value="<%= quiz.getId() %>" type="hidden" /> <br>
		Choose Rating: 
		<select name='rating'>
			<%
				for (int i=1;i<=10;i++){
					out.print("<option value='" + i + "'>" + i + "</option>");
				}
			%>
		</select> <br>
		<input id="submit" value="Submit Review" type="submit" />
	</form>
	</div>
	
	<div id="toppanel">
		<jsp:include page='<%= toppanel %>' />
	</div>
</body>
</html>