<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Not Found</title>
<style>
	h1 {
		position: absolute;
		width: 800px;
		text-align: center;
		top:110px;
		left: calc(50% - 400px);
	}
</style>
</head>
<body>
	<%
		String toppanel; 
		if (request.getSession().getAttribute("MasterUser") == null) toppanel = "toppanel-loggedout.jsp";
		else toppanel = "toppanel-loggedin.jsp";
	%>
	
	<div id="toppanel">
		<jsp:include page='<%= toppanel %>' />
	</div>
	<div id="panel">
		<h1>The Quiz or A Person You're Looking For Doesn't Exist</h1>
	</div>
</body>
</html>