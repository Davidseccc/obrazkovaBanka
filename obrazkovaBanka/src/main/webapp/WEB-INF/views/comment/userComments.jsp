<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My comments</title>
<jsp:include page="/WEB-INF/views/include/headerFullHeader.jsp"></jsp:include>

</head>
<body>
	<div class="container container-menu">
		<div class="header clearfix">
			<nav>
			<ul class="nav nav-pills pull-right">
				<li><a href="/obrazkovaBanka/">Home</a></li>
				<li><a href="/obrazkovaBanka/user/${loggedInUser}/edit">Profile</a></li>
				<li><a href="/obrazkovaBanka/user/${loggedInUser}/images">Images</a></li>
				<li class="active"><a href="/obrazkovaBanka/user/${loggedInUser}/comments">Comments</a></li>
				<c:if test="${loggedInUserRole == 'ROLE_ADMIN'}">
					<li><a href="/obrazkovaBanka/user/show?all&start=0&end=100">All users</a></li>
					<li><a href="/obrazkovaBanka/category/show?all">Manage
							categories</a></li>
				</c:if>
			</ul>
			</nav>
		</div>
	</div>


	<div class="container center-block clearfix">

		<div class="row" style="margin-top: 2em">
			<c:forEach var="comment" items="${commentList}">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="col-md-2" style="">
							<a href="/obrazkovaBanka/image/${comment.image.hash}" class=""> <img
								src="/obrazkovaBanka/controller/thumbnail/getImage/${comment.image.hash}"
								alt="${comment.image.name}" class="img-thumbnail"
								style="width: 100px; max-height: 100px">
							</a>
						</div>
						<div class="col-md-10">
							<a href="/obrazkovaBanka/image/${comment.image.hash}" class="">
								<h5 class="text-left">${comment.image.name}</h5>
							</a>
							<p class="small text-muted">Posted on: ${comment.date.toGMTString()}</p>
							<p class="small text-muted"> ${comment.content}</p>

						</div>
					</div>
				</div>
			</c:forEach>

		</div>
	</div>

</body>
</html>