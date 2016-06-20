<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 
	Parameters: 'statement' - promblem statement;
				

 -->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/createtest.css">
</head>
<body>
	Text:
	<textarea type="text" cols="60" rows="6" name="statement" id="stmnt"></textarea> <br>
	
	<button onclick="addBlank();">Add Blank</button>
	
	<form action="" method="post">
		<input id="submitquiz" type="submit" value="Submit">
	</form>
	
	<script type="text/javascript">
		function addBlank(){
			document.getElementById('stmnt').value += " ______ ";
			document.getElementById('stmnt').focus();
		}
	</script>
</body>
</html>