<%@ page contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
  <head><title>Vehicle for person</title></head>

  <body>
    <h2>Vehicles for: ${person.name} ${person.surname}</h2>
    
    <ul>
    <c:forEach items="${vehicles}" var="vehicle">
    <li><c:out value="${vehicle.plateNumber}"/>/<c:out value="${vehicle.type}"/> --  <fmt:formatDate pattern = "yyyy-MM-dd" value = "${vehicle.registrationDate}" />
    </c:forEach>
    </ul>
  </body>
</html>
