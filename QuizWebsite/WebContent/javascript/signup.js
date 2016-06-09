
$().ready(function(){
	$('#form').submit(function(event) {
		if ($('input#username').val() == "" || $('input#password').val()==""){
			document.getElementById("fillall").style.display = "block";
			document.getElementById("default").style.display = "none";
			document.getElementById("usernameused").style.display = "none";	
		} else {
			var resp = $.ajax({
					type: "POST",
					url: "Signup",
					data : {
						username : $('input#username').val(),
						password : $('input#password').val(),
					},
					dataType : "text",
					success : function (data, textStatus, jqXHR){
						if (data.trim() == "used") {
							document.getElementById("fillall").style.display = "none";
							document.getElementById("default").style.display = "none";
							document.getElementById("usernameused").style.display = "block";	
							document.getElementById("username").value = "";
							document.getElementById("password").value = "";
						} else {
							var resp2 = $.ajax({
								type: "POST",
								url: "Login",
								data : {
									username : $('input#username').val(),
									password : $('input#password').val(),
								},
								dataType : "text",
								success : function (data, textStatus, jqXHR){
									window.location = "index.jsp";
								},
								error: function(jqXHR, textStatus, errorThrown){
									alert("Problem with Login!");
								}
							});
						}
					},
					error: function(jqXHR, textStatus, errorThrown){
						alert("Problem with SignUp!");
					}
			
			});
			
		}
		event.preventDefault();
	});
});