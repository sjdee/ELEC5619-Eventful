<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Register</title>
    <%@ include file="header.jsp" %>  
</head>
<body>

<div class="center">
	<h1>
		Register page 
	</h1>

	<c:url var="registerUrl" value="/register" />
	<div class="row" style="width: 50%;">
		<form:form class="col s12" method="post" action="${registerUrl}" modelAttribute="user">
			<div class="row">
				<div class="input-field col s12">
					<form:input type="email" path="email" cssClass="validate"/>
					<form:label path="email">Email*</form:label>
					<form:errors path="email" cssClass="error" element="p"/>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<form:password path="password" cssClass="validate"/>
					<form:label path="password">Password*</form:label>
					<form:errors path="password" cssClass="error" element="p"/>
				</div>
			</div>
			<%-- <div class="row">
				<div class="input-field col s12">
					<form:password path="confirmpassword" cssClass="validate"/>
					<form:label path="confirmpassword">Confirm Password*</form:label>
					<form:errors path="confirmpassword" cssClass="error" element="p"/>
				</div>
			</div> --%>
			<div class="row">
				<div class="input-field col s12">
					<form:input path="alias" type="text" cssClass="validate"/>
					<form:label path="alias">Alias</form:label>
					<form:errors path="alias" cssClass="error" element="p"/>
				</div>
			</div>
			<%-- <div class="row">
				<div class="input-field col s12">
					<div class="file-field">
						<div class="btn">
							<span>Avatar</span>
							<form:input path="avatar" type="file"/>
						</div>
						<div class="file-path-wrapper">
							<form:input path="avatar_path" class="file-path validate" type="text" placeholder="Post Image"/>
						</div>
					</div>
				</div>
			</div> --%>
			<div class="row">
				<div class="input-field col s12">
					<form:textarea path="bio" cssClass="materialize-textarea"></form:textarea>
					<form:label path="bio" rows="3">Bio</form:label>
				</div>
			</div>
			<div class="row">
				<button class="btn waves-effect waves-light" type="submit" value="Submit">Register</button>
			</div>
		</form:form>
	</div>
	<a href="<c:url value="/login"/>">Already a user?</a>
</div>


<%@ include file="footer.jsp" %> 
</body>
</html>