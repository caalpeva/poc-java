<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Redirect/Forward Example</title>
</head>
<body>
	<%
	String firstName = (String) request.getAttribute("firstName");
	if (firstName == null) {
		firstName = "Not found in request";
	}
	
	String lastName = (String) request.getAttribute("lastName");
	if (lastName == null) {
		lastName = "Not found in request";
	}
	%>

	<b>First Name:</b><%=firstName %><br>
	<b>Last Name:</b><%=lastName %><br>
</body>
</html>