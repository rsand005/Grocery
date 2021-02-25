<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%@include file="header.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

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


	<div class="container">
		<br>
		<h3>Your shopping cart items:</h3>
		<table class="table table-bordered">
			<thead>
				<tr >
					<th>Product</th>
					<th>Description</th>
					<th>Category</th>
					<th>Price</th>
					<th>Discount</th>
					<th>Cart Quantity</th>
					<th>Cost</th>
					<th>Option</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="cartItem" items="${sessionScope.CARTITEMS_SESSION}">
					<tr>
						<td class="col-sm-6 col-md-6">
							<div class="media">
								<a class="thumbnail pull-left" href="#"> <img
									class="media-object"
									src="http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/72/product-icon.png"
									style="width: 72px; height: 72px;">
								</a>
								<div class="media-body">
									<h4 class="media-heading">
										<c:out value="${cartItem.product.prodName}" />
									</h4>
									<h5 class="media-heading">
										by
										<c:out value="${cartItem.product.brand}" />
									</h5>
									<span>Status: Only</span><span class="text-danger"><strong>
											<c:out value="${cartItem.product.productInventory.quantity}" /> left in
											stock!
									</strong></span>
								</div>
							</div>
						</td>
						<td class="col-sm-2 col-md-2 text-center">
						<c:out
								value="${cartItem.product.prodDescription}" /></td>
						<td class="col-sm-1 col-md-1 text-center"><c:out
								value="${cartItem.product.category.type}" /></td>

						<td class="col-sm-1 col-md-1 text-center">$<c:out
								value="${cartItem.product.price}" /></td>
						<td class="col-sm-1 col-md-1 text-center">$<c:out
								value="${cartItem.product.productInventory.discount }" /></td>
						<td class="col-sm-1 col-md-1 text-center">
						<form:form
								action="/user/updateCartItemQtyFromCart"
								modelAttribute="cartItem" method="POST">
								
									<form:input type="number" path="quantity"
										value="${cartItem.quantity}" min="1" max="${cartItem.product.quantity}"></form:input>
									<form:input type="hidden" path="product"
										value="${cartItem.product.productId}"></form:input>
									<input type="submit" value="Update QTY">
								
							</form:form></td>


						<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${(cartItem.product.price * cartItem.quantity -cartItem.product.productInventory.discount )}"/>
						
						<%-- <span>$</span>${Math.round(cartItem.product.price * cartItem.quantity -cartItem.product.productInventory.discount )} --%></td>
						<td class="col-sm-1 col-md-1"><form:form
								action="/user/removeSessionCartItem/${cartItem.product.productId}"
								modelAttribute="cartItem"  method="GET">
								<input type="submit" class="btn btn-danger" value="Remove" />
							</form:form></td>
					</tr>
				</c:forEach>
				<%-- <c:forEach var="cartItem" items="${CARTITEMS_SESSION}">
					<tr>
						<td><c:out value="${cartItem.product.image}" /></td>
						<td><c:out value="${cartItem.product.prodName}" /></td>
						<td><c:out value="${cartItem.product.prodDescription}" /></td>
						<td><c:out value="${cartItem.product.category.type}" /></td>
						<td><c:out value="${cartItem.product.price}" /></td>
						<td><c:out
								value="${cartItem.product.productInventory.discount}" /></td>
						<td><form:form  action="/user/updateCartItemQtyFromCart"
								modelAttribute="cartItem" method="POST">
								<div class="product-quantity">
									<form:input type="number" path="quantity" value="${cartItem.quantity}" min="1"></form:input>
										<form:input type="hidden" path="product" value="${cartItem.product.productId}"></form:input>
										<input type="submit" value="Update QTY">
								</div>
							</form:form></td>

						<td>${Math.round(cartItem.product.price * cartItem.quantity -cartItem.product.productInventory.discount )}
						</td>
						<td><form:form
								action="/user/removeSessionCartItem/${cartItem.product.productId}"
								modelAttribute="cartItemRemove" method="POST">
								<input type="submit" class="btn btn-danger" value="Remove" />  
							</form:form></td>
							
					</tr>
				</c:forEach> --%>


				<%-- <c:forEach var="cartItem" items="${listOfCartItems}">
					<tr> 
						<td><c:out value="${cartItem.product.image}" /></td>
						<td><c:out value="${cartItem.product.prodName}" /></td>
						<td><c:out value="${cartItem.product.prodDescription}" /></td>
						<td><c:out value="${cartItem.product.category.type}" /></td>
						<td><c:out value="${cartItem.product.price}" /></td>
						<td><c:out value="${cartItem.quantity}" /></td>
						<td><c:out value="${cartItem.product.productInventory.discount}" /></td>
						<td>${Math.round(cartItem.product.price * cartItem.quantity -cartItem.product.productInventory.discount)} </td>
						<td><form:form
								action="/user/removeCartItem/${cartItem.id}"
								method="POST" >
								<input type="submit" class="btn btn-info" value="Remove" />
							</form:form></td>
					</tr>
				</c:forEach> --%>

			</tbody>

		</table>
			
					<form:form action="/order/placeNewOrder" method="GET">
					<a href="/home">
					<button type="button" class="btn btn-default">
						<span class="glyphicon glyphicon-shopping-cart"></span> Continue
						Shopping
					</button></a>
					<input type="submit" class="btn btn-success" value="Go to Checkout"/> 
				</form:form>
	</div>



</body>
</html>