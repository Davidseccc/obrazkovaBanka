<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User home</title>
</head>
<jsp:include page="/WEB-INF/views/include/userSectionHeader.jsp"></jsp:include>
<body>
	<div class="container">
		<div class="header clearfix">
			<nav>
			<ul class="nav nav-pills pull-right">
				<li class="active"><a href="#">Home</a></li>
				<li><a href="${user.nickName}/edit">Profile</a></li>
				<li><a href="${user.nickName}/images">Images</a></li>
				<li><a href="${user.nickName}/comments">Comments</a></li>
			</ul>
			</nav>
		</div>
	</div>
	<div class="center-block" style="width: 500px; padding: 20px;">
		<h2>Welcome back ${user.nickName}</h2>
		<p></p>
	</div>
</body>
</html>