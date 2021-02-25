<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<title>Update Product</title>
</head>
<body>
	<h3>Update Product</h3>
	<form:form method="POST" action="/product/updateProduct"
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
				<td><form:errors path="prodDescription" cssStyle="color:red"></form:errors>
			</tr>
			<tr>
				<td><form:label path="price">Price</form:label></td>
				<td><form:input path="price"></form:input></td>
				<td><form:errors path="price" cssStyle="color:red"></form:errors>
			</tr>
			
			<tr>
			<td><form:input type="hidden" path="productInventory.id"></form:input></td>
			</tr>
			
			<tr>
				<td><form:label path="productInventory.discount">Discount</form:label></td>
				<td><form:input path="productInventory.discount"></form:input></td>
			</tr>
			<tr>
				<td><form:label path="productInventory.quantity">Quantity</form:label></td>
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
				<td><form:label path="image">Product image</form:label></td>
				<td><form:input path="image"></form:input></td>
			</tr>
			<tr>
				<td><form:input type="hidden" path="category"></form:input></td>
			</tr>
			<tr>
				<td><input type="submit" value="Updated Product" /></td>
			</tr>
		</table>
	</form:form>


</body>
</html>
