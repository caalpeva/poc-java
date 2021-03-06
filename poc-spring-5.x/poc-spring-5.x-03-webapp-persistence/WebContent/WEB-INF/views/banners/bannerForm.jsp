<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
      <title>Creacion de imagenes del Banner</title>    
	  <spring:url value="/resources" var="publicResourcesUrl" />
	  <spring:url value="/banners/save" var="bannerCreationUrl" />
      <link href="${publicResourcesUrl}/bootstrap/css/bootstrap.min.css" rel="stylesheet">   
      <link href="${publicResourcesUrl}/bootstrap/css/theme.css" rel="stylesheet">

   </head>

   <body>
	  <jsp:include page="../includes/menu.jsp"></jsp:include>

      <div class="container theme-showcase" role="main">

         <h3 class="blog-title"><span class="label label-success">Datos de la imagen</span></h3>

		<spring:hasBindErrors name="banner">
			<div class='alert alert-danger' role='alert'>
				Por favor corrija los siguientes errores:
				<ul>
				<c:forEach var="error" items="${errors.allErrors}">
					<li><spring:message message="${error}" /></li>
				</c:forEach>
				</ul>
			</div>
		</spring:hasBindErrors>

         <form:form action="${bannerCreationUrl}" enctype="multipart/form-data" method="post" modelAttribute="banner">

		<c:if test="${banner.id ne 0}">
			<div class="row">
				<div class="col-sm-3">
					<div class="form-group">
						<img class="img-rounded"
							src="${publicResourcesUrl}/images/movies/${banner.filename}"
							alt="Generic placeholder image" width="1140" height="250">
					</div>
				</div>
			</div>
		</c:if>
		

			<div class="row">         
               <div class="col-sm-6">
                  <div class="form-group">
                     <label for="titulo">Titulo</label>
                     <form:hidden path="id"/>           
                     <form:input type="text" class="form-control" path="title" id="titulo" required="required"/>
                  </div>
               </div>


               <div class="col-sm-3">
                  <div class="form-group">
                     <label for="imagen">Imagen</label>
                     <form:hidden path="filename"/>
                     <c:if test="${banner.id eq 0}">
                     	<input type="file" id="archivoImagen" name="imageFile" required="required"/>
                     </c:if>
                     <c:if test="${banner.id ne 0}">
                     	<input type="file" id="archivoImagen" name="imageFile" />
                     </c:if>
                     <p class="help-block">Tama�o recomendado: 1140 x 250 </p>
                  </div> 
               </div> 

               <div class="col-sm-3">
                  <div class="form-group">
                     <label for="estatus">Estatus</label>             
                     <form:select id="estatus" path="status" class="form-control">
                        <form:option value="ACTIVE">Activo</form:option>
                        <form:option value="INACTIVE">Inactivo</form:option>                
                     </form:select>  
                  </div>
               </div>
            </div>

            <button type="submit" class="btn btn-danger">Guardar</button>
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