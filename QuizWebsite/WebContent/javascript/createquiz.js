/**
 * 
 */

var count = 0;
var tagCount = 0;

function addQuestion(jsp){
	var obj = document.createElement("iframe");
	obj.name = "question" + count; obj.src = "Create/" + jsp + ".jsp"; obj.id = count;
	document.getElementById('questions').appendChild(obj);
	
	var obj2 = document.createElement("button");
	obj2.id = "remove" + count;  obj2.className = "removeq"; obj2.innerHTML = "Click to Delete Question Above";
	obj2.onclick = function() { remove(obj2,obj); };
	document.getElementById('questions').appendChild(obj2);
	
	count++;
}

function addTag(){
	if (tagCount >= 10) return;
	var obj = document.createElement("input");
	obj.name = "tag" + tagCount; obj.type= "text"; obj.className = "tag"; obj.id = "tag" + tagCount;
	document.getElementById('tags').appendChild(obj);
	tagCount++;
}

function remove(obj2,obj){
	obj.parentNode.removeChild(obj);
	obj2.parentNode.removeChild(obj2);
}

function submitQuiz(){
	var questions = document.getElementsByTagName("iframe");
	for (var i=0;i<questions.length;i++){
		var doc = questions[i].contentDocument;
		var button = doc.getElementById("submit");
		button.click();
	}
	document.getElementById("infoform").submit();
}