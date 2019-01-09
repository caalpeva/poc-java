<%@ page contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<title>Motorist Vehicles</title>
</head>

<body>
	<h2>Motorist Vehicles vehicles (Step 2 of 3)</h2>

	<form:form commandName="person" method="POST" action="register.htm">
		<input type="hidden" name="page" value="1" />
		<br />
		<input type="hidden" value="<c:out value="${nextVehicle}" />" />
		<br />
      Person Vehicles: <c:out value="${person.name}" />&nbsp;<c:out
			value="${person.surname}" />
		<br />
      Vehicles: <br />
		<ul>
			<c:forEach items="${person.vehicles}" var="vehicle">
				<li><c:out value="${vehicle.type}" /> - <c:out
						value="${vehicle.plateNumber}" /></li>
			</c:forEach>
		</ul>

		<br />
		<b><spring:message code="field.type" /></b>
		<form:select path="vehicles[${nextVehicle}].type">
			<!--<form:option value="--" label="--CHOOSE--" />-->
			<c:forEach items="${vehicleTypes}" var="type">
				<form:option value="${type}" label="${type}" />
			</c:forEach>
		</form:select>
		<br />
		<b><spring:message code="field.plateNumber" /></b>
		<form:input path="vehicles[${nextVehicle}].plateNumber" />
		<form:errors path="vehicles[${nextVehicle}].plateNumber" cssClass="error" />
		<br />
		<input type="submit" name="_target0" value="Back">&nbsp;
      <input type="submit" name="_target1" value="Add Vehicle">&nbsp;
      <input type="submit" name="_target2" value="Next">
	  <input type="submit" name="_cancel" value="Cancel">
	</form:form>
</body>
</html>
