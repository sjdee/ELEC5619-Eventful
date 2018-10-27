<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Profile</title>
<%@ include file="header.jsp"%>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col s12" style="text-align: center;">
				<h1>Edit Profile</h1>
			</div>
		</div>
		<div class="row">
			<div class="col s6">
				<h5 class="col s12" style="text-align: center;">Update Password</h5>
				<form class="col s12" method="post" action="#"<%-- modelAttribute="user" --%>>
					<div class="row">
						<div class="input-field col s12">
							<input type="password" path="oldPassword" class="validate" /> <label
								path="oldPassword">Old Password</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12">
							<input type="password" path="password" class="validate" /> <label
								path="password">New Password</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12">
							<input type="password" path="confirmPassword" class="validate" />
							<label path="confirmPassword">Confirm New Password</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12" style="text-align: right;">
							<button class="btn waves-effect waves-light" type="submit"
								name="Submit" value="Submit">Submit</button>
						</div>
					</div>
				</form>
				<%-- <form:form class="col s12" method="post" id="changePassword" action="#" modelAttribute="user" >
					<div class="row">
						<div class="input-field col s12">
							<form:password path="oldPassword" cssClass="validate" />
							<form:label path="oldPassword">Old Password</form:label>
							<form:errors path="oldPassword" cssClass="error" element="p" />
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12">
							<form:password path="password" cssClass="validate" />
							<form:label path="password">New Password</form:label>
							<form:errors path="password" cssClass="error" element="p" />
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12">
							<form:password path="confirmPassword" cssClass="validate" />
							<form:label path="confirmPassword">Confirm New Password</form:label>
							<form:errors path="confirmPassword" cssClass="error" element="p" />
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12">
							<button class="btn waves-effect waves-light" type="submit"
							name="Submit" value="Submit">Submit</button>
						</div>
					</div>
				</form:form> --%>
			</div>
			<div class="col s6" style="text-align: center;">
				<h5 class="col s12" style="text-align: center;">Update Profile
					Information</h5>
				<form class="col s12" method="post" action="#"<%-- modelAttribute="user" --%>>
					<div class="row">
						<div class="input-field col s12">
							<input path="alias" type="text" class="validate col s10" /> <label
								path="alias">New Alias</label>
							<%-- <form:errors path="alias" cssClass="error" element="p" /> --%>
							<button class="btn waves-effect waves-light col s2" type="submit"
								name="Submit" value="Submit">Submit</button>
						</div>
					</div>
				</form>
				<form class="col s12" method="post" action="#"<%-- modelAttribute="user" --%>>
					<div class="row">
						<div class="input-field col s12">
							<div class="file-field col s10">
								<div class="btn">
									<span>New Avatar</span> <input path="avatar" type="file" />
								</div>
								<div class="file-path-wrapper">
									<input path="avatar_path" class="file-path validate"
										type="text" placeholder="Post Image" />
								</div>
							</div>
							<button class="btn waves-effect waves-light col s2" type="submit"
								name="Submit" value="Submit">Submit</button>
						</div>
					</div>
				</form>
				<form class="col s12" method="post" action="#"<%-- modelAttribute="user" --%>>
					<div class="row">
						<div class="input-field col s12">
							<textarea path="bio" class="materialize-textarea col s10"></textarea>
							<label path="bio" rows="3">New Bio</label>
							<button class="btn waves-effect waves-light col s2" type="submit"
								value="Submit">Submit</button>
						</div>
					</div>
				</form>
				<%-- <form:form class="col s12" method="post" action="#" modelAttribute="user">
					<div class="row">
						<div class="input-field col s12">
							<form:input path="alias" type="text" cssClass="validate" />
							<form:label path="alias">New Alias</form:label>
							<form:errors path="alias" cssClass="error" element="p" />
							<button class="btn waves-effect waves-light" type="submit"
								name="Submit" value="Submit">Submit</button>
						</div>
					</div>
				</form:form>
				<form:form class="col s12" method="post" action="#" modelAttribute="user">
					<div class="row">
						<div class="input-field col s12">
							<div class="file-field">
								<div class="btn">
									<span>New Avatar</span>
									<form:input path="avatar" type="file" />
								</div>
								<div class="file-path-wrapper">
									<form:input path="avatar_path" class="file-path validate"
										type="text" placeholder="Post Image" />
								</div>
							</div>
						</div>
						<button class="btn waves-effect waves-light" type="submit"
							name="Submit" value="Submit">Submit</button>
					</div>
				</form:form>
				<form:form class="col s12" method="post" action="#" modelAttribute="user">
					<div class="row">
						<div class="input-field col s12">
							<form:textarea path="bio" cssClass="materialize-textarea"></form:textarea>
							<form:label path="bio" rows="3">New Bio</form:label>
						</div>
						<button class="btn waves-effect waves-light" type="submit"
							value="Submit">Submit</button>
					</div>
				</form:form> --%>
			</div>
		</div>
		<div class="row">
			<a class="col s12" href="<c:url value="/profile" />"
				style="text-align: center;">Return to Profile Page </a>
		</div>
	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>