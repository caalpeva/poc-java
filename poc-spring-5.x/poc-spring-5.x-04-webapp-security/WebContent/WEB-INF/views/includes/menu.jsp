<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 
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
			<sec:authorize access="isAnonymous()"><a class="navbar-brand" href="${publicRootUrl}">My CineSite</a></sec:authorize>
			<sec:authorize access="isAuthenticated()"><a class="navbar-brand" href="${publicRootUrl}admin/index">My CineSite | Administracion</a></sec:authorize>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<sec:authorize access="hasAnyAuthority('MANAGER')">
					<li><a href="${publicRootUrl}users/index">Usuarios</a></li>
					<li><a href="${publicRootUrl}banners/index">Banners</a></li>
				</sec:authorize>
				<sec:authorize access="hasAnyAuthority('EDITOR')">
					<%-- <li><a href="${publicRootUrl}movies/index">Peliculas</a></li> --%>
					<li><a href="${publicRootUrl}movies/paginateIndex?page=0">Peliculas</a></li>
					<li><a href="${publicRootUrl}news/index">Noticias</a></li>
					<li><a href="${publicRootUrl}showtimes/create">Horarios</a></li>
				</sec:authorize>
				<sec:authorize access="isAnonymous()">
					<li><a href="${publicRootUrl}contact">Contacto</a></li>
					<li><a href="${publicRootUrl}about">Acerca</a></li>
					<li><a href="${publicRootUrl}login">Login</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li><a href="${publicRootUrl}admin/logout">Logout</a></li>
				</sec:authorize>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>