<%@ include file="/WEB-INF/views/include.jsp"%>
<html>
<head>
<title>Forgot Password</title>
<%@ include file="publicHeader.jsp"%>
</head>
<body>

	<div class="container">
		<h1 class="center">Forgot Password</h1>
		<form id="resetPassword" class="col s6 push-s3" method="post"
			action="${registerUrl}">
			<div class="row">
				<div class="input-field col s12" id="emailDiv">
					<input id="email" type="email" name="email" class="validate"
						maxlength="255" /> <label for="email">Email *</label>
					<p class="error"></p>
				</div>
			</div>
			<div class="row center">
				<button class="btn waves-effect waves-light" type="submit"
					value="Submit" form="registerUser">Reset Password</button>
			</div>
		</form>
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