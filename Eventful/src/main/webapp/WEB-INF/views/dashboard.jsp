<%@ include file="/WEB-INF/views/include.jsp"%>
<html>
<head>
<title>Dashboard</title>
<%@ include file="header.jsp"%>
</head>
<body>
	<div class="row" style="text-align: center;">
		<h2>Hello there, ${model.user.alias}!</h2>
		<h4>It is currently ${model.serverTime}.</h4>
	</div>

	<div class="container"
		style="display: flex; justify-content: center; align-content: center;">
		<div>
			<div class="row">
				<div class="col s12">
					<h5 style="text-align: center;">Here's the events you've
						created:</h5>
				</div>
			</div>
			<c:choose>
				<c:when test="${empty model.events}">
					<p style="text-align: center;">
						You haven't created any events yet! Create an event <a
							href="<c:url value="/createEvent"/>">here.</a>
					</p>
				</c:when>
				<c:otherwise>
					<div
						style="width: 700px; height: 400px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
						<c:forEach var="event" items="${model.events}">
							<div class="row">
								<div class="col s6 offset-s3">
									<div class="card">
										<div class="card-image">
											<!-- NEW CODE: show event image if it exists, otherwise show stock image  -->
											<c:if test="${empty event.eventImagePath}">
												<img
													src="https://static.photocdn.pt/images/articles/2017/04/28/iStock-546424192.jpg">
											</c:if>
											<c:if test="${not empty event.eventImagePath}">
												<img src="${event.eventImagePath}">
											</c:if>

											<!-- OLD CODE: Show stock image for all events  -->
											<!-- <img src="https://static.photocdn.pt/images/articles/2017/04/28/iStock-546424192.jpg"> -->

											<span class="card-title"><a style="color: white;"
												href="event/${event.getId()}"><b>Event:</b>
													${event.title}</a></span>
										</div>

										<div class="card-content">
											<p style="margin-top: 0.5em; margin-bottom: 1.5em;">
												<b>Description:</b><br>${event.description}</p>
											<div class="card-panel row valign-wrapper post-bar">
												<ul>
													<li>Location: ${event.location}</li>
													<li>Time: ${event.datetime}</li>
													<li>Maximum people: ${event.maxPeople}</li>
													<li>Frequency: ${event.repetition}</li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>

						</c:forEach>
					</div>

					<%-- 			<c:forEach var="post" items="${model.event.posts}">
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
		</c:forEach> --%>
				</c:otherwise>
			</c:choose>
		</div>

		<div>
			<div class="row">
				<div class="col s12">
					<h5 style="text-align: center;">Here's the events you've
						subscribed to:</h5>
				</div>
			</div>
			<c:choose>
				<c:when test="${empty model.user.subscribedEvents}">
					<p style="text-align: center;">You haven't subscribed to any
						events yet!</p>
				</c:when>
				<c:otherwise>
					<div
						style="width: 700px; height: 400px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
						<c:forEach var="event" items="${model.user.subscribedEvents}">
							<div class="row">
								<div class="col s6 offset-s3">
									<div class="card">
										<div class="card-image">
											<!-- NEW CODE: show event image if it exists, otherwise show stock image  -->
											<c:if test="${empty event.eventImagePath}">
												<img
													src="https://static.photocdn.pt/images/articles/2017/04/28/iStock-546424192.jpg">
											</c:if>
											<c:if test="${not empty event.eventImagePath}">
												<img src="${event.eventImagePath}">
											</c:if>

											<!-- OLD CODE: Show stock image for all events  -->
											<!-- <img src="https://static.photocdn.pt/images/articles/2017/04/28/iStock-546424192.jpg"> -->

											<span class="card-title"><a style="color: white;"
												href="event/${event.getId()}"><b>Event:</b>
													${event.title}</a></span>
										</div>

										<div class="card-content">
											<p style="margin-top: 0.5em; margin-bottom: 1.5em;">
												<b>Description:</b><br>${event.description}</p>
											<div class="card-panel row valign-wrapper post-bar">
												<ul>
													<li>Location: ${event.location}</li>
													<li>Time: ${event.datetime}</li>
													<li>Maximum people: ${event.maxPeople}</li>
													<li>Frequency: ${event.repetition}</li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>

						</c:forEach>
					</div>

				</c:otherwise>
			</c:choose>
		</div>

	</div>
<%@ include file="footer.jsp"%>
</body>
</html>