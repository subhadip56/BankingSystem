<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Apply for FD / RD – SecureBank</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/FDRequest.css">
<script
	src="${pageContext.request.contextPath}/JAVA_SCRIPT/FDRequest.js" defer></script>
</head>
<body>
	<div class="card">
		<h2>Apply for Fixed / Recurring Deposit</h2>
		<c:if test="${not empty message}">
			<div class="msg">${message}</div>
		</c:if>
		<form id="fdForm" method="post"
			action="<c:url value='/FDRequestServlet'/>">
			<label for="type">Deposit Type:</label> <select id="type"
				name="depositType" required>
				<option value="">— select —</option>
				<option value="FD">Fixed Deposit</option>
				<option value="RD">Recurring Deposit</option>
			</select> <label for="amount">Amount (₹):</label> <input id="amount"
				name="amount" type="number" step="0.01" min="1" required /> <label
				for="tenure">Tenure (months):</label> <input id="tenure"
				name="tenure" type="number" min="1" required /> <label for="rate">Interest
				Rate (%):</label> <input id="rate" name="interestRate" type="text" readonly />

			<button type="submit">Submit Request</button>
		</form><br>
		<a class="back-link" href="${pageContext.request.contextPath}/JSP/CustomerDashboard.jsp"> ← Back to
			Dashboard </a>
	</div>
</body>
</html>
