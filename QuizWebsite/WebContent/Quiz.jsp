<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="classes.*" %>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/quiz.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<%
	String toppanel; 
	if (request.getSession().getAttribute("MasterUser") == null) toppanel = "toppanel-loggedout.jsp";
		else toppanel = "toppanel-loggedin.jsp";
	
	Quiz quiz = (Quiz)request.getAttribute("Quiz");
	User master = (User) request.getSession().getAttribute("MasterUser");
	UserDAO uD = (UserDAO) request.getServletContext().getAttribute("userDAO");
	
	
%>
<title><%= quiz.getQuizName() %></title>
</head>
<body>
	<div id="centerpanel">
		<span id="qname"><%= quiz.getQuizName() %> (Cat: <%=quiz.getCategory() %>)</span>
		<span id="qdescription"><%= quiz.getDescription() %></span>
		<span id="qauthor"><a href='Profile?username=<%= quiz.getUserName() %>'>Author: <%= quiz.getUserName() %></a></span>
		<%
			
			if (master != null) {
				out.print("<a href=\"CreateChallenge.jsp?quizId="+ quiz.getId() + "\"><button id=\"sendchallenge\">Challenge your Friends</button></a>");
				if (master.equals(quiz.getUserName())) {
					out.print("<button id='editdescr'>Edit Description</button>");
				}
				out.print("<a href=\"Reviews?quizId=" + quiz.getId() + "\"><button id=\"reviews\">View Reviews</button></a>");
				
			} 
	
		%>
		
		
		
		<div id="topscores">
			<div class="divtitle">Top Scores:</div>
			<div class="inf">
				<span class="usrname">Username</span>
				<span class="scr">Score</span>
				<span class="timetaken">Time Taken</span>
				<span class="date">Date</span>
			</div>
			<div class="list">
			<%
			List<Result> res = ( List<Result>) request.getAttribute("BestResults");
			List<String> users = (List<String> ) request.getAttribute("BestResultsUsers");
				
			if (res != null && users != null){
				int i = 0;
				for (Result r : res){
					out.print("<div class='listentry'>");
					
					out.print("<span class='uname'><a href='Profile?username=" + users.get(i) + "'>" + users.get(i) + "</a></span>");
					out.print("<span class='scor'>" + r.getFinalGrade() + "</span>");
					out.print("<span class='tim'>" + r.getTimeTaken()/(1000 * 60) + "mins</span>");
					out.print("<span class='dt'>" + new Date(r.getTimeStarted()) + "</span>");
					
					out.print("</div>");
					i++;
				}
			}
			%>
			</div>
		</div>
		<div id="recentscores">
			<div class="divtitle">Recent Scores:</div>
			<div class="inf">
			<span class="usrname">Username</span>
			<span class="scr">Score</span>
			<span class="timetaken">Time Taken</span>
			</div>
			<div class="list">
			<%
			 res = ( List<Result>) request.getAttribute("RecentTakersQuizzes");
			users = (List<String> ) request.getAttribute("RecentTakersUsers");
				
			if (res != null && users != null){
				int i = 0;
				for (Result r : res){
					out.print("<div class='listentry'>");
					
					out.print("<span class='uname'><a href='Profile?username=" + users.get(i) + "'>" + users.get(i) + "</a></span>");
					out.print("<span class='scor'>" + r.getFinalGrade() + "</span>");
					out.print("<span class='tim'>" + r.getTimeTaken()/(1000 * 60) + "mins</span>");
							
					out.print("</div>");
					i++;
				}
			}
			
			%>
			</div>
		</div>
		
		<% String disp = null; if (master== null) disp = "none"; else disp= "block";  %>
		
		<div id='yourperformance' style="display:<%=disp %>">
			<div class="divtitle">Your Past Performance:</div>
			<div class="inf">
				<span class="dt" >Date</span>
				<span class="z2">Score</span>
				<span class="z3" >Time Taken</span>
			</div>
			<div class="list">
				<%
					res =  (List<Result>) request.getAttribute("PastResults");
					if (res != null){

					for (Result r : res){
						out.print("<div class='listentry'>");
						
						out.print("<span class='dt'>" + r.getTimeStarted() + "</span>");
						out.print("<span class='scor'>" + r.getFinalGrade() + "</span>");
						out.print("<span class='tim'>" + r.getTimeTaken()/(1000 * 60) + "mins</span>");
								
						out.print("</div>");
					}
				}
				%>
			</div>
		</div>
		
		<%
			String disabled = "";
			if (request.getSession().getAttribute("MasterUser")==null) disabled = "disabled";
			String disabledPractice = null;
			if (quiz.hasPracticeMode() && master != null) disabledPractice = ""; else disabledPractice = "disabled";
		%>
		<form action="TakeQuiz" method="post">
			<input type="hidden" name="id" value='<%= quiz.getId() %>' >
			<button id="startquiz"  <%= disabled %>>Start Quiz</button>
		</form>
		
		<form id="practice" action="TakeQuiz" method="post">
			<input type="hidden" name="id" value='<%= quiz.getId() %>' >
			<input type="hidden" name="PracticeMode" value='Yes'>			
		</form>
		<button id="startquizpractice"  <%= disabledPractice %> onclick='practiceMode();'>Start in Practice Mode</button>
		
	</div>
	<%
		if (master!=null && uD.isAdmin(master.getUserName())) {
		out.println("<form action=\"DeleteQuiz\" method=\"post\" >");
		out.println("<input name='quizId' value='" + quiz.getId() + "'>");
		out.println("<button id='deletequiz' > Delete Quiz </button> </form>");
		
		out.println("<button id='deletehistory' onclick=\"DeleteHistory('" + quiz.getId() + "')\"> Delete Quiz History</button> </form>");
		
	}
	%>
	<div id="toppanel">
		<jsp:include page='<%= toppanel %>' />
	</div>
	
	<script type="text/javascript">
		function DeleteHistory(id){
			$.ajax(
					{
						async: true,
						url: "DeleteQuizHistory",
						type: "POST",
						data: "&quizId=" + id,
						success: function(data){
							document.getElementById("deletehistory").innerHTML = "Deleted!";
						},
						error: function(data){
							alert("Results were NOT deleted!");
						}
					}		
				);
		}
		
		function practiceMode(){
			var decide = confirm("In Practice Mode Your Result Will Not Be Saved!" + '\n' + "Want to Continue?");
			if (decide) {
				document.getElementById("practice").submit();
			}
		}
	</script>
</body>
</html>