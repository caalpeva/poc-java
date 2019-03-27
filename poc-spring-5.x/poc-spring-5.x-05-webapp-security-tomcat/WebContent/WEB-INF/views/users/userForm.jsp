<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">    
		<meta name="author" content="">
		<title>Creacion de Usuarios</title>
		<spring:url value="/resources" var="publicResourcesUrl" />
		<spring:url value="/users/save" var="userCreationUrl" />
		<link href="${publicResourcesUrl}/bootstrap/css/bootstrap.min.css" rel="stylesheet">    
		<link href="${publicResourcesUrl}/bootstrap/css/theme.css" rel="stylesheet">
	</head>

	<body>

		<jsp:include page="../includes/menu.jsp"></jsp:include>

		<div class="container theme-showcase" role="main">

			<h3 class="blog-title"><span class="label label-success">Datos del Usuario</span></h3>  

			<form:form action="${userCreationUrl}" method="post" modelAttribute="user">
				<div class="row">         
					<div class="col-sm-3">
						<div class="form-group">
							<label for="perfil" class="control-label">Perfil</label>
							<form:select id="perfil" path="roles" multiple="multiple" class="form-control" required="required">
							<form:options items="${profileList}" itemValue="name" itemLabel="name"/>
							</form:select>
						</div> 
					</div>
					
					<div class="col-sm-3">
						<div class="form-group">
							<label for="estatus" class="control-label">Estatus</label>
							<form:select id="genero" path="status" class="form-control">
								<form:option value="ACTIVE">Activa</form:option>
								<form:option value="INACTIVE">Inactiva</form:option>
							</form:select>
						</div>
					</div>					
				</div>	
				<div class="row"> 	
					<div class="col-sm-3">
						<div class="form-group">
							<label for="cuenta">Cuenta</label>
							<c:choose>
								<c:when test="${empty user.name}">
									<form:input type="text" class="form-control" path="name" id="cuenta" required="required"/>
								</c:when>
								<c:otherwise>
									<form:input type="text" class="form-control" path="name" id="cuenta" required="required" readonly="true"/>		
								</c:otherwise>
							</c:choose>
						</div>  
					</div>
					
					<div class="col-sm-3">
						<div class="form-group">
							<label for="pwd">Password</label>             
							<form:input type="password" class="form-control" path="password" id="pwd" required="required"/>
						</div>  
					</div>
					
					<div class="col-sm-3">
						<div class="form-group">
							<label for="email">Email</label>
							<form:input type="text" class="form-control" path="email" id="email" placeholder="Correo electrónico" required="required"/>
						</div>  
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<label for="telefono">Teléfono</label>
							<form:input type="text" class="form-control" path="phone" id="telefono" required="required"/>
						</div>  
					</div>

				</div>

				<button type="submit" class="btn btn-danger" >Guardar</button>
			</form:form> 

			<hr class="featurette-divider">

			<jsp:include page="../includes/footer.jsp"></jsp:include>

		</div> <!-- /container -->

		<!-- Bootstrap core JavaScript
		================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
		<script src="${publicResourcesUrl}/bootstrap/js/bootstrap.min.js"></script>    
	</body>
</html>
