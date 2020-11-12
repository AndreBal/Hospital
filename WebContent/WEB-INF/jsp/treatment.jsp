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
<title></title>
</head>
<body>
	<table>
		<c:forEach items="${treatmentList}" var="treatment">
			<tr>
				<td><c:out value="${treatment.type}" /></td>
				<td><c:out value="${treatment.description}" /></td>
				<td><c:out value="${treatment.appointer.surname}" /></td>
				<c:choose>
					<c:when test="${treatment.completed == true}">
						<td><c:out value="${treatment.performer.surname}" />
						<c:out value="${treatment.performedDate}" /></td>
					</c:when>
					<c:otherwise>
						<td><fmt:message key="treatment.notPerformed" /></td>
					</c:otherwise>
				</c:choose>

			</tr>
		</c:forEach>
	</table>
</body>
</html>