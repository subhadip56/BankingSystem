<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pending Fund Transfers – SecureBank</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/pendingFundTransfers.css">
</head>
<body>
    <div class="header">
        <h1>SecureBank</h1>
        <div class="nav-links">
            <a href="${pageContext.request.contextPath}/JSP/admin_dashboard.jsp">Dashboard</a>
            <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>
        </div>
    </div>
    <div class="dashboard">
        <h2>Pending Fund Transfers</h2>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
        <table border="1">
            <tr>
                <th>Request ID</th>
                <th>Sender</th>
                <th>Sender Account</th>
                <th>Beneficiary</th>
                <th>Amount</th>
                <th>Description</th>
                <th>Created At</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="transfer" items="${transfers}">
                <tr>
                    <td>${transfer.requestId}</td>
                    <td>${transfer.senderName} (${transfer.senderCustomerId})</td>
                    <td>${transfer.senderAccountNumber}</td>
                    <td>${transfer.beneficiaryName} (${transfer.beneficiaryCustomerId})</td>
                    <td>${transfer.amount}</td>
                    <td>${transfer.description}</td>
                    <td>${transfer.createdAt}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/ProcessFundTransferServlet" method="post" style="display:inline;">
                            <input type="hidden" name="requestId" value="${transfer.requestId}">
                            <input type="hidden" name="action" value="approve">
                            <button type="submit">Approve</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/ProcessFundTransferServlet" method="post" style="display:inline;">
                            <input type="hidden" name="requestId" value="${transfer.requestId}">
                            <input type="hidden" name="action" value="reject">
                            <textarea name="comment" placeholder="Reason for rejection" rows="2" cols="20"></textarea>
                            <button type="submit">Reject</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>