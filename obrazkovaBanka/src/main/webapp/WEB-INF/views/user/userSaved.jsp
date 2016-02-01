<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Saved</title>
</head>


	<div style="max-width: 800px; margin: 15px auto 0 auto">
		<div class="panel-body alert alert-success text-center" role="alert">
			<p><a href="user?login" class="alert-link"></a>
				User information successfully saved. You can login NOW.
				</p>
				
			<div class="btn-group text-center" role="group"><a class="btn btn-default dropdown-toggle" href="../user?login">Login</a></div>
		</div>
	</div>

</body>
</html>