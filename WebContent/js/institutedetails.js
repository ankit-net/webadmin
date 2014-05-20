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
				var detailshtml = 'Ankit';
				alert(inst);
				//$(this).parent("tr:first").next("tr").slideToggle();
				//$(detailshtml).appendTo("#div"+this.id);
				if($("#div"+inst).is(":hidden")){
					alert('this row is already hidden');
					$("#div"+inst).slideToggle();
				}
				else {
					$("#div"+inst).empty();
					$("#div"+inst).slideToggle();
					
				}
			}
		});
	});
});