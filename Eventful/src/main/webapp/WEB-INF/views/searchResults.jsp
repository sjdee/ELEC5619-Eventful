<%@ include file="/WEB-INF/views/include.jsp"%>
<html>
<head>
<title>Search Results</title>
<%@ include file="header.jsp"%>
</head>
<body>
	<div class="row" style="text-align: center;">
	    <h2>Search Results
	    <c:if test="${not empty model.searchQuery}">: ${model.searchQuery}</c:if>
	    </h2>
    </div>
    
	<div class="container">	
		<c:choose>
			<c:when test="${empty model.events}">
				<p style="text-align: center;">Your search returned no results!</p>
			</c:when>
			<c:otherwise>
				<div class="row">			
				<c:forEach var="event" items="${model.events}">
					<div class="row">
						<div class="col s6 offset-s3">
							<div class="card">
								<div class="card-image">
								<!-- NEW CODE: show event image if it exists, otherwise show stock image  -->
									<c:if test="${empty event.eventImagePath}">
										<img src="https://static.photocdn.pt/images/articles/2017/04/28/iStock-546424192.jpg">
									</c:if>
									<c:if test="${not empty event.eventImagePath}">
										<img src="${event.eventImagePath}">
									</c:if>
									
									<!-- OLD CODE: Show stock image for all events  -->
									<!-- <img src="https://static.photocdn.pt/images/articles/2017/04/28/iStock-546424192.jpg"> -->
									
									<span class="card-title"><a style="color:white;" href="event/${event.getId()}"><b>Event:</b> ${event.title}</a></span>
								</div>
		
								<div class="card-content">
									<p style="margin-top: 0.5em; margin-bottom: 1.5em;"><b>Description:</b><br>${event.description}</p>
									<div class="card-panel row valign-wrapper post-bar">
										<ul>
											<li>Location: ${event.location}</li>
											<li>Time: ${event.datetime}</li>
											<li>Maximum people: ${event.maxPeople} </li>
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
	    
	<%@ include file="footer.jsp"%>
</body>
</html>