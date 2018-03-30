$(function(){
	
	//表格每一行的点击事件，获得td中的值
	$("#table tr").click(function(){
		var id,key,value;
		var td=$(this).find("td");
		//console.log(td)
		//获取指定id的孩子节点
		id=td.eq(2).children("button#button_modify").attr("modifyId");
		//第一列   存放key值ֵ
		key=td.eq(0).text();
		//第二列  存放value值ֵ
		value=td.eq(1).text();
		
		//给模态框中的值设值
		//val()直接给表单中的元素设值
		$("#id").val(id);
		$("#key").attr("value",key);
		$("#value").attr("value",value);
	})
	
})