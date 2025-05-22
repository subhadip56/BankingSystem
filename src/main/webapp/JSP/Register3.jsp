<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SecureBank - Account Details</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Register3.css">
</head>
<body>
	<h1>SecureBank</h1>
	<div class="register-container">
		<form action="<c:url value='RegisterStep3'/>" method="post" class="register3-form">
			<h2>Page 3: Account Details</h2>
			<c:if test="${not empty error}">
				<p class="error-message">${error}</p>
			</c:if>
			<div class="form-group">
				<label>Account Type:</label>
				<div class="radio-group">
					<div class="radio-row">
						<label><input type="radio" name="accountType"
							value="Savings Account" required> Savings Account</label> <label><input
							type="radio" name="accountType" value="Current Account">
							Current Account</label>
					</div>
					<div class="radio-row">
						<label><input type="radio" name="accountType"
							value="Fixed Deposit Account"> Fixed Deposit Account</label> <label><input
							type="radio" name="accountType" value="Recurring Deposit Account">
							Recurring Deposit Account</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label>Services Required:</label>
				<div class="checkbox-group">
					<div class="checkbox-row">
						<label><input type="checkbox" name="services"
							value="ATM Card"> ATM Card</label> <label><input
							type="checkbox" name="services" value="Internet Banking">
							Internet Banking</label>
					</div>
					<div class="checkbox-row">
						<label><input type="checkbox" name="services"
							value="Mobile Banking"> Mobile Banking</label> <label><input
							type="checkbox" name="services" value="Email & SMS Alerts">
							Email & SMS Alerts</label>
					</div>
					<div class="checkbox-row">
						<label><input type="checkbox" name="services"
							value="Cheque Book"> Cheque Book</label> <label><input
							type="checkbox" name="services" value="E-Statement">
							E-Statement</label>
					</div>
				</div>
			</div>
			<button type="submit" class="form-button">Submit</button>
		</form>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>