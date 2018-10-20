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
				<table class="col s10 offset-s2">
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
		<c:forEach var="post" items="${model.event.posts}">
			<div class="row">
				<div class="col s6 offset-s3">
					<div class="card">

						<div class="card-content">
							<div class="row valign-wrapper">
								<div class="col s2 pull-5">
									<a href="#"><img
										src="https://cdn.onlinewebfonts.com/svg/img_191958.png" alt=""
										class="circle responsive-img"></a>
								</div>
								<div class="col s10 push-7">
									<a href="#"><h5>${post.poster.alias}</h5></a>
								</div>
							</div>
						</div>

						<div class="card-image">
							<img
								src="https://static.photocdn.pt/images/articles/2017/04/28/iStock-546424192.jpg">
							<span class="card-title">${post.title}</span>
						</div>

						<div class="card-content">
							<p style="margin-top: 0.5em; margin-bottom: 1.5em;">${post.description}</p>
							<div class="card-panel row valign-wrapper post-bar">
								<div class="valign-wrapper col s6 push-s8">
									<a href="#"><i class="small material-icons"
										style="margin-right: 0.5em;">thumb_up</i></a> <span>
										${post.numLikes} Likes </span>
								</div>
								<div class="valign-wrapper col s6 pull-s4">
									<i class="small material-icons" style="margin-right: 0.5em;">comment</i>
									<span> ${post.numComments} Comments </span>
								</div>
							</div>
						</div>

						<div>
							<ul class="collection">
								<c:forEach var="comment" items="${post.comments}">
									<li>
										<div class="row valign-wrapper comment-box">
											<div class="col s1 pull-11">
												<a href="#"><img
													src="https://cdn.onlinewebfonts.com/svg/img_191958.png"
													alt="" class="circle responsive-img"></a>
											</div>
											<div class="col s11 push-1">
												<span class="black-text"> <a href="#">${comment.commenter.alias}</a><br>
													${comment.contents}
												</span>
											</div>

										</div>
									</li>
								</c:forEach>
							</ul>
						</div>

						<div class="card-action">
							<form id="comment-form-${post.id}"
								action="/eventful/createComment/${post.id}" method="POST">

								<input type="text" name="contents" placeholder="Comment" /> <input
									type="submit" value="Comment" style="display: none" />

							</form>
							<a href="/eventful/createComment/${post.id}"
								onclick="event.preventDefault(); document.getElementById('comment-form-${post.id}').submit();">Post
								Comment</a>
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