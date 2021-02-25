<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="com.DailyGroceryShop.domain.Customer"%>
<%@ page import="com.DailyGroceryShop.domain.Address"%>
<%@ page import="com.DailyGroceryShop.domain.User"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" />

<%@include file="header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<title>Manage Products Inventory</title>
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
		<div class="row">
			<center>
				<h2>Raise your Complaints Via E-mail</h2>
			</center>
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="col-md-12 row"
							style="padding: 0px; margin-bottom: 10px;">

							<div class="col-md-2">
								<img src="http://dinus.org/img/fakultas/FIK/cs/cs.svg">
								<div style="margin-left: 30px;">
									<img src="http://dinus.org/img/fakultas/FIK/cs/cs.png"
										width="70px" height="70px" class="img-circle"
										style="border: 3px solid #052C52;">
								</div>
							</div>
							<div class="col-md-10">
								<hr>
								<p style="padding-left: 55px; font-size: 1.3em;">
									<strong>Daily Grocery Shop Team will assist you.</strong>
								</p>

							</div>
						</div>
						<table class="table">
							<%
								Customer c1 = (Customer) session.getAttribute("CUSTOMER_SESSION");
							%>

							<tr>
								<spring:bind path="order">
									<td><Strong>Complaint for Order Number#
											${order.orderId}</Strong></td>
								</spring:bind>
							</tr>
							<tr>
								<td>Customer Name: <%=c1.getUser().getUserName()%>
								</td>
							</tr>
							<tr>
								<td>Customer Email: <%=c1.getUser().getUserEmail()%>
								</td>
							</tr>
							<tr>
							   <td><Strong style="color:red">${message}</Strong></td>
							 </tr>
						</table>
						<c:if test ="${empty message}">
						<form:form method="POST" action="/order/saveRaisedComplain"
							modelAttribute="complain">
							<table>
								<tr>
									<td><form:input type="hidden" path="id"></form:input></td>
								</tr>
								<tr>
									<td><form:label path="complainRaisedDate">Complain Date</form:label></td>
									<td><fmt:formatDate value="${now}" pattern="MM-dd-yyyy" /></td>
									<td><form:input type="hidden" path="complainRaisedDate" value="${now}"></form:input></td>
								</tr>
								<tr>
									<td><form:label path="status">Status</form:label></td>
									<td><Strong>OPEN</Strong></td>
									<td><form:input type="hidden" path="status" value="OPEN"></form:input></td>
								</tr>
								<tr>
									<td><form:label path="complainType">Complain Type:</form:label></td>
									<td><form:select  path="complainType" >
										<form:option value="CANCEL ORDER">Cancel Order</form:option>
										<form:option value="ITEM REFUND">Item Refund</form:option>
									   <form:option value="OTHER">Other</form:option>
									</form:select></td>
								</tr>
								<tr>
									<td><form:label path="description">Description</form:label></td>
									<td><form:input path="description"></form:input></td>
								</tr>
								
								<tr>
									<spring:bind path="order">
										<td><form:label path="order">Order Num#</form:label></td>
										<td><Strong>${order.orderId} </Strong></td>
										<td><form:input type="hidden" path="order" value="${order.orderId}"></form:input></td>
									</spring:bind>
								</tr>
								<tr>
									<td><form:label path="orderedProd">Product</form:label></td>
									<td><c:forEach  var="orderedProd" items="${order.orderedProductList}" >
									<Strong>${orderedProd.product.prodName}</Strong>
									<td><form:input  type="hidden" path="orderedProd" value="${orderedProd.id}"></form:input></td>
									</c:forEach></td>
								</tr>
								<tr>
									<td>
									   <input type="submit" class="btn btn-info"
										value="Raise Complain" />
										</td>
								</tr>
							</table>
						</form:form>
						</c:if>
					</div>
</body>
</html>
