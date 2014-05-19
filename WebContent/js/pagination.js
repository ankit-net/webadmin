/**
 * Script file for calling records according to the pagination
 */
function getResultofPage(pageno){
	//alert('pageno=>'+pageno);
	$("#dynamicusers").hide();
	$("#ajax_loader").show();
	$("#paggination").hide();
	
	$.ajax({
		url: '/WebAdmin/userspagination.do',
		type: "GET",
		async:true,
		dataType:'json',
		data: "cp="+pageno,
		beforeSend: function() {
			$("#ajax_loader").attr("style", "display:block");
		}, 
		success:function(response) {
			$("#dynamicusers").empty();
			$("#ajax_loader").hide();
			$("#paggination").empty();
			
			var paggination  = "<table align='center'><tr>";
			var totalPageajax = response.totalpages;
			var currentPage = response.currentpage;
			
			
			var dynamicusershtml = "<table border='1' align='center'><tr bgcolor='pink'><th>ID</th><th>UserName</th><th>Password</th><th>GroupName</th><th>Email</th><th>Mobile</th><th>IsActive</th></tr>";
			var myusers = response.users;
			for(var i=0;i < myusers.length;i++){
				dynamicusershtml += "<tr><td>"+myusers[i].id+"</td><td>"+myusers[i].username+"</td><td>"+myusers[i].password+"</td><td>"+myusers[i].groupname+"</td><td>"+myusers[i].email+"</td><td>"+myusers[i].mobile+"</td><td>"+myusers[i].isactive+"</td></tr>"; 
			}
			dynamicusershtml += "</table>";
			
			$(dynamicusershtml).appendTo("#dynamicusers");
			//alert('length->'+totalPageajax.length);
			for(var i=1;i <= totalPageajax.length;i++){
				//alert('i=>'+i);
				if(currentPage == i )
					paggination += " <b style='color: red'> "+i+" | </b>";
				
				else
					paggination +="<b><a href='javascript:getResultofPage("+i+");'>"+i+"</a> | </b>";
			}
			paggination += "</td></tr>";
			
			$(paggination).appendTo("#paggination");
			$("#paggination").show();
			$("#dynamicusers").show();
			
		}	
	});
}