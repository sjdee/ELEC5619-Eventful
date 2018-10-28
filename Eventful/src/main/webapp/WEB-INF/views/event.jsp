<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ page session="false" %>

<html>
<head>
    <title>${model.event.title}</title>
    <%@ include file="header.jsp" %> 
</head>
<body>

<div class="center">
	
	
	<c:if test="${not empty model.event.eventImagePath}">
		<div class="card-image">
			<img id="eventImage" src="${model.event.eventImagePath}">	
		</div>
	</c:if>
	
	<h1>${model.event.title}</h1>
	
	<a class="waves-effect waves-light btn-large ${model.ability}"
	style="margin-top: 1em; margin-bottom: 1em;"
	href="<c:url value="/event/${model.function}/${model.event.id}" />"><i class="material-icons left">${model.buttonIcon}</i>${model.buttonValue}</a>
	
	<h6> Location: <b>${model.event.location}</b></h6>
	<h6> When: <b>${model.event.datetime}</b> </h6>
	<h6> More info: ${model.event.description} </h6>	
	<h6> Maximum People allowed: ${model.event.maxPeople} </h6>
	<h6> Frequency: ${model.repetition} </h6> <p>

<!--onclick="location.href='/event/rate/${model.event.id}'"  -->
 <form action="#" >
 	<fieldset class="rating" onChange="javascript:submit()" ${model.ratingVisibility}>
	    <input type="radio" id="star5" name="rating" value="5" ${model.check50}/><label class = "full" for="star5" title="Awesome! - 5 stars"></label>
	    <input type="radio" id="star4half" name="rating" value="4.5" ${model.check45}/><label class="half" for="star4half" title="Excellent- 4.5 stars"></label>
	    <input type="radio" id="star4" name="rating" value="4" ${model.check40}/><label class = "full" for="star4" title="Very Good- 4 stars"></label>
	    <input type="radio" id="star3half" name="rating" value="3.5" ${model.check35}/><label class="half" for="star3half" title="Good - 3.5 stars"></label>
	    <input type="radio" id="star3" name="rating" value="3" ${model.check30}/><label class = "full" for="star3" title="Above Average - 3 stars"></label>
	    <input type="radio" id="star2half" name="rating" value="2.5" ${model.check25}/><label class="half" for="star2half" title="Average - 2.5 stars"></label>
	    <input type="radio" id="star2" name="rating" value="2" ${model.check20}/><label class = "full" for="star2" title="Poor- 2 stars"></label>
	    <input type="radio" id="star1half" name="rating" value="1.5" ${model.check15}/><label class="half" for="star1half" title="Very Poor - 1.5 stars"></label>
	    <input type="radio" id="star1" name="rating" value="1" ${model.check10}/><label class = "full" for="star1" title="Unacceptable- 1 star"></label>
	    <input type="radio" id="starhalf" name="rating" value=".5" ${model.check5}/><label class="half" for="starhalf" title="Sucks big time - 0.5 stars"></label>
	</fieldset>
</form>	
	<p>
	<!-- Subscribed users avatars -->
	<c:if test="${not empty model.event.subscribedUsers}">
		<c:forEach var="user" items="${model.event.subscribedUsers}">
			<c:if test="${not empty user.filePath}">
				<span class="card-image avatar">
					<img src="${user.filePath}" class="circle">		
				</span>
				<%-- <span class="card-title">${user.alias}</span> --%>
			</c:if>
		</c:forEach>
	</c:if>	
	<p>
	<a class="waves-effect waves-light btn-large"
		style="margin-top: 1em; margin-bottom: 1em;"
		href="<c:url value="/createPost/${model.event.id}" />"><i class="material-icons left">cloud</i>Create a Post
	</a>
	
</div>


<c:forEach var="post" items="${model.event.posts}">
	<div class="row">
		<div class="col s6 offset-s3">
			<div class="card">
			
				<div class="card-content"  style="padding-top: 1.5em; padding-bottom: 0em;">
					<div class="row valign-wrapper">
		                <div class="col s2 pull-5">
		                	<a href="/profile/${post.poster.id}"><img src="https://cdn.onlinewebfonts.com/svg/img_191958.png" alt="" class="circle responsive-img"></a>
		                </div>
		                <div class="col s10 push-7">
			                <a href="/profile/${post.poster.id}"><h5> ${post.poster.alias} </h5></a>
			                <p class="grey-text"><fmt:formatDate value="${post.timePosted}" pattern="dd/MM/yy 'at' hh:mm a" /></p>
		        		</div>
			    	</div>
				</div>
				<c:if test="${not empty post.imagePath}">
					<div class="card-image">
						<img src="${post.imagePath}">
						
						<span class="card-title">${post.title}</span>
					</div>
				</c:if>
				<div class="card-content" style="padding-top: 0.5em; padding-bottom: 0.5em;">
					<c:if test="${empty post.imagePath}">
						<span class="card-title grey-text">${post.title}</span>
					</c:if>
					<p style="margin-top: 0.5em; margin-bottom: 1.5em;">${post.description}</p>
					<div class="card-panel row valign-wrapper post-bar">
						<div class="valign-wrapper col s6 push-s8">
							<a href="/likePost/${post.id}" onclick="event.preventDefault(); document.getElementById('post-like-form-${post.id}').submit();" ><i class="small material-icons" style="margin-right: 0.5em;">thumb_up</i></a>
							<span> ${post.numLikes} Likes </span>
							<form action="/likePost/${post.id}" method="post" id="post-like-form-${post.id}">
							    <button name="foo" value="Like" style="display: none"></button>
							</form>
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
					                	<a href="/profile/${comment.commenter.id}"><img src="https://cdn.onlinewebfonts.com/svg/img_191958.png" alt="" class="circle responsive-img"></a>
					                </div>
					                <div class="col s11 push-1">
						            	<span class="black-text">
						               		<a href="/profile/${comment.commenter.id}">${comment.commenter.alias}</a><br> ${comment.contents}<br>
						               		<div class="valign-wrapper">
							               		<a href="/likeComment/${comment.id}" onclick="event.preventDefault(); document.getElementById('comment-like-form-${comment.id}').submit();" ><i class="material-icons" style="margin-right: 0.5em; font-size: 1.2em;">thumb_up</i></a>
												<span style="font-size: 0.8em"> ${comment.numLikes} Likes </span>
												<form action="/likeComment/${comment.id}" method="post" id="comment-like-form-${comment.id}">
												    <button name="foo" value="Like" style="display: none"></button>
												</form>
												&nbsp;&middot;&nbsp;<p class="grey-text" style="font-size:0.8em"><fmt:formatDate value="${comment.timePosted}" pattern="dd/MM/yy 'at' hh:mm a" /></p>
						            		</div>
						            	</span>
					        		</div>
						    	</div>
						    </li>
						</c:forEach>
					</ul>
	   		 	</div>
	   		 	
	   		 	<div class="card-action">
	        		<form class="comment-form" id="comment-form-${post.id}" action="/createComment/${post.id}" method="POST">

						<input type="text" name="contents" placeholder="Comment"/>
						<input type="submit" value="Comment" style="display: none"/>
					
					</form>
					<a href="/createComment/${post.id}"
					onclick="event.preventDefault(); document.getElementById('comment-form-${post.id}').submit();" >Post Comment</a>
				</div>
				
			</div>
		</div>
	</div>

</c:forEach>

<%@ include file="footer.jsp" %> 
</body>
</html>