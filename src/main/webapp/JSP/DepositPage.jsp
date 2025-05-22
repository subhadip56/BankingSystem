<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Deposit Funds</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/deposit_withdraw.css">
</head>
<body>
	<div class="form-container">
		<h1>Deposit Balance</h1>
		<form action="<c:url value='SubmitDepositServlet'/>" method="post">
			<label for="accountNumber">Account:</label> <select
				id="accountNumber" name="accountNumber" required>
				<option value="" disabled selected>-- Select Account --</option>
				<c:forEach var="acct" items="${accounts}">
					<option value="${acct.accountNumber}">${acct.accountType}
						– ${acct.accountNumber}</option>
				</c:forEach>
			</select> <label for="amount">Amount (₹):</label> <input id="amount"
				type="number" name="amount" min="1" step="0.01" required />

			<button type="submit">Submit Deposit Request</button>
		</form>
		<br>
		<h4>
			<a class="back"
				href="${pageContext.request.contextPath}/JSP/CustomerDashboard.jsp">
				← Back to Dashboard </a>
		</h4>

		<c:if test="${not empty message}">
			<div class="info">${message}</div>
		</c:if>
	</div>
</body>
</html>
