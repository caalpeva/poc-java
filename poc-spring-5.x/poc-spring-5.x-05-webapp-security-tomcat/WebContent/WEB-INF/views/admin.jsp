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
    <title>Error</title>    
	<spring:url value="/resources" var="publicResourcesUrl" />
    <link href="${publicResourcesUrl}/bootstrap/css/bootstrap.min.css" rel="stylesheet">   
    <link href="${publicResourcesUrl}/bootstrap/css/theme.css" rel="stylesheet">

  </head>

  <body>

	<jsp:include page="includes/menu.jsp"></jsp:include>   

    <div class="container theme-showcase" role="main">

      <div class="jumbotron">        
        <h3>Administración del Sistema</h3>
        <p>Bienvenido(a) ${username}</p>
      </div>

	<jsp:include page="includes/footer.jsp"></jsp:include>   

    </div> <!-- /container -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
    <script src="${publicResourcesUrl}/bootstrap/js/bootstrap.min.js"></script> 
    <script>
      function goBack() {
         window.history.back();
      }
    </script>
  </body>
</html>