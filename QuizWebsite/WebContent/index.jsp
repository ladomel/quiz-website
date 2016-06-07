<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Main.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="stylesheet" type="text/css" href="css/header.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<title><% out.print("Welcome to " + Constants.SITE_NAME); %></title>
</head>
<body>
	<%
		String toppanel; 
		if (request.getSession().getAttribute("MasterUser") == null) toppanel = "toppanel-loggedout.jsp";
		else toppanel = "toppanel-loggedin.jsp";
	%>
	
	
	<div id="centerpanel">
		<div id="secondbox">
			<span id="friendsactivity">
				<div class="divtitle">Friends Activity</div>
				<div class="list">
				<%
					
				%>
				</div>
			</span>
			<span class="quizzes" id="recentlytakenquizzes">
				<div class="divtitle">Recently Taken Quizzes</div>
				<div class="list">
				<%
					
				%>
				</div>
			</span>
			<span class="quizzes" id="recentlycreatedquizzes">
				<div class="divtitle">Recently Created Quizzes</div>
				<div class="list">
				<%
					
				%>
				</div>
			</span>
			
			<% 
				if (request.getSession().getAttribute("MasterUser") == null){
					out.println("<span id=\"loginlabel\">Log In to See More!</span>");		
				}
			%>
			
		</div>
		
		<div class="breaker">User Activity:</div>
		
		<div id="firstbox">	
			<span id="announcements">
				<div class="divtitle">Announcements</div>
				<div class="list">
				<%
					ArrayList<String> ann = new ArrayList<String>();
					ann.add("Jandaba1");ann.add("Jandaba2");ann.add("Jandaba3");
					ann.add("Jandaba1");ann.add("Jandaba2");ann.add("Jandaba3");
					ann.add("Jandaba1");ann.add("Jandaba2");ann.add("Jandaba3");
					ann.add("Jandaba1");ann.add("Jandaba2");ann.add("Jandaba3");
					for (int i=0;i<ann.size();i++){
						out.println("<div class=\"listentry\">" + ann.get(i) + "</div>");	
					}
				%>
				</div>
			</span>
			<span class="quizzes" id="recentquizzes">
				<div class="divtitle">Recent Quizzes</div>
				<div class="list">
				<%
					ann = new ArrayList<String>();
					ann.add("Jandaba1");ann.add("Jandaba2");ann.add("Jandaba3");
					ann.add("Jandaba1");ann.add("Jandaba2");ann.add("Jandaba3");
					ann.add("Jandaba1");ann.add("Jandaba2");ann.add("Jandaba3");
					ann.add("Jandaba1");ann.add("Jandaba2");ann.add("Jandaba3");
					ann.add("Jandaba1");ann.add("Jandaba2");ann.add("Jandaba3");
					ann.add("Jandaba1");ann.add("Jandaba2");ann.add("Jandaba3");
					for (int i=0;i<ann.size();i++){
						out.println("<div class=\"listentry\">" + ann.get(i) + "</div>");	
					}
				%>
				</div>
			</span>
			<span class="quizzes" id="popularquizzes">
				<div class="divtitle">Popular Quizzes</div>
				<div class="list">
				<%
					ann = new ArrayList<String>();
					ann.add("Jandaba1");ann.add("Jandaba2");ann.add("Jandaba3");
					ann.add("Jandaba1");ann.add("Jandaba2");ann.add("Jandaba3");
					ann.add("Jandaba1");ann.add("Jandaba2");ann.add("Jandaba3");
					ann.add("Jandaba1");ann.add("Jandaba2");ann.add("Jandaba3");
					ann.add("Jandaba1");ann.add("Jandaba2");ann.add("Jandaba3");
					ann.add("Jandaba1");ann.add("Jandaba2");ann.add("Jandaba3");
					for (int i=0;i<ann.size();i++){
						out.println("<div class=\"listentry\">" + ann.get(i) + "</div>");	
					}
				%>
				</div>
			</span>
		</div>
		<%
			if (request.getSession().getAttribute("MasterUser") != null){
				out.println("<a href=\"CreateQuiz.jsp\"><button id=\"createaquiz\">Create a Quiz!</button></a>");		
			}
		%>
		
	</div>
	
	<div id="toppanel">
		<jsp:include page='<%= toppanel %>' />
	</div>
</body>
</html>