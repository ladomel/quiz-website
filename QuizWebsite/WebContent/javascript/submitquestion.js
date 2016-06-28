/**
 * 
 */

function submit(questionID){
	alert($("#form").serialize());
	$.ajax(
		{
			async: false,
			url: "../Submit",
			type: "POST",
			data: $("#form").serialize() + "&questionPosition=" + questionID
		}		
	);
}