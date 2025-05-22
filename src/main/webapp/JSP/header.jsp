<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<header class="header">
	<div class="header-content">
		<h1 class="logo">SecureBank</h1>
		<nav class="nav-links">
			<c:choose>
				<c:when test="${not empty sessionScope.user}">
					<span>Welcome, ${sessionScope.user.fullName}</span>
					<a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/Login.jsp">Customer
						Login</a>
					<a href="${pageContext.request.contextPath}/Register1.jsp">Customer
						Register</a>
				</c:otherwise>
			</c:choose>
		</nav>
	</div>
</header>
