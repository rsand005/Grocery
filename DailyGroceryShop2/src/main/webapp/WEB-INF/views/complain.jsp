<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<title>Simple Complaint Form</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link type="text/css" rel="stylesheet"
	href="http://dinus.org/assets/mail/mailtip.css" />
<script type="text/javascript"
	src="http://dinus.org/assets/mail/jquery.mailtip.js"></script>

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
								</tr></table>
								<form method="POST" action="/order/saveRaisedComplain">
									<table>
									 <tr>
											<td><label for="status">status</label></td>
											<td><input type="text" id="status" name="status" value="OPEN"></input></td>
										</tr>
										<tr>
											<td><label for="description">description</label></td>
											<td><input type="text" id="description" name="description" ></input></td>
										</tr>
										<tr>
											<td><label for="orderedProd">orderedProd</label></td>
											<td><select  id="orderedProd" name="orderedProd" items="${order.orderedProductList}"></input></td>
										</tr>
										<tr>
											<td><label for="order">order</label></td>
											<td><input  id="order" name="order" value="${order}"></input></td>
										</tr>
										<tr>
											<td><label for="complainRaisedDate">complainRaisedDate</label></td>
											<td><input type="date" id="complainRaisedDate" name="complainRaisedDate"></input></td>
										</tr>
										<tr>
											<td><input type="submit" class="fa fa-envelope-o" value="Raise Complain" /></td>
										</tr>
									</table>
								</form>
								</div>
					</div>
				</div>
			</div>
		</div>


</body>
</html>