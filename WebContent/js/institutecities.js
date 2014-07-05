/**
 * This JavaScript file will call and display Cities according to State ID. 
 * This File is called on addinst.jsp file
 */
$(document).ready(function() {
	$("#allstates").change(function() {
		var selectedstate = $("#allstates").val();
		//alert('stateid=>'+selectedstate);
		$("#allcitieslist").hide();
		$.ajax({
			url:'/WebAdmin/citylist.do',
			type:"GET",
			async:true,
			dataType:'json',
			data:"stateid="+selectedstate+'&currentpage=-1',
			success:function(response) {
				//alert('i got the response=>'+response);
				var mycities = response.cities.citieslists;;
				var citieslisthtml = "<select name='allcitieslist' id='allcitieslist'>";
				for(var i=0;i<mycities.length;i++){
					//alert('id=>'+mycities[i].id+'\tname->'+mycities[i].name);
					
					citieslisthtml += '<option value='+mycities[i].id+'>'+mycities[i].name+'</option>';
					
					
				}
				citieslisthtml += '</select>';
				$("#allcitieslist").html(citieslisthtml);
				$("#allcitieslist").show();
			}
		});
	});
});