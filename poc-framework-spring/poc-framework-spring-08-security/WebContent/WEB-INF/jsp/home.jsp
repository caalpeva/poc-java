<%@ page contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Recent Persons:</h2>

<ul>
<c:forEach items="${persons}" var="person">
<li><c:out value="${person.name}"/>/
    <c:out value="${person.surname}"/> -- 
    <c:out value="${person.birthdate}"/>
</c:forEach>
</ul>
