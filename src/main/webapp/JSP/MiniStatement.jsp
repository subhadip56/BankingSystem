<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mini Statement</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/MiniStatement.css">
</head>
<body>
	<h3>
		<a class="back-link"
			href="${pageContext.request.contextPath}/JSP/CustomerDashboard.jsp">
			← Back to Dashboard </a>
	</h3>
	<h1>Last 10 Transactions</h1>
	<table class="ms-table">
		<tr>
			<th>Date</th>
			<th>Description</th>
			<th>Type</th>
			<th>Amount</th>
		</tr>
		<c:forEach var="tx" items="${transactions}">
			<tr>
				<td>${tx.date}</td>
				<td>${tx.description}</td>
				<td>${tx.type}</td>
				<td>₹${tx.amount}</td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>