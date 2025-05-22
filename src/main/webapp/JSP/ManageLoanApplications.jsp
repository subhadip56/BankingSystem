<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Pending Loan Apps – SecureBank</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/PendingLoan.css">
</head>
<body>
	<div class="container">
		<div class="card">
			<h2>Pending Loan Applications</h2>
			<table class="loan-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Customer</th>
						<th>Type</th>
						<th>Amount</th>
						<th>Tenure</th>
						<th>Applied On</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="l" items="${pendings}">
						<tr>
							<td>${l.id}</td>
							<td>${l.cust}</td>
							<td>${l.type}</td>
							<td>₹${l.amount}</td>
							<td>${l.tenure}m</td>
							<td>${l.appliedOn}</td>
							<td class="actions">
								<form method="post" action=<c:url value='ApproveLoanServlet'/>>
									<input type="hidden" name="id" value="${l.id}" />
									<button type="submit" class="btn approve">Approve</button>
								</form>
								<form method="post" action="<c:url value='ApproveLoanServlet'/>">
									<input type="hidden" name="id" value="${l.id}" /> <input
										type="hidden" name="reject" value="1" />
									<button type="submit" class="btn reject">Reject</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a class="back-link"
				href="${pageContext.request.contextPath}/JSP/admin_dashboard.jsp">←
				Back to Admin Dashboard</a>
		</div>
	</div>
</body>
</html>