<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="${pageContext.request.contextPath}/resources/css/login.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div class="jumbotron text-center">
		<h1>Wellcome to Carpooling</h1>
		<p>Please login your account</p>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Please sign in</h3>
					</div>
					<div class="panel-body">
						<c:if test="${not empty error}">
							<div class="alert alert-danger">
								<%if (request.getAttribute("error") != null) { %>
								<p>${error}</p>
								<%}%>
							</div>
						</c:if>
						<form action="login" method="post">
							<fieldset>
								<div class="form-group">
									<input class="form-input-large" placeholder="User Name"
										name='username' type="text">
								</div>
								<div class="form-group">
									<input class=" form-input-large" placeholder="Password"
										name='password' type="password" value="">
								</div>
								<input class="btn btn-lg btn-success btn-mini" type="submit"
									value="Login">
							</fieldset>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>

						<div class="a-divider a-divider-break">
							<h5>New to Carpooling?</h5>
						</div>

						<span id="auth-create-account-link"
							class="a-button a-button-span12"> <span
							class="a-button-inner"> <a id="createAccountSubmit"
								tabindex="6" href="register" class="a-button-text"
								role="button"> Create your Carpooling account </a>
						</span></span>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>