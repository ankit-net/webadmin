<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/WebAdmin/js/tinymce/tinymce.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/institutecities.js"></script>
<script type="text/javascript">
tinymce.init({
    selector: "textarea"
 });
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Form for Adding Institute</title>
</head>
<body>
	<h2 align="center">Add Institute Form</h2>
	<form action="<%=request.getContextPath()%>/addinstitute.do" method="post">
	User Type:	<c:forEach var="ut"  items="${usertypes}">
					<input type="radio" name="usertype" value="${ut.id}"/><c:out value="${ut.name}"/>
	
				</c:forEach>
				<br/>
	EmailID: <input type="text" name="email"/> <br/>
	
	Institute Name:<input type="text" name="name"> <br/>
	
	Josh Rating: <input type="radio" value="Gold" name="rating"> Gold <input type="radio" value="Silver" name="rating"> Silver <input type="radio" value="Bronze" name="rating"> Bronze

	<br/>
		
	Primary Number: <input type="text" name="phone"><br/>
	
	Institute Type: <c:forEach var="insttype" items="${insttypes}">
						<input type="radio" name="institutetype" id="institutetype" value="${insttype.id}"><c:out value="${insttype.name}"/><br/>
					</c:forEach>
					<br/>
					
	Year of Formation : <select name="year" >
								 <c:forEach var="year" items="${totalyears}">
									<option value="${year}"><c:out value="${year}"/></option>
								</c:forEach>					
						</select>
						<br/>
						
	About the Institute: <textarea rows="20" cols="100"  id="about" name="about"></textarea>
	<br/>
	
	State: <select name="allstates" id="allstates">
				<option value="-1">--Please Select--</option>
				<c:forEach var="state" items="${states}">
					<option value="${state.id}">${state.name}</option>					
				</c:forEach>
			</select>
			
	<br/>
	
	Cities: <select name="allcitieslist" id="allcitieslist">
				
	
			</select>			
			
			
			
			<br/>
			<input type="submit" value="Submit">
			</form>				
</body>
</html>