<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
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
</head>
<body>
	<security:authentication var="principal" property="principal" />
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<!-- Brand/logo -->
		<a class="navbar-brand" href="#"> </a>


		<!-- Links -->
		<ul class="navbar-nav">
		
			<li class="nav-item"><a class="nav-link" href="/home">Home</a></li>
			
		 <security:authorize access="hasAnyRole('ROLE_ADMIN')">
         <li class="nav-item"><a class="nav-link" href="/product">Products</a></li>
         <li class="nav-item"><a class="nav-link" href="/crmDept">CRM Dept</a></li>
          </security:authorize>

			<li style="float: right"><a class="nav-link"
				href="/user/userAccount"><span class="glyphicon glyphicon-user"></span>
					Your Account</a></li>

			<li class="nav-item navbar-right"><a class="nav-link" href="/user/cart"><span
					class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>

			<li class="nav-item"><a class="nav-link" href="/login">Log-in</a></li>
			
			<%-- <!-- 	 <li class="nav-item"><a class="nav-link" href="/login">Log-in</a></li> -->
			<security:authorize ="isAuthenticated()"> 
			<li class="nav-item"><a class="nav-link" href="/login">Log-in</a></li>
			</security:authorize>

			<c:if test="${principal != null}">
   					hello
			</c:if> --%>
			<%-- <!--  --><li class="nav-item"><span style="color:white">${principal.username}</span></li> --%>
			
			<li class="nav-item"><a class="nav-link" href="/login?logout">Log-out</a></li>
		</ul>

	</nav>


</body>
</html>
