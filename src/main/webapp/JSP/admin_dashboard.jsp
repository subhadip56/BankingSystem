<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard – SecureBank</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/admin_dashboard.css">
</head>
<body>

	<!-- HEADER -->
	<div class="header">
		<h1>SecureBank</h1>
		<div class="nav-links">
			<a href="admin_dashboard.jsp">Dashboard</a> <a
				href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>
		</div>
	</div>

	<c:if test="${not empty param.message}">
		<div id="flash-msg" class="flash-msg">${param.message}</div>
		<script>
			setTimeout(function() {
				const f = document.getElementById('flash-msg');
				if (f)
					f.style.opacity = '0';
			}, 3000);
		</script>
	</c:if>

	<!-- MAIN DASHBOARD -->
	<div class="dashboard">
		<h2>Welcome Admin</h2>
		<div class="dashboard-content">

			<div class="card">
				<h3>Create Employee</h3>
				<p>Add a new employee to the SecureBank system.</p>
				<a href="CreateEmployee.jsp" class="btn">Create Employee</a>
			</div>

			<div class="card">
				<h3>Manage Employees</h3>
				<p>Update, delete or manage employee accounts.</p>
				<a href="${pageContext.request.contextPath}/ManageEmployeesServlet"
					class="btn">Manage Employees</a>
			</div>

			<div class="card">
				<h3>Manage Loan Apps</h3>
				<p>Review, approve or reject loan applications.</p>
				<a href="${pageContext.request.contextPath}/ViewPendingLoansServlet"
					class="btn">View Loans</a>
			</div>

			<!-- NEW: Fixed & Recurring Deposits Management -->
			<div class="card">
				<h3>Manage FD/RD Requests</h3>
				<p>Approve or reject fixed and recurring deposit requests.</p>
				<a href="${pageContext.request.contextPath}/ViewPendingFDServlet"
					class="btn">View FD/RD Requests</a>
			</div>

			<div class="card">
				<h3>Manage Grievances</h3>
				<p>View and update customer grievance tickets.</p>
				<a href="${pageContext.request.contextPath}/AdminGrievanceServlet"
					class="btn">Manage Grievances</a>
			</div>
			
			<div class="card">
  <h3>Manage Fund Transfers</h3>
  <p>Approve or reject internal transfers.</p>
				<a
					href="${pageContext.request.contextPath}/ViewPendingFundTransfersServlet"
					class="btn"> View Transfers </a>
			</div>

		</div>
	</div>

</body>
</html>
