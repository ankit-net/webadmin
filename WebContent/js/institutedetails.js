/** 
 * Get the Courses,Departments,Campus and Media Listing for a single institute
 */
$(document).ready(function(){
	$("button").click(function(){
		alert('instituteid->'+this.id);
		var inst = this.id;
		$.ajax({
			url: '/WebAdmin/details.do',
			type: "GET",
			async: true,				
			dataType:'json',
			data : "instid="+inst,
			
			success:function(response) {
				alert('i got the response');
				var detailshtml = '<ul class="tabs"><li><a href="#tab1">Courses</a></li><li><a href="#tab2">Departments</a></li></ul><div class="tabContainer"><div id="tab1" class="tabContent"><b>Courses</b></div><div id="tab2" class="tabContent"><b>Departments</b></div></div>';
				//$(this).parent("tr:first").next("tr").slideToggle();
				//$(detailshtml).appendTo("#div"+this.id);
				var courseshtml = '<td bgcolor="#FFFFFF" style="padding:20px" colspan="4"><table width="100%" cellspacing="2" cellpadding="4" border="0" align="left"><tbody><tr><td align="right" class="tableheader" colspan="14"><a class="left_tabH" href="/Education/view/education/mediaters/AddCourseMediater.jsp?viewInstId=4905">Add New Course</a></td></tr> <tr> <td width="20" class="tableheader">Id </td><td width="68" class="tableheader">Course Name</td> </tr>';
				var departmenthtml = '<tr><td class="tableheader">Dept ID</td><td>Dept Name</td></tr>';
				
				var mycourses = response.courses;
				for(var i=0;i < mycourses.length;i++){
					courseshtml += "<tr><td class='tabledata'>"+mycourses[i].courseid+"</td>";
					courseshtml += "<td class='tabledata'>"+mycourses[i].coursename+"</td></tr>";
					
				}
				
				var mydepartments = response.departments;
				
				for(var i=0;i < mydepartments.length;i++){
					departmenthtml += "<tr><td class='tabledata'>"+mydepartments[i].deptid+"</td>";
					departmenthtml += "<td class='tabledata'>"+mydepartments[i].deptname+"</td></tr>";
				}
				//alert('coursehtml=>'+courseshtml);
				$("#coursesid").html(courseshtml);
				$("#departmentid").html(departmenthtml);
				//$("#departmentid").empty();
				
				//$(courseshtml).appendTo("#coursesid");
				//$(departmenthtml).appendTo("#departmentid");
				
				alert('Displaying Details for Institute ID=>'+inst);
				$("#alldetails").show();
			
				
			
			}
		});
	});
});