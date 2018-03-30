$(function(){
	var sId;//select选中的科目信息
	var qtId,qTitle,qAnswer,qType,qDifficultyEN;//全部变量--题型Id等
	var kps=new Array();//知识点
	var qts=new Array();//存放生成的试卷中所有题型
	var selectQuestions=new Array();//存放被选中的题目
	var pageNum,pageSize;
	
	//数组对象   的    是否包含方法
	Array.prototype.contains = function ( needle ) {
	  for (i in this) {
	    if (this[i] == needle) return true;
	  }
	  return false;
	}
	
	//数组对象  的  移除方法
	Array.prototype.removeByValue = function(val) {
	  for(var i=0; i<this.length; i++) {
	    if(this[i] == val) {
	      this.splice(i, 1);
	      break;
	    }
	  }
	}
	
	//查询知识点信息
	$("#subjectSelect").change(function(){
		sId=$("#subjectSelect").val();
		getKnowsBySId();
	})
	
	//题型
	/*$("#qtSelect").change(function(){
		qtId=$("#qtSelect").val();
	})*/
	
	//查询试题信息
	$("#questionsCheckButton").click(function(){
		//获取知识点信息
		//先清空知识点
		kps=[];
		$("input[name='konwledgePoint']:checked").each(function(){
			kps.push($(this).val())
		})
		
		//题型id
		qtId=$("#qtSelect").val();
		//科目
		sId=$("#subjectSelect").val();
		
		//console.log(kps)
		//查询时，默认值：pageNum=1，pageSize=10
		pageNum=1;pageSize=10;
		getQuestionsBySIdAndKnowsAndQt(sId,qtId,kps,selectQuestions,pageNum,pageSize);
	})
	
	
	//设置题型分数  input  失去焦点事件
	$("input[name='changeQtScore']").blur(function(){
		var qtScore,qtNum,qtId,veryEasyNum,esayNum,mediumNum,hardNum,veryHardNum,paperScore=0;
		if(isNaN(parseInt($(this).val()))){
			alert("您输入的非数值，请重新设值")
		}else{
			qtScore=parseInt($(this).val());
		}
		//console.log("qtScore:"+qtScore)
		qtId=$(this).attr("id").split("_")[3];
		//console.log($(this).attr("id"))
		//console.log("qtId:"+qtId)
		
		veryEasyNum=parseInt($.trim($("#set_qt_"+qtId+"_veryEasy").text()));
		easyNum=parseInt($.trim($("#set_qt_"+qtId+"_easy").text()));
		mediumNum=parseInt($.trim($("#set_qt_"+qtId+"_medium").text()));
		hardNum=parseInt($.trim($("#set_qt_"+qtId+"_hard").text()));
		veryHardNum=parseInt($.trim($("#set_qt_"+qtId+"_veryHard").text()));
		
		qtNum=parseInt(veryEasyNum+easyNum+mediumNum+hardNum+veryHardNum);
		
		//该题型总个数
		$("#set_qt_"+qtId+"_total_num").text(qtNum);
		
		//该题型总分
		$("#set_total_score_"+qtId).text(qtNum*qtScore)
		
		//试卷总分
		$("td[name='paperScore']").each(function(){
			paperScore+=parseInt($.trim($(this).text()));
		})
		
		$("#set_total_score").text(paperScore);
		
	})
	
	//生成试卷按钮点击
	$("#generate_paper_button").click(function(){
		generatePaperSelf();
	})
	

	//根据科目Id查询所有的知识点信息
	function  getKnowsBySId(){
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
					$("#kp_div .modal-body").append("<button class='btn btn-default' data-dismiss='modal'>确认</button>")
				}
				//全选功能
				clickAll();
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

	//根据  科目+知识点+题型查出所有题目
	function getQuestionsBySIdAndKnowsAndQt(sId,qtId,kps,selectQuestions,pageNum,pageSize){
		var sIdFlag=true;
		if(sId==null){
			sIdFlag=false;
			alert("必须选择科目");
		}
		var data={
			"sId":sId,
			"qtId":qtId,
			"kps":kps,
			"pageNum":pageNum,
			"pageSize":pageSize
		}
		//生成json数据
		var jsonData=JSON.stringify(data);
		
		if(sIdFlag){
			$.ajax({
				url:"getQuesBySIdAndKnowsAndQt",
				method:"post",
				data:jsonData,
				contentType:"application/json; charset=utf-8",
				success:function(data){
//					console.log(data)
					//解析数据
					parseQuestionsData(data,sId,qtId,kps,selectQuestions);
					
					//给查询的模态框赋值
					dataForModel();
					
					//改变试卷的试题信息
					changePaperInfo();
				},
				error:function(){
					
				}
			})
		}
	}

	//解析后台传回的  试题，分页信息
	function parseQuestionsData(data,sId,qtId,kps,selectQuestions){
		//var list=data.list;
		console.log(data)
		var questions=data.map.questionsList;//题目信息
		var page=data.map.page;//页面信息
		var state,qTitle,qAnswer;//试卷状态：可用，已选   qTitle:过滤特殊字符展示
		var qtNum,qtScore,qtTotalScore,totalScore;//题型个数，题型分数，题型总分，试卷总分
		
		//解析题目数据展示在前台
		$("#questionsTable tbody").html("")
		/*$("#questionsTable tbody").append(
					"<th colspan='5'>试题信息</th>"
					+"<tr>"
						+"<td>题目</td>"
						+"<td>难易度</td>"
						+"<td>抽取次数</td>"
						+"<td>状态</td>"								
						+"<td>操作</td>"
					+"</tr>");*/
		
		$.each(questions,function(item){
			if(selectQuestions.contains(questions[item].qId)){
				state="<font color='#FF0000'>已添加</font>";
			}else{
				state="<font color='#3385ff'>可用</font>";
			}
			//过滤掉qTitle中的特殊字符
			qTitle=questions[item].qTitle.replace(/[\-\_\,\!\|\~\`\(\)\#\$\%\^\&\*\{\}\:\;\"\L\<\>\?]/g, '');
			qAnswer=questions[item].qAnswer.replace(/[\-\_\,\!\|\~\`\(\)\#\$\%\^\&\*\{\}\:\;\"\L\<\>\?]/g, '');
			
			$("#questionsTable tbody").append("<tr>" +
				"<td class='fix_td_class' title='"+questions[item].qTitle+"'>" +qTitle+"</td>"+
				"<td class='fix_td_class' title='"+questions[item].qAnswer+"'>" +qAnswer+"</td>"+
				"<td title='"+questions[item].questionTypeId+"'>"+questions[item].questionTypeCN+"</td>"+
				"<td title='"+questions[item].qDiffocultyLevelEN+"'>"+questions[item].qDifficultyLevelCN+"</td>"+
				"<td>"+questions[item].count +"</td>"+
				"<td id='question_state"+questions[item].qId+"'>"+state+"<td>"+
				"<button class='btn' data-toggle='modal' data-target='#detailModal'>查</button>" +
				"<button class='btn' id='questionsAdd_"+questions[item].qId+"' name='questionAdd'>添</button>"+
				"<button class='btn' id='questionRemove_"+questions[item].qId+"' name='questionRemove'>移</button>"+
				"</tr>");
		})
		
		//分页
		$("#questionsTable tfoot tr td").html("")
		$("#questionsTable tfoot tr td").append("<a id='firstPage' style='cursor: pointer;color:#3385ff'>首页</a>|<a id='prePage' style='cursor: pointer;color:#3385ff'>上一页</a>|<a id='nextPage' style='cursor: pointer;color:#3385ff'>下一页</a>|<a id='lastPage' style='cursor: pointer;color:#3385ff'>尾页</a><br>共"+page.total+"条数据，当前为第"+page.pageNum+" 页，共"+page.pages+" 页")	
		
		var firstPage=page.firstPage;
		var prePage=page.prePage;
		var nextPage=page.nextPage;
		var lastPage=page.lastPage;
		//console.log(firstPage+"  "+prePage+"  "+nextPage+"  "+lastPage)
		$("#firstPage").click(function(){
			getQuestionsBySIdAndKnowsAndQt(sId,qtId,kps,selectQuestions,firstPage,pageSize);
		})
		
		$("#prePage").click(function(){
			getQuestionsBySIdAndKnowsAndQt(sId,qtId,kps,selectQuestions,prePage,pageSize);
		})
		
		$("#nextPage").click(function(){
			getQuestionsBySIdAndKnowsAndQt(sId,qtId,kps,selectQuestions,nextPage,pageSize);
		})
		
		$("#lastPage").click(function(){
			getQuestionsBySIdAndKnowsAndQt(sId,qtId,kps,selectQuestions,lastPage,pageSize);
		})
	}
	
	//赋值给模态框
	function dataForModel(){
		$("#questionsTable tbody tr").click(function(){
			var td=$(this).find("td");
			//获取指定id的孩子节点
			qTitle=td.eq(0).attr("title");
			qAnswer=td.eq(1).text();
	
//			console.log("模态框")
		/*	console.log("问题:"+qTitle)
			console.log("答案："+qAnswer)
			console.log("qType:"+qType+"  难度系数："+qDifficultyEN)*/
			
			//给模态框中的值设值
			$("#qTitle").html(qTitle);
			$("#qAnswer").html(qAnswer);
		})
	}
	
	//更新试题信息
	function changePaperInfo(){
		//试题添加
		if($("button[name='questionAdd']")){
			$("button[name='questionAdd']").click(function(){
				var td,qtId,qtScore,qtNum,veryEasyNum,esayNum,mediumNum,hardNum,veryHardNum,qDifficultyEN,qId,paperScore=0;
				
				td=$(this).parent().parent().find("td");
				qtId=td.eq(2).attr("title");//题型id
				qDifficultyEN=td.eq(3).attr("title");
				qId=$(this).attr("id").split("_")[1] ;//问题id
				
				if(!selectQuestions.contains(qId)){
					//加入题目数组中
					selectQuestions.push(qId)
//					alert(selectQuestions)
					//更新试题状态
					$("#question_state"+qId).html("<font color='#FF0000'>已添加</font>")
					//更新试卷信息
					qtNum=parseInt($.trim($("#set_qt_"+qtId+"_"+qDifficultyEN).text()));
					//console.log("qtNum"+qtNum+"   qType:"+qType+"  qDifficultyEN:"+qDifficultyEN)
					$("#set_qt_"+qtId+"_"+qDifficultyEN).text(qtNum+=1);
					
					veryEasyNum=parseInt($.trim($("#set_qt_"+qtId+"_veryEasy").text()));
					easyNum=parseInt($.trim($("#set_qt_"+qtId+"_easy").text()));
					mediumNum=parseInt($.trim($("#set_qt_"+qtId+"_medium").text()));
					hardNum=parseInt($.trim($("#set_qt_"+qtId+"_hard").text()));
					veryHardNum=parseInt($.trim($("#set_qt_"+qtId+"_veryHard").text()));
					qtNum=parseInt(veryEasyNum+easyNum+mediumNum+hardNum+veryHardNum);
					
					qtScore=parseInt($.trim($("#set_qt_score_"+qtId).val()));
					
					//该题型总个数
					$("#set_qt_"+qtId+"_total_num").text(qtNum);
					
					//该题型总分
					$("#set_total_score_"+qtId).text(qtNum*qtScore);
					
					//试卷总分
					$("td[name='paperScore']").each(function(){
						paperScore+=parseInt($.trim($(this).text()));
					})
					$("#set_total_score").text(paperScore);
				}
				
			})
		}
		
		//试题移除
		if($("button[name='questionRemove']")){
			$("button[name='questionRemove']").click(function(){
				var td,qtId,qtScore,qtNum,veryEasyNum,esayNum,mediumNum,hardNum,veryHardNum,qDifficultyEN,qId,paperScore=0;
				td=$(this).parent().parent().find("td");
				qId=$(this).attr("id").split("_")[1] ;
				qtId=td.eq(2).attr("title");
				qDifficultyEN=td.eq(3).attr("title");
				
				//从题目数组中移除
				if(selectQuestions.contains(qId)){
					selectQuestions.removeByValue(qId)
//					alert(selectQuestions)
					//更新试题状态
					$("#question_state"+qId).html("<font color='#3385ff'>可用</font>")
					//更新试卷信息
					qtNum=parseInt($.trim($("#set_qt_"+qtId+"_"+qDifficultyEN).text()));
					if(qtNum>0){
						$("#set_qt_"+qtId+"_"+qDifficultyEN).text(qtNum-=1);
						
						veryEasyNum=parseInt($.trim($("#set_qt_"+qtId+"_veryEasy").text()));
						easyNum=parseInt($.trim($("#set_qt_"+qtId+"_easy").text()));
						mediumNum=parseInt($.trim($("#set_qt_"+qtId+"_medium").text()));
						hardNum=parseInt($.trim($("#set_qt_"+qtId+"_hard").text()));
						veryHardNum=parseInt($.trim($("#set_qt_"+qtId+"_veryHard").text()));
						qtNum=parseInt(veryEasyNum+easyNum+mediumNum+hardNum+veryHardNum);
						
						qtScore=parseInt($.trim($("#set_qt_score_"+qtId).val()));
						
						//该题型总个数
						$("#set_qt_"+qtId+"_total_num").text(qtNum);
						//该题型总分
						$("#set_total_score_"+qtId).text(qtNum*qtScore);
						
						//试卷总分
						$("td[name='paperScore']").each(function(){
							paperScore+=parseInt($.trim($(this).text()));
						})
						$("#set_total_score").text(paperScore);
						
					}
				}
			})
		}
	}
	
	//生成试卷
	function generatePaperSelf(){
		var qtId;
		var qtIds=new Array();
		var flag=true;
		var qtInfo=[];
		var resultData={},jsonData;
		var paperName=$("#paper_name").val();
		var paperTotalScore=parseInt($.trim($("#set_total_score").text()));
		var sId=$("#subjectSelect").val();//科目Id
		
		if(paperName==""){
			alert("试卷标题不能为空");
			flag=false;
			return ;
		}
		if(paperTotalScore<=0){
			alert("您还没有选择任何试题");
			flag=false;
			return ;
		}
		//先清空
		qtIds=[];
		$("td[name='paperScore']").each(function(){
			//分数大于0代表有此试题--校验
			if(parseInt($.trim($(this).text()))>0){
				qtId=$(this).attr("id").split("_")[3];
				if(parseInt($.trim($("#set_qt_"+qtId+"_order").val()))==0){
					alert("您有试题顺序未选择");
					flag=false;
//					return;
				}
				if(parseInt($.trim($("#set_qt_score_"+qtId).text()))==0){
					alert("您有题型的分值未设置");
					flag=false;
//					return;
				}
				qtIds.push(qtId)
			}
		})
		
		//校验成功
		if(flag){
			console.log("qtIds:"+qtIds)
			qtIds.forEach(function(item){
				var tempMap={};
				tempMap["qtId"]=item;
				tempMap["qtScore"]=parseInt($.trim($("#set_qt_score_"+item).val()));
				tempMap["qtNum"]=parseInt($.trim($("#set_qt_"+item+"_total_num").text()));
				tempMap["qtOrder"]=parseInt($.trim($("#set_qt_"+item+"_order").val()));
				qtInfo.push(tempMap)
			})
				
			resultData["qtInfo"]=qtInfo;
			resultData["paperName"]=paperName;
			resultData["paperTotalScore"]=paperTotalScore;
			resultData["qIds"]=selectQuestions;
			resultData["sId"]=sId;
			
			console.log(resultData);
			
			jsonData=JSON.stringify(resultData);
			
			$.ajax({
				url:'generatePaperSelf',
				method:'post',
				contentType:'application/json;charset=utf-8',
				data:jsonData,
				success:function(data){
					console.log("返回data："+data)
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
				}
				
			})
			
			
		}
	}
	
})//function结束



