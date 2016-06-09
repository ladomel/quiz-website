
$().ready(function(){
	$('#loginform').submit(function(event) {
		var resp2 = $.ajax({
			type: "POST",
			url: "Login",
			data : {
				username : $('input#username').val(),
				password : $('input#password').val(),
			},
			dataType : "text",
			success : function (data, textStatus, jqXHR){
				if (data.trim() == "success") {
					if (window.location.pathname.split("/")[2] == "invalidlogin.jsp" ||
							indow.location.pathname.split("/")[2] == "index.jsp") {
								window.location = "index.jsp"; 
					} else window.history.back();
				} else window.location = "invalidlogin.jsp"; 
			},
			error: function(jqXHR, textStatus, errorThrown){
				alert("Problem with Login!");
			}
		});
		event.preventDefault();
	});
});
