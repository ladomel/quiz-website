<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/toppanel.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script type="text/javascript" src="javascript/login.js"></script>
<script type="text/javascript" src="javascript/search.js"></script>
</head>
<body>
	<div id="toppanel">
		<a href="index.jsp">
			<img id="i" src="images/qmark.png">
		</a>
		<span id="searchbar">
			<form id="searchform">
				<input type="text" id="searchfield" placeholder="Search">
				<button type="submit" id="searchbutton">Search</button>
			</form>
		</span>
		
		<span id="loginbox">
			<form  id="loginform">
				Login:
				<input type="text" id="username" placeholder="Username">
				<input type="password" id="password" placeholder="Password">
				<input type="submit" id="login" value="Login">
			</form>
		</span>
		<span id="signupbox">
			Not a member yet?
			<input type="submit" id="signupbutton" class="button" value="Sign Up" 
					onclick="signUp()">
		</span>
	
		 
	</div>
	<script> function signUp(){ window.location = "signup.jsp";} </script>
</body>
</html>