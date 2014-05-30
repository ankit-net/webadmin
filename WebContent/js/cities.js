$(document).ready(function() {
	
	$("#states").change(function() {
		//var cp = $("#cpcity").val();
		getResultCity(1);
	});	

});
function getResultCity(pageno) {	
	
	alert('pageno=>'+pageno);
	
	//code executed to show list of cities under that particular state;
	//alert('code executed to show list of cities under that particular state');
	var selectedstate = $("#states").val();
	var currentpage = pageno;
	var pagginationcity  = "<table align='center'><tr>";
	//alert('>>>'+selectedstate);
	$("#ajax_loader").show();
	$("#citieslist").hide();
	$("#citypaggination").empty();

	$.ajax({
		url:'/WebAdmin/citylist.do',
		type:"GET",
		async:true,
		dataType:'json',
		data:"stateid="+selectedstate+'&currentpage='+currentpage,
		success:function(response) {
			alert('i got the response=>'+response);
			$("#citieslist").empty();
			$("#ajax_loader").hide();
			var citiescounthtml = "<p align='center'>Total Records Found:&nbsp;&nbsp;"+response.cities.count+'</p><br/>';
			var citieslisthtml = citiescounthtml + "<table border='1' align='center' ><tr bgcolor='pink'><th>Sno</th><th>ID</th><th>Name</th><th>IsActive</th><th>CreatedDate</th></tr>";
			var mycities = response.cities.citieslists;
			var totalPageajax = response.cities.totalpages;
			var cpresponse = response.cities.currentpage;
			for(var i=0;i<mycities.length;i++){
				//alert('id=>'+mycities[i].id+'\tname->'+mycities[i].name);
				var sno = i;
				citieslisthtml += '<tr><td>'+(++sno)+'</td><td>'+mycities[i].id+'</td><td>'+mycities[i].name+'</td><td>'+mycities[i].isactive+'</td><td>'+mycities[i].createddate+'</td></tr>';
				
				
			}
			
			citieslisthtml += "</table>";
			$(citieslisthtml).appendTo("#citieslist");
			$("#citieslist").show();

			for(var i=1;i <= totalPageajax.length;i++){
				//alert('i=>'+i);
				if(cpresponse == i )
					pagginationcity += " <b style='color: red'> "+i+" | </b>";
				
				else
					pagginationcity +="<b><a href='javascript:getResultCity("+i+");'>"+i+"</a> | </b>";
			}
			pagginationcity += "</td></tr>";
			
			$(pagginationcity).appendTo("#citypaggination");
			
			
		}
	});
}
