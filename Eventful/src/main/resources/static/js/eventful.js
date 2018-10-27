$(document).ready(function() {
	
	var filePath = "";
		
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
	
	$('#create-post, #registerUser, #changeAvatar').submit(function(event) {
									
		$(this).append('<input type="hidden" name="filePath" value="' + filePath + '">');
	    
	});

    // File Input Path
    $(document).on('change', '.file-field input[type="file"]', function () {
    	
    	$('#singleUploadForm').submit();
    });
    
});
