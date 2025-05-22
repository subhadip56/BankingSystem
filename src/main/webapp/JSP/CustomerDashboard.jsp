<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Dashboard – SecureBank</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/Customer_dashboard.css">
</head>
<body>
	<!-- Site Header -->
	<header class="site-header">
		<div class="header-inner">
			<h1>SecureBank</h1>
		</div>
	</header>

	<!-- Authentication Check -->
	<c:if test="${empty sessionScope.customerId}">
		<c:redirect
			url="${pageContext.request.contextPath}/JSP/Customer_login.jsp" />
	</c:if>

	<!-- User Navigation -->
	<header class="dash-header">
		<nav class="dash-nav">
			<span>Welcome, <strong>${sessionScope.customerName}</strong></span> <a
				href="${pageContext.request.contextPath}/LogoutServlet"
				class="logout-btn">Logout</a>
		</nav>
	</header>

	<!-- Dashboard Cards -->
	<div class="dash-container">

		<div class="card">
			<h2>Account Overview</h2>
			<ul>
				<li><a
					href="${pageContext.request.contextPath}/ViewBalanceServlet">View
						Balance</a></li>
				<li><a
					href="${pageContext.request.contextPath}/MiniStatementServlet">Mini
						Statement</a></li>
			</ul>
		</div>

		<div class="card">
			<h2>Deposit & Withdrawal</h2>
			<ul>
				<li><a
					href="${pageContext.request.contextPath}/DepositPageServlet">Deposit
						Balance</a></li>

				<li><a
					href="${pageContext.request.contextPath}/WithdrawPageServlet">Withdraw
						Funds</a></li>
			</ul>
		</div>

		<div class="card">
			<h2>Fund Transfer</h2>
			<ul>
				<li><a
					href="${pageContext.request.contextPath}/FundTransferServlet">Transfer
						Funds</a></li>
				<li><a
					href="${pageContext.request.contextPath}/AddBeneficiaryServlet">Add
						Beneficiary</a></li>
				<li><a
					href="${pageContext.request.contextPath}/TransferStatusServlet">
						Transfer Status</a></li>
			</ul>
		</div>

		<div class="card">
			<h2>Loan Services</h2>
			<ul>
				<li><a
					href="${pageContext.request.contextPath}/LoanRequestServlet">Apply
						for Loan</a></li>
				<li><a
					href="${pageContext.request.contextPath}/LoanStatusServlet">My
						Loan Status</a></li>
			</ul>
		</div>

		<div class="card">
			<h2>Fixed & Recurring Deposits</h2>
			<ul>
				<li><a href="FDAccountRequest.jsp">Apply for FD/RD Account</a></li>
				<li><a
					href="${pageContext.request.contextPath}/FDStatusServlet">My
						FD/RD Status</a></li>
			</ul>
		</div>

		<div class="card">
			<h2>Profile & Security</h2>
			<ul>
				<li><a
					href="${pageContext.request.contextPath}/ChangePasswordServlet">Change
						Password</a></li>
			</ul>
		</div>

		<div class="card">
			<h2>Grievance</h2>
			<ul>
				<li><a
					href="${pageContext.request.contextPath}/JSP/GrievanceRequest.jsp">Create
						Grievance Request</a></li>
				<li><a
					href="${pageContext.request.contextPath}/GrievanceStatusServlet">My
						Grievances</a></li>
			</ul>
		</div>

	</div>

</body>
</html>
