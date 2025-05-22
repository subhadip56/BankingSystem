<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Employee - SecureBank</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/CreateEmployee.css">
</head>
<body>
	<div class="header">
		<h1>SecureBank</h1>
		<div class="nav-links">
			<a href="${pageContext.request.contextPath}/JSP/admin_dashboard.jsp">Dashboard</a>
		</div>
	</div>

	<div class="form-container">
		<h2>Create New Employee Account</h2>
		<form
			action="${pageContext.request.contextPath}/CreateEmployeeServlet"
			method="post">
			<label for="fullName">Full Name:</label> <input type="text"
				id="fullName" name="fullName" required> <label for="email">Email:</label>
			<input type="email" id="email" name="email" required> <label
				for="username">Username:</label> <input type="text" id="username"
				name="username" required> <label for="password">Password:</label>
			<input type="password" id="password" name="password" required>

			<label for="role">Role:</label> <select id="role" name="role"
				required>
				<option value="" disabled selected>Select a Role</option>
				<option value="Clerk">Clerk</option>
				<option value="Casher ">Casher</option>
				<option value="Loan Officer">Loan Officer</option>
				<option value="Financial Advisor">Financial Advisor</option>
				<option value="IT Support">IT Support</option>

			</select>

			<button type="submit" class="btn">Create Employee</button>
		</form>
	</div>
</body>
</html>