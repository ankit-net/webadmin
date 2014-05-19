<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<p align="center"><b>Kindly enter your login details</b></p>
	<br/><br/>

	<form:form commandName="loginobj"  method="post">
		
		<div align="center" ><b>${message}</b></div>
		<table bgcolor="pink" border="1" align="center">
			<tr>
				<td>UserName</td>
				<td><form:input path="username"/></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><form:password path="password"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="Submit"/>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>