<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>About</title>
</head>
<jsp:include page="/WEB-INF/views/include/userSectionHeader.jsp"></jsp:include>
<body>
		<div class="container container-menu">
		<div class="header clearfix">
			<nav>
				<ul class="nav nav-pills pull-right">
					<li><a href="/obrazkovaBanka/">Home</a></li>
					<li><a href="/obrazkovaBanka/search/?image">Search</a></li>
					<li class="active" ><a href="/obrazkovaBanka/about.jsp">About</a></li>
					<li><a href="/obrazkovaBanka/contact.jsp">Contact</a></li>
					<c:if test="${!empty loggedInUser}">
						<li><a href="/obrazkovaBanka/user/${loggedInUser}/edit">User
								section</a></li>
					</c:if>
				</ul>
			</nav>
		</div>
	</div>


	<div class="center-block" style="width: 800px; padding: 20px;"></div>
	<h2>About</h2>
</body>
</html>