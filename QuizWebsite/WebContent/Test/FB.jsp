<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<h2>Fill in the Blanks:</h2>
	<%
		ArrayList<String> a = new ArrayList<String>(); a.add("Sheni"); a.add("vatire me!");
		out.print(a.get(0) + " ");
		for (int i=1;i<a.size();i++){
			out.print("<input type=\"text\" id=\"" + (i-1) + "\"  style=\" width: 50px; \" >");
			out.print(" " + a.get(i) + " ");
		}
		out.print("<input type=\"submit\" style=\"display: none;\">");
	%>
</body>
</html>