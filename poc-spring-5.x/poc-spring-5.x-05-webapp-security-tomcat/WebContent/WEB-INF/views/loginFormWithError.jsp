<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">    
		<meta name="description" content="">
		<meta name="author" content="">
		<title>Login</title>
		<spring:url value="/resources" var="publicResourcesUrl" />
		<spring:url value="/" var="publicRootUrl" />
		<link href="${publicResourcesUrl}/bootstrap/css/bootstrap.min.css" rel="stylesheet">    
		<link href="${publicResourcesUrl}/bootstrap/css/theme.css" rel="stylesheet">
		<link href="${publicResourcesUrl}/bootstrap/css/signin.css" rel="stylesheet">

	</head>

	<body>

		<jsp:include page="includes/menu.jsp"></jsp:include>

		<div class="container theme-showcase" role="main">
			<hr class="featurette-divider">
			<img src="${publicResourcesUrl}/images/login.png" width="136" height="136" class="center">
			<form class="form-signin" action="j_security_check" method="post">
				<img src="${publicResourcesUrl}/images/error.png" width="48" height="48" class="center">
				<h4 class="form-signin-heading" style="color:red">El usuario y/o password incorrectos</h4>
				<h3 class="form-signin-heading">CineSite | Administracion</h3>        
				<label for="j_username" class="sr-only">Usuario</label>
				<input type="text" id="j_username" name="j_username" class="form-control" placeholder="Usuario" required autofocus>
				<label for="j_password" class="sr-only">Contraseña</label>
				<input type="password" id="j_password" name="j_password" class="form-control" placeholder="Password" required>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
			</form>

		</div> <!-- /container -->
		<!-- Bootstrap core JavaScript
		================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="${publicResourcesUrl}/bootstrap/js/bootstrap.min.js" ></script>
	</body>
</html>