<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

</head>
<body>
	<h2>Register</h2>

	<div class="container" style="width: 500px;">
		<jsp:include page="/WEB-INF/views/include/messages.jsp"></jsp:include>
	
		<form class="form-horizontal" name=registerForm method="POST" action="user/register">
			<div class="form-group">
				<label for="inputNick" class="col-sm-2 control-label">Nickname</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="nickName" id="nickName"
						placeholder="Nickname" required autofocus>
				</div>
			</div>

			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" name="email" id="email"
						placeholder="Email" required>
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-2 control-label">Password</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" name="password"  id="password"
						placeholder="Password" required>
				</div>
			</div>
			<div class="form-group form-inline">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" class="btn btn-success" value="Register"/>
				</div>
			</div>
		</form>
	</div>
	<!-- /container -->
</body>
</html>