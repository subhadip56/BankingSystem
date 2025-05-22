<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Employee – SecureBank</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/edit_employee.css">
</head>
<body>
	<div class="container">
		<header class="header">
			<h1>SecureBank Admin</h1>
			<nav>
				<a href="ManageEmployeesServlet" class="back-link">← Back to
					Employees</a>
			</nav>
		</header>

		<main class="card form-card">
			<h2>Edit Employee</h2>
			<form action="<c:url value='UpdateEmployeeServlet'/>" method="post">
				<input type="hidden" name="id" value="${editEmpId}" />

				<div class="form-group">
					<label for="fullName">Full Name</label> <input id="fullName"
						type="text" name="fullName" value="${fullName}" required>
				</div>

				<div class="form-group">
					<label for="email">Email</label> <input id="email" type="email"
						name="email" value="${email}" required>
				</div>

				<div class="form-group">
					<label for="username">Username</label> <input id="username"
						type="text" name="username" value="${username}" required>
				</div>

				<div class="form-group">
					<label for="role">Role</label> <select id="role" name="role"
						required>
						<option ${role=='Cashier'?'selected':''}>Cashier</option>
						<option ${role=='Loan Officer'?'selected':''}>Loan
							Officer</option>
						<option ${role=='Account Manager'?'selected':''}>Account
							Manager</option>
						<option ${role=='IT Support'?'selected':''}>IT Support</option>
						<option ${role=='Clerk'?'selected':''}>Clerk</option>
					</select>
				</div>

				<button type="submit" class="btn btn-primary">Save Changes</button>
			</form>
		</main>

		<footer class="footer">
			<p>&copy; 2025 SecureBank. All rights reserved.</p>
		</footer>
	</div>
</body>
</html>