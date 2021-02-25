<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<title>Customer information</title>
<style>
body {
	font-family: "Lato", sans-serif;
}

.tablink {
	background-color: #555;
	color: white;
	float: left;
	border: none;
	outline: none;
	cursor: pointer;
	padding: 14px 16px;
	font-size: 17px;
	width: 25%;
}

.tablink:hover {
	background-color: #777;
}

/* Style the tab content */
.tabcontent {
	color: black;
	display: none;
	padding: 100px;
	align: left;
	text-align: center;
}
</style>
</head>
<body>
	<security:authentication var="principal" property="principal" />
	<h2>User account information</h2>
	<span>Hello, ${principal.username}</span>
	<hr>

	
	<button class="tablink" onclick="openCity('Orders', this, 'green')" id="defaultOpen">Order History</button>
	<button class="tablink" onclick="openCity('Address', this, 'red')">Address</button>
	
	<div id="Address" class="tabcontent container">
		<div class="container">
			<div class="list-group">
				<form:form method="GET" modelAttribute="customer">
					<ul class="list-group">
						<h3><Strong>Personal information</strong></h3>
						<li class="list-group-item">Name: ${customer.user.userName}</li>
						<li class="list-group-item">Email: ${customer.user.userEmail}</li>
						<c:forEach var="role" items="${customer.user.roles}">
							<li class="list-group-item">Role: ${role.roleName}</li>
						</c:forEach>
						<c:forEach var="address" items="${customer.addresses}">
							<li class="list-group-item">Customer Address:
								${address.addressType}</li>
							<li class="list-group-item">House No#: ${address.houseNo}</li>
							<li class="list-group-item">Street: ${address.street}</li>
							<li class="list-group-item">City: ${address.city}</li>
							<li class="list-group-item">State: ${address.state}</li>
							<li class="list-group-item">ZipCode: ${address.zipCode}</li>
						</c:forEach>
					</ul>
				</form:form>
			</div>
		</div>
	</div>

	<div id="Orders" class="tabcontent">
		<div class="container">
			<br>
			<h2>List of Your Orders</h2>
			<!-- <a href="/product/addProduct"> Add new Product</a> -->
			<table class="table table-bordered">
				<thead>
					<tr >
						<th class="text-center">Order Id</th>
						<th class="text-center">Ordered On</th>
						<th class="text-center">Delivered On</th>
						<th class="text-center">Status</th>
						<th class="text-center">Order Total</th>
						<th class="text-center">View Order Details</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="order" items="${sessionScope.ORDER_SESSION}">
				<c:set var="orderTotal" value="${0}" />
						<tr>
							<td><c:out value="${order.orderId}" /></td>
							<td><c:out value="${order.orderDate}" /></td>
							<td><c:out value="${order.deliveryDate}" /></td>
							<td><c:out value="${order.status}" /></td>
							<c:forEach var="orderProd" items="${order.orderedProductList}" >
								<c:if test="${orderProd.status == 'Completed'}">
							<c:set var="orderTotal" value="${orderTotal + orderProd.finalCost}"/>
								</c:if>
							</c:forEach>
							<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${orderTotal}"/></td>
							<td><form:form
									action="/order/orderDetails/${order.orderId}"
									method="GET">
									<input type="submit" class="btn btn-success"
										value="View Order Details" />
								</form:form></td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>


	<script>
		function openCity(cityName, elmnt, color) {
			var i, tabcontent, tablinks;
			tabcontent = document.getElementsByClassName("tabcontent");
			for (i = 0; i < tabcontent.length; i++) {
				tabcontent[i].style.display = "none";
			}
			tablinks = document.getElementsByClassName("tablink");
			for (i = 0; i < tablinks.length; i++) {
				tablinks[i].style.backgroundColor = "";
			}
			document.getElementById(cityName).style.display = "block";
			elmnt.style.backgroundColor = color;

		}
		// Get the element with id="defaultOpen" and click on it
		document.getElementById("defaultOpen").click();
	</script>
</body>
</html>