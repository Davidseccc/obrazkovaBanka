<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Categories</title>
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
		<jsp:include page="/WEB-INF/views/include/messages.jsp"></jsp:include>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Description</th>
					<th>Edit</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${roleList}" var="role">
					<form action="save?id=${role.id}" method="POST">
					<tr>
						<td>${role.id}</td>
						<td>	<input type="text" id="name" name="name" class="list-group-item disabled" value="${role.name}" disabled /> </td>
						<td><input type="text" id="description" name="description" class="list-group-item" value="${role.description}"/></td>
						<td><button type="submit" class="btn-link glyphicon glyphicon-floppy-saved"></button></td>
					</tr>
					</form>
					<ul>
					</ul>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>