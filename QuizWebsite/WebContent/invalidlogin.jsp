<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Main.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><% out.print("Welcome to " + Constants.SITE_NAME); %></title>
	<link rel="stylesheet" type="text/css" href="css/login.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
</head>
<body>
	<div id="container">
		<h1><% out.print("Log In to " + Constants.SITE_NAME + "!"); %></h1> 
		<p id="notfound">Invalid username or password</p>
		<form id="loginform" action = "Login" method = "post">
			<input class="textbox" type="text" id="username" name = "username" placeholder="Username"><br>
			<input class="textbox" type="password" id="password" name = "password" placeholder="Password"><br><br>
			<input class="button" type="submit" id="submit" value="LogIn">
		</form>
		<div id="signupdiv">
			<h3>Not a Member Yet?</h3>
			<button id="signup" onclick="signUp();">Sign Up Here!</button>
		</div>
	</div>
	<script> function signUp(){ window.location = "signup.jsp";} </script>
</body>
</html>