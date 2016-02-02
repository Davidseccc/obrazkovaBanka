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
	<div class="container container-menu">
		<div class="header clearfix">
			<nav>
			<ul class="nav nav-pills pull-right">
				<li><a href="/obrazkovaBanka/">Home</a></li>
				<li><a href="/obrazkovaBanka/user/${loggedInUser}/edit">Profile</a></li>
				<li><a href="/obrazkovaBanka/user/${loggedInUser}/images">Images</a></li>
				<li><a href="/obrazkovaBanka/user/${loggedInUser}/comments">Comments</a></li>
				<c:if test="${loggedInUserRole == 'ROLE_ADMIN'}">
					<li><a href="/obrazkovaBanka/user/show?all&start=0&end=100">All users</a></li>
					<li class="active"><a href="/obrazkovaBanka/category/show?all">Manage
							categories</a></li>
				</c:if>
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
					<th>Images</th>
					<th>Edit</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${categoryList}" var="category">
					<form action="save?id=${category.id}" method="POST">
					<tr>
						<td>${category.id}</td>
						<td><input type="text" name="name" id="name"
							value="${category.name}" /></td>
						<td></td>
						<td><button type="submit" class="btn-link glyphicon glyphicon-floppy-saved"></button></td>
					</tr>
					</form>
					<ul>
					</ul>
				</c:forEach>
			</tbody>
		</table>

		<button type="button" style="margin: 0px auto;" id="myButton"
			data-loading-text="Loading..." class="glyphicon-plus btn btn-success center-block"
			autocomplete="off"> Add new</button>

		<div id="comments" style="display: none">
			<form action="addNew" method="POST">
				<div class="pull-right col-lg-8" style="padding:20px" >
					<div class="input-group">
						<input type="text" name="name" id="name" class="form-control"
							placeholder="Enter category name"> <span
							class="input-group-btn">
							<button class="btn btn-default" type="submit">Save</button>
						</span>
					</div>
					<!-- /input-group -->
				</div>
				<!-- /.col-lg-6 -->
			</form>
		</div>
	</div>
</body>
</html>