<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Dashboard - SecureBank</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/employee_dashboard.css">
</head>
<body>
	<div class="header">
		<h1>SecureBank</h1>
		<div class="nav-links">
			<a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>

		</div>
	</div>

	<div class="dashboard">
		<h2>Welcome Employee</h2>
		<div class="dashboard-content">
			<div class="card">
				<h3>Pending Registrations</h3>
				<p>Review, approve, or reject new customer registrations.</p>
				<a
					href="${pageContext.request.contextPath}/ViewPendingCustomersServlet"
					class="btn">Manage Customers</a>
			</div>
			<div class="card">
				<h3>Pending Transactions</h3>
				<p>Approve customer deposit & withdrawal requests.</p>
				<a
					href="${pageContext.request.contextPath}/ViewPendingTransactionsServlet"
					class="btn">Manage Transactions</a>
			</div>
		</div>
	</div>
</body>
</html>