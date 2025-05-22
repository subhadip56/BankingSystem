<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Login – SecureBank</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/Customer_login.css">
</head>
<body>

	<!-- Site banner -->
	<header class="site-header">
		<div class="header-inner">
			<h1>SecureBank</h1>
		</div>
	</header>

	<div class="login-container">
		<h2>Customer Login</h2>

		<c:if test="${param.error == 'invalid'}">
			<div class="error">Invalid Customer ID or Password!!</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/CustomerLoginServlet"
			method="post">
			<label for="customerId">Customer ID</label> <input type="text"
				id="customerId" name="customerId" required> <label
				for="password">Password</label> <input type="password" id="password"
				name="password" required>

			<button type="submit">Login</button>
		</form>
		<p class="small-link">
			<a href="Register1.jsp">New? Register here</a>
		</p>
	</div>

</body>
</html>
