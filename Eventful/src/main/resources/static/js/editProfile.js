$(document).ready(function() {
	$("#changeAlias").submit(function(event) {
		event.preventDefault();
		
		$.ajax({
			type: "POST",
			url: "/profile/edit/alias",
			data: {"alias": $("#alias").val()},
			success: function (response) {
				$("#errors").text("");
				$("#messages").text("Alias update successful.");
				$("#alias").val("");
			},
			error: function (error) {
				$("#errors").text(error.responseText);
			}
		});
	});
	
	$("#changeBio").submit(function(event) {
		event.preventDefault();
		
		$.ajax({
			type: "POST",
			url: "/profile/edit/bio",
			data: {"bio": $("#bio").val()},
			success: function (response) {
				$("#errors").text("");
				$("#messages").text("Bio update successful.");
				$("#bio").val("");
			},
			error: function (error) {
				$("#errors").text(error.responseText);
			}
		});
	});
	
	$("#changePassword").submit(function(event) {
		event.preventDefault();
		
		$.ajax({
			type: "POST",
			url: "/profile/edit/password", 
			data: {"oldPassword": $("#oldPassword").val(), "password": $("#password").val(), "confirmPassword": $("#confirmPassword").val()},
			success: function (response) {
				$("#errors").text("");
				$("#messages").text("Password update successful.");
				$("#oldPassword").val("");
				$("#password").val("");
				$("#confirmPassword").val("");
			}, 
			error: function (error) {
				$("#errors").text(error.responseText);
			}
		});
	});
	
	$("#changeAvatar").submit(function(event) {
		event.preventDefault();
		
		$.ajax({
			type: "POST",
			url: "/profile/edit/avatar",
			data: {"filePath": $('input[name="filePath"]').val()},
			success: function (response) {
				$("#errors").text("");
				$("#messages").text("Avatar update successful.");
				$('input[name="filePath"]').val("");
			},
			error: function (error) {
				$("#errors").text(error.responseText);
			}
		});
	});
});