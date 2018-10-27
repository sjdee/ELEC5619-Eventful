$(document).ready(function() {
	$('#email').focusout(function(event) {
		event.preventDefault();
		// EXTERNAL API
		$.ajax({
			type : "POST",
			url : "https://api.cloudmersive.com/validate/email/address/full",
			data : $("#email").val(),
			headers : {
				"Apikey" : "8a7b9313-8b16-4e55-bf2e-bf0d0e73d6d7"
			},
			success : function(response) {
				console.log(response);
				if (response.ValidAddress)
					$("#emailerror").remove();
				else
					$("#emailDiv").append("<p id='emailerror' style='color:red;'>Invalid email.</p>");
			},
			error : function(error) {
				$("#emailDiv").append("<p id='emailerror' style='color:red;'>Invalid email.</p>");
			}
		});
	});
});