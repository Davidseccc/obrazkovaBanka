<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
				<li><a href="/obrazkovaBanka/">Home</a></li>
				<li><a href="/obrazkovaBanka/user/${loggedInUser}/edit">Profile</a></li>
				<li class="active"><a
					href="/obrazkovaBanka/user/${loggedInUser}/images">Images</a></li>
				<li><a href="/obrazkovaBanka/user/${loggedInUser}/comments">Comments</a></li>
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
			<c:forEach var="image" items="${imageList}">


				<div class="panel panel-default">
					<div class="panel-body">
						<div class="col-md-2" style="">
							<a href="/obrazkovaBanka/image/${image.hash}" class=""> <img
								src="/obrazkovaBanka/controller/thumbnail/getImage/${image.hash}"
								alt="${image.name}" class="img-thumbnail"
								style="width: 250px; max-height: 150px">
							</a>
						</div>
						<div class="col-md-10">
							<c:if
								test="${image.user.nickName.equals(loggedInUser) || loggedInUserRole == 'ROLE_ADMIN'}">
								<div style="margin: -10px -25px 0px 0; display: block"
									class="pull-right">
									<a href="/obrazkovaBanka/image/${image.hash}?edit" class="btn btn-link btn-sm"> <span
										class="glyphicon glyphicon-edit" aria-hidden="true"></span>
										Edit
									</a> <a href="/obrazkovaBanka/image/delete/${image.deleteHash }"
										class="btn btn-link btn-sm"> <span
										class="glyphicon glyphicon-trash" aria-hidden="true"></span>
										Delete
									</a>
								</div>
							</c:if>
							<a href="/obrazkovaBanka/image/${image.hash}" class="">
								<h4 class="text-left">${image.name}</h4>
							</a>
							<p class="small text-muted">${image.description}</p>
							<p class="small text-muted">File size: ${image.fileSize}kb;
								Type:${image.fileType}; Category: ${image.category.name};
								Uploaded: ${image.uploadDate.toGMTString()}</p>

						</div>
					</div>
				</div>
			</c:forEach>

		</div>
	</div>

</body>
</html>