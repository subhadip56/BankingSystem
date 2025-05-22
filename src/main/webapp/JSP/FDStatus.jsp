<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My FD/RD Status</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/FDStatus.css">
</head>
<body>
	<div class="card">
		<h2>My FD/RD Requests</h2>
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Type</th>
					<th>Amount</th>
					<th>Tenure</th>
					<th>Rate</th>
					<th>Status</th>
					<th>Applied On</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="r" items="${fdList}">
					<tr>
						<td data-label="ID">${r.requestId}</td>
						<td data-label="Type">${r.depositType}</td>
						<td data-label="Amount">₹${r.amount}</td>
						<td data-label="Tenure">${r.tenureMonths}mo</td>
						<td data-label="Rate">${r.interestRate}%</td>
						<td data-label="Status" data-status="${r.status.toLowerCase()}">${r.status}</td>
						<td data-label="Applied On">${r.createdAt}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table><br>
		<h4>
			<a class="back-link"
				href="${pageContext.request.contextPath}/JSP/CustomerDashboard.jsp">
				← Back to Dashboard </a>
		</h4>
	</div>
</body>
</html>
