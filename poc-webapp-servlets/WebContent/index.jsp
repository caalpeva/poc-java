<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ResourceBundle" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	ResourceBundle resources = null;
	try {
		resources = ResourceBundle.getBundle("strings");
	} catch(Exception e) {
		//Do nothing
	}
%>
<html>
<head>
<title>Servlets examples</title>
</head>
<body>
	<h3>Servlets examples</H3>
	<ul>
		<li><a href="HelloWorldServlet">Hello World</a></li>
		<li><a href="getRequestData">GET request</a></li>
		<li><a href="getRequestData?firstName=ALBERTO&lastName=PEREZ">GET request with parameters</a></li>
		<li>POST request
			<form action="getRequestData" method=POST>
				<%=resources.getString("requestparams.firstname")%>
				<input type=text size=20 name=firstname> <br>
				<%=resources.getString("requestparams.lastname")%>
				<input type=text size=20 name=lastname> <br>
				<input type=submit value="<%=resources.getString("form.send")%>">
			</form></li>
		<li><a href="redirectServlet">Redirect</a></li>
		<li><a href="forwardServlet">Forward</a></li>
	</ul>
</body>
</html>