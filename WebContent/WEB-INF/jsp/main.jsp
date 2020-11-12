<%@ page language="java" import="by.htp.ts.bean.User" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="resources.messages"/>

<html lang="${lang}">
<head>
<meta charset="utf-8">
<title><fmt:message key="main.pageName" /></title>
<style><%@include file="/WEB-INF/style/main.css"%></style>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Domine&display=swap" rel="stylesheet">

</head>
<body>
<c:if test="${user == null}">
   <c:redirect url="error.jsp?error=You need to sign in to access this page"/>	
	</c:if>
 <fmt:message key="main.greetings" />
 
   <% User user = (User) session.getAttribute("user");
   out.println(user.getName());%>
  
	<br/>
	<br/>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="retrieve_medical_history" />
		<fmt:message key="main.historyNumber" /><br/>
		<input type="text" name="historyNumber" value="" /><br/>
		
		<button type="submit"><fmt:message key="main.submit"/></button>
		</form>
	
	
	
	<ul>
		<li><a href="?sessionLocale=en&command=go_to_main_page"><fmt:message key="lang.en" /></a></li>
		<li><a href="?sessionLocale=ru&command=go_to_main_page"><fmt:message key="lang.ru" /></a></li>
	</ul>
	 <%@include file="footer.jsp"%>
</body>
</html>