<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">



<title>Daily Grocery Shop</title>
<style>
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
	<form class="example" method="post" action="/findProduct"
		style="margin: auto; max-width: 500px; padding-top: 20px">
		<input type="text" placeholder="Search.." name="searchProd">
		<button type="submit">
			<i class="fa fa-search"></i>
		</button>
	</form>

	<div class="container">
		<br>
		<h2>List of Products</h2>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Image</th>
					<th>Name</th>
					<th>Description</th>
					<th>Price</th>
					<th>Discount</th>
					<th>In-Stock</th>
					<th>Brand</th>
					<th>Date Of Expiry</th>
					<th>Category</th>
					<th>Add to Cart</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${listOfProducts}">
					<tr>
						<td>
						<img height="100px" width="100px"
						src="https://www.biggerbolderbaking.com/wp-content/uploads/2020/04/Hearty-Yeast-Free-Bread-WS-Thumbnail.jpg"/>
						</td>
					   <td><c:out value="${product.prodName}" /></td>
						<td><c:out value="${product.prodDescription}" /></td>
						<td><c:out value="${product.price}" /></td>
						<td><c:out value="${product.productInventory.discount}" /></td>
						<td><span class="text-danger">Only <c:out value="${product.productInventory.quantity}" /> left!</span></td>
						<td><c:out value="${product.brand}" /></td>
						<td><c:out value="${product.dateOfExpiry}" /></td>
						<td><c:out value="${product.category.type}" /></td>

						<td><form:form action="/user/saveCartItemFromHome" method="POST"
								modelAttribute="cartItem">
								<table>
								<tr>
									<td><form:input type="hidden" path="product" value="${product.productId}"></form:input></td>
								</tr>
								<tr>
									<td><form:input type="number" path="quantity" value="0" min="1" max="${product.productInventory.quantity}"></form:input></td>
								</tr>
								<tr>
									<td>
									<input type="submit" class="btn btn-info" value="Add to Cart" /></td>
								</tr>
								</table>
							</form:form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>