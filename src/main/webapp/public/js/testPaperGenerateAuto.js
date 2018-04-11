$(function(){
	var sId;
	//Set数据结构存放所有题型信息
	qtSet=new Set();
	//存放所有知识点信息
	kps=new Array();
	//存放传给后台的所有数据
	resultMap={};
	orderFlag=true,scoresFlag=true;
	
	//设置题型信息
	$("#subjectSelect").change(function(){
		sId=$("#subjectSelect").val()
		getAllKps(sId);
		getQuesCount1(sId);		
	})
	
	//点击生成试卷按钮
	$("#generate_paper_button").click(function(){
		generateButtonClick(sId);
	})
	
})

//画知识点信息
function getAllKps(sId){
	
		$.ajax({
			url:"knowledgPoints/"+sId,
			method:"get",
			success:function(data){
				var knows=data.map.knows;
				
				$("#kp_div .modal-body").html("");
				for(var i in knows){
					if(knows[i].kSubNum==0){
						$("#kp_div .modal-body").append("<label><input name='konwledgePoint' class='kpF"+knows[i].kNum+"' type='checkbox' value="+knows[i].kId+">"+knows[i].kNum+"."+knows[i].kSubNum+"."+knows[i].kTitle+"</label>");
					}else{
						$("#kp_div .modal-body").append("<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name='konwledgePoint' class='kpS"+knows[i].kNum+"' type='checkbox' value="+knows[i].kId+">"+knows[i].kNum+"."+knows[i].kSubNum+"."+knows[i].kTitle+"</label>");
					}
				}
				
				if(knows!=""){
					$("#kp_div .modal-body").append("<button class='btn btn-default' data-dismiss='modal' id='kp_button_commit'>确认</button>")
				}
				//全选功能
				clickAll();
				
				//选择知识点范围
				$("#kp_button_commit").click(function(){
					kpButtonClick(sId);
				})
			},
			error:function(){
			}
		})
		
}

//全选功能
function clickAll(){
	$("#kp_div .modal-body input[name='konwledgePoint']").click(function(){
		var kpf_class=$(this).attr("class");
		//alert("kp_class:::::"+kp_class)
		//截取字符串--不包括3
		var temp=kpf_class.substring(0,3); 
		if(temp=="kpF"){
			var kps_class="kpS"+kpf_class.substring(3,kpf_class.length);
			//console.log("index---"+temp+"    kps_class:"+kps_class);
			
			
			if($("#kp_div .modal-body ."+kpf_class).is(":checked")){
				//全选
				$("input[class="+kps_class+"]").each(function(){
				 	$(this).attr("checked", true); 
				})
			}else{
				//全不选
				$("input[class="+kps_class+"]").each(function(){
				 	$(this).attr("checked", false); 
				})
			}
		}
		
	})
}

//知识点确定点击方法---查询条件限制：科目，知识点
function kpButtonClick(sId){
	//先清空知识点
	kps=[];
	$("input[name='konwledgePoint']:checked").each(function(){
//		console.log($(this).val())
		kps.push($(this).val())
	})
	
	//console.log(kps)
	
	var data={
		"kps":kps
	}
	//生成json数据
	var jsonData=JSON.stringify(data);
	
	$.ajax({
		url:'amount/'+sId,
		method:'post',
		contentType: "application/json; charset=utf-8",
		data:jsonData,
		success:function(data){
			//解析数据
			parseData(data);
		},
		error:function(){}
		
	})
}

//改变不同科目的题目个数信息-----old
/*function getQuesCount(sId){
	$.ajax({
		url:'amount/'+sId,
		method:'get',
		success:function(data){
			var map=data.map;
			//题型种类
			var qtlength=Object.keys(map).length/5;
			var key1;
			//console.log(map)
			$.each(map,function(key,value){
				//console.log(key+"  "+value)
				//设置题目个数信息
				$("#qt_"+key).text(value);
				
				//初始化题目个数
				$("#set_qt_"+key).html("");
				for(var i=1;i<=value;i++){
					$("#set_qt_"+key).append("<option value="+key+">"+i+"</option>");
				}
				
				key1=key.split("_")[0];
				qtSet.add(key1);
				//console.log("key1:::::"+key1)
				$("#set_qt_"+key1+"_order").html("");
				for(var i=0;i<=qtlength;i++){
					//console.log("key1---key::"+"#set_qt_"+key1+"_order")
					$("#set_qt_"+key1+"_order").append("<option value="+key1+"order>"+i+"</option>")
				}
				
			})
			
		},
		error:function(){
		}
	})
}*/

//改变不同科目的题目个数信息-----new
function getQuesCount1(sId){
	$.ajax({
		url:'amount/'+sId,
		method:'get',
		success:function(data){
			//解析数据
			parseData(data);
		},
		error:function(){
		}
	})
}

