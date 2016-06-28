<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sorry</title>
</head>
<body>
	<% 
		String error = (String)request.getAttribute("Error");
		if (error == null) error = "Unknown Error!";
	%>
	<h1><%= error %></h1>
</body>
</html>