<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WebAdmin - Create City</title>
</head>
<body>	
	<p align="center">Create New City</p>

	<form:form method="post" commandName="city">
		State Name: &nbsp;
		<form:select path="state_id">
			<option value="-1">Please Select</option>
			<c:forEach var="mystate" items="${states}">
								
				<form:option value="${mystate.id}">${mystate.name}</form:option>								
			</c:forEach>
			
		</form:select>
		<br/>
		City 

	</form:form>
	
		
</body>
</html>