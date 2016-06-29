<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="classes.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/profile.css">
<%

	String toppanel; 
	if (request.getSession().getAttribute("MasterUser") == null) toppanel = "toppanel-loggedout.jsp";
		else toppanel = "toppanel-loggedin.jsp";
	
	User user = (User)request.getAttribute("User");
	String disabled = "disabled";
%>
<title><% out.print( "'s Profile"); %></title>
</head>
<body>
	<div id="centerpanel">
		<img id="profileimage" src="http://vignette3.wikia.nocookie.net/whentheycry/images/5/50/Fuck_you.jpg">
		<span id="profilename">JANDABA</span>
		<span id="description">YLEA ES CHEMISA</span>
		<button id="addfriendb"  <%= disabled %>>
			<% 
				if (disabled.equals("disabled")) out.print("Already your friend!"); 
					else out.print("Add as a friend!"); 
			%>
		</button>
		<%
			if (request.getSession().getAttribute("MasterUser")!=null) {
				out.println("<button id='sendnote'>Send Note</button>");
			}
			
			if (request.getSession().getAttribute("MasterUser").equals(user)){
				out.println("<button id='editdescr'>Edit Status</button>");
			}
		%>
		<div id="achievements">
			<img class="achimage" src="http://vignette3.wikia.nocookie.net/whentheycry/images/5/50/Fuck_you.jpg" onclick="alert('yleo')">
			<img class="achimage" src="http://vignette3.wikia.nocookie.net/whentheycry/images/5/50/Fuck_you.jpg" onclick="alert('yleo')">
			<img class="achimage" src="http://vignette3.wikia.nocookie.net/whentheycry/images/5/50/Fuck_you.jpg" onclick="alert('yleo')">
		</div>
		
		<div id="friendslist">
			<div class="divtitle">Friends:</div>
			<div class="list">
			<%
				out.println("<a href=\"Profile?username=" + "ja" + "\" ><div class=\"listentry\">" + "jandaba" + "</div></a>");	
			%>
			</div>
		</div>
		<div id="createdquizzes">
			<div class="divtitle">Created Quizzes:</div>
			<div class="list">
			<%
				out.println("<a href=\"Quiz?id=" + 1 + "\" ><div class=\"listentry\">" + "jandabaxtfu" + "</div></a>");	
			%>
			</div>
		</div>
	</div>
	
	<div id="toppanel">
		<jsp:include page='<%= toppanel %>' />
	</div>
</body>
</html>