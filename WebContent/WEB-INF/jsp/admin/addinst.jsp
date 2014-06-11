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