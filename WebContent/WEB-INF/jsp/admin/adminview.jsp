<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<html>
<head>
	<script src="<%=request.getContextPath()%>/js/jquery-1.10.2.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/admintabs.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/admintabs.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/cities.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/pagination.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>WebAdmin - Administrator Main Page</title>
</head>
<body>
	<div align="center">Administrator Main Page</div>
	<p align="right">Welcome, ${myuser} </p>
	
	<ul class="tabs">
		<li><a href="#tab1">Users</a></li>
		<li><a href="#tab2">States</a></li>
		<li><a href="#tab3">Cities</a></li>
	</ul>
	<div  class="tabContainer">
		<div id="tab1" class="tabContent">
			<p align="center"><b>Users list</b></p>
			<p align="right">
				<a href="<%=request.getContextPath()%>/createuser.do">Create User</a>
			</p>
			<p align="center">
				Total Records Found:${countusers}
			</p>
			<div id="dynamicusers">
			<table border="1" align="center">
				<tr bgcolor="pink">	
					<th>ID</th>
					<th>UserName</th>
					<th>Password</th>
					<th>GroupName</th>
					<th>Email</th>
					<th>Mobile</th>
					<th>IsActive</th>
					<th>Edit</th>
				</tr>	
			
			<c:forEach var="user" items="${users}" varStatus="loopcounter" >
				<tr>
					<td><c:out value="${user.id}"/></td>
					<td><c:out value="${user.username}"/></td>
					<td><c:out value="${user.password}"/></td>
					<td><c:out value="${user.groupname}"/></td>
					<td><c:out value="${user.email}"/></td>
					<td><c:out value="${user.mobile}"/></td>
					<td><c:out value="${user.isactive}"/></td>
					<td><a href="<%=request.getContextPath()%>/edituser/<c:out value="${user.id}"/>.do"><img width="18px" alt="Edit" src="<%=request.getContextPath()%>/images/edit.png"></a></td>
				</tr>
			</c:forEach>
			</table>
			</div>
			<div id="paggination">
  <table >
  	
  <tr>
  <c:forEach var="userpages" items="${totalPageusers}">
  <td align="center" colspan="4">
  	
	<c:choose>
      <c:when test="${currentpage==userpages}">
      <b style="color: red">
      <c:out value="${userpages} "></c:out>
      | </b>
     </c:when>

      <c:otherwise>
      
      <b>									
      <a  href="javascript:getResultofPage('<c:out value="${userpages} "></c:out>');">
      <c:out value="${userpages} "></c:out>
      </a> | </b>
      </c:otherwise>
</c:choose>
   
 </td>
  
  </c:forEach>
  
  </tr></table>
  </div>
					
		</div>
		<div id="tab2" class="tabContent">
			<p align="center">States List </p>
			<p align="right">
				<a href="<%=request.getContextPath()%>/createstate.do">Create State</a>
			</p>
			<table align="center" border="1">
				<tr bgcolor="pink">	
					<th>Sno</th>
					<th>ID</th>
					<th>Name</th>
					<th>IsActive</th>
					<th>CreatedDate</th>
					<th>Edit</th>
				</tr>
				<c:forEach var="state" items="${states}" varStatus="loopcounter">
					<tr>	
						<td>${loopcounter.count}</td>
						<td><c:out value="${state.id}"/></td>
						<td><c:out value="${state.name}"/></td>
						<td><c:out value="${state.isactive}"/></td>
						<td><c:out value="${state.createddate}"/></td>
						<td><a href="<%=request.getContextPath()%>/editstate/<c:out value="${state.id}"/>.do"><img width="18px" alt="Edit" src="<%=request.getContextPath()%>/images/edit.png"></a></td>
					</tr>	
				
				</c:forEach>
			</table>									
		</div>
		<div id="tab3" class="tabContent">
			<input id="cpcity" type="text" style="display: none;" value="1"/>
			<p align="center">Cities List</p>
			<p align="center">
			<select name="states" id="states">
				<option value="-1">Select a State</option>
				<c:forEach var="state" items="${states}">
					<option value="${state.id}">${state.name}</option>
				</c:forEach>
			</select>
			</p>
			<br/><br/>
			
			<div id="citieslist">
					
						
						
				
			</div>
			<div id="citypaggination">
			
			
			</div>
		</div>
		<div id="ajax_loader" align="center" style="display: none;"> <img alt="ajax-loader" src="<%=request.getContextPath() %>/images/ajax-loader.gif" style="padding-left:200px;" height="80px;" width="80px;" /> </div>
	</div>
	
</body>
</html>