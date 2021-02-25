<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.DailyGroceryShop.domain.CartItem"%>
<%@ page import="com.DailyGroceryShop.domain.Customer"%>
<%@ page import="com.DailyGroceryShop.domain.Address"%>
<%@ page import="com.DailyGroceryShop.domain.User"%>
<%@ page session="true"%>
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
<div class="container">
	<div class="row">
			<div class="col-sm-12 col-md-10 col-md-offset-1">
					<%
						Customer c1 = (Customer) session.getAttribute("CUSTOMER_SESSION");
					// out.println("Customer object from session:"+c1);
					//out.println("Customer object from session:"+c1.getCustomerId());
					%>
					<div class="col-sm-6 invoice-col">
					<h4><strong>Details of order number#  ${order.orderId}</strong></h4>
						<address>
							<strong> <%=c1.getUser().getUserName()%>
							</strong> <br> Address:
							<%=c1.getAddresses().get(0).getHouseNo()%>
							<%=c1.getAddresses().get(0).getStreet()%>
							<br>
							<%=c1.getAddresses().get(0).getCity()%>
							<%=c1.getAddresses().get(0).getState()%>
							<%=c1.getAddresses().get(0).getZipCode()%>
							<br> Email:
							<%=c1.getUser().getUserEmail()%>
							<br>
						</address>
					</div>
					<div class="col-sm-5 invoice-col"></div>
					<!-- /.col -->

					<div class="col-sm-3 invoice-col">
						<br>
						<b>Order Date: ${order.orderDate}</b>
						<fmt:formatDate value="${order.orderDate}" pattern="dd-MM-yyyy" />
						<fmt:formatDate value="${now}" pattern="dd-MM-yyyy" />
						<br> <b>Payment method:</b> VISA-34567
						<br>
						<b>Delivery Date: ${order.deliveryDate}</b>
					</div>
			</div>	
		</div>	

		<br>
		<h3>List of ordered Products</h3>
		<!-- <a href="/product/addProduct"> Add new Product</a> -->
		<table class="table table-bordered">
			<thead>
				<tr>
					<th class="text-center">#Item</th>
					<th class="text-center">Status</th>
					<th class="text-center" >Name</th>
					<th class="text-center">Description</th>
					<th class="text-center">Price</th>
					<th class="text-center">Discount</th>
					<th class="text-center">Quantity Ordered</th>
					<th class="text-center">Cost</th>
					<th class="text-center">Brand</th>
					<th class="text-center">Category</th>
					<th class="text-center">Return</th>
					<th class="text-center">File Complain</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="orderedProd" items="${order.orderedProductList}">
					<tr>
						<td><c:out value="${orderedProd.id}" /></td>
						<td><c:out value="${orderedProd.status}" /></td>
						<td><c:out value="${orderedProd.product.prodName}" /></td>
						<td><c:out value="${orderedProd.product.prodDescription}" /></td>
						<td><c:out value="${orderedProd.product.price}" /></td>
						<td><c:out value="${orderedProd.discount}" /></td>
						<td><c:out value="${orderedProd.quantity}" /></td>
						<td><c:out value="${orderedProd.finalCost}" /></td>
						<td><c:out value="${orderedProd.product.brand}" /></td>
						<td><c:out value="${orderedProd.product.category.type}" /></td>
						<td><form:form
								action="/order/returnOrderedProduct"
								method="POST"  modelAttribute="order">
								<spring:bind path="orderId">
								<form:input type="hidden"  path="orderId" />
								</spring:bind>
								<spring:bind path="orderedProductList">
								<form:input type="hidden" path="orderedProductList" value="${orderedProd.id}"/> 
								</spring:bind>
								<input type="submit" class="btn btn-danger" value="Return Product" />
							</form:form></td>
							<td><form:form
								action="/order/raiseComplain"
								method="GET"  modelAttribute="order">
								<spring:bind path="orderId">
								<form:input type="hidden"  path="orderId" />
								</spring:bind>
								<spring:bind path="orderedProductList">
								<form:input type="hidden" path="orderedProductList" value="${orderedProd.id}"/> 
								</spring:bind>
								<input type="submit" class="btn btn-warning" value="Raise Complain" />
							</form:form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>