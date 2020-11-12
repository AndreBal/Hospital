<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="resources.messages" />
<html>
<head>
<meta charset="utf-8">
<title>This is footer</title>
</head>
<body>
	<br />
	<c:if test="${user != null}">
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="log_out" />
			<button type="submit">
				<fmt:message key="footer.logOut" />
			</button>
			<br />
		</form>
	</c:if>
</body>
</html>