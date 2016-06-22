<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Main.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><% out.print("Welcome to " + Constants.SITE_NAME); %></title>
	<link rel="stylesheet" type="text/css" href="css/signup.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script type="text/javascript" src="javascript/signup.js"></script>
</head>
<body>
	<div id="container">
		<h1><% out.print("Sign Up For " + Constants.SITE_NAME + "");%></h1> 
		<p id="default">Please fill the blanks below</p>
		<p id="fillall">Please fill all of the blanks before proceeding</p>
		<p id="usernameused">Username already used, Please try again</p>
		<form id="form" method="post">
			<input class="textbox" type="text" id="username" name="username" placeholder="Username"><br>
			<input class="textbox" type="password" id="password" name="password"  placeholder="Password"><br><br>
			<input class="button" type="submit" name="signup" value="SignUp">
		</form>
	</div>
	<script type="text/javascript">
		document.getElementById("fillall").style.display = "none";
		document.getElementById("default").style.display = "block";
		document.getElementById("usernameused").style.display = "none";
	</script>
</body>
</html>