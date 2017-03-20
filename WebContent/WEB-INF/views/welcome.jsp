<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Welcome Page</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/main.css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/main.js"></script>	
</head>
<body>
		<div id="container">
			<h1>Welcome Page</h1>		
 			<div id="searchInput" class="searchInput">            
				<input type="button" id="btnGo" value="GO" />
			</div>
 			<div id="userPost" class="divHide">
			</div>
		</div>
</body>
</html>