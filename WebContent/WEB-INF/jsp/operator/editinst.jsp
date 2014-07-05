<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/js/jquery-1.10.2.js"></script>

<script type="text/javascript" src="/WebAdmin/js/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/institutecities.js"></script>
<script type="text/javascript">
tinyMCE.init({
    theme : "advanced",
    mode : "textareas",
    plugins : "pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,wordcount,advlist,autosave",
    // Theme options
    theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect",
    theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
    theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",
    theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak,restoredraft",
    theme_advanced_toolbar_location : "top",
    theme_advanced_toolbar_align : "left",
    theme_advanced_statusbar_location : "bottom",
    theme_advanced_resizing : true

});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WebAdmin - Edit Institute Form</title>
</head>
<body>
	<h2 align="center">Edit Institute From</h2>
	<form action="<%=request.getContextPath()%>/updateInstitute.do" method="post">
		Institute ID: <input type="text" name="id" value="${institutedetails.instid}" readonly="readonly">
		<input type="hidden" name="memid" value="${institutedetails.memberid}">
		<br/>
		User Type:	
				<c:forEach var="ut"  items="${usertypes}">
					<c:choose>
						<c:when test="${ut.id == institutedetails.usertype}">
							<input type="radio" name="usertype" checked="checked" value="${ut.id}"/><c:out value="${ut.name}"/>		
						</c:when>
						<c:otherwise>
							<input type="radio" name="usertype" value="${ut.id}"/><c:out value="${ut.name}"/>
						</c:otherwise>
					</c:choose>
						
				</c:forEach>
		<br/>
			EmailID: <input type="text" name="email" value="${institutedetails.email}"/> <br/>
	
			Institute Name:<input type="text" name="name" value="${institutedetails.institutename}"> <br/>
	
			Josh Rating:
					 
					<input type="radio" value="Gold" name="rating"> Gold 
					<input type="radio" value="Silver" name="rating"> Silver 
					<input type="radio" value="Bronze" name="rating"> Bronze
			
				<br/>
		
			Primary Number: <input type="text" name="phone" value="${institutedetails.phone}"><br/>
	
			Institute Type: 
					<c:forEach var="insttype" items="${insttypes}">
						<c:choose>
							<c:when test="${insttype.id == institutedetails.institutetype}">
								<input type="radio" checked="checked" name="institutetype" id="institutetype" value="${insttype.id}"><c:out value="${insttype.name}"/><br/>	
							</c:when>
							<c:otherwise>
								<input type="radio" name="institutetype" id="institutetype" value="${insttype.id}"><c:out value="${insttype.name}"/><br/>					
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<br/>
					
			Year of Formation : <select name="year" >
								 	<c:forEach var="myyear" items="${totalyears}">
								 		<c:choose>
								 			<c:when test="${myyear == institutedetails.yearoffrom}">
								 				<option selected="selected" value="${myyear}"><c:out value="${myyear}"/></option>
								 			</c:when>
								 			<c:otherwise>
								 				<option value="${myyear}"><c:out value="${myyear}"/></option>	
								 			</c:otherwise>
								 		</c:choose>
										
									</c:forEach>					
								</select>
	<br/>
						
			About The Institute: <textarea rows="20" cols="100"  id="about" name="about" ><c:out value="${institutedetails.about}"/>  </textarea>
	<br/>
	
			State: <select name="allstates" id="allstates">
						<option value="-1">--Please Select--</option>
						<c:forEach var="state" items="${states}">
							<c:choose>
								<c:when test="${state.id == institutedetails.stateid}">
									<option selected="selected" value="${state.id}">${state.name}</option>	
								</c:when>
								<c:otherwise>
									<option value="${state.id}">${state.name}</option>
								</c:otherwise>
							</c:choose>					
						</c:forEach>
					</select>
			
	<br/>
	
			Cities: <select name="allcitieslist" id="allcitieslist">
						<c:forEach var="city" items="${cities}">
							<c:choose>
								<c:when test="${city.id == institutedetails.cityid}">
									<option selected="selected" value="${city.id}">${city.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${city.id}">${city.name}</option>	
								</c:otherwise>
							</c:choose>
							
						</c:forEach>				
					</select>			
			
			
			
	<br/>
			<input type="submit" value="Submit">
			
	</form>
</body>
</html>