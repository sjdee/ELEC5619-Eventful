<%@ include file="/WEB-INF/views/include.jsp"%>
<html>
<head>
<title>Forgot Password</title>
<%@ include file="publicHeader.jsp"%>
</head>
<body>

	<div class="center">
		<h1>Forgot Password</h1>
		<label for="email">email</label> <input id="email" name="email"
			type="email" value="" />
		<button type="submit" onclick="resetPass()">reset</button>

		<a href="@{/registration.html}"> registration </a> <a href="@{/login}">login</a>

		<script src="jquery.min.js"></script>
		<script>
			var serverContext = [[@{/}]];
			function resetPass(){
			    var email = $("#email").val();
			    $.post(serverContext + "user/resetPassword",{email: email} ,
			      function(data){
			          window.location.href = 
			           serverContext + "login?message=" + data.message;
			    })
			    .fail(function(data) {
			        if(data.responseJSON.error.indexOf("MailError") > -1)
			        {
			            window.location.href = serverContext + "emailError.html";
			        }
			        else{
			            window.location.href = 
			              serverContext + "login?message=" + data.responseJSON.message;
			        }
			    });
			}
		</script>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>