<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Pending Customers - SecureBank</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/manage_customers.css">
<script src="manage_customers.js" defer></script>
</head>
<body>
	<div class="header">
		<h1>SecureBank</h1>
		<div class="nav-links">
			<a
				href="${pageContext.request.contextPath}/JSP/employee_dashboard.jsp">Dashboard</a>
			<a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>
		</div>
	</div>
	<div class="content">
		<h2>Pending Customer Registrations</h2>
		<c:choose>
			<c:when test="${empty pendingList}">
				<p>No pending registrations.</p>
			</c:when>
			<c:otherwise>
				<table>
					<thead>
						<tr>
							<th>Reference No.</th>
							<th>Name</th>
							<th>Email</th>
							<th>Account Type</th>
							<th>Services</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="pc" items="${pendingList}">
							<tr>
								<td>${pc.referenceNumber}</td>
								<td>${pc.name}</td>
								<td>${pc.email}</td>
								<td>${pc.accountType}</td>
								<td>${pc.services}</td>
								<td>
									<form class="action-form"
										action="<c:url value='ProcessCustomerServlet'/>" method="post">
										<input type="hidden" name="referenceNumber"
											value="${pc.referenceNumber}" />
										<button type="submit" name="action" value="approve"
											class="approve-btn">Approve</button>
										<button type="submit" name="action" value="reject"
											class="reject-btn">Reject</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>