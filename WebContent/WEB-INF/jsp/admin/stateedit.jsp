<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WebAdmin - Edit State</title>
</head>
<body>
	<form:form commandName="bean" method="post">
		ID:<form:input path="id" cssStyle="display:none"/><br/>
		State Name:<form:input path="name"/><br/>
		IsActive:Yes: <form:radiobutton path="isactive" value="1"/> 	 
	No: <form:radiobutton path="isactive" value="0"/><br/>
		<input type="submit" value="Submit">
	
	</form:form>
</body>
</html>