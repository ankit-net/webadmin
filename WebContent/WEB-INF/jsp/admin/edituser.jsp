<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WebAdmin - Edit User </title>
</head>
<body>
	<form:form action="edituser.do" commandName="userobj" method="post">
		ID: <form:input path="id" cssStyle="display:none"/><br/>
		UserName:<form:input path="username"/><br/>
		Password:<form:input path="password"/><br/>
		GroupName:<form:select path="groupname">
						<form:option value="-1">Please Select</form:option>
						<form:option value="administrator">Administrator</form:option>
						<form:option value="manager">Manager</form:option>
						<form:option value="operator">Operator</form:option>
					</form:select>
	<br/>
	Email:<form:input path="email"/><br/>
	Mobile:<form:input path="mobile"/><br/>
	IsActive:
	
	
	
	
	Yes: <form:radiobutton path="isactive" value="1"/> 	 
	No: <form:radiobutton path="isactive" value="0"/>
	<br/>
	<input type="submit" value="Submit">
	</form:form>
</body>
</html>