<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="mum.edu.carpooling.domain.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>User Details</title>
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<link rel="stylesheet" href="/Carpooling/resources/css/menu.css"/>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/userDetails.js"></script>	
</head>
<body>
<div class="topnav">
  <a href="/Carpooling/login" id="menuhome">Home</a>
  <a href="/Carpooling/AddPost" id="menuaddpost">Add Post</a>
  <a href="#map" id="menumap">Map</a>
  <a class="active" href="/Carpooling/updateUserDetails" id="menuprofile">Update Profile</a>
</div>

	<div class="jumbotron text-left">
		<h1>Welcome to Car Pooling Service</h1>
	</div>
		
	<%
		User user = (User) request.getAttribute("user");
	%>

	<form id="userForm" action="updateUserDetails" method="post">
		<div class="container">
			<div class="row">
				<div class="col-sm-6">
					<h3>Update User Information</h3>
					<div class="form-horizontal">
						<div class="form-group">
							<label class="control-label col-lg-3" for="firstName">*Full
								name:</label>
							<div class="col-sm-7">
								<input id="fullName" name="fullName" type="text" class="form-control" value="${user.getFullname()}"/>
								<div id="errorsFullName" style="display: none; color: red;"></div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3" for="gender">*Gender:</label>
							<div class="col-sm-7">
								<div class="btn dropdown-toggle">
									<select id="gender" name="gender">
										<option value="male" label="Male" />
										<option value="female" label="Female" />
									</select>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3" for="email">*Email:</label>
							<div class="col-sm-7">
								<input class="form-control"
									placeholder="eg.example@newyork.com" id="email" name="email" type="text" value="${user.getEmail()}"/>
								<div id="errorsEmail" style="display: none; color: red;"></div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3" for="dob">DOB:</label>
							<div class="col-sm-7">
								<input class="form-control" placeholder="MM/DD/YYYY"
									id="dob" name="dob" type="text" value="${user.getDob().toLocalDate().getMonthValue()}/${user.getDob().toLocalDate().getDayOfMonth()}/${user.getDob().toLocalDate().getYear()}"/>
								<div id="errorsDob" style="display: none; color: red;"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<h3>Address</h3>
					<div class="form-horizontal">
						<div class="form-group">
							<label class="control-label col-sm-2" for="street">Street:</label>
							<div class="col-sm-8">
								<input class="form-control" id="street" name="street" type="text" value="${user.getStreet()}"/>
								<div id="errorsStreet" style="display: none; color: red;"></div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="city">City:</label>
							<div class="col-sm-8">
								<input class="form-control" id="city" name="city" type="text" value="${user.getCity()}"/>
								<div id="errorsCity" style="display: none; color: red;"></div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="state">State:</label>
							<div class="col-sm-8">
								<input class="form-control" id="state" placeholder="Eg.IA (Must be 2 characters)" name="state" type="text" value="${user.getState()}"/>
								<div id="errorsState" style="display: none; color: red;"></div>
							</div>
						</div> 
						<div class="form-group">
							<label class="control-label col-sm-2" for="zipCode">ZipCode:</label>
							<div class="col-sm-8">
								<input class="form-control" id="zipCode" name="zipCode" type="text" value="${user.getZipCode()}"/>
								<div id="errorsZipCode" style="display: none; color: red;"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<label class="control-label col-sm-3" for="state"></label>
					<div class="col-sm-8">
						<input id="addUser" name="Save" type="submit"
							class="btn btn-primary col-sm-3" />
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>