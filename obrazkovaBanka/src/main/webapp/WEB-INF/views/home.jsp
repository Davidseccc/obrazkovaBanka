<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<jsp:include page="/WEB-INF/views/include/headerFullHeader.jsp"></jsp:include>
<link href="/obrazkovaBanka/resources/css/dropzone.css" type="text/css" rel="stylesheet" />
<script src="/obrazkovaBanka/resources/js/myuploadfunction.js"></script>
<body>
	<div class="container container-menu">
		<div class="header clearfix">
			<nav>
			<ul class="nav nav-pills pull-right">
				<li class="active"><a href="#">Home</a></li>
				<li><a href="search/?image">Search</a></li>
				<li><a href="about.jsp">About</a></li>
				<li><a href="contact.jsp">Contact</a></li>
			</ul>
			</nav>
		</div>
	</div>
<div class="center-block" style="width: 500px; padding: 20px;">

		<input style="padding-bottom: 20px; margin: 0 auto 0 auto" id="fileupload" type="file" name="files[]"
			data-url="controller/savefiles" multiple>

		<div id="dropzone" class="center-block fade well">Drop files here</div>
		<p class="text-center small text-muted">Supported content path: <kbd class="kdb-grey">image/bmp</kbd>, <kbd class="kdb-grey">image/jpeg</kbd> and <kbd class="kdb-grey">image/png</kbd></p>
		<h2>Upload progress</h2>
		<div class="progress" style="width: 600px;">
			<div class="progress-bar progress-bar-success progress-bar-striped"
				role="progressbar" aria-valuenow="0" aria-valuemin="0"
				aria-valuemax="100" style="width: 0%">0%</div>
		</div>
		<table id="uploaded-files" class="table">
			<tr>
				<th>File Name</th>
				<th>File Size</th>
				<th>File Type</th>
				<th>Download</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</table>

	</div>

<div class="center-block" style="width: 90%; padding-top: 20px;" class="row">
    <div class="col-sm-4">
      <h3>Column 1</h3>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>
    </div>
    <div class="col-sm-4">
      <h3>Column 2</h3>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>
    </div>
    <div class="col-sm-4">
      <h3>Column 3</h3>        
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>
    </div>
     <div class="clearfix" ><p class="text-muted center col-sm-12" >  The time on the server is ${serverTime}. </p></div>
    
  </div>

</body>
</html>
