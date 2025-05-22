<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Waiting for Approval</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/waiting_page.css">
</head>
<body>
	<div class="container">
		<h1>Registration Submitted!</h1>

		<c:if test="${not empty referenceNumber}">
			<p class="message">
				Your Reference Number: <strong class="ref-number">${referenceNumber}</strong>
			</p>
			<p class="instruction">Please use this to track your approval
				status.</p>
			<a class="track-link"
				href="${pageContext.request.contextPath}/JSP/TrackStatus.jsp">Click
				here to Track Status</a>
		</c:if>

		<c:if test="${empty referenceNumber}">
			<p class="error">Reference number not found. Please try again.</p>
		</c:if>
	</div>
</body>
</html>