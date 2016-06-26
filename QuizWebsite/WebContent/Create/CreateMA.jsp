<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 
	Parameters: 'statement' - problem statement;
				'answernum' - how many answers are required by author;
				'order'(checkbox) - checked if order is required;
				'answer' + i - correct answers; 
 -->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/createtest.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script type="text/javascript" src="../javascript/createquestion.js"></script>
</head>
<body>
	<form id="form" onkeypress="return event.keyCode != 13;">
	Question:
	<textarea type="text" cols="60" rows="6" name="statement"></textarea> <br>
	Number of desired input fields: <input type="text" id="answernum" name="answernum"> <br>
	Does order matter? <input type="checkbox" id="order" name="order"> <br> <br> <br>
	Correct Answers:
	<span id="answerfield">
		<input type="text" class="ans" name="answer0">
	</span>
	</form>
	<br>
	<button onclick="addAnswer();">Add Answer</button> 
	<input id="submit" onclick="submit('CreateMA');" type="hidden" />
	<script type="text/javascript">
		var count = 1;
		function addAnswer(){
			var field = document.createElement("input");
			field.name = "answer" + count; field.className = "ans"; field.type = "text";
			document.getElementById('answerfield').appendChild(field);
			count++;
		}
		document.getElementById('submitquiz').addEventListener("click", function(){
			if (isNaN(parseInt(document.getElementById('answernum').value))) {alert("Enter a number in a number field");event.preventDefault();}
			if (parseInt(document.getElementById('answernum').value) > count) {alert("Add more corect answers");event.preventDefault();}
			if (parseInt(document.getElementById('answernum').value) > 30) {alert("Too many desired answers");event.preventDefault();}
		});
	</script>
</body>
</html>