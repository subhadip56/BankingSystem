<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pending FD/RD Requests – Admin</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/AdminFD.css">
</head>
<body>
	<c:if test="${not empty param.message}">
		<div class="msg">${param.message}</div>
	</c:if>
	<div class="card">
		<h2>Pending FD/RD Requests</h2>
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Customer</th>
					<th>Type</th>
					<th>Amt</th>
					<th>Tenure</th>
					<th>Rate</th>
					<th>Applied</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="r" items="${pendingFD}">
					<tr>
						<td>${r.requestId}</td>
						<td>${r.customerId}</td>
						<td>${r.depositType}</td>
						<td>₹${r.amount}</td>
						<td>${r.tenureMonths}</td>
						<td>${r.interestRate}%</td>
						<td>${r.createdAt}</td>
						<td>
							<form method="post"
								action="<c:url value='/UpdateFDStatusServlet'/>"
								style="display: inline">
								<input type="hidden" name="requestId" value="${r.requestId}" />
								<button name="action" value="Approved">Approve</button>
								<button name="action" value="Rejected">Reject</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>