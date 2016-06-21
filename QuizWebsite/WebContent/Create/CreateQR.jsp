<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 
	Parameters: 'statement' - problem statement;
				'answer' + i - possible answers(ex: 'answer0')

 -->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/createtest.css">
</head>
<body>
	Question:
	<textarea type="text" cols="60" rows="6" name="statement"></textarea> <br>
	Correct Answers:
	<span id="answerfield">
		<input type="text"  name="answer0">
	</span>
	<br>
	<button onclick="addAnswer();">Add Answer</button>
	
	<form action="" method="post">
		<input id="submitquiz" type="submit" value="Submit">
	</form>
	
	<script type="text/javascript">
		var count = 1;
		function addAnswer(){
			var field = document.createElement("input");
			field.name = "answer" + count; field.type = "text";
			document.getElementById('answerfield').appendChild(field);
			count++;
		}
	</script>
</body>
</html>