<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Image</title>
<jsp:include page="/WEB-INF/views/include/headerFullHeader.jsp"></jsp:include>

</head>
<body>
		<div class="container container-menu">
			<div class="header clearfix">
				<nav>
				<ul class="nav nav-pills pull-right">
					<li >				<a href="/obrazkovaBanka/">Home</a></li>
					<li class="active">	<a href="/obrazkovaBanka/search/?image">Search</a></li>
					<li>				<a href="/obrazkovaBanka/about.jsp">About</a></li>
					<li> 				<a href="/obrazkovaBanka/contact.jsp">Contact</a></li>
					<c:if test="${!empty loggedInUser}">
					<li><a href="/obrazkovaBanka/user/${loggedInUser}/edit">User section</a></li>
					</c:if>
				</ul>
				</nav>
			</div>
		</div>
		
			<div class="container" style="width: 500px;">
			 <h2>Search Image</h2>
		<p>Enter key world to find image</p>
			
		<jsp:include page="/WEB-INF/views/include/messages.jsp"></jsp:include>
			
		<form class="navbar-form navbar-left" role="search" action="?image" method="GET">
			<div class="form-group">
				<input type="text" name="q" id="q" class="form-control" placeholder="Search">
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>	<!-- /container -->
</body>
</html>