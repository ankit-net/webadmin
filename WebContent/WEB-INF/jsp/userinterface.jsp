<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="<%=request.getContextPath()%>/js/jquery-1.10.2.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/admintabs.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/admintabs.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/institutedetails.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>WebAdmin - User Interface Page</title>
</head>
<body>
	
	
	
<br/>
	Total Institutes Found: <c:out value="${count}"/> <br/>
	
	<table border="1">
		<tr bgcolor="pink">
			<th>ID</th>
			<th>Institute Name</th>
			<th>Institute Type</th>
			<th>Location</th>
			<th>Created By</th>
			<th>Created Date</th>
		</tr>
		<c:forEach items="${institutes}" var="inst">
			<tr>
				<td>${inst.institute_id}</td>
				<td>${inst.institute_name}</td>
				<td>${inst.institute_type}</td>
				<td>${inst.country},&nbsp;${inst.state},&nbsp;${inst.city}</td>
				<td>${inst.createdby}</td>
				<td>${inst.created_date}</td>
				<td><button id="${inst.institute_id}">Click Here</button></td>
			</tr>
			<tr style="display:none" id="div${inst.institute_id}">
				<td colspan="10">
					Ankit${inst.institute_id}					
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
