<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.DailyGroceryShop.domain.CartItem"%>
<%@ page import="com.DailyGroceryShop.domain.Customer"%>
<%@ page import="com.DailyGroceryShop.domain.Address"%>
<%@ page import="com.DailyGroceryShop.domain.User"%>
<%@ page session="true"%>

<%@include file="header.jsp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<title>Place order</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-12 col-md-10 col-md-offset-1">
				<form:form method="POST" action="/order/saveNewOrder"
					modelAttribute="order">

					<%
						Customer c1 = (Customer) session.getAttribute("CUSTOMER_SESSION");
					// out.println("Customer object from session:"+c1);
					//out.println("Customer object from session:"+c1.getCustomerId());
					
					%>
					<div class="col-sm-4 invoice-col">
						<form:hidden path="customer" value="${c1.getUser().getUserName()}"></form:hidden>
						To
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
					<!-- /.col -->
					<div class="col-sm-5 invoice-col"></div>
					<!-- /.col -->

					<div class="col-sm-3 invoice-col">
						<br>
						<b>Order Date:</b>
						<fmt:formatDate value="${now}" pattern="dd-MM-yyyy" />
						<br> <b>Payment Due:</b>
						<fmt:formatDate value="${now}" pattern="dd-MM-yyyy" />
						<br> <b>Payment method:</b> VISA-34567
					</div>
					<!-- /.col -->

					<%-- <form:input type="hidden" path="customer" value="<%=c1%>"></form:input> --%>
					<form:input type="hidden" path="status" value="Completed"></form:input>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Product</th>
								<th>Quantity</th>
								<th class="text-center">Price</th>
								<th class="text-center">Discount</th>
								<th class="text-center">Total</th>
								<th> </th>
							</tr>
						</thead>
						<tbody>
							<c:set var="subtotal" value="${0}" />
							<%-- <form:input type="hidden" path="orderedProductList"
								value="${sessionScope.CARTITEMS_SESSION}"></form:input> --%>
							<c:forEach var="cartItem"
								items="${sessionScope.CARTITEMS_SESSION}">
								<tr>
									<%-- <form:input type="hidden" path="orderedProductList" value="${cartItem}"></form:input> --%>
									<td class="col-sm-8 col-md-6">
										<div class="media">
											<a class="thumbnail pull-left" href="#"> <img
												class="media-object"
												src="http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/72/product-icon.png"
												style="width: 72px; height: 72px;"/>
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
									<td class="col-sm-1 col-md-1" style="text-align: center">
										<input type="number" class="form-control"
										id="exampleInputEmail1" value="${cartItem.quantity}" />
									</td>
									<td class="col-sm-1 col-md-1 text-center"><strong>$<c:out
												value="${cartItem.product.price}" /></strong></td>
									<td class="col-sm-1 col-md-1 text-center"><strong>$<c:out
												value="${cartItem.product.productInventory.discount}" /></strong></td>
									<td class="col-sm-1 col-md-1 text-center"><c:set
											var="subtotal"
											
											value="${subtotal + Math.round(cartItem.product.price * cartItem.quantity -cartItem.product.productInventory.discount )}" />
										<strong>${Math.round(cartItem.product.price * cartItem.quantity -cartItem.product.productInventory.discount )}</strong></td>
									<td class="col-md-1">
										<a href="/user/cart">
										<button type="button" class="btn btn-danger">
											<span class="glyphicon glyphicon-shopping-cart"></span>
											Edit Cart
										</button>
								</a></td>
								</tr>
							</c:forEach>
							<tr>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td><h5>Choose delivery date</h5></td>
								<td class="text-right">
								<spring:bind path="deliveryDate">
								<form:input path="deliveryDate" type="date" id="deliveryDate" pattern="YYYY-MM-DD"  />
								</spring:bind>
							</tr>
							<tr>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td><h5>Subtotal</h5></td>
								<td class="text-right"><h5>
										<strong> <fmt:formatNumber type="number" maxFractionDigits="2" value="${subtotal}"/></strong>
									</h5></td>
							</tr>
							<tr>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td><h5>Delivery charges</h5></td>
								<td class="text-right"><h5>
										<form:input type="hidden" path="deliveryCharge" value="7.99"></form:input>
										<strong>$7.99</strong>
									</h5></td>
							</tr>
							<tr>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td><h3>Total</h3></td>
								<td class="text-right"><h3>
										$<strong> <fmt:formatNumber type="number" maxFractionDigits="2" value="${subtotal+7.99}"/></strong>
									</h3></td>
							</tr>

							<tr>

								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td><a href="/home">
										<button type="button" class="btn btn-default">
											<span class="glyphicon glyphicon-shopping-cart"></span>
											Continue Shopping
										</button>
								</a></td>
								<td>
									<button type="submit" class="btn btn-success">
										Place Order <span class="glyphicon glyphicon-play"></span>
									</button>
								</td>
							</tr>
						</tbody>
					</table>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>