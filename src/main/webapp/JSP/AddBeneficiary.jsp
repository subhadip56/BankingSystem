<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Beneficiary – SecureBank</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/AddBeneficiary.css">
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
        <h2>Add Beneficiary</h2>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
        <form action="${pageContext.request.contextPath}/AddBeneficiaryServlet" method="post">
            <label for="beneficiaryCustomerId">Select Customer to Add as Beneficiary:</label>
            <select name="beneficiaryCustomerId" id="beneficiaryCustomerId" required>
                <c:forEach var="customer" items="${customers}">
                    <option value="${customer}">${customer}</option>
                </c:forEach>
            </select><br><br>
            <button type="submit">Add Beneficiary</button>
        </form>
        <a href="${pageContext.request.contextPath}/JSP/CustomerDashboard.jsp">← Back to Dashboard</a>
    </div>
</body>
</html>