$(document).ready(function(){
	$(".filterbutton").click(function() {
		filterInstitutes(1);
	});

	$("#maincat").change(function(){
		var maincategory = $("#maincat").val();
		alert('main category called=>'+maincategory);
		
		$.ajax({
			url: '/WebAdmin/categorylist.do',
			type: "POST",
			async:true,
			dataType:'json',
			data: "maincategory="+maincategory,
			beforeSend: function() {
		        //some stuff
		        //alert('i am going to send request');
				$("#childcatloader").show();
				$("#childcat").hide();
		    },
		    success:function(response){
		    	alert("i got the response");
		    	$("#childcat").empty();
		    	
		    	var childcategoryhtml = "<option selected='selected' value='-1'>--Please Select--</option>";
		    	for(var i=0;i<response.childcat.length;i++){
		    		//alert(i+'\tlength->'+response.childcat.length);
		    		childcategoryhtml +=  "<option value='"+response.childcat[i].id+"'>"+response.childcat[i].categoryname+"</option>";
		    	}
		    	
		    	$(childcategoryhtml).appendTo("#childcat");
		    	$("#childcat").show();
		    	$("#childcatloader").hide();
		    }
		});
	});
	$("#states").change(function(){
		var stateid = $("#states").val();
		alert(stateid);
		$.ajax({
			url: '/WebAdmin/citylist.do',
			type: "GET",
			async:true,
			dataType:'json',
			data: "stateid="+stateid+"&currentpage=1",
			beforeSend: function () {
				$("#citiesloader").show();
				$("#cities").hide();
			},
			success : function(response){
				alert('i got the response');
				$("#cities").empty();
				var citieshtml = "<option value='-1' selected='selected'>--Please Select--</option>";
				var mycities = response.cities.citieslists;
				for(var i=0;i<mycities.length;i++){
					citieshtml += "<option value='"+mycities[i].id+"'>"+mycities[i].name+"</option>";
				}
				$(citieshtml).appendTo("#cities");
				$("#cities").show();
				$("#citiesloader").hide();
			}
		});
	});
});

function filterInstitutes(pageno){
	alert("hi");
	var maincategory = $("#maincat").val();
	var childcategory = $("#childcat").val();
	var state = $("#states").val();
	var cities = $("#cities").val();		
	var cp =  pageno;
	$.ajax({			
		url: '/WebAdmin/filterInstitute.do',
		type: "GET",
		async:true,
		dataType:'json',
		data: "maincategory="+maincategory+"&childcategory="+childcategory+"&states="+state+"&cities="+cities+"&cp="+cp,
		beforeSend:function(){
			$("#listing").hide();
			$("#ajax").show();
		},
		success:function(response){
			alert('i got the response');
			$("#ajax").hide();
			$("#listing").empty();
			var listingajax = "<tr><td width='30' class='tableheader'>Id </td><td width='166' class='tableheader'>Institute Name</td><td width='110' class='tableheader'>Institute type </td><td width='166' class='tableheader'>Location</td><td width='97' class='tableheader'>Verified By </td><td width='97' class='tableheader'>Created By</td><td width='97' class='tableheader'>Expand</td></tr>";
			var countajax = "<tr><td align='center' colspan='7'>Total Institutes Found: "+response.count+"</td></tr>";
			var instajax =  response.institutes;
			alert(instajax.length);
			var allajax = countajax + listingajax;
			for(var i=0;i < instajax.length;i++){
				allajax += "<tr><td class='tabledata'>"+instajax[i].id+"</td>";
				allajax += "<td class='tabledata'>"+instajax[i].name+"</td>";
				allajax += "<td class='tabledata'>"+instajax[i].type+"</td>";
				allajax += "<td class='tabledata'>"+instajax[i].country+",&nbsp;"+instajax[i].state+",&nbsp;"+instajax[i].city+"</td>";
				allajax += "<td class='tabledata'>"+instajax[i].verifiedby+"</td>";
				allajax += "<td class='tabledata'>"+instajax[i].createdby+"</td>";
				allajax += "<td class='tabledata'><button id='detailbutton' value="+instajax[i].id+">Click Here</button></td>";
			}
			
			$("#listing").html(allajax);
			
			$("#listing").show();
		}
	});
}

