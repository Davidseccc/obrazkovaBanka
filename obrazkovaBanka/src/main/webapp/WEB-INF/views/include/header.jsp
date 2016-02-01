<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="/obrazkovaBanka/resources/js/jquery.1.9.1.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script src="/obrazkovaBanka/resources/js/vendor/jquery.ui.widget.js"></script>
<script src="/obrazkovaBanka/resources/js/jquery.iframe-transport.js"></script>



<!-- bootstrap just to have good looking page -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

</head>
<body>
	<div style="padding-right:20px;margin-top:-10px" class="pull-right">
		<c:choose>
  			<c:when test="${empty loggedInUser}">
  				<p><a href="/obrazkovaBanka/user?login">Sign in</a>  <a href="/obrazkovaBanka/user?register">Sign up</a></p>
  			</c:when>
  			<c:otherwise>
				<p><a href="/obrazkovaBanka/user?logout">Sign out</a></p>
  			</c:otherwise>
		</c:choose>
	</div>