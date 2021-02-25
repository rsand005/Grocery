<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<title>Manage Products Inventory</title>
<style >
body {
	font-family: Arial;
}

* {
	box-sizing: border-box;
}

form.example input[type=text] {
	padding: 10px;
	font-size: 17px;
	border: 1px solid grey;
	float: left;
	width: 80%;
	background: #f1f1f1;
}

form.example button {
	float: left;
	width: 20%;
	padding: 10px;
	background: #2196F3;
	color: white;
	font-size: 17px;
	border: 1px solid grey;
	border-left: none;
	cursor: pointer;
}

form.example button:hover {
	background: #0b7dda;
}

form.example::after {
	content: "";
	clear: both;
	display: table;
}
</style>

</head>
<body>
	<br>
	<h4 class="container" style="color:blue">Add New Products below:</h4>
	<br>
	<div class="container">
	<a></a>
	<form action="/csv/uploadCSV" method="GET">
		<input type="submit" class="btn btn-info" value="Upload CSV File" />
		<h6>click on CSV upload to add batch of products.</h6>
		<hr>
	</form>
	</div>
	
	<div class="container">
	<h6>Please enter details below to add a new product:</h6>
	<form:form method="POST" action="/product/saveProduct"
		modelAttribute="product">
		<table>
			<tr>
				<td><form:input type="hidden" path="productId"></form:input></td>
			</tr>
			<tr>
				<td><form:label path="prodName">Product Name</form:label></td>
				<td><form:input path="prodName"></form:input></td>
				<td><form:errors path="prodName" cssStyle="color:red"></form:errors>
			</tr>
			<tr>
				<td><form:label path="prodDescription">Description</form:label></td>
				<td><form:input path="prodDescription"></form:input></td>
				<td><form:errors path="prodDescription" cssStyle="color:red"></form:errors></td>
			</tr>
			<tr>
				<td><form:label path="price">Price</form:label></td>
				<td><form:input path="price"></form:input></td>
				<td><form:errors path="price" cssStyle="color:red"></form:errors></td>
			</tr>
			<tr>
				<td><form:label  path="productInventory.quantity">Quantity</form:label></td>
				<td><form:input path="productInventory.quantity"></form:input></td>
			</tr>
			<tr>
				<td><form:label path="brand">Brand</form:label></td>
				<td><form:input path="brand"></form:input></td>
			</tr>
			<tr>
				<td><form:label path="dateOfExpiry">Date Of Expiry</form:label></td>
				<td><form:input path="dateOfExpiry"></form:input></td>
			</tr>
			<tr>
				<td><form:label path="category">Category</form:label></td>
				<td><form:select id="category" path="category">
						<c:forEach var="cat" items="${listOfCategory}">
							<form:option path="category" value="${cat.id}">${cat.type}</form:option>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td><form:label path="productInventory.discount">Discount</form:label></td>
				<td><form:input path="productInventory.discount"></form:input></td>
			</tr>
			<tr>
				<td><form:label path="image">Product image</form:label></td>
				<td><form:input path="image"></form:input></td>
			</tr>
			<tr>
				<td><input type="submit" class="btn btn-info" value="Save Product" /></td>
			</tr>
		</table>
	</form:form>
	</div>

	<div class="container">
		<br>
		<h2>List of Products</h2>
		<!-- <a href="/product/addProduct"> Add new Product</a> -->
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>#Id</th>
					<th>Image</th>
					<th>Name</th>
					<th>Description</th>
					<th>Price</th>
					<th>Discount</th>
					<th>Quantity</th>
					<th>Brand</th>
					<th>Date Of Expiry</th>
					<th>Category</th>
					<th>Update</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${listOfProducts}">
					<tr>
						<td><c:out value="${product.productId}" /></td>
						<td><c:out value="${product.image}" /></td>
						<td><c:out value="${product.prodName}" /></td>
						<td><c:out value="${product.prodDescription}" /></td>
						<td><c:out value="${product.price}" /></td>
						<td><c:out value="${product.productInventory.discount}" /></td>
						<td><c:out value="${product.productInventory.quantity}" /></td>
						<td><c:out value="${product.brand}" /></td>
						<td><c:out value="${product.dateOfExpiry}" /></td>
						<td><c:out value="${product.category.type}" /></td>
						<td><form:form
								action="/product/updateProduct/${product.productId}"
								method="GET">
								<input type="submit" class="btn btn-warning" value="Update Product" />
							</form:form></td>
						<td><form:form
								action="/product/deleteProduct/${product.productId}"
								method="POST">
								<input type="submit" class="btn btn-danger" value="Delete Product" />
							</form:form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>
