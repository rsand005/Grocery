<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="com.DailyGroceryShop.domain.Complain"%>
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

		<div class="container">
			<br>
			<h3>Complains raised by customers, please take action.</h3>
			<table class="table table-bordered">
				<thead>
					<tr >
						<th class="text-center">Complain #</th>
						<th class="text-center">Complain Raised On</th>
						<th class="text-center">Description</th>
						<th class="text-center">Status</th>
						<th class="text-center">Complain Type</th>
						<th class="text-center">Order #</th>
						<th class="text-center">Order Total</th>
						<th class="text-center">Complain Closed On</th>
						<th class="text-center">Action</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="complain" items="${listOfComplains}">
				<c:set var="orderTotal" value="${0}" />
						<tr>
							<td><c:out value="${complain.id}" /></td>
							<td><c:out value="${complain.complainRaisedDate}" /></td>
							<td>
							<c:out value="${complain.description}" /></td>
							<td>
							
							<c:out value="${complain.status}" /></td>
							<td>
							<c:out value="${complain.complainType}" /></td>
							<td>
							<c:out value="${complain.order.orderId}" /></td>
							<c:forEach var="orderProd" items="${complain.order.orderedProductList}" >
							<c:set var="orderTotal" value="${orderTotal + orderProd.finalCost}"/>
							</c:forEach>
							<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${orderTotal}"/></td>
							<td>
							<c:out value="${complain.complainClosedDate}" /></td>
							<td><form:form
								action="/crmDept/resloveComplain"
								method="POST" modelAttribute="complain" >
								<spring:bind path="id">
								<form:input type="hidden"  path="id" value="${complain.id}" />
								</spring:bind>
								<spring:bind path="order">
								<form:input type="hidden"  path="order" value="${complain.order.orderId}" />
								</spring:bind>
								<spring:bind path="orderedProd">
								<form:input type="hidden"  path="orderedProd" value="${complain.orderedProd}" />
								</spring:bind>
								<spring:bind path="complainType">
								<form:input type="hidden"  path="complainType" value="${complain.complainType}"/>
								</spring:bind>
								<input type="submit" class="btn btn-danger" value="Reslove Complain" />
							</form:form></td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	
</body>
</html>