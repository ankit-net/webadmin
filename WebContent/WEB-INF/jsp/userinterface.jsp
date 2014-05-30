<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="<%=request.getContextPath()%>/js/jquery-1.10.2.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/admintabs.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/admintabs.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/institutedetails.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/filter.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>WebAdmin - User Interface Page</title>
</head>
<body>
		
	<table width="100%" border="0" align="center">
		<tr>
			<td>MainCategory: <select id="maincat">
					<option value="-1">--Please Select--</option>
					<c:forEach items="${maincategories}" var="cat">
						<option value="${cat.id}"><c:out value="${cat.categoryname}" />
						</option>
					</c:forEach>

			</select>
			</td>
			<td>ChildCategory:
				<div id="childcatloader" style="display: none">
					<img src="<%=request.getContextPath()%>/images/ajax-loader.gif">
				</div> <select id="childcat" multiple="multiple">
					<option selected="selected" value="-1">--Please Select--</option>
			</select>
			</td>

			<td>State <select id="states">
					<option value="-1">--Please Select--</option>
					<c:forEach items="${statelist}" var="state">
						<option value="${state.id}"><c:out
								value="${state.name}" /></option>
					</c:forEach>

			</select>

			</td>

			<td>City
				<div id="citiesloader" style="display: none">
					<img src="<%=request.getContextPath()%>/images/ajax-loader.gif">
				</div> <select id="cities" multiple="multiple">
					<option value="-1" selected="selected">--Please Select--</option>
					<!-- <option value="5">New Delhi</option>
						<option value="6">Patna</option>-->
			</select>
			</td>

		</tr>
		<tr>
			<td colspan="4" align="center"><input class="filterbutton" type="button" value="Submit" /></td>
		</tr>
	</table>
	

	
	<div id="alldetails" style="display:none"> 
		<ul class='tabs'>
			<li><a href='#tab1'>Courses</a></li>
			<li><a href='#tab2'>Departments</a></li>
		</ul>
		<div class='tabContainer'>
			<div id='tab1' class='tabContent'>
				<b>Courses</b>
				<p id="coursesid"></p>
			</div>
			<div id='tab2' class='tabContent'>
				<b>Departments</b>
				<p id="departmentid"></p>
			</div>
		</div>
	</div>
	<br/>
	<br/>
	<table border="1">
		<tr>
			<td align="center"  colspan="7">Total Institutes Found: <c:out value="${count}"/> </td>
		</tr>
		<tr bgcolor="pink">
			<th>ID</th>
			<th>Institute Name</th>
			<th>Institute Type</th>
			<th>Location</th>
			<th>Created By</th>
			<th>Created Date</th>
			<th>Expand</th>
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
					
		</c:forEach>
		
		
	</table>
	

</body>
</html>
