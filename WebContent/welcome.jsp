<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Welcome Page</title>
	<link rel="stylesheet" href="/Carpooling/resources/css/main.css"/>
	<link rel="stylesheet" href="/Carpooling/resources/css/menu.css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js" type="text/javascript"></script>
	<script src="/Carpooling/resources/js/main.js"></script>	
	
</head>
<body>


</head>
<body>

<div class="topnav">
  <a class="active" href="/Carpooling/login">Home</a>
  <a href="/Carpooling/AddPost">Add Post</a>
  <a href="#map">Map</a>
  <a href="#map">Update Profile</a>
</div>
		<input type="hidden" name="username" id="username" value="${username}">
		<div id="container">
			<h1>Welcome to Car Pooling Service</h1>		
 			<br>
 			<div id="userPost" class="divHide">
			</div>
		</div>		
</body>
</html>