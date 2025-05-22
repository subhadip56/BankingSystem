<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Track Approval Status</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/Track_status.css">
</head>
<body>
	<div class="container">
		<h1>Track Your Approval Status</h1>

		<form action="${pageContext.request.contextPath}/TrackStatusServlet"
			method="post">
			<input type="text" name="referenceNumber"
				placeholder="Enter your Reference Number" required class="input-box">
			<button type="submit" class="submit-btn">Track Status</button>
		</form>

		<!-- Show Status if available -->
		<c:if test="${not empty status}">
			<div class="status-section">
				<h2>Status:</h2>
				<p class="status-message">${status}</p>
			</div>
		</c:if>

		<!-- Show Error if available -->
		<c:if test="${not empty error}">
			<p class="error-message">${error}</p>
		</c:if>
		<br>
		<h4>
			<a class="back-link"
				href="${pageContext.request.contextPath}/JSP/Home.jsp">← Back to
				Home Page</a>
		</h4>
	</div>
</body>
</html>