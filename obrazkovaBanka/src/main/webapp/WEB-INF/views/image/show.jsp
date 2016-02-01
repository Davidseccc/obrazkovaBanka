<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show Image</title>
<jsp:include page="/WEB-INF/views/include/userSectionHeader.jsp"></jsp:include>

</head>
<body>
	<div class="container container-menu">
		<div class="header clearfix">
			<nav>
			<ul class="nav nav-pills pull-right">
				<li><a href="../">Home</a></li>
				<li class="active"><a href="#">Show</a></li>
				<li><a href="../about.jsp">About</a></li>
				<li><a href="../contact.jsp">Contact</a></li>
			</ul>
			</nav>
		</div>
	</div>


	<div class="container-fluid"
		style="width: 700px; padding: 20px 0px 10px 20px;">
		<jsp:include page="/WEB-INF/views/include/messages.jsp"></jsp:include>

		<h2>${image.name}</h2>

		<div>
			<h4>Info:</h4>
			<p>Autor: ${image.user.nickName } Size: ${image.fileSize}Kb Type:
				${image.fileType } Uploaded: ${image.uploadDate.toGMTString()}
				Category: ${image.category.name}</p>
		</div>
		
		<c:if test="${image.user.nickName.equals(loggedInUser) || loggedInUserRole == 'ROLE_ADMIN'}">
		<div style="margin-right:-45px; display: block" class="pull-right">
			<a href="?edit" class="btn btn-link btn-sm"> <span
				class="glyphicon glyphicon-edit" aria-hidden="true"></span> Edit
			</a> <a href="./delete/${image.deleteHash }" class="btn btn-link btn-sm">
				<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
				Delete
			</a>
		</div>
		</c:if>
	</div>
	<img src="../controller/getImage/${image.hash}"
		class="center-block img-rounded" alt="Cinque Terre"
		alt="${image.name}" width="800" height="auto">
	<div class="container-fluid" style="width: 800px; padding: 20px;">



		<div class="bs-example" style="margin: 20;">
			<div class="" style="">
				<c:forEach var="tags" items="${TagList}">
					<a href="../search/?tag=${tags.name}"
						class="tags label label-success"># <c:out value="${tags.name}"></c:out>
					</a>
				</c:forEach>
			</div>
			<p>${image.description}</p>
			<div>
				<h4>Rate this image:</h4>
				<p class="text-muted text-tabed">Average rating: ${avgRating}</p>
				<div id='rating' style=''>
					<a href='rate/${image.id}/1' class='hod' id='hod1'
						onmousemove="getElementById('rating').style.backgroundPosition='-160px 0'"
						onmouseout="getElementById('rating').style.backgroundPosition='-200px 0'"></a>
					<a href='rate/${image.id}/2' class='hod' id='hod2'
						onmousemove="getElementById('rating').style.backgroundPosition='-120px 0'"
						onmouseout="getElementById('rating').style.backgroundPosition='-200px 0'"></a>
					<a href='rate/${image.id}/3' class='hod' id='hod3'
						onmousemove="getElementById('rating').style.backgroundPosition='-80px 0'"
						onmouseout="getElementById('rating').style.backgroundPosition='-200px 0'"></a>
					<a href='rate/${image.id}/4' class='hod' id='hod4'
						onmousemove="getElementById('rating').style.backgroundPosition='-40px 0'"
						onmouseout="getElementById('rating').style.backgroundPosition='-200px 0'"></a>
					<a href='rate/${image.id}/5' class='hod' id='hod5'
						onmousemove="getElementById('rating').style.backgroundPosition='0px 0'"
						onmouseout="getElementById('rating').style.backgroundPosition='-200px 0'"></a>
				</div>
			</div>
		</div>
	</div>

	<div class="center-block" style="width: 800px; padding: 20px;">
		<div class="col-md-4 center-block">
			<button type="button" style="margin: 0px auto;" id="myButton"
				data-loading-text="Loading..." class="btn btn-success center-block"
				autocomplete="off">
				Show comments <span class="badge">${image.comments.size() } </span>
			</button>
		</div>
		<div id="comments" class="center-block" style="display: none">
			<h4>Comments:</h4>
			<c:forEach var="comment" items="${image.comments}">
				<div class="panel panel-default">
					<div class="panel-heading">
						<p class="text-muted small">
							<c:out value="Posted on: ${comment.date.toGMTString()} "></c:out>
							<c:out value=" by: ${comment.user.nickName }"></c:out>
						</p>
					</div>
					<div class="panel-body">
						<p>
							<c:out value="${comment.content}"></c:out>
						</p>
					</div>
				</div>
			</c:forEach>
			<h4>Replay:</h4>
			<div>
				<c:choose>
					<c:when test="${empty loggedInUser}">
						<p class="text-tabed">
							You are not logged in, please <a href="../user?login">Sign in</a>
							before posting replay.
						</p>
					</c:when>
					<c:otherwise>
						<form class="form-horizontal" name=commentForm method="POST"
							action="../comment/add/${image.id}">
							<div class="form-group">
								<label for="content" class="col-sm-2 control-label">Content:</label>
								<div class="col-sm-10">
									<textarea class="form-control" rows="4" cols="86"
										name="content" id="content" required autofocus></textarea>
								</div>
							</div>
							<div class="form-group form-inline">
								<div class="col-sm-offset-2 col-sm-10">
									<input type="submit" style="float: right" class="btn success"
										value="Submit" />
								</div>
							</div>
						</form>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>


</body>
</html>