<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
inc
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

<body>

	<div style="max-width: 800px; margin: 15px auto 0 auto">
		<div class="panel-body alert alert-danger text-center" role="alert">
			<p>${ERROR}	</p>
				
			<div class="btn-group text-center" role="group">
			<button class="btn btn-danger" onclick="window.history.back();">Go Back</button></div>
		</div>
	</div>

</body>
</html>