//解析--getQuesCount1--方法数据
function parseData(data){
	//console.log(data)
	var map1=data.list[0];
	var map2=data.list[1];
	//题型种类
	var qtlength=Object.keys(map2).length;
	
	//将总的题目个数和总分清0
	$("#total_ques").text("0")
	$("#total_score").text("0")
	
	$.each(map1,function(key,value){
		//设置题目个数信息
		$("#qt_"+key).text(value);
	})
	
	$.each(map2,function(key,value){
		qtSet.add(key)
		
		//将个题型总分清0
		$("#set_qt_"+key+"_total_score").text("0")
		
		//设置试题顺序的select
		$("#set_qt_"+key+"_order").html("");
		for(var i=0;i<=qtlength;i++){
			//console.log("key1---key::"+"#set_qt_"+key1+"_order")
			$("#set_qt_"+key+"_order").append("<option value="+key+">"+i+"</option>");
		}
		
		//设置--用户选择--不同题型的个数
		$("#set_qt_"+key+"_num").html("");
		for(var i=0;i<=value;i++){
			$("#set_qt_"+key+"_num").append("<option value="+key+">"+i+"</option>");
		}
		
		//不同题型的分值
		$("#set_qt_"+key+"_score").html("");
		for(var i=0;i<=100;i++){
			$("#set_qt_"+key+"_score").append("<option value="+key+">"+i+"</option>");
		}
		
	})
	
	//题目数量的select改变
	$("select[name='changes_num']").change(function(){
		var quesType=$(this).val();
		var id=$(this).attr("id");
		var quesTypeNum=$.trim($("#"+id+" option:selected").text());
		//console.log(quesType+":num:"+quesTypeNum)
		var quesTypeScore=$.trim($("#set_qt_"+quesType+"_score option:selected").text());
		//console.log(quesTypeNum+"   "+quesTypeScore+"  "+$.trim($("#set_qt_"+quesType+"_total_score").text()))
		$("#set_qt_"+quesType+"_total_score").text(quesTypeNum*quesTypeScore)
		
		var nums=0,totalScore=0;
		//qtSet中存放所有题型
		qtSet.forEach(function(item){
			nums+=parseInt($.trim($("#set_qt_"+item+"_num option:selected").text()));
			totalScore+=parseInt($.trim($("#set_qt_"+item+"_total_score").text()));
		})
		
		//console.log("totalScore:"+nums+"   "+totalScore)
		$("#total_ques").text(nums)
		$("#total_score").text(totalScore)
		
	})
	
	//题型分数的select改变
	$("select[name='changes_score']").change(function(){
		var quesType=$(this).val();
		var id=$(this).attr("id");
		var quesTypeScore=$.trim($("#"+id+" option:selected").text());
		//console.log(quesType+":score:"+quesTypeScore)
		var quesTypeNum=$.trim($("#set_qt_"+quesType+"_num option:selected").text());
		//console.log(quesTypeNum+"   "+quesTypeScore+"  "+$("#set_qt_"+quesType+"_total_score").text())
		$("#set_qt_"+quesType+"_total_score").text(quesTypeNum*quesTypeScore)
		
		var totalScore=0;
		//qtSet中存放所有题型
		qtSet.forEach(function(item){
			totalScore+=parseInt($.trim($("#set_qt_"+item+"_total_score").text()));
		})
		//console.log("totalScore："+totalScore)
		$("#total_score").text(totalScore)
	})
}

