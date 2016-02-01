$(function () {
    $('#fileupload').fileupload({
        dataType: 'json',
        
        done: function (e, data) {
        	$("tr:has(td)").remove();
            $.each(data.result, function (index, image) {
            	
            	
                $("#uploaded-files").append(
                		$('<tr/>')
                		.append($('<input type="text" value="'+ image.name + '">'))
                		.append($('<td/>').text(image.fileSize))
                		.append($('<td/>').text(image.fileType))
                		.append($('<td/>').html('<a href="/obrazkovaBanka/controller/get/' + image.hash +'" class="thumbnail">' 
                				+ "<img src='/obrazkovaBanka/controller/thumbnail/getImage/" + image.hash + "' alt="+ image.fileName +"/>" + 
                				'</a>'))
                		.append($('<td/>').html('<a href="/obrazkovaBanka/image/' + image.hash +'">Show</a>'))
                		.append($('<td/>').html('<a href="/obrazkovaBanka/image/delete/' + image.deleteHash +'">Delete</a>'))
                		)//end $("#uploaded-files").append()
            }); 
        },
        
        progressall: function (e, data) {
	        var progress = parseInt(data.loaded / data.total * 100, 10);
	        $('.progress-bar').css(
	            'width',
	            progress + '%'
	        );
	        $('.progress-bar').text(progress + "%")
	        
   		},
   		
		dropZone: $('#dropzone')
    });
});