<%@ include file="/WEB-INF/views/include.jsp"%>
<html>
<head>
<title>Profile</title>
<%@ include file="header.jsp"%>
<script src="<c:url value="/js/profile.js" />"></script>
</head>
<body>
	<div class="container">
		<div class="col s10">
			<c:if test="${model.selfProfile}">
				<div class="row">
					<div class="col s3 push-s11">
						<a class="waves-effect waves-light btn-large"
							style="margin-top: 1em; margin-bottom: 1em;"
							href="<c:url value="/profile/edit" />">Edit Profile</a>
					</div>
				</div>
			</c:if>
			<div class="row">
				<div class="col s7 push-s5">
					<h2 class="col s12" style="text-align: center;">${model.user.alias}</h2>
					<div class="col s12">
						<c:if test="${model.selfProfile}">
							<div class="row">
								<div class="col s5" style="text-align: right;">Email:</div>
								<div class="col s7">${model.user.email}</div>
							</div>
						</c:if>
						<div class="col s11 push-s1" style="text-align: bio;">${model.user.bio}</div>
					</div>
				</div>
				<div class="col s5 pull-s7" style="text-align: center;">
					<c:if test="${not empty model.user.filePath}">
						<img src="${model.user.filePath}" alt=""
							class="circle responsive-img" style="width: 200px;">
					</c:if>
					<c:if test="${empty model.user.filePath}">
						<img src="https://cdn.onlinewebfonts.com/svg/img_191958.png"
							alt="" class="circle responsive-img" style="width: 200px;">
					</c:if>
				</div>
			</div>
			<c:if test="${empty model.events}">
				<div class="row">
					<div class="col s12">
						<h5 style="text-align: center;">The user has not create an
							event yet.</h5>
					</div>
				</div>
			</c:if>
			<c:if test="${not empty model.events}">
				<div class="row">
					<div class="col s12">
						<h5 style="text-align: center;">Created Events:</h5>
					</div>
				</div>
				<div class="row">
					<div class="col s12">
						<div class="slider">
							<ul class="slides">
								<c:forEach var="event" items="${model.events}">
									<li><a href="<c:url value="/event/${event.id}"/>"> <c:if
												test="${not empty event.eventImagePath}">
												<img src="${event.eventImagePath}" />
											</c:if> <c:if test="${empty event.eventImagePath}">
												<img
													src="https://img00.deviantart.net/c24e/i/2012/331/5/b/minimal_grey_by_gominhos-d5mdf9z.png" />
											</c:if>
											<div class="caption center-align">
												<h3>${event.title}</h3>
												<h5 class="light grey-text text-lighten-3">${event.description}</h5>
											</div>
									</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</c:if>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>