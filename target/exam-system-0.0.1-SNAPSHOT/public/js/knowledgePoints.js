$(function(){
	$("select#k_select").change(function(){
		if($(this).val()!=""){
			console.log($(this).val());
			location.href=""+$(this).val();
		}
	});
})