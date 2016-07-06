<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="classes.*" %>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="Main.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/profile.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script type="text/javascript" src="javascript/addfriend.js"></script>
<%

	String toppanel; 
	if (request.getSession().getAttribute("MasterUser") == null) toppanel = "toppanel-loggedout.jsp";
		else toppanel = "toppanel-loggedin.jsp";
	User masterUser = (User)request.getSession().getAttribute("MasterUser");
	User user = (User)request.getAttribute("User"); 
	UserDAO uD = (UserDAO) request.getServletContext().getAttribute("userDAO");
	Set<String> friends = (Set<String>) request.getAttribute("Friends");
	
	StringChanger sc = new StringChanger();
%>
<title><% out.print(user.getUserName() + "'s Profile"); %></title>
</head>
<body>
	<div id="centerpanel">
		<img id="profileimage" src="<%= user.getImage() %>">
		<span id="profilename"><%= user.getUserName() %></span>
		<span id="description"><%= sc.changeString(user.getDescription()) %></span>
		<% 
		if (masterUser != null){
			if (!friends.contains(masterUser.getUserName()) && !user.equals(masterUser)){
				out.print("<button id='addfriendb'  onclick=\"addFriend('" + user.getUserName() + "')\">Send friend request</button>");
			} 
			
			if (friends.contains(masterUser.getUserName())) {
				out.print("<form action=\"DeleteFriend\" method=\"post\" >");
				out.print("<input type=\"hidden\" name='user' value='" + user.getUserName() + "' />");
				out.print("<button id='addfriendb'>Delete from Friends</button>");
				out.print("</form>");	
			} 
			
			if ((boolean)request.getAttribute("FriendRequestSent")) {
				out.print("<button id='addfriendb'>Friend request sent!</button>");
			} else {
			if ((boolean)request.getAttribute("FriendRequestReceived")) {
				out.print("<button id='addfriendb'>Check your messages stupid!</button>");
			}}
		
			if (!user.equals(masterUser)) {
				out.println("<a href='CreateNote.jsp?getter=" + user.getUserName() + "'>");
				out.println("<button id='sendnote'>Send Note</button></a>");
			}
			
			if (user.equals(masterUser)){
				out.println("<a href='EditProfile.jsp'><button id='editdescr'>Edit Profile</button></a>");
			}
			
			if ((boolean)request.getSession().getAttribute("isAdmin") && !uD.isAdmin(user.getUserName())) {
				if (!uD.isAdmin(user.getUserName())){
					out.println("<a href='MakeAdmin?username=" + user.getUserName() + "'>");
					out.println("<button id='makeadmin'>Make Admin</button></a>");
				}
				out.println("<a href='DeleteAccount?username=" + user.getUserName() + "'>");
				out.println("<button id='deleteacc'>Delete Account</button></a>");
			}
		}
		%>
		
		<div id="achievements">
			<%
				
				AchievementDAO aD = (AchievementDAO) request.getServletContext().getAttribute("achievementDAO");
				Achievements ach = (Achievements) request.getServletContext().getAttribute("achievements");
				
				for (int i=0; i<ach.getNumAchievements(); i++){
					Achievement a = ach.getAchievement(i);
					
						if (aD.hasAchievement(user.getUserName(), i)) 
							out.print("<img class='achimage' src='" + a.getPictureURL() + "' onclick=\"dropDown('" + a.getPictureURL() + "','" + a.getName() + "','" + sc.changeString( a.getDescription()) + "');\" />");
						else 
							out.print("<img class='achimage' src='" + ach.getDefaultURL() + "' onclick=\"dropDown('" + ach.getDefaultURL() + "','" + a.getName() + "','" + sc.changeString(a.getDescription()) + "');\" />");
					
				}
			
			%>
		</div>
		
		<div id="achdrop" class='achdropdown-content' onclick="dropDownDown();">
			<img id="achimg" class='achimage-content' src='' />
			<span id="achnm" class='achnm'></span>
			<span id="achdscr" class='achdescr'></span>
		</div>
		
		
		<div id="friendslist">
			<div class="divtitle">Friends:</div>
			<div class="list">
			<%	
				if (friends!=null){
				for (String friend : friends){
					out.println("<a href=\"Profile?username=" + friend + "\" ><div class=\"listentry\">" + friend + "</div></a>");	
				}}
			%>
			</div>
		</div>
		<div id="createdquizzes">
			<div class="divtitle">Created Quizzes:</div>
			<div class="list">
			<%
				List<Quiz> quizzes = (List<Quiz>) request.getAttribute("createdQuizzes");
				if (quizzes != null)
				for (int i=0;i<quizzes.size();i++){ 
					out.println("<a href=\"Quiz?id=" + quizzes.get(i).getId() + "\" ><div class=\"listentry\">" + sc.changeString(  quizzes.get(i).getQuizName() ) + "</div></a>");	
				}
			%>
			</div>
		</div>
		
		<div class="quizzes" id="recentresults">
			<div class="divtitle">Results:</div>
			<div class="inf">
				<span class="qzname">Quiz</span>
				<span class="date">Date</span>
				<span class="scr" >Score</span>
				<span class="timetaken" >Time Taken</span>
			</div>
			<div class="list" id="reslist">
			<%	
				List<Result> recent = (List<Result>) request.getAttribute("recentResults");
				quizzes = (List<Quiz>) request.getAttribute("recentQuizzes");
				if (recent != null && quizzes != null){
				for (int i=0;i<recent.size();i++){ 
					out.print("<div class='listentry'>");
						
					out.print("<a href='Quiz?id=" + quizzes.get(i).getId() + "'><span class='entryquiz'>" + sc.changeString(  quizzes.get(i).getQuizName() ) + "</span><a/>");
					out.print("<span class='entrydate'>" + new Date(recent.get(i).getTimeStarted()) + "</span>");
					out.print("<span class='entryscore'>" + recent.get(i).getFinalGrade() + "</span>");
					out.print("<span class='entrytime'>" + recent.get(i).getTimeTaken()/(1000 * 60) + "mins</span>");
					
					out.print("</div>");	
				}}
			%>
			</div>
		</div>
		
		 
		<!-- It's useless and stupid just visit his/her page
		
		<div id="friendsachievements">
			<div class="divtitle">Achievements</div>
			<div class="inf">
				<span class="usrname">User</span>
				<span class="qzname">Achievement</span>
			</div>
			<div class="list" id="jj">
			<% /*
			List<String> achh = (List<String>) request.getAttribute("FriendsAchievements");
			List<String> frnds = (List<String>) request.getAttribute("FriendsAchievementsUser");
	
			if (achh != null && frnds != null){
			for (int i=0;i<achh.size();i++){
				out.print("<div class='listentry'>");
				
				out.print("<a href='Profile?username=" + frnds.get(i) + "'><span class='entryuser'>" + frnds.get(i) + "</span><a/>");
				out.print("<span class='entryquiz'>" + achh.get(i) + "</span>");
				
				out.print("</div>");	
			} }*/
			%>
			</div>
		</div>
		 -->
		 
		 <div class="breaker">
		 	Friend's Activities:
		 </div>
		 
		<div id="friendsrecent">
			<div class="divtitle">Recent Results</div>
			<div class="inf">
				<span class="usrname">User</span>
				<span class="qzname">Quiz</span>
				<span class="date">Date</span>
				<span class="scr" >Score</span>
				<span class="timetaken">Time Taken</span>
			</div>
			<div class="list" id="j">
			<% 
			recent = (List<classes.Result>) request.getAttribute("FriendsRecentResults");
			quizzes = (List<classes.Quiz>) request.getAttribute("FriendsRecentQuizzes");
			List<String> frnds = (List<String>) request.getAttribute("FriendsRecentUsers");
			if (recent != null && quizzes != null && frnds!= null)
			for (int i=0;i<recent.size();i++){ 
				out.print("<div class='listentry'>");
					
				out.print("<a href='Profile?username=" + frnds.get(i) + "'><span class='entryuser'>" + frnds.get(i) + "</span><a/>");
				out.print("<a href='Quiz?id=" + quizzes.get(i).getId() + "'><span class='entryquiz'>" + quizzes.get(i).getQuizName() + "</span><a/>");
				out.print("<span class='entrydate'>" + new Date(recent.get(i).getTimeStarted()) + "</span>");
				out.print("<span class='entryscore'>" + recent.get(i).getFinalGrade() + "</span>");
				out.print("<span class='entrytime'>" + recent.get(i).getTimeTaken()/(1000 * 60) + "mins</span>");
				
				out.print("</div>");	
			}
			%>
			</div>
		</div>
		<div id="friendscreated">
			<div class="divtitle">Created Quizzes</div>
			<div class="inf">
				<span class="usrname">User</span>
				<span class="qzname">Quiz</span>
			</div>
			<div class="list" id="jjj">
			<% 
				
				quizzes = (List<Quiz>) request.getAttribute("FriendsCreatedQuizzes");
				frnds = (List<String>) request.getAttribute("FriendsCreatedUsers");
				if (quizzes != null && frnds != null){
				for (int i=0;i<quizzes.size();i++){ 
					out.print("<div class='listentry'>");
					
					out.print("<a href='Profile?username=" + frnds.get(i) + "'><span class='entryuser'>" + frnds.get(i) + "</span><a/>");
					out.print("<a href='Quiz?id=" + quizzes.get(i).getId() + "'><span class='entryquiz'>" + sc.changeString(  quizzes.get(i).getQuizName()) + "</span><a/>");
					
					out.print("</div>");	
				}}
			%>
			</div>
		</div>
		
		</div>
	
	
	
	
	<div id="toppanel">
		<jsp:include page='<%= toppanel %>' />
	</div>
	
	<script type="text/javascript">
		function dropDown(url,nm,desc){
			document.getElementById('achimg').src = url;
			document.getElementById('achdscr').innerHTML = "       " + desc;
			document.getElementById('achnm').innerHTML = nm;
			document.getElementById('achdrop').style.display = "block";
		}
		function dropDownDown(){
			document.getElementById('achdrop').style.display = "none";
		}
	</script>
	
</body>
</html>