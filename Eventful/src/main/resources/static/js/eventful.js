var filePath = "";
var oldestPostId = -1;

$(document).ready(function() {
	
	$('#singleUploadForm').submit(function(event) {
	    var formElement = this;
	    // You can directly create form data from the form element
	    // (Or you could get the files from input element and append them to FormData as we did in vanilla javascript)
	    var formData = new FormData(formElement);
	    
	    $.ajax({
	        type: "POST",
	        enctype: 'multipart/form-data',
	        url: "/uploadFile",
	        data: formData,
	        processData: false,
	        contentType: false,
	        success: function (response) {
	            console.log(response);
	            filePath = response.fileDownloadUri;
	            document.getElementById('uploaded-image').setAttribute("src", filePath); 
	            window.alert("Image uploaded successfully!");
	        },
	        error: function (error) {
	            console.log(error);
	            // process error
	        }
	    });

	    event.preventDefault();
	});
	
	$('#create-post').submit(function(event) {
									
		$(this).append('<input type="hidden" name="filePath" value="' + filePath + '">');
	    
	});

    // File Input Path
    $(document).on('change', '.file-field input[type="file"]', function () {
    	
    	$('#singleUploadForm').submit();
    });
   
	$('#singleUploadForm').submit(function(event) {
	    var formElement = this;
	    // You can directly create form data from the form element
	    // (Or you could get the files from input element and append them to FormData as we did in vanilla javascript)
	    var formData = new FormData(formElement);
	    
	    $.ajax({
	        type: "POST",
	        enctype: 'multipart/form-data',
	        url: "/uploadFile",
	        data: formData,
	        processData: false,
	        contentType: false,
	        success: function (response) {
	            filePath = response.fileDownloadUri;
	            
	            window.alert("Image uploaded successfully!");
	        },
	        error: function (error) {
	            console.log(error);
	            // process error
	        }
	    });

	    event.preventDefault();
	});
    
});

function likePost(postId) {
      
  var postLike = {}
  postLike["id"] = postId;
  
  $.ajax({
      type: "POST",
      contentType: "application/json",
      url: "/likePost/",
      data: JSON.stringify(postLike),
      dataType: 'json',
      cache: false,
      timeout: 600000,
      success: function (data) {
    	  
    	  updateLikeIcon(document.getElementById("post-like-icon-" + postId), data.isLiked);
    	  document.getElementById("post-likes-" + postId).innerHTML = data.numLikes + " Likes";
          
      },
      error: function (e) {

          window.alert("ERROR : "+ JSON.stringify(e));
          
      }
  });
  
}

