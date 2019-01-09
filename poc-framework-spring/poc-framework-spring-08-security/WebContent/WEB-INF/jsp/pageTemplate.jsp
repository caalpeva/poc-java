<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles"%>
<%@ taglib prefix="authz" uri="http://acegisecurity.org/authz"%>

<html>
<head>
<title><tiles:getAsString name="title" /></title>
<link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div class="header">
		</div>

		<div class="menuBar">
			<authz:authorize ifNotGranted="ROLE_ANONYMOUS">
				<a href="home.htm">Your account</a>&nbsp;|&nbsp;
				<a href="logout">Logout</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				Welcome <authz:authentication operation="username" />
			</authz:authorize>
			<authz:authorize ifAnyGranted="ROLE_ANONYMOUS">
				<a href="register.htm">Register</a>&nbsp;|&nbsp;<a href="login.htm">Login</a>
			</authz:authorize>
		</div>

		<div class="contentArea">
			<tiles:insert name="content" />
		</div>

		<div class="footer">
			<tiles:insert name="footer" />
		</div>
	</div>
</body>
</html>
