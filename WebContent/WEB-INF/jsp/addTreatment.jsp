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
<form action="Controller" method="post" accept-charset="utf-8">
		<input type="hidden" name="command" value="add_treatment" />
		
		<fmt:message key="addTreatment.type" /><br/>
		<input type="text" name="type" value="" /><br/>
		<fmt:message key="addTreatment.description" /><br/>
		<input type="text" name="description" value="" /><br/>
		<button type="submit"><fmt:message key="addTreatment.submitTreatment"/></button>
	</form>

</body>
</html>