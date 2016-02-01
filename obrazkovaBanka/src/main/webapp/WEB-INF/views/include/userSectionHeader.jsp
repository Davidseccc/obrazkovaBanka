<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="/obrazkovaBanka/resources/js/jquery.1.9.1.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>


<script src="/obrazkovaBanka/resources/js/vendor/jquery.ui.widget.js"></script>
<script
	src="/obrazkovaBanka/resources/js/jquery.iframe-transport.min.js"></script>
<script src="/obrazkovaBanka/resources/js/mycommnetfunction.js"></script>


<!-- bootstrap just to have good looking page -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/obrazkovaBanka/resources/css/main.css">


</head>
<body>
	<div class="page-header">

		<div style="padding-right: 20px; margin-top: -10px" class="pull-right">
			<c:choose>
				<c:when test="${empty loggedInUser}">
					<p>
						<a href="/obrazkovaBanka/user?login">Sign in</a> <a
							href="/obrazkovaBanka/user?register">Sign up</a>
					</p>
				</c:when>
				<c:otherwise>
					<p>
						Hello <a style="padding-right: 1.5em"
							href="/obrazkovaBanka/user/<%=request.getSession().getAttribute("loggedInUser")%>/edit"><%=request.getSession().getAttribute("loggedInUser")%></a>
						<a href="/obrazkovaBanka/user?logout">Sign out</a>
					</p>
				</c:otherwise>
			</c:choose>
		</div>
		<h1>
			Image Upload <small>The right Shoot for your images</small>
		</h1>


	</div>
</body>
</html>