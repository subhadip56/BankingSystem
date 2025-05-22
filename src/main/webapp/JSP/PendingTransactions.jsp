<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Pending Transactions</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/pending-transactions.css">
</head>
<body>
	<h3>
		<a
			href="${pageContext.request.contextPath}/JSP/employee_dashboard.jsp">←
			Back to Dashboard</a>
	</h3>
	<h1>Pending Transaction Requests</h1>

	<table>
		<tr>
			<th>Type</th>
			<th>Account</th>
			<th>Customer</th>
			<th>Amount</th>
			<th>Action</th>
		</tr>
		<c:forEach var="r" items="${deposits}">
			<tr>
				<td>${r.type}</td>
				<td>${r.acct}</td>
				<td>${r.cid}</td>
				<td>₹${r.amt}</td>
				<td><a href="ApproveTransactionServlet?type=Deposit&id=${r.id}">Approve</a>
					| <a
					href="ApproveTransactionServlet?type=Deposit&id=${r.id}&reject=true">Reject</a>
				</td>
			</tr>
		</c:forEach>
		<c:forEach var="r" items="${withdrawals}">
			<tr>
				<td>${r.type}</td>
				<td>${r.acct}</td>
				<td>${r.cid}</td>
				<td>₹${r.amt}</td>
				<td><a
					href="ApproveTransactionServlet?type=Withdrawal&id=${r.id}">Approve</a>
					| <a
					href="ApproveTransactionServlet?type=Withdrawal&id=${r.id}&reject=true">Reject</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
