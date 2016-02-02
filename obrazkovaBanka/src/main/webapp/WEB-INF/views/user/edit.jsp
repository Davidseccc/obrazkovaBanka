<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="/obrazkovaBanka/resources/js/myuserfunction.js"></script>

<title>Edit user</title>
<jsp:include page="/WEB-INF/views/include/userSectionHeader.jsp"></jsp:include>
</head>
<body>

<div class="container container-menu">
			<div class="header clearfix">
				<nav>
				<ul class="nav nav-pills pull-right">
					<li> 				<a href="/obrazkovaBanka/">Home</a></li>
					<li class="active">	<a href="#">Profile</a></li>
					<li>				<a href="/obrazkovaBanka/user/${user.nickName}/images">Images</a></li>
					<li>				<a href="/obrazkovaBanka/user/${user.nickName}/comments">Comments</a></li>
					<c:if test="${loggedInUserRole == 'ROLE_ADMIN'}">
					<li>				<a href="/obrazkovaBanka/user/show?all&start=0&end=100">All users</a></li>
					<li>				<a href="/obrazkovaBanka/category/show?all">Manage categories</a></li>
					</c:if>
				</ul>
				</nav>
			</div>
		</div>
		

    
		<h2>Edit ${user.nickName}:</h2>
	<div class="container" style="width: 377px;">
	
	<jsp:include page="/WEB-INF/views/include/messages.jsp"></jsp:include>
    
		<div class="list-group">
		<form action="../${user.nickName}/update" method="POST">
		<label for="nickName" class="col-sm-5 control-label">Nick: </label>
			<input type="text" id="nickName" name="nickName" class="list-group-item disabled" placeholder="${user.nickName}" disabled /> 
		<label for="name" class="col-sm-5 control-label">Name: </label>
			<input type="text" id="name" name="name" class="list-group-item" placeholder="${user.name}" /> 
		<label for="email" class="col-sm-5 control-label">Email: </label>
			<input type="email" id="email" name="email" class="list-group-item" placeholder="${user.email}" /> 
		<label for="pasword" class="col-sm-5 control-label">Password: </label>
			
			<a href="#curpassword" style="margin: 5 20px 0 10px; width: 202px" id="showpassbutton"
				data-loading-text="Loading..." class="btn-in-list btn-link center-block"
				autocomplete="off">
				Change password</a>
				<div style = "margin-bottom: 2em;" id="userButton">
		<button type="submit"   class="btn btn-success pull-right" >Update user</button>
		</div></form>
		
		<div id=changepassword style="margin:2.5em 0 1em 0; display: none">
			<div class="list-group">
			<form action="../${user.nickName}/passchange" method="POST">
				<label for="curpassword" class="col-sm-5 control-label">Your password </label>
					<input type="password" id="curpasswrd" name="curpasswrd" class="list-group-item" placeholder=""/> 
				<label for="password1" class="col-sm-5 control-label">New password: </label>
					<input type="password" id="passwrd1" name="passwrd1" class="list-group-item" placeholder=""/> 
				<label for="password2" class="col-sm-5 control-label">Repeat password: </label>
					<input type="password" id="password2" name="password2" class="list-group-item" placeholder="" /> 
				<button class="btn btn-success" style="margin-left:207px" type="submit">Change password</button>
			</form>
		</div>
		
		</div>
		<ul style="padding-top: 2em" class="list-group">
		  	<li class="list-group-item">
    			<span class="badge">14</span>
    				<a href="#">Images</a>
  			</li>
  			<li class="list-group-item">
    			<span class="badge">14</span>
    				<a href="#">Comments</a>
  			</li>
		</ul>
</div></div>
</body>
</html>