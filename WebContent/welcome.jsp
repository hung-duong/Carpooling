<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Welcome Page</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu.css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>	
	
</head>
<body>


</head>
<body>

<div class="topnav">
  <a class="active" href="welcome.jsp">Home</a>
  <a href="addpost.jsp">Add Post</a>
  <a href="#map">Map</a>
  <a href="updateUserDetails">Update Profile</a>
</div>
		<div id="container">
			<h1>Welcome to Car Pooling Service</h1>		
 			<br>
 			<div id="userPost" class="divHide">
			</div>
		</div>
</body>
</html>