//点击生成试卷按钮
function generateButtonClick(sId){
	var paperName= $("#paper_name").val();
	var paperDiffLevA= $("#paper_difficulty_level_A").val();
	var paperDiffLevB=$("#paper_difficulty_level_B").val();
	var qtTitle,qtNum,qtScore,qtOrder,qtTotalScore,nunms,scores;
	//存放试题信息
	qtList=new Array();
	
	$("#paper_table").html("")
	$("#paper_table").append("<th colspan='5'>试卷标题："+paperName+"&nbsp;&nbsp;&nbsp;&nbsp;A卷难度"+paperDiffLevA+"&nbsp;&nbsp;&nbsp;&nbsp;B卷难度："+paperDiffLevB+"</th>")
	$("#paper_table").append("<tr>" +
			"<td>题型</td>" +
			"<td>题目个数</td>" +
			"<td>分值</td>" +
			
			"<td>试题顺序</td>" +
			"<td>该题型总分</td>" +
			"</tr>")
	
	//循环之前清空resultList
		
	qtSet.forEach(function(item){
		//用每个题型总分作为判断依据
		if($.trim($("#set_qt_"+item+"_total_score").text())!=0){
			qtTitle=$.trim($("#"+item+"_title").text());
			qtNum=$.trim($("#set_qt_"+item+"_num option:selected").text());
			qtScore=$.trim($("#set_qt_"+item+"_score option:selected").text());
			qtOrder=$.trim($("#set_qt_"+item+"_order option:selected").text());
			qtTotalScore=$.trim($("#set_qt_"+item+"_total_score").text());
			
			tempMap={};
			tempMap["qtId"]=item;
			tempMap["qtNum"]=qtNum;
			tempMap["qtScore"]=qtScore;
			tempMap["qtOrder"]=qtOrder;
			qtList.push(tempMap)
			
			//试题顺序不能有空
			orderFlag=true;
			if(Number(qtOrder)==0){
				orderFlag=false;
			}
			
			$("#paper_table").append("<tr name='"+item+"'>" +
					"<td>"+qtTitle+"</td>" +
					"<td>"+qtNum+"</td>" +
					"<td>"+qtScore+"</td>" +
					"<td>"+qtOrder+"</td>" +
					"<td>"+qtTotalScore+"</td>" +
					"</tr>")
		}
		
	})
	
	nums=$.trim($("#total_ques").text());
	scores=$.trim($("#total_score").text());
	$("#paper_table").append("<tr>" +
			"<td>总计</td>" +
			"<td>"+nums+"</td>" +
			"<td></td>" +
			"<td></td>" +
			"<td>"+scores+"</td>" +
			"</tr>")
	$("#paper_table").append("<tr><td colspan='5'><button style='margin-left:40%' class='btn btn-default' id='generatePaper'>确认生成</button></td></tr>")
	
	//先将resultMap清空
	resultMap={};	
//	resultMap.set("qtList",qtList);
//	resultMap.set("totalScore",scores);
	resultMap["qtList"]=qtList;
	resultMap["totalScore"]=scores;
	
	//第二次改变会保持false
	scoresFlag=true
	if(Number(scores)<=0){
		scoresFlag=false;
	}
	
	//确认生成试卷--ajax
	$("#generatePaper").click(function(){
		generatePaper(sId);
	})
}

//生成试卷
function generatePaper(sId){
		var paperName= $("#paper_name").val();
		var paperDiffLevA= $("#paper_difficulty_level_A").val();
		var paperDiffLevB= $("#paper_difficulty_level_B").val();
		var paperDiffFalgA=true;
		var paperDiffFalgB=true;
		
		if(paperName==""){                                                                       
			alert("请填写试卷标题")
		}
		if(paperDiffLevA==""){
			paperDiffFalgA=false;
			alert("请填写A卷难度系数")
		}else{
				paperDiffLevA=Number(paperDiffLevA);
				//Number.NAN--Not a Number
				if(isNaN(paperDiffLevA)){
					alert("A卷难度系数格式错误")
					paperDiffFalgA=false;
					
				}else if(paperDiffLevA<0||paperDiffLevA>1){
					alert("A卷难度必须位于0-1之间")
					paperDiffFalgA=false;
				}
			
		}
		if(paperDiffLevB==""){
			paperDiffFalgB=false;
			alert("请填写B卷难度系数")
		}else{
				paperDiffLevB=Number(paperDiffLevB);
				//Number.NAN--Not a Number
				if(isNaN(paperDiffLevB)){
					alert("B卷难度系数格式错误")
					paperDiffFalgB=false;
					
				}else if(paperDiffLevB<0||paperDiffLevB>1){
					alert("B卷难度必须位于0-1之间")
					paperDiffFalgB=false;
				}
			
		}
		if(!orderFlag){
			alert("试题顺序未选择")
		}
		
		if(!scoresFlag){
			alert("总分不能为空，请选择试题信息");
		}
		
		if(paperName!=""&&paperDiffFalgA&&paperDiffFalgB&&orderFlag&&scoresFlag){
			resultMap["paperName"]=paperName;
			resultMap["paperDiffLevA"]=paperDiffLevA;
			resultMap["paperDiffLevB"]=paperDiffLevB;
			
			resultMap["sId"]=sId;
			resultMap["kps"]=kps;
			
			console.log(resultMap)
			var jsonData=JSON.stringify(resultMap);
			//console.log(jsonData)
			
			
			$.ajax({
				url:'generatePaperAuto',
				method:'post',
				contentType: "application/json; charset=utf-8",
				data:jsonData,
				success:function(data){
					//alert(data)
					if(data.rtnCode=="-9999"){
						alert(data.rtnMessage)
					}
					if(data.rtnCode=="0"){
						if(confirm(data.rtnMessage)){
							//点击确定按钮
							window.location.href="get"
						}
					}
				},
				error:function(){
					alert("error");
				}
			})
			
		}
		
}
