<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    
<!DOCTYPE html>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="resources.messages"/>

<html lang="${lang}">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title><fmt:message key="sign.pageName" /></title>
<!-- CSS -->
<style><%@include file="/WEB-INF/style/main.css"%></style>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Domine&display=swap" rel="stylesheet">

<!-- JS-->

</head>
<body>
<c:if test="${user != null}">
  <c:redirect url="Controller?command=go_to_main_page" />
	</c:if>
<div class='container'>
<form action="Controller" method="post">

		<input type="hidden" name="command" value="authorization"/>
		<fmt:message key="sign.login" /><br/>
		
		
		<input type="text" name="login" value="" placeholder="Another input placeholder" /><br/>
	
		<fmt:message key="sign.password" /><br/>
	
		
		<input type="password" name="password" value="" /><br/>
		
		<button type="submit" ><fmt:message key="sign.sign_in"/></button><br/>
	</form>
	
	
	<br/>

	<br/>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="go_to_registration_page" />
		<button type="submit"><fmt:message key="sign.registration"/></button><br/>
	</form>
	
	
	<ul>
		<li><a href="?sessionLocale=en&command=go_to_sign_in_page"><fmt:message key="lang.en" /></a></li>
		<li><a href="?sessionLocale=ru&command=go_to_sign_in_page"><fmt:message key="lang.ru" /></a></li>
	</ul>
	<%@include file="footer.jsp"%>
	</div>
</body>
</html>