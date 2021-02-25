<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<br>
<h4 class="container" style="color:blue">Please upload CSV file.</h4>
<div class="container">
	<form method="POST"  enctype="multipart/form-data">
    <table>
        <tr>
            <td>Select a file to upload</td>
            <td><input type="file" class="btn btn-info" name="file" /></td>
        </tr>
        <tr>
            <td><input type="submit" class="btn btn-success" value="Upload" /></td>
          </tr>
         
          <tr>
            <td><a href="/product"><button type="button" class="btn btn-primary">back to Product page</button></a></td>
        </tr>
    </table>
</form>
	</div>
</body>
</html>