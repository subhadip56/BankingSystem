<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Login - SecureBank</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/admin_employee_login.css">
</head>
<body>
	<header class="header">
		<div class="header-top">
			<h1>SecureBank</h1>
		</div>
	</header>

	<div class="login-container">

		<c:if test="${not empty errorMessage}">
			<div class="error-message">${errorMessage}</div>
		</c:if>

		<h2>Employee Login</h2>
		<form action="${pageContext.request.contextPath}/EmployeeLoginServlet" method="post"
			class="login-form">
			<input type="text" name="username" placeholder="Enter Username"
				required> <input type="password" name="password"
				placeholder="Enter Password" required>
			<button type="submit">Login</button>
		</form>
	</div>
	<footer>
		<p>© Bank Management System. All rights reserved.</p>
	</footer>
</body>
</html>