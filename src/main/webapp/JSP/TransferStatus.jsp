<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transfer Status – SecureBank</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/TransferStatus.css">
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
        <h2>Transfer Status</h2>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
        <table border="1">
            <tr>
                <th>Request ID</th>
                <th>Beneficiary</th>
                <th>Amount</th>
                <th>Status</th>
                <th>Admin Comments</th>
            </tr>
            <c:forEach var="request" items="${transferRequests}">
                <tr>
                    <td>${request.requestId}</td>
                    <td>${request.beneficiaryName} (${request.beneficiaryCustomerId})</td>
                    <td>${request.amount}</td>
                    <td>${request.status}</td>
                    <td>${request.adminComments}</td>
                </tr>
            </c:forEach>
        </table>
        <a href="${pageContext.request.contextPath}/JSP/CustomerDashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>