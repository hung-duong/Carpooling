<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Post</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu.css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>	
</head>
<body>
<div class="topnav">
  <a href="/Carpooling/login">Home</a>
  <a class="active"  href="addpost.jsp">Add Post</a>
  <a href="#map">Map</a>
  <a href="#map">Update Profile</a>
</div>
<h1>User Add Post</h1>
	<form action="AddPost" method="post">
		<p>Title: </p> <input type="text" name="title" id="title"/>
		<p>Content: </p> <textarea rows="4" cols="50" name="body" id="body"></textarea>
		<p>Type: &nbsp;&nbsp;
		 <select name="posttype" id="posttype">
		  <option value="0">Asking</option>
		  <option value="1">Offering</option>
		</select></p>		
		<input type="submit" value="Submit"/>
	</form>

</body>
</html>