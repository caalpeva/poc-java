<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">    
		<meta name="description" content="">
		<meta name="author" content="">
		<title>Acerca</title>    
		<spring:url value="/resources" var="publicResourcesUrl" />
		<link href="${publicResourcesUrl}/bootstrap/css/bootstrap.min.css" rel="stylesheet">   
		<link href="${publicResourcesUrl}/bootstrap/css/theme.css" rel="stylesheet">

	</head>

	<body>

		<jsp:include page="includes/menu.jsp"></jsp:include>

		<div class="container theme-showcase" role="main">

			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">ACERCA DE ESTA APLICACION</h3>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-3">
							<p class="text-center">
								<img class="img-rounded" src="${publicResourcesUrl}/images/acerca.png" alt="Generic placeholder image" height="220">            
							</p>
						</div>
						<div class="col-sm-9">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">DETALLES</h3>
								</div>
								<div class="panel-body">                           
									LOS DETALLES DE ESTA APLICACION
								</div>
							</div>                          
						</div>
					</div>
				</div>
			</div>

			<jsp:include page="includes/footer.jsp"></jsp:include>

		</div> <!-- /container -->

		<!-- Bootstrap core JavaScript
		================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
		<script src="${publicResourcesUrl}/bootstrap/js/bootstrap.min.js"></script> 
	</body>
</html>