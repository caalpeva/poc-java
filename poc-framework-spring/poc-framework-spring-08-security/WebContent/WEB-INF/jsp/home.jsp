<%@ page contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>Recent Persons:</h2>

<ul>
<c:forEach items="${persons}" var="person">
<li><c:out value="${person.name}"/>
    <c:out value="${person.surname}"/>
    <fmt:formatDate pattern = "yyyy-MM-dd" value = "${person.birthDate}" /> 
</c:forEach>
</ul>
