<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<spring:url value="/" var="publicRootUrl" />
<!-- Fixed navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			${roles}
			<c:if test="${empty roles}"><a class="navbar-brand" href="${publicRootUrl}">My CineSite</a></c:if>
			<c:if test="${not empty roles}"><a class="navbar-brand" href="${publicRootUrl}admin/index">My CineSite | Administracion</a></c:if>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<c:if test="${fn:containsIgnoreCase(roles, 'manager')}">
					<li><a href="${publicRootUrl}banners/index">Banners</a></li>
				</c:if>
				<c:if test="${fn:containsIgnoreCase(roles, 'editor')}">
					<%-- <li><a href="${publicRootUrl}movies/index">Peliculas</a></li> --%>
					<li><a href="${publicRootUrl}movies/paginateIndex?page=0">Peliculas</a></li>
					<li><a href="${publicRootUrl}news/index">Noticias</a></li>
					<li><a href="${publicRootUrl}showtimes/create">Horarios</a></li>
				</c:if>
				<c:if test="${empty roles}">
					<li><a href="${publicRootUrl}contact">Contacto</a></li>
					<li><a href="${publicRootUrl}about">Acerca</a></li>
					<li><a href="${publicRootUrl}admin/index">Login</a></li>
				</c:if>
				<c:if test="${not empty roles}">
					<li><a href="${publicRootUrl}admin/logout">Logout</a></li>
				</c:if>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>