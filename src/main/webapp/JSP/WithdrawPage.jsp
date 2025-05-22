<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Withdraw Funds – SecureBank</title>
<!-- Link to external CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/withdraw_funds.css">
</head>
<body>
	<div class="container">
		<div class="card">
			<h2>Withdraw Funds</h2>

			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty message}">
				<div class="msg">${message}</div>
			</c:if>

			<form action="<c:url value='WithdrawServlet'/>" method="post">
				<label for="acct">Choose Account:</label> <select
					name="accountNumber" id="acct" required>
					<option value="">-- select an account --</option>
					<c:forEach var="a" items="${accounts}">
						<option value="${a.accountNumber}">
							${a.accountType} – ${a.accountNumber}</option>
					</c:forEach>
				</select> <label for="amt">Amount to withdraw:</label> <input type="number"
					step="0.01" name="amount" id="amt" required />

				<button type="submit">Request Withdrawal</button>
			</form>

			<a class="back"
				href="${pageContext.request.contextPath}/JSP/CustomerDashboard.jsp">
				← Back to Dashboard </a>
		</div>
	</div>
</body>
</html>