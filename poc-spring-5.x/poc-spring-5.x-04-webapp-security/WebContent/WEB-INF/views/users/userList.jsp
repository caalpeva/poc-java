<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Listado de usuarios</title>
    <spring:url value="/resources" var="publicResourcesUrl" />
	<spring:url value="/users/create" var="userFormUrl" />
	<spring:url value="/users/edit" var="userEditUrl" />
	<spring:url value="/users/delete" var="userDeleteUrl" />
    <link href="${publicResourcesUrl}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${publicResourcesUrl}/bootstrap/css/theme.css" rel="stylesheet">
    
  </head>

  <body>

    <jsp:include page="../includes/menu.jsp"></jsp:include>

    <div class="container theme-showcase" role="main">

      <h3>Listado de Usuarios</h3>
      
      <c:if test="${successMessage != null}">      	
      	<div class='alert alert-success' role="alert">${successMessage}</div>      	
      </c:if>
      
      <a href="${userFormUrl}" class="btn btn-success" role="button" title="Nuevo Usuario" >Nuevo</a><br><br>
      <div class="table-responsive">
        <table class="table table-hover table-striped table-bordered">
            <tr>
                <th>Nombre</th>                           
                <th>Email</th>              
                <th>Telefono</th>
                <th>Estatus</th>
                <th>Opciones</th>              
            </tr>
            <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.name}</td>
                <td>${user.email}</td>    
                <td>${user.phone}</td>                         
                <td>
                <c:choose>
                <c:when test="${user.status eq 'ACTIVE'}">
                	<span class="label label-success">${user.status}</span>
                </c:when>
                <c:otherwise>
               		<span class="label label-danger">${user.status}</span>
                </c:otherwise>
                </c:choose>
                </td>
                <td>
                    <a href="${userEditUrl}/${user.id}" class="btn btn-success btn-sm" role="button" title="Edit" ><span class="glyphicon glyphicon-pencil"></span></a>
                    <c:if test="${authentication.principal.username ne user.name}">
                   	<a href="${userDeleteUrl}/${user.id}" onclick='return confirm("¿Desea eliminar el usuario ${user.name}?")' class="btn btn-danger btn-sm" role="button" title="Eliminar" ><span class="glyphicon glyphicon-trash"></span></a>
                    </c:if>
                </td>
            </tr>
            </c:forEach>
        </table>
      </div>  
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