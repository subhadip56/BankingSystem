<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Loan Status – SecureBank</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/LoanStatus.css">
</head>
<body>
	<div class="container">
		<div class="card">
			<h2>My Loan Applications</h2>
			<table class="loan-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Type</th>
						<th>Amount</th>
						<th>Tenure</th>
						<th>Status</th>
						<th>Applied On</th>
						<th>Decision Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="l" items="${loans}">
						<tr>
							<td>${l.id}</td>
							<td>${l.type}</td>
							<td>₹${l.amount}</td>
							<td>${l.tenure}mo</td>
							<td>${l.status}</td>
							<td>${l.applied}</td>
							<td><c:out value="${l.decided != null ? l.decided : '-'}" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a class="back-link"
				href="${pageContext.request.contextPath}/JSP/CustomerDashboard.jsp">
				← Back to Dashboard </a>
		</div>
	</div>
</body>
</html>
