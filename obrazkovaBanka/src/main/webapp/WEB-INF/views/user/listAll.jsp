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
					<li class="active"><a href="/obrazkovaBanka/user/show?all&start=0&end=100">All users</a></li>
					<li><a href="/obrazkovaBanka/category/show?all">Manage
							categories</a></li>
				</c:if>
			</ul>
			</nav>
		</div>
	</div>


	<div class="center-block" style="width: 800px; padding: 20px;">
		<jsp:include page="/WEB-INF/views/include/messages.jsp"></jsp:include>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Email</th>
					<th>Last visit date</th>
					<th>User Role <a href="/obrazkovaBanka/role/show?all">Info</a></th>
					<th>Reset password</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userList}" var="user">
					<form action="saveRole?id=${user.id}" method="POST">
						<tr>
							<td>${user.id}</td>
							<td>${user.nickName}</td>
							<td>${user.email}</td>
							<td>${user.lastVisit == null ? '< never >' : user.lastVisit.toGMTString()}</td>
							<td><select name="role" id="role">
									<c:forEach items="${roleList}" var="role">
										<option
											${user.role.name == role.name ? 'selected="selected"' : ''}
											value="${role.id }">${role.name}</option>
									</c:forEach>
							</select></td>
							<td><a href="/obrazkovaBanka/user/password/forceReset?id=${user.id}">Reset password</a></td>
							<td><button type="submit"
									class="btn-link glyphicon glyphicon-floppy-saved"></button></td>
						</tr>
					</form>
					<ul>
					</ul>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<nav>
  <ul class="pager">
    <li><a href="show?all&start=${start <= 100 ? 0 : start-100}&end=${end <= 101 ? end : end-100}">Previous</a></li>
    <li><a href="show?all&start=${start <= 100 ? 101 : start+100}&end=${end < 100 ? end : end+100}">Next</a></li>
  </ul>
</nav>

</body>
</html>