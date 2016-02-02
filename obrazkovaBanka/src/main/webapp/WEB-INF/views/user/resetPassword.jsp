<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

<body>
	<h2>Login</h2>
	<div class="container" style="width: 280px;">
	<jsp:include page="/WEB-INF/views/include/messages.jsp"></jsp:include>
	
	<img alt="user_icon" src="resources/img/user-icon.png" class="centered">
	
		<form class="form-signin" name=resetForm method="POST" action="user/reset">
          <h2 class="form-signin-heading">Please sign in</h2>
          <label for="email" class="sr-only">Email address</label>
          <input type="email" id="email" name="email" class="form-control" placeholder="Email address" required autofocus>
          <input class="btn btn-lg btn-success text-center btn-block" type="submit" value="Reset password"/>
		</form>
	</div>
	<!-- /container -->
</body>
</html>