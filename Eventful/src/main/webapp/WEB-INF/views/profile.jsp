<%@ include file="/WEB-INF/views/include.jsp"%>
<html>
<head>
<title>Profile</title>
<%@ include file="header.jsp"%>
</head>
<body>
	<div class="container">
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
				<h1 class="col s12" style="text-align: center;">${model.user.alias}</h1>
				<table class="col s10 offset-s2"> <!-- TODO:: FIX THIS -->
					<tbody>
						<c:if test="${model.selfProfile}">
							<tr style="border: none;">
								<td width="20%">Email:</td>
								<td width="80%">${model.user.email}</td>
							</tr>
						</c:if>
						<tr style="border: none;">
							<td>Bio:</td>
							<td>${model.user.bio}</td>
						</tr>
						<tr style="border: none;">
							<td>Rating:</td>
							<td><fmt:formatNumber value="${model.user.avgEventRating}"
									type="number" groupingUsed="false" maxFractionDigits="2" />/5</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="col s5 pull-s7" style="text-align: right;">
				<img src="https://cdn.onlinewebfonts.com/svg/img_191958.png" alt=""
					class="circle responsive-img" style="width: 300px;">
			</div>
		</div>
		<div class="row">
			<div class="col s12">
				<h5 style="text-align: center;">Created Events:</h5>
			</div>
		</div>
		<c:forEach var="event" items="${model.events}">
			<div class="row">
				<div class="col s6 offset-s3">
					<div class="card">
						<div class="card-image">
							<img
								src="https://static.photocdn.pt/images/articles/2017/04/28/iStock-546424192.jpg">
							<span class="card-title">${event.title}</span>
						</div>

						<div class="card-content">
							<p style="margin-top: 0.5em; margin-bottom: 1.5em;">${event.description}</p>
							<div class="card-panel row valign-wrapper post-bar">
								<span>Rating: </span>
							</div>
						</div>
					</div>
				</div>
				`
			</div>

		</c:forEach>

	</div>


	<%@ include file="footer.jsp"%>
</body>
</html>