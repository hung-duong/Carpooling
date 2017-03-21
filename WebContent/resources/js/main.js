"use strict";

$(function() {
	var USERPOST_URL = "http://localhost:8080/Carpooling/Post";	
	var USERCOMM_URL = "http://localhost:8080/Carpooling/Comment?postId=";	
	var POSTDEL_URL = "http://localhost:8080/Carpooling/DelPost?postId=";
	var ADDCOMM_URL = "http://localhost:8080/Carpooling/AddComment?postId=";
	// click GO button to search
	
	(function() { 
		$.get(USERPOST_URL)
		.done(displayPosts)
		.fail(ajaxFailure);
	})();
	
	//display posts of user
	function displayPosts(posts) {
	    $("#userPost").empty();
	    
		// All posts of user
		// using div for each post
		$.each(posts, function(i, post) {
			// create dev posts of user
	        var divPost = $("<div>");

	        // creat title, body post and hidden input
	        var title = $("<p>").text(post.title);
	        var body= $("<p>").text(post.body);
	        var hiddenPostId = $("<input>").prop("type", "hidden").val(post.id);

	        if ($("#username").val() == post.username) {
		        var btnDel = $("<input>").prop("value", "Delete")
					.prop("type", "button");
			    btnDel.on( "click", function(evt) {
						var postId = $(this).siblings(":hidden").val();
					$.get(POSTDEL_URL + postId)
						.done(function(comments){
							deleteComment(divPost, comments);
						})
						.fail(ajaxFailure);
					evt.stopImmediatePropagation();
				});
	        }
		
	        // create comment button
			var btnComments = $("<input>").prop("value", "Comments")
				.prop("type", "button");
			btnComments.on( "click", function(evt) {
					var postId = $(this).siblings(":hidden").val();
				$.get(USERCOMM_URL + postId)
					.done(function(comments){
						displayComments(divPost, comments, postId);
					})
					.fail(ajaxFailure);
				evt.stopImmediatePropagation();
			});
			

	        // setup css
	        divPost.addClass("divPost");
	        title.addClass("pTitle");
	        body.addClass("pBody");
	        if ($("#username").val() == post.username) 
	        	btnDel.addClass("btnComments");
	        btnComments.addClass("btnComments");
	        
			
			// append them to DIV element
			title.appendTo(divPost);
			body.appendTo(divPost);
			if ($("#username").val() == post.username) 
				 btnDel.appendTo(divPost);
			btnComments.appendTo(divPost);			
			hiddenPostId.appendTo(divPost);
			divPost.appendTo($("#userPost"));
		});
	    
	    $("#userPost").prop("class", "userPost");
	}

	function deleteComment(parent, comments) {
		parent.remove();
	}
	// display comments of user post
	function displayComments(parent, comments, postId) {
	    $("div.divComment").remove();
		
		$.each(comments, function(i, comment) {
			// create elements
	        var divComment = $("<div>");
			var name = $("<span>").text(comment.username + " ");
			var body = $("<p>").text(comment.body);
			var header = $("<p>");
						
	        // setup css
	        divComment.addClass("divComment");
	        header.addClass("divCommentTitle");
	        body.addClass("pBody");
	        
			// append to DOM
			name.appendTo(header);
			header.appendTo(divComment);
			body.appendTo(divComment);
			divComment.appendTo(parent);
		});
		
	
		var textComment = $("<textarea rows='1' cols='100' id='postcomm' value=''>");		
		textComment.addClass("textEdit");
	    // create comment button
		var btnAdd = $("<input>").prop("value", "Add")
			.prop("type", "button");
		btnAdd.addClass("btnAdd");
		btnAdd.on( "click", function(evt) {				
			$.get(ADDCOMM_URL + postId + "&comment=" + $("#postcomm").val())
				.done(function(comments){
					displayComments(parent, comments, postId);
				})
				.fail(ajaxFailure);
			evt.stopImmediatePropagation();
		});
		var divComm = $("<div>");		
        // setup css
		divComm.addClass("divComment");
		textComment.appendTo(divComm);		
		btnAdd.appendTo(divComm);		
		divComm.appendTo(parent);
	}

	function ajaxFailure(xhr, status, exception) {
	    console.log(xhr, status, exception);
	}
	
});
