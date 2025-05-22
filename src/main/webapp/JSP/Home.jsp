<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SecureBank - Welcome</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/Home.css">
</head>
<body>
     
	<header class="header">
		<div class="header-top">
			<h1>SecureBank</h1>
		</div>
		<nav class="nav-links">
			<a href="adminlogin.jsp">Admin Login</a> <a href="employeelogin.jsp">Employee
				Login</a> <a href="Customer_login.jsp">Customer Login</a> <a
				href="Register1.jsp">Customer Registration</a>
				<a href="TrackStatus.jsp">Track Status</a>
		</nav>
	</header>

	<!-- Main Content -->
	<div class="home-container">
		<h2>Welcome to SecureBank</h2>
		<p>Your trusted partner for all your banking needs. Manage your
			accounts, transfer funds, and more with ease.</p>

		<div class="features">
			<div class="feature">
				<h3>Online Banking</h3>
				<p>Access your accounts anytime, anywhere.</p>
			</div>
			<div class="feature">
				<h3>Secure Transactions</h3>
				<p>Bank with confidence using our secure platform.</p>
			</div>
			<div class="feature">
				<h3>Customer Support</h3>
				<p>24/7 support for all your banking queries.</p>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />

</body>
</html>
