<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WebAdmin - Create State</title>
</head>
<body>
		<div align="center">Create A New State</div>
	<form:form action="createstate.do" commandName="state" method="post">
		State Name:<form:input path="name"/><br/>
		IsActive: 	Yes: <input type="radio" name="isactive" value="1">	 
	No: <input type="radio" name="isactive" value="0">

	<br/>
	<input type="submit" value="Submit">
	</form:form>
		
</body>
</html>