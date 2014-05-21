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
				var courseshtml = '<td bgcolor="#FFFFFF" style="padding:20px" colspan="4"><table width="100%" cellspacing="2" cellpadding="4" border="0" align="left"><tbody><tr><td align="right" class="tableheader" colspan="14"><a class="left_tabH" href="/Education/view/education/mediaters/AddCourseMediater.jsp?viewInstId=4905">Add New Course</a></td></tr><tr><td class="tableheader" colspan="14">Course List of <font class="pagingtxt">A-IHM Institute of Hotel Management </font>institute </td> </tr> <tr> <td width="20" class="tableheader">Id </td><td width="68" class="tableheader">Old Course Name</td> <td width="68" class="tableheader">New Course Name</td> <td width="40" class="tableheader">Josh Rating </td> <td width="68" class="tableheader">Main Category</td> <td width="71" class="tableheader">Sub Category </td><td width="40" class="tableheader">Rating </td><td width="40" class="tableheader">Ranking </td><td width="40" class="tableheader">Duration </td><td width="40" class="tableheader">Fees </td><td width="40" class="tableheader">Is Verified </td><td width="40" class="tableheader">Verified By </td><td width="35" class="tableheader">View </td><td width="35" class="tableheader">Edit </td></tr>';
				var departmenthtml = 'alldepartments listing='+inst;
				
				var mycourses = response.courses;
				
				$("#coursesid").html(courseshtml);
				$("#departmentid").html(departmenthtml);
				//$("#departmentid").empty();
				
				//$(courseshtml).appendTo("#coursesid");
				//$(departmenthtml).appendTo("#departmentid");
				
				alert('Displaying Details for Institute ID=>'+inst);
				$("#alldetails").show();
			
				
			/*	if($("#div"+inst).is(":hidden")){
					alert('Displaying Details for Institute ID=>'+inst);
					
					
					$(detailshtml).appendTo("#div"+inst);
					$("#div"+inst).show();
					
				}
				else {
					alert('Displaying Details for Institute ID=>'+inst);
					
					$("#coursesid").empty();
					$("#departmentid").empty();
					
					$(courseshtml).appendTo("#coursesid");
					$(departmenthtml).appendTo("#departmentid");
					
					
					//$("#div"+inst).slideToggle();
					
				}
				*/
			}
		});
	});
});