<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
			<a class="navbar-brand" href="${publicRootUrl}">My CineSite</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="${publicRootUrl}banners/index">Banners</a></li>
				<li><a href="${publicRootUrl}movies/index">Peliculas</a></li>
				<li><a href="${publicRootUrl}news/index">Noticias</a></li>
				<li><a href="${publicRootUrl}showtimes/create">Horarios</a></li>
				<li><a href="${publicRootUrl}contact">Contacto</a></li>
				<li><a href="#">Acerca</a></li>
				<li><a href="#">Login</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>