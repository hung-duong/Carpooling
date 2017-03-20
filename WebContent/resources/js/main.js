"use strict";

$(function() {
	var USERPOST_URL = "http://localhost:8080/Carpooling/Post";	
	var USERCOMM_URL = "http://localhost:8080/Carpooling/Comment?postId=";	
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

	        // create comment button
			var btnComments = $("<input>").prop("value", "Comments")
				.prop("type", "button");
			btnComments.on( "click", function(evt) {
					var postId = $(this).siblings(":hidden").val();
				$.get(USERCOMM_URL + postId)
					.done(function(comments){
						displayComments(divPost, comments);
					})
					.fail(ajaxFailure);
				evt.stopImmediatePropagation();
			});

	        // setup css
	        divPost.addClass("divPost");
	        title.addClass("pTitle");
	        body.addClass("pBody");
	        btnComments.addClass("btnComments");
			
			// append them to DIV element
			title.appendTo(divPost);
			body.appendTo(divPost);
			btnComments.appendTo(divPost);
			hiddenPostId.appendTo(divPost);
			divPost.appendTo($("#userPost"));
		});
	    
	    $("#userPost").prop("class", "userPost");
	}

	// display comments of user post
	function displayComments(parent, comments) {
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
	}

	function ajaxFailure(xhr, status, exception) {
	    console.log(xhr, status, exception);
	}
	
});
