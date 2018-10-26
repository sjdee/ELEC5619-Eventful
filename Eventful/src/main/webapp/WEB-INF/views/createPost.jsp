<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ page session="false" %>

<html>
<head>
    <title>Event</title>
    <%@ include file="header.jsp" %>  
</head>
<body>

<div class="center">
	<h1>Create post</h1>
	
	<p> Creating post for the event <b>${model.event.title}</b> </p>
</div>

<form action="/createPost/${model.event.id}" method="POST" id="create-post">
	<div class="row">
		<div class="col s6 offset-s3">
			<!--Title: <input type="text" name="title"/>-->
			<!--Description: <input type="text" name="description"/>-->
			<div class="row">
				<div class="input-field col s12">
		        	<input id="title-field" type="text" class="validate" name="title">
		        	<label for="title-field">Post Title</label>
		        </div>
		    	<div class="input-field col s12">
		        	<textarea id="description-area" class="materialize-textarea" name="description"></textarea>
          			<label for="description-area">Description</label>
		    	</div>
		    	<!--<div class="input-field col s12">
			    	<div class="file-field">
					<div class="btn">
					    <span>File</span>
					    <input type="file">
					</div>
					<div class="file-path-wrapper">
					    <input class="file-path validate" type="text" placeholder="Post Image">
						</div>
					</div>
				</div>  -->
		    </div>
		    
        </div>
			<!--<input type="submit" value="Post"/>-->
			<div class="center">
				<button class="btn waves-effect waves-light" type="submit" name="action">Post
				  <i class="material-icons right">send</i>
				</button>
        	</div>
		</div>
	</div>

</form>

<form id="singleUploadForm" name="singleUploadForm">
	<div class="row">
		<div class="col s6 offset-s3">
			<div class="row">
		    	<div class="input-field col s12">
			    	<div class="file-field">
					<div class="btn">
					    <span>Upload Image</span>
					    <input id="singleFileUploadInput" type="file" name="file" class="file-input">
					</div>
					<div class="file-path-wrapper">
					    <input class="file-path validate" type="text" placeholder="Post Image">
						</div>
					</div>
				</div>
		    </div>
		    
        </div>
			<!--<input type="submit" value="Post"/>-->
			<div class="center">
				<button class="btn waves-effect waves-light" type="submit" name="action" style="display: none;">Post
				  <i class="material-icons right">send</i>
				</button>
        	</div>
		</div>
	</div>

</form>

<!-- <div class="single-upload">
	<h3>Upload Single File</h3>
	 <form id="singleUploadForm" name="singleUploadForm">
		<div class="file-field">
    		<input id="singleFileUploadInput" type="file" name="file" class="file-input" required />
    	</div>
    	<button type="submit" class="primary submit-btn">Submit</button>
	 </form>
	<div class="upload-response">
  		<p id="singleFileUploadError"></p>
    	<img src="#" > <p>
	<div id="singleFileUploadSuccess"></div>
</div> -->

<div class="row">
		<div class="col s6 offset-s3">
			<div class="card">
				<div class="card-image">
					<img id="uploaded-image">
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="footer.jsp" %> 
</body>
</html>