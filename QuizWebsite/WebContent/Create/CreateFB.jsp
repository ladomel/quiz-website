<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 
	Parameters: 'statement' - problem statement;
				'answer' + i + '/' + j - possible answer to i-th blank(ex: answer1/12, means blank 0 has at least 13 correct answers);

 -->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/createtest.css">
</head>
<body>
	Text:
	<textarea type="text" cols="60" rows="6" name="statement" id="stmnt"></textarea> <br>
	Correct Answers:
	<span id="answerfield">
		
	</span>
	
	<button onclick="addBlank();">Add Blank</button>
	
	<form action="" method="post">
		<input id="submitquiz" type="submit" value="Submit">
	</form>
	
	<script type="text/javascript">
		var count = 0;
		function addBlank(){
			document.getElementById('stmnt').value += " ______ ";
			document.getElementById('stmnt').focus();
			var field = document.createElement("input");
			field.name = "answer" + count + "/" + "0"; field.className = "ans"; field.type = "text";
			document.getElementById('answerfield').appendChild(field);
			count++;
		}
	</script>
</body>
</html>