/**
 * This JavaScript file will call and display Cities according to State ID. 
 * This File is called on addinst.jsp file
 */
$(document).ready(function() {
	$("#state_id").change(function() {
		var selectedstate = $("#state_id").val();
		//alert('stateid=>'+selectedstate);
		$("#city_id").hide();
		$.ajax({
			url:'/WebAdmin/citylist.do',
			type:"GET",
			async:true,
			dataType:'json',
			data:"stateid="+selectedstate+'&currentpage=-1',
			success:function(response) {
				//alert('i got the response=>'+response);
				var mycities = response.cities.citieslists;;
				var citieslisthtml = "<select name='city_id' id='city_id'>";
				for(var i=0;i<mycities.length;i++){
					//alert('id=>'+mycities[i].id+'\tname->'+mycities[i].name);
					
					citieslisthtml += '<option value='+mycities[i].id+'>'+mycities[i].name+'</option>';
					
					
				}
				citieslisthtml += '</select>';
				$("#city_id").html(citieslisthtml);
				$("#city_id").show();
			}
		});
	});
});