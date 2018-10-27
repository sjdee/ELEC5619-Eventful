<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Register</title>
<%@ include file="publicHeader.jsp"%>
<script src="<c:url value="/js/register.js" />"></script>
</head>
<body>
	<div class="center">
		<h1>Register as New User</h1>
		<c:url var="registerUrl" value="/register" />
		<div class="row" style="width: 50%;">
			<form:form id="registerUser" class="col s6 push-s3" method="post"
				action="${registerUrl}" modelAttribute="user">
				<form:errors cssClass="error" element="p" />
				<div class="row">
					<div class="input-field col s12" id="emailDiv">
						<form:input id="email" type="email" path="email" cssClass="validate" maxlength="255"/>
						<form:label path="email">Email *</form:label>
						<form:errors path="email" cssClass="error" element="p" />
					</div>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<form:password path="password" cssClass="validate" />
						<form:label path="password">Password *</form:label>
						<form:errors path="password" cssClass="error" element="p" />
					</div>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<form:password path="confirmPassword" cssClass="validate" />
						<form:label path="confirmPassword">Confirm Password *</form:label>
						<form:errors path="confirmPassword" cssClass="error" element="p" />
					</div>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<form:input path="alias" type="text" cssClass="validate" maxlength="50"/>
						<form:label path="alias">Alias *</form:label>
						<form:errors path="alias" cssClass="error" element="p" />
					</div>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<form:textarea path="bio" cssClass="materialize-textarea" maxlength="255"></form:textarea>
						<form:label path="bio" rows="3">Bio</form:label>
					</div>
				</div>
			</form:form>
			<form id="singleUploadForm" name="singleUploadForm">
				<div class="row" style="width: 50%;">
					<div class="col s12">
						<div class="row">
							<div class="input-field col s12">
								<div class="file-field">
									<div class="btn">
										<span>Upload Avatar</span> <input id="singleFileUploadInput"
											type="file" name="file" class="file-input">
									</div>
									<div class="file-path-wrapper">
										<input class="file-path validate" type="text"
											placeholder="User Avatar Image">
									</div>
								</div>
							</div>
						</div>

					</div>
					<!--<input type="submit" value="Post"/>-->
					<div class="center">
						<button class="btn waves-effect waves-light" type="submit"
							name="action" style="display: none;">
							Create User <i class="material-icons right">send</i>
						</button>
					</div>
				</div>
			</form>
			<div class="row">
				<div class="col s6 offset-s3">
					<div class="card">
						<div class="card-image">
							<img id="uploaded-image">
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<button class="btn waves-effect waves-light" type="submit"
					value="Submit" form="registerUser">Register</button>
			</div>
			<div class="row">
				<a class="col s12" href="<c:url value="/login"/>">Already a
					user?</a>
			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>