<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ page session="false" %>

<html>
<head>
    <title>Event</title>
    <%@ include file="header.jsp" %> 
</head>
<body>

<div class="center">
	
	<h1> ${model.event.title} </h1>
		
	<a class="waves-effect waves-light btn-large ${model.ability}"
	style="margin-top: 1em; margin-bottom: 1em;"
	href="<c:url value="/event/${model.function}/${model.event.id}" />"><i class="material-icons left">${model.buttonIcon}</i>${model.buttonValue}</a>
	
	
	<h6> <b>Location:</b> ${model.event.location} </h6>
	<h6> <b>When:</b> ${model.event.datetime} </h6>
	<h6> <b>More info:</b> ${model.event.description} </h6>
	
	<h6> Maximum People allowed: ${model.event.maxPeople} </h6>
	<h6> Frequency: ${model.repetition} </h6>
	<img src="${model.event.eventImage}" alt="Event Image"> <p>
	
	
	<a class="waves-effect waves-light btn-large"
	style="margin-top: 1em; margin-bottom: 1em;"
	href="<c:url value="/createPost/${model.event.id}" />"><i class="material-icons left">cloud</i>Create a Post</a>
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
							<!--<a href="/likePost/${post.id}" onclick="event.preventDefault(); document.getElementById('post-like-form-${post.id}').submit();" ><i class="small material-icons" style="margin-right: 0.5em;">thumb-up-outline</i></a>-->
							<a href="javascript:void(0)" onclick="likePost(${post.id});"><span id="post-like-icon-${post.id}" class="mdi mdi-thumb-up-outline" style="font-size:2em; margin-right: 0.5em;"></span></a>
							<span id="post-likes-${post.id}"> ${post.numLikes} Likes </span>
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
						               			<a href="javascript:void(0)" onclick="likeComment(${comment.id});"><span id="comment-like-icon-${comment.id}" class="mdi mdi-thumb-up-outline" style="font-size:1.2em; margin-right: 0.5em;"></span></a>
							               		<span style="font-size: 0.8em" id="comment-likes-${post.id}"> ${comment.numLikes} Likes </span>
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

<a href="javascript:void(0)" onclick="loadPosts();">Load more posts...</a>

<%@ include file="footer.jsp" %> 
</body>
</html>