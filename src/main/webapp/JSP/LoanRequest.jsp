<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	import="com.fasterxml.jackson.databind.ObjectMapper, com.fasterxml.jackson.core.JsonProcessingException"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Apply for Loan – SecureBank</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/LoanRequest.css" />
<script
	src="${pageContext.request.contextPath}/JAVA_SCRIPT/LoanRequest.js"
	defer></script>
</head>
<body>
	<div class="card">
		<h2>Apply for Loan</h2>

		<!-- Error / success messages -->
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty message}">
			<div class="msg">${message}</div>
		</c:if>

		<%
		// Convert the List<Rate> to JSON for your JS
		Object ratesObj = request.getAttribute("rates");
		ObjectMapper mapper = new ObjectMapper();
		String ratesJson;
		try {
			ratesJson = mapper.writeValueAsString(ratesObj);
		} catch (JsonProcessingException e) {
			ratesJson = "[]";
		}
		%>
		<script id="loanRatesData" type="application/json">
      <%=ratesJson%>
    </script>

		<form method="post" action="<c:url value='ApplyLoanServlet'/>">
			<label for="acct">From Account:</label> <select id="acct"
				name="accountNumber">
				<c:forEach var="a" items="${accounts}">
					<option value="${a.accountNumber}">${a.accountNumber}
						(${a.accountType})</option>
				</c:forEach>
			</select> <label for="type">Loan Type:</label> <select id="type"
				name="loanType" required>
				<option value="">— select —</option>
				<c:forEach var="r" items="${rates}">
					<option value="${r.type}">${r.type} @ ${r.rate}%</option>
				</c:forEach>
			</select> <label for="amount">Amount (₹):</label> <input id="amount"
				name="amount" type="number" step="0.01" required /> <label
				for="tenure">Tenure (months):</label> <input id="tenure"
				name="tenure" type="number" required />

			<button type="submit">Submit Application</button>
		</form><br>
		<h4>
		<a class="back"
			href="${pageContext.request.contextPath}/JSP/CustomerDashboard.jsp">
			← Back to Dashboard </a></h4>
	</div>
</body>
</html>