<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/filter.js"></script>
<script type="text/javascript" src="/WebAdmin/js/tiny_mce/tiny_mce.js"></script>

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
<title>WebAdmin - Add Course</title>
</head>
<body>
	<h2 align="center">Add Course Form</h2>
	<form action="<%=request.getContextPath()%>/addcourse.do" method="post">
		Institute ID: <input type="text" value="${instituteid}" readonly="readonly" name="instituteid" size="1"> <br/>
		Course Name: <input type="text" name="coursename"/> <br/>
		Course Keyword: <textarea name="coursekeyword" rows="20" cols="100"></textarea> <br/>
		Level of Education: <select name="leveledu" id="leveledu">
								<option value="-1">Please Select</option>
								<c:forEach var="current" items="${leveledulist}">
									<option value="${current.id}"><c:out value="${current.name}"/></option>
								</c:forEach>
							</select><br/>
		Type of Education: <select name="typeedu" id="typeedu">
								<option value="-1">Please Select</option>			 
								<c:forEach var="current" items="${typeedulist}">
									<option value="${current.id}"><c:out value="${current.name}"/></option>
								</c:forEach>
							</select><br/>
		Course Main Category: 	<select name="maincat" id="maincat">
								<option value="-1">Please Select</option> 
								<c:forEach var="current" items="${parentcat}">
									<option value="${current.id}"><c:out value="${current.categoryname}"/></option>
								</c:forEach>
						</select>	<br/>
		Course SubCategory: <div id="childcatloader" style="display: none">
							<img src="<%=request.getContextPath()%>/images/ajax-loader.gif">
						</div>
						<select id="childcat" name="childcat" multiple="multiple">
							<option selected="selected" value="-1">--Please Select--</option>
						</select>	<br/>
		Syllabus: <textarea rows="20" cols="100"></textarea> 
		<br/>
		<div align="center"><input type="submit" value="Add Course"></div>
	</form>
</body>
</html>