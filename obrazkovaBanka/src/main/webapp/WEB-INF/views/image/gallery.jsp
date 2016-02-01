<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/include/headerFullHeader.jsp"></jsp:include>

</head>
<body>
<div class="container">
  <h2>Image Gallery</h2>
  <p>The .thumbnail class can be used to display an image gallery. Click on the images to see it in full size:</p>            
  <div class="row">
    <div class="col-md-4">
      <a href="pulpitrock.jpg" class="thumbnail">
        <p>Pulpit Rock: A famous tourist attraction in Forsand, Ryfylke, Norway.</p>    
        <img src="pulpitrock.jpg" alt="Pulpit Rock" style="width:150px;height:150px">
      </a>
    </div>
    <div class="col-md-4">
      <a href="moustiers-sainte-marie.jpg" class="thumbnail">
        <p>Moustiers-Sainte-Marie: Considered as one of the "most beautiful villages of France".</p>
        <img src="moustiers-sainte-marie.jpg" alt="Moustiers Sainte Marie" style="width:150px;height:150px">
      </a>
    </div>
    <div class="col-md-4">
      <a href="cinqueterre.jpg" class="thumbnail">
        <p>The Cinque Terre: A rugged portion of coast in the Liguria region of Italy.</p>      
        <img src="cinqueterre.jpg" alt="Cinque Terre" style="width:150px;height:150px">
      </a>
    </div>
  </div>
</div>
</body>
</html>