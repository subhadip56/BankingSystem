<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Change Password – SecureBank</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/ChangePassword.css">
</head>
<body>
	<div class="card">
		<h2>Change Password</h2>

		<c:if test="${not empty error}">
			<div class="msg error">${error}</div>
		</c:if>
		<c:if test="${not empty message}">
			<div class="msg success">${message}</div>
		</c:if>

		<form method="post" action="<c:url value='/ChangePasswordServlet'/>">
			<label for="current">Current Password</label> <input id="current"
				name="currentPassword" type="password" required /> <label
				for="newpwd">New Password</label> <input id="newpwd"
				name="newPassword" type="password" required minlength="6" /> <label
				for="confirm">Confirm New Password</label> <input id="confirm"
				name="confirmPassword" type="password" required minlength="6" />

			<button type="submit">Update Password</button>
		</form>
		<br> <a class="back-link"
			href="${pageContext.request.contextPath}/JSP/CustomerDashboard.jsp">
			← Back to Dashboard </a>
	</div>
</body>
</html>