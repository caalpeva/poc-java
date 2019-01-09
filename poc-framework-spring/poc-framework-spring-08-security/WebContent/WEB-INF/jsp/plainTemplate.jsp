<%@ taglib prefix="tiles"
	uri="http://jakarta.apache.org/struts/tags-tiles"%>
<%@ taglib prefix="authz" uri="http://acegisecurity.org/authz"%>

<html>
<head>
<title><tiles:getAsString name="title" /></title>
<link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div class="header"></div>

		<div class="menuBar">
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
