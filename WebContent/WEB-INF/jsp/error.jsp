<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>E R R O R</title>
</head>
<body>

Error message :  <%= request.getParameter("error") %>!


<form action="Controller" method="post">
		<input type="hidden" name="command" value="go_to_sign_in_page" />
		<input type="submit" value="go to sign in" /><br/>
</form>

</body>
</html>