function loadPosts(eventId) {
    
  var postRequest = {}
  postRequest["eventId"] = eventId;
  postRequest["oldestPostId"] = oldestPostId;
  
  $.ajax({
      type: "POST",
      contentType: "application/json",
      url: "/loadPosts/",
      data: JSON.stringify(postRequest),
      dataType: 'json',
      cache: false,
      timeout: 600000,
      success: function (data) {
    	  
    	  //window.alert("WEWLAD: " + data.testString);
    	  //window.alert("WEWEE: " + data.posts[0].title);
          
    	  for (i = 0; i < data.posts.length; i++) {
    		  var t = document.querySelector('#test-template');
        	  
    		  t.content.querySelector('a[class*="poster-img-link"]').href = "/profile/" + data.posts[i].poster.id;
    		  t.content.querySelector('a[class*="poster-alias-link"]').href = "/profile/" + data.posts[i].poster.id;
    		  t.content.querySelector('a[class*="poster-alias-link"]').innerHTML = data.posts[i].poster.alias;
    		  t.content.querySelector('p[class*="time-post-posted"]').innerHTML = data.posts[i].timePosted;
    		  t.content.querySelector('p[class*="post-description"]').innerHTML = data.posts[i].description;
        	      		  
    		  if (data.posts[i].imagePath === null || data.posts[i].imagePath == "") {
    			  t.content.querySelector('div[class*="post-img-card"]').style.display = "none";
    		  } else {
        		  t.content.querySelector('img[class*="post-img"]').src = data.posts[i].imagePath;
        		  t.content.querySelector('span[class*="post-img-title"]').innerHTML = data.posts[i].title;
        		  t.content.querySelector('span[class*="post-text-title"]').style.display = "none"
    		  }
    		  
    		  t.content.querySelector('span[class*="post-text-title"]').innerHTML = data.posts[i].title;
    		  t.content.querySelector('p[class*="post-description"]').innerHTML = data.posts[i].description;
    		  t.content.querySelector('a[class*="like-post-button"]').setAttribute( "onClick",  "likePost(" + data.posts[i].id + ");");
    		  t.content.querySelector('span[class*="like-post-icon"]').id = "post-like-icon-" + data.posts[i].id;
    		  
    		  t.content.querySelector('span[class*="like-post-icon"]').id = "post-like-icon-" + data.posts[i].id;
    		  updateLikeIcon(t.content.querySelector('span[class*="like-post-icon"]'), data.posts[i].isLiked);
    		  t.content.querySelector('span[class*="post-likes-count"]').id = "post-likes-" + data.posts[i].id;
    		  t.content.querySelector('span[class*="post-likes-count"]').innerHTML = data.posts[i].numLikes + " Likes";
    		  
    		  t.content.querySelector('span[class*="post-num-comments"]').innerHTML = data.posts[i].numComments + " Comments";
    		      		  
    		  t.content.querySelector('form[class*="comment-form"]').id = "comment-form-" + data.posts[i].id;
    		  
    		  t.content.querySelector('form[class*="comment-form"]').setAttribute( "action",  "/createComment/" + data.posts[i].id);
    		  
    		  t.content.querySelector('a[class*="create-comment-button"]').href = "/createComment/" + data.posts[i].id;
    		  
    		  t.content.querySelector('a[class*="create-comment-button"]').setAttribute( "onClick", "event.preventDefault(); document.getElementById('comment-form-" + data.posts[i].id + "').submit();");
    		      		     		 
    		  t.content.querySelector('ul[class*="comments-anchor"]').id = "comment-anchor-" + data.posts[i].id;
    		  
        	  // add to document DOM
        	  var clone = document.importNode(t.content, true); // where true means deep copy
        	  document.getElementById("post-anchor").appendChild(clone);
        	  
        	  var commentAnchor = document.getElementById("comment-anchor-" + data.posts[i].id);
        	  loadComment(data.posts[i].comments[0], commentAnchor);
        	  
    	  } 
    	  
      },
      error: function (e) {

          window.alert("ERROR : " + JSON.stringify(e));
    	  console.log(e);

      }
  });

}

function loadComment(comment, commentAnchor) {
	
	var t = document.querySelector('#comment-template');
	
	t.content.querySelector('span[class*="comment-contents"]').innerHTML = "something";
	t.content.querySelector('a[class*="commenter-img-link"]').href = "/profile/" + comment.commenter.id;
	t.content.querySelector('a[class*="commenter-alias-link"]').innerHTML = "/profile/" + comment.commenter.id;

	t.content.querySelector('a[class*="commenter-alias-link"]').innerHTML = comment.commenter.alias;
		
	t.content.querySelector('a[class*="like-comment-link"]').setAttribute( "onClick", "likeComment(" + comment.id + ");");
		
	t.content.querySelector('span[class*="comment-like-icon"]').id = "comment-like-icon-" + comment.id;

	updateLikeIcon(t.content.querySelector('#comment-like-icon-' + comment.id), true);
		
	t.content.querySelector('span[class*="num-comment-likes"]').innerHTML = comment.numLikes + " Likes";
	
	// add to document DOM
	var clone = document.importNode(t.content, true); // where true means deep copy
	commentAnchor.appendChild(clone);
	
}

function likeComment(commentId) {
   	
  var commentLike = {}
  commentLike["id"] = commentId;
  
  $.ajax({
      type: "POST",
      contentType: "application/json",
      url: "/likeComment/",
      data: JSON.stringify(commentLike),
      dataType: 'json',
      cache: false,
      timeout: 600000,
      success: function (data) {

    	  updateLikeIcon(document.getElementById("comment-like-icon-" + commentId), data.isLiked);
    	  document.getElementById("comment-likes-" + commentId).innerHTML = data.numLikes + " Likes";
          
      },
      error: function (e) {

          window.alert("ERROR : ", e);
    	  console.log(e);
    	  
      }
  });
  
}

function updateLikeIcon(icon, isLiked) {
	
	if (isLiked) {
		
		if (icon.classList.contains("mdi-thumb-up-outline")){
			icon.classList.remove("mdi-thumb-up-outline");
		}
		
		icon.classList.add("mdi-thumb-up");
		
	} else {
		
		if (icon.classList.contains("mdi-thumb-up")){
			icon.classList.remove("mdi-thumb-up");
		}
		
		icon.classList.add("mdi-thumb-up-outline");
		
	}
	
}