<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Profile</title>
<%@ include file="header.jsp"%>
<script src="<c:url value="/js/editProfile.js" />"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col s12" style="text-align: center;">
				<h1>Edit Profile</h1>
			</div>
		</div>
		<div class="row" id="messages" style="text-align:center;"></div>
		<div class="row" id="errors" style="color:red; text-align:center;"></div>
		<div class="row">
			<div class="col s6">
				<h5 class="col s12" style="text-align: center;">Update Password</h5>
				<form class="col s12" method="post" id="changePassword">
					<div class="row">
						<div class="input-field col s12">
							<input type="password" id="oldPassword" class="validate" /> <label
								for="oldPassword">Old Password</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12">
							<input type="password" id="password" class="validate" /> <label
								for="password">New Password</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12">
							<input type="password" id="confirmPassword" class="validate" />
							<label for="confirmPassword">Confirm New Password</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12" style="text-align: right;">
							<button class="btn waves-effect waves-light" type="submit"
								name="Submit" value="Submit">Submit</button>
						</div>
					</div>
				</form>
			</div>
			<div class="col s6" style="text-align: center;">
				<h5 class="col s12" style="text-align: center;">Update Profile
					Information</h5>
				<form class="col s12" method="post" id="changeAlias">
					<div class="row">
						<div class="input-field col s12">
							<input type="text" id="alias" class="validate col s10"/>
							<label for="alias">New Alias</label>
							<button class="btn waves-effect waves-light col s2" type="submit"
								value="Submit">Submit</button>
						</div>
					</div>
				</form>
				<form class="col s10" method="post" id="singleUploadForm"
					name="singleUploadForm">
					<div class="row">
						<div class="input-field col s12">
							<div class="file-field">
								<div class="btn">
									<span>New Avatar</span> <input
										id="singleFileUploadInput" type="file" name="file"
										class="file-input">
								</div>
								<div class="file-path-wrapper">
									<input class="file-path validate" type="text"
										placeholder="Upload Image">
								</div>
							</div>
							<button class="btn waves-effect waves-light" type="submit"
								name="action" style="display: none;">
								Update User Avatar <i class="material-icons right">send</i>
							</button>
							<div class="card">
								<div class="card-image">
									<img id="uploaded-image">
								</div>
							</div>
						</div>
					</div>
				</form>
				<form class="col s2" method="post" id="changeAvatar">
					<div class="input-field col s12" style="text-align: right;">
						<button class="btn waves-effect waves-light" type="submit"
							name="Submit" value="Submit">Submit</button>
					</div>
				</form>
				<form class="col s12" method="post" id="changeBio">
					<div class="row">
						<div class="input-field col s12">
							<textarea id="bio" class="materialize-textarea col s10"></textarea>
							<label for="bio" rows="3">New Bio</label>
							<button class="btn waves-effect waves-light col s2" type="submit"
								value="Submit">Submit</button>
						</div>
					</div>
				</form>
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