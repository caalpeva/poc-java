<%@ page contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h2>Vehicles for: ${person.name} ${person.surname}</h2>

<a href="vehiclesforperson.xls?id=${person.id}"><img src="images/excel.png" border="0" alt="download excel"></a>
<a href="vehiclesforperson.pdf?id=${person.id}"><img src="images/pdf.png" border="0" alt="download pdf"></a>
<ul>
	<c:forEach items="${vehicles}" var="vehicle">
		<li><c:out value="${vehicle.plateNumber}" />/<c:out
				value="${vehicle.type}" /> -- <fmt:formatDate pattern="yyyy-MM-dd"
				value="${vehicle.registrationDate}" />
	</c:forEach>
</ul>
