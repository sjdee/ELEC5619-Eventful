<%@ include file="/WEB-INF/views/include.jsp"%>
<html>
<head>
<title>Login</title>
<%@ include file="publicHeader.jsp"%>
</head>
<body style="min-height: 100vh;">

	<main class="center">
	<h1>Login page</h1>

	<c:url var="loginUrl" value="/login" />
	<div class="row" style="width: 50%;">
		<c:choose>
			<c:when test="${param.error != null}">
				<div class="col s12" style="text-align: center; color: red;">Incorrect
					username or password.</div>
			</c:when>
		</c:choose>
		<form class="col s6 push-s3" action="${loginUrl}" method="post">
			<div class="row">
				<div class="input-field col s12">
					<input id="email" type="email" name="email" class="validate"
						required> <label for="email">Email</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<input id="password" type="password" name="password"
						class="validate" required> <label for="password">Password</label>
				</div>
			</div>
			<div class="row">
				<button id="login" class="btn waves-effect waves-light"
					type="submit" name="Submit" value="Login"
					style="padding-left: 10px; padding-right: 10px;">Login</button>
			</div>
		</form>
	</div>
	<div class="row">
		<a class="" style="margin-top: 1em; margin-bottom: 1em;"
			href="<c:url value="/register" />">Register As New User</a>
	</div>
	<%--  <a
			class="waves-effect waves-light btn-large"
			style="margin-top: 1em; margin-bottom: 1em;"
			href="<c:url value="/forgot" />">Forgot Password</a> --%> </main>
	<%@ include file="footer.jsp"%>
</body>
</html>