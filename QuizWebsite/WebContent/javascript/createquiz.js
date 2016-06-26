/**
 * 
 */

var count = 0;
function addQuestion(jsp){
	var obj = document.createElement("iframe");
	obj.name = "question" + count; obj.src = "Create/" + jsp + ".jsp"; obj.id = count;
	document.getElementById('questions').appendChild(obj);
	count++;
}

function submitQuiz(){
	for (var i=0;i<count;i++){
		var doc = document.getElementById(i).contentDocument;
		doc.getElementById("submit").click();
	}
}