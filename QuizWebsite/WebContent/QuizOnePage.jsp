<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="classes.*" %>
<%@ page import="java.util.*" %>
<%@ page import="classes.question.Abstract.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	Quiz quiz = (Quiz) request.getSession().getAttribute("Quiz");
%>
<title><%= quiz.getQuizName() %></title>
<style>
	html, body {
		height: 100%;
		width: 100%;
		margin: 0px;
		padding: 0px;
	}
	object {
		border: black solid 5px;
		min-height: 400px;
		max-height: 400px;
		min-width: 1000px;
		max-width: 1000px;
		position: relative;
		left: 50%;
		margin-left: -500px;
	}	
</style>
</head>
<body>
	<div id="toppanel">
		<span id="quizname"><%= quiz.getQuizName() %></span>
		<span id="hours">1</span>hours,
		<span id="mins">5</span>mins and 
		<span id="secs">0</span>secs remaining;
	</div>
	
<%	
	/*int numQuestions = ((ArrayList<Question>) request.getSession().getAttribute("Questions")).size();
	
	for (int i=0;i<numQuestions;i++){
		out.print("<iframe name='question" + i + "' src='Question.jsp?id=" + i + "' id='" + i + "' />");
	}*/
%>
	
	
	<script type="text/javascript">
		var startTime = new Date().getTime();
		function countDown(){
			var d = new Date().getTime();
			var k = new Date(d - startTime);
			var h = k.getHours();
			var m = k.getMinutes();
			var s = k.getSeconds(); 
			updateTime(h,m,s);
		}
		function updateTime(h,m,s){
			document.getElementById("hours").innerHTML = h;
			document.getElementById("mins").innerHTML = m;
			document.getElementById("secs").innerHTML = s;
		}
		var j = setInterval("countDown()",1000);
	</script>
</body>
</html>