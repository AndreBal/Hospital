<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="resources.messages"/>

<html lang="${lang}">
<head>
<meta charset="utf-8">
<title><fmt:message key="reg.pageName" /></title>
<style><%@include file="/WEB-INF/style/main.css"%></style>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Domine&display=swap" rel="stylesheet">
</head>
<body>
<form action="Controller" method="post" accept-charset="utf-8">
		<input type="hidden" name="command" value="registration" />
		
		<fmt:message key="reg.name" /><br/>
		<input type="text" name="name" value="" /><br/>
		<fmt:message key="reg.surname" /><br/>
		<input type="text" name="surname" value="" /><br/>
		<fmt:message key="reg.email" /><br/>
		<input type="text" name="email" value="" /><br/>
		<fmt:message key="reg.role" /><br/>
		<input type="text" name="role" value="" /><br/>
		<fmt:message key="reg.login" /><br/>
		<input type="text" name="login" value="" /><br/>
		<fmt:message key="reg.password" /><br/>
		<input type="password" name="password" value="" /><br/>
		<button type="submit"><fmt:message key="reg.registrationButton"/></button>
	</form>
	<br/>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="go_to_sign_in_page" />
		<button type="submit"><fmt:message key="reg.sign_in"/></button>
	</form>
	
	<ul>
		<li><a href="?sessionLocale=en&command=go_to_registration_page"><fmt:message key="lang.en" /></a></li>
		<li><a href="?sessionLocale=ru&command=go_to_registration_page"><fmt:message key="lang.ru" /></a></li>
	</ul>
	<%@include file="footer.jsp"%>
</body>
</html>