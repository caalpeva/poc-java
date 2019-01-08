<%@ page contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<h2>Enter a vehicle...</h2>
<form:form method="POST" action="addVehicle.htm?personId=${personId}" commandName="vehicle">
	<b><spring:message code="field.type" /></b>
	<form:select path="type">
		<!--<form:option value="--" label="--CHOOSE--" />-->
		<c:forEach items="${vehicleTypes}" var="type">
			<form:option value="${type}" label="${type}" />
		</c:forEach>
	</form:select>
	<form:errors path="type" cssClass="error" />
	<br>
	<b><spring:message code="field.plateNumber" /></b>
	<form:input path="plateNumber"/>
	<form:errors path="plateNumber" cssClass="error" />
	<br>
	<input type="submit" />
</form:form>

