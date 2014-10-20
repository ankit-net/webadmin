<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib  uri="http://www.springframework.org/tags" prefix="spring"%>
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

<title>Form for Adding Institute</title>
</head>
<body>
	<h2 align="center">Add Institute Form</h2>
	<form action="<%=request.getContextPath()%>/addinstitute.do" method="post">
		
		
			User Type:	
			<spring:bind path="membean.user_type_id">
				<c:forEach var="ut"  items="${usertypes}">
					<input type="radio" name="user_type_id" value="${ut.id}"/><c:out value="${ut.name}"/>	
				</c:forEach>
			</spring:bind>
	<br/>
	
			EmailID: <spring:bind path="membean.email"><input type="text" name="email"/></spring:bind> <br/>
	
			Institute Name:<spring:bind path="membean.name"><input type="text" name="name"> </spring:bind><br/>
	
			Josh Rating: 
			<spring:bind path="instbean.josh_rating">			
				<input type="radio" value="Gold" name="josh_rating"> Gold <input type="radio" value="Silver" name="josh_rating"> Silver <input type="radio" value="Bronze" name="josh_rating"> Bronze
			</spring:bind>
	<br/>
		
			Primary Number:<spring:bind path="membean.phone"> <input type="text" name="phone"></spring:bind><br/>
	
			Institute Type: 
				<spring:bind path="instbean.instype">
					<c:forEach var="insttype" items="${insttypes}">
						<input type="radio" name="institutetype" id="institutetype" value="${insttype.id}"><c:out value="${insttype.name}"/><br/>
					</c:forEach>
				</spring:bind>
					<br/>
					
			Year of Formation :	<spring:bind path="instbean.yearoffrom"> 
									<select name="yearoffrom" >
									 	<c:forEach var="year" items="${totalyears}">
											<option value="${year}"><c:out value="${year}"/></option>
										</c:forEach>						
									</select>
								</spring:bind>
	<br/>
						
			About The Institute:<spring:bind path="instbean.about"> <textarea rows="20" cols="100"  id="about" name="about"></textarea></spring:bind>
	
	
	<br/>
	
			Type of Education: <c:forEach var="typebean" items="${typeedu}">
									<input type="checkbox" name="typeedu" id="typeedu" value="${typebean.id}"/><c:out value="${typebean.name}"/><br/>
								</c:forEach>
	<br/>
	
			Level of Education: <c:forEach var="levelbean" items="${leveledu}">
									<input type="checkbox" name="leveledu" id="leveledu" value="${levelbean.id}"/><c:out value="${levelbean.name}"/><br/>
								</c:forEach>
	
	<br/>
			Keyword:	<spring:bind path="instbean.keyword">		<input type="text" name="keyword"/></spring:bind>
			
	<br/>
			Source:		<spring:bind path="instbean.source">		<input type="text"	name="source" ></spring:bind>	
			
	<br/>
			Address:	<spring:bind path="instbean.address">		<textarea rows="20" cols="100" id="address" name="address"></textarea></spring:bind>		
	
	
 	<br/>
			State:<spring:bind path="instbean.state_id"> <select name="state_id" id="state_id">
						<option value="-1">--Please Select--</option>
						<c:forEach var="state" items="${states}">
							<option value="${state.id}">${state.name}</option>					
						</c:forEach>
					</select>
			</spring:bind>
	<br/>
	
			Cities:<spring:bind path="instbean.city_id"> 
						<select name="city_id" id="city_id">
							
				
						</select>				
					</spring:bind>
			
		 	
	<br/>
			<input type="submit" value="Submit">
	</form>				
</body>
</html>