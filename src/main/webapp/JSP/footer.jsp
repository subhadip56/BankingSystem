<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<footer class="footer">
	<div class="footer-content">
		<p>
			&copy;
			<fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy" />
			SecureBank. All rights reserved.
		</p>
		<p>
			Contact: <a href="mailto:support@securebank.com">support@securebank.com</a>
			| Phone: (123) 456-7890
		</p>
	</div>
</footer>
