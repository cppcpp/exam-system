$(function(){
	$("#modifyButton").click(function(){
		var eTitle=$("#eTitle").val()
		var eId=$.trim($("#eId").html());
		var orderList=[]
		var options=$("select option:selected");
		
		console.log(options)
		options.each(function(item){
			tempOrderMap={}
			tempOrderMap["qtId"]=options[item].value;
			if(options[item].innerHTML==""||options[item].innerHTML==null){
				alert("题型序号不能为空")
				return;
			}
			tempOrderMap["qtOrder"]=options[item].innerHTML;
			orderList.push(tempOrderMap)
		});
		
		if(eTitle==null||eTitle==""){
			alert("试卷标题不能为空")
		}
		
		var data={}
		data["eId"]=eId
		data["eTitle"]=eTitle;
		data["eOrder"]=orderList;
		var jsonData=JSON.stringify(data);
		
		console.log(jsonData)
		
		$.ajax({
			method:'post',
			url:eId,
			data:jsonData,
			contentType:"application/json;charset=utf-8",
			success:function(data){
				console.log(data)
				if(data=="success"){
					alert("修改成功")
				}
			},
			error:function(){
			}
		})
		
	})
})