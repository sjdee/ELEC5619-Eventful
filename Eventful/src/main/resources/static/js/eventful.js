$(document).ready(function() {
	
	var filePath = "";
	var oldestPostDate;
	
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

          window.alert("ERROR : ", e);

      }
  });
  
}

function loadPosts() {
    
	  var postRequest = {}
	  postRequest["oldestPostDate"] = oldestPostDate;
	  
	  $.ajax({
	      type: "POST",
	      contentType: "application/json",
	      url: "/loadPosts/",
	      data: JSON.stringify(postRequest),
	      dataType: 'json',
	      cache: false,
	      timeout: 600000,
	      success: function (data) {
	    	  
	    	  window.alert("WEWLAD: " + data.posts.length);
	          
	      },
	      error: function (e) {

	          window.alert("ERROR : ", e);

	      }
	  });
	  
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