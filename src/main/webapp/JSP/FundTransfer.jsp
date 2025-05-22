<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fund Transfer – SecureBank</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/FundTransfer.css">
</head>
<body>
    <header class="site-header">
        <div class="header-inner">
            <h1>SecureBank</h1>
        </div>
    </header>
    <header class="dash-header">
        <nav class="dash-nav">
            <span>Welcome, <strong>${sessionScope.customerName}</strong></span>
            <a href="${pageContext.request.contextPath}/LogoutServlet" class="logout-btn">Logout</a>
        </nav>
    </header>
    <div class="dash-container">
        <h2>Fund Transfer</h2>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
        <c:choose>
            <c:when test="${empty beneficiaries}">
                <p>You have no beneficiaries added. Please add a beneficiary first.</p>
                <a href="${pageContext.request.contextPath}/AddBeneficiaryServlet">Add Beneficiary</a>
            </c:when>
            <c:otherwise>
                <form action="${pageContext.request.contextPath}/FundTransferServlet" method="post">
                    <label for="senderAccount">From Account:</label>
                    <select name="senderAccount" id="senderAccount" required>
                        <c:forEach var="account" items="${accounts}">
                            <option value="${account}">${account}</option>
                        </c:forEach>
                    </select><br><br>

                    <label for="beneficiaryCustomerId">Beneficiary:</label>
                    <select name="beneficiaryCustomerId" id="beneficiaryCustomerId" required>
                        <c:forEach var="beneficiary" items="${beneficiaries}">
                            <option value="${beneficiary}">${beneficiary}</option>
                        </c:forEach>
                    </select><br><br>

                    <label for="amount">Amount:</label>
                    <input type="number" name="amount" id="amount" step="0.01" min="0.01" required><br><br>

                    <label for="description">Description (Optional):</label>
                    <input type="text" name="description" id="description"><br><br>

                    <button type="submit">Submit Request</button>
                </form>
            </c:otherwise>
        </c:choose>
        <a href="${pageContext.request.contextPath}/JSP/CustomerDashboard.jsp">← Back to Dashboard</a>
    </div>
</body>
</html>