<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Image</title>
<jsp:include page="/WEB-INF/views/include/userSectionHeader.jsp"></jsp:include>

</head>
<body>

	<div class="container-fluid"
		style="width: 700px; padding: 20px 0px 10px 20px;">
		<h2>${image.name}</h2>
		<img src="../controller/thumbnail/getImage/${image.hash}"
			class="center-block img-rounded" alt="Cinque Terre"
			alt="${image.name}" width="200" height="auto">
		<div>
			<p class="center-block text-center">Autor: ${image.user.nickName }
				Size: ${image.fileSize}Kb Type: ${image.fileType } Uploaded:
				${image.uploadDate.toGMTString()}</p>
				<form class="form-horizontal" name=commentForm action="../image/${image.hash}?save" method="POST">
			<label for="name" class="col-sm-2 control-label">Name:</label>
			<div class="col-sm-10">
				<input class="form-control" type="text" id="name" name="name"
					value="${image.name}" required autofocus />
			</div>
			<label for="description" class="col-sm-2 control-label">Description:</label>
			<div class="col-sm-10">
				<textarea class="form-control" rows="4" cols="86" id="description"
					name="description" id="description" >${image.description}</textarea>
			</div>
			<label for="cat" class="col-sm-2 control-label">Category:</label>
			<div class="col-sm-10">
				<select class="form-control" name="cat" id="cat">
					<c:forEach var="category" items="${categoryList}">
						<option ${image.category.name == category.name ? 'selected="selected"' : ''}>${category.name}</option>
					</c:forEach>
				</select>
			</div>
			<label for="tagList" class="col-sm-2 control-label">Tags:</label>
			<div class="col-sm-10">
				<textarea class="form-control" rows="4" cols="86" id="tagList"
					name="tagList" >
					<c:forEach var="tags" items="${TagList}">${tags.name};</c:forEach>
				</textarea>
				<p class="text-muted text-small">Each tag separate with ";"</p>
			</div>
			<button type="submit" class="center-block btn btn-success">Edit
				image</button>
				</form>
		</div>
	</div>
</body>
</html>