<%@ page contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
  <head>
    <title>Motorist Detail</title>
  </head>
  
  <body>
    <h2>Motorist info (Step 1 of 3)</h2>
    
    <form:form commandName="person" method="POST" action="register.htm">
      <input type="hidden" name="page" value="0" /><br>      
      <spring:message code="field.name" /><form:input path="name" /><br>
      <form:errors path="name" cssClass="error" /><br>
      <spring:message code="field.surname" /><form:input path="surname" /><br>
      <form:errors path="surname" cssClass="error" /><br>
       <spring:message code="field.email" /><form:input path="email" /><br>
      <form:errors path="email" cssClass="error" /><br>
      <spring:message code="field.password" /><form:password path="password" /><br>
      <form:errors path="password" cssClass="error" /><br>
      <input type="submit" name="_target1" value="Next">
      <input type="submit" name="_cancel" value="Cancel">
    </form:form>
  </body>
</html>
