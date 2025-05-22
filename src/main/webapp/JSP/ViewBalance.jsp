<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account Balance</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/ViewBalance.css">
</head>
<body>
	<h3>
		<a class="back-link"
			href="${pageContext.request.contextPath}/JSP/CustomerDashboard.jsp">
			← Back to Dashboard </a>
	</h3>
	<h1>Your Accounts</h1>
	<div class="balance-list">
		<c:forEach var="acct" items="${accounts}">
			<div class="acct-card">
				<h2>${acct.accountType}</h2>
				<p>
					<strong>Account No:</strong> ${acct.accountNumber}
				</p>
				<p>
					<strong>Balance:</strong> ₹${acct.balance}
				</p>
			</div>
		</c:forEach>
	</div>
</body>
</html>