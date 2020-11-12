<%@ page language="java" import="by.htp.ts.bean.History"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="resources.messages" />

<html lang="${lang}">
<head>
<meta charset="utf-8">
<title><fmt:message key="medicalHistory.pageName" /></title>
<style>
<%@includefile="/WEB-INF/style/main.css"%>
</style>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Domine&display=swap"
	rel="stylesheet">

</head>
<body>
	<c:if test="${user == null}">
		<c:redirect url="Controller?command=go_to_sign_in_page" />
	</c:if>
	<c:if test="${history == null}">
		<c:redirect url="Controller?command=go_to_main_page" />
	</c:if>
	<%
		History his = (History) request.getAttribute("history");
	%>

	<fmt:message key="medicalHistory.historyNumber" /><br />
	<%out.println(his.getNumber());%><br />
	<fmt:message key="medicalHistory.provisionalDiagnosis" /><br />
	<%out.println(his.getProvisionalDiagnosis());%><br />
	<fmt:message key="medicalHistory.currentDiagnosis" /><br />
	<%out.println(his.getCurrentDiagnosis());%><br />
	<fmt:message key="medicalHistory.finalDiagnosis" /><br />
	<%out.println(his.getFinalDiagnosis());%><br />

	<table>
		<tr>
			<th><fmt:message key="medicalHistory.type" /></th>
			<th><fmt:message key="medicalHistory.description" /></th>
			<th><fmt:message key="medicalHistory.appointerSurname" /></th>
			<th><fmt:message key="medicalHistory.perform" /></th>
		</tr>
		<c:forEach items="${treatmentList}" var="treatment">
			<tr>
				<td><c:out value="${treatment.type}" /></td>
				<td><c:out value="${treatment.description}" /></td>
				<td><c:out value="${treatment.appointer.surname}" /></td>
				<c:choose>
					<c:when test="${treatment.completed == true}">
						<td><c:out value="${treatment.performer.surname}" /> <c:out
								value="${treatment.performedDate}" /></td>
					</c:when>
					<c:otherwise>
						<td><fmt:message key="treatment.notPerformed" /></td>
					</c:otherwise>
				</c:choose>

			</tr>
		</c:forEach>
	</table>

	<c:if test="${user.role == 'doctor'}">
		<%@include file="addTreatment.jsp"%>
	</c:if>

	<%@include file="footer.jsp"%>
</body>
</html>