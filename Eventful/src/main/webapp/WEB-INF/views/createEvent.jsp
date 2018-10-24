<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ page session="false" %>


<html>

<head>
	<title>Create Event</title>
    <%@ include file="header.jsp" %>  
	<script src="/js/createEvent.js"></script> 
</head>

<body>

<div class="center">
	<h1>Create Event</h1>
</div>

<form action="/createEvent" method="POST">
	<div class="row">
		<div class="col s6 offset-s3">
			<!--Title: <input type="text" name="title"/>-->
			<!--Description: <input type="text" name="description"/>-->
			<div class="row">
				<div class="input-field col s12">
		        	<input id="title-field" type="text" class="validate" name="title">
		        	<label for="title-field">Event Title*</label>
		        </div>
		    	<div class="input-field col s12">
		        	<textarea id="description-area" class="materialize-textarea" name="description"></textarea>
          			<label for="description-area">Description*</label>
		    	</div>
		    	<div class="input-field col s12">
		        	<input id="location-field" type="text" class="validate" name="location">
		        	<label for="location-field">Location*</label>
		        </div>
				<div class="input-field col s12">
		        	<input id="people-field" type="number" min="1" class="validate" name="maxPeople">
		        	<label for="people-field">Maximum People Allowed</label>
		        </div>
		        <div class="input-field col s12">
		        	<input id="date-field" type="text" class="datepicker" format="ddmmyyyy">
		        	<label for="date-field">Pick date of the event</label>
		        </div>
		        <div class="input-field col s12">
		        	<input id="time-field" type="text" class="timepicker">
		        	<label for="time-field">Pick time of the event</label>
		        </div>
		        <div class="input-field col s12">
		        	<p>
		        	<label>Repetition</label>
		        	<p>
		        	<label>
        				<input type="radio" name="repetition" value="0" checked />
        				<span>One off</span>
     				 </label>
     				 <label>
        				<input type="radio" name="repetition" value="1" />
        				<span>Daily</span>
     				 </label>
     				 <label>
        				<input type="radio" name="repetition" value="2" />
        				<span>Weekly</span>
     				 </label>
     				 <label>
        				<input type="radio" name="repetition" value="3" />
        				<span>Fortnightly</span>
     				 </label>
     				 <label>
        				<input type="radio" name="repetition" value="4" />
        				<span>Montlhy</span>
     				 </label>
		        </div>
		    	<div class="input-field col s12">
			    	<div class="file-field">
					<div class="btn">
					    <span>File</span>
					    <input type="file">
					</div>
					<div class="file-path-wrapper">
					    <input class="file-path validate" type="text" placeholder="Event Image">
						</div>
					</div>
				</div>
		    </div>
			
			<div class="center">
				<button class="btn waves-effect waves-light" type="submit" name="action">Create Event
				  <i class="material-icons right">send</i>
				</button>
        	</div>
		</div>
	</div>
</form>
<%@ include file="footer.jsp" %> 

</body>
</html>