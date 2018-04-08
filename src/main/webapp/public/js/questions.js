$(function() {
	CKEDITOR.replace( 'qTitle' );
	
	// 初始化页面的几个信息
	getAllSubjects();
	
	getAllQuesTypes();
	
	getKnowsBySubjectId();
	
	getQDifficultyLevel();
});

// 得到所有科目信息
function getAllSubjects(){
	$.ajax({
			url : "subjects",
			method : "get",
			success : function(data) {
				//console.log("subjects:" + data.map + data.rtnCode + " " +data.rtnMessage);
				var subjects=data.map.subjects;
				
				$("#subjectSelect").append("<option value=''>请选择科目</option>")
				// 遍历json数据intoselect
				for (var i in subjects) {
					$("#subjectSelect").append("<option value="
							+ subjects[i].sId + ">" + subjects[i].sTitle
							+ "</option>");
				}
			},
			error:function(){
				
			}
		});
}

// 点击科目信息，得到相应的知识点信息
function getKnowsBySubjectId(){
	// 科目信息的点击事件
	$("#subjectSelect").change(function(){
		// 获取option中的值
		var sId=$("#subjectSelect").val()
		// console.log("科目Id:"+sId);
		
		
		$.ajax({
			url:"knowledgPoints/"+sId,
			method:"get",
			success:function(data){
//				console.log(data.rtnCode+"  "+data.rtnMessage+"   "+data.map)
				var knows=data.map.knows;
				// 这边应该替换掉select中的值
				$("#kPointsSelect").html("<option value=''>请选择知识点</option>");
				for(var i in knows){
//					console.log(knows[i].kNum+"."+knows[i].kSubNum+":"+knows[i].kTitle);
					if(knows[i].kSubNum==0){
//						console.log(knows[i].kSubNum)
						$("#kPointsSelect").append("<option value="+knows[i].kId+">"+knows[i].kNum+"."+knows[i].kSubNum+"."+knows[i].kTitle+"</option>");
					}else{
						$("#kPointsSelect").append("<option value="+knows[i].kId+">"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+knows[i].kNum+"."+knows[i].kSubNum+"."+knows[i].kTitle+"</option>");
					}
					
				}
			},
			error:function(){
			}
			
		})
		
	})
	
}


// 得到所有题型信息
function getAllQuesTypes(){
	$.ajax({
		url:"questionTypes",
		method:"get",
		success:function(data){
			var quesTypes=data.map.quesTypes;
			// console.log("quesTypes:"+quesTypes)
			$("#quesTypeSelect").append("<option value=''>请选择题型信息</option>");
			for(var i in quesTypes){
				$("#quesTypeSelect").append("<option value="+quesTypes[i].tId+">"+quesTypes[i].tTitle+"</option>")
			}
			
			//画出答案
			getAnswerByQType();
			
		},
		error:function(){
		}
	});
}

//初始化难易程度
function getQDifficultyLevel(){
	$.ajax({
		url:"qDiffLevel",
		method:"get",
		success:function(data){
			//console.log(data.map)
			$("#qDifficultyLevel_veryeasy").val(data.map.veryEasy);
			$("#qDifficultyLevel_easy").val(data.map.easy);
			$("#qDifficultyLevel_medium").val(data.map.medium);
			$("#qDifficultyLevel_hard").val(data.map.hard);
			$("#qDifficultyLevel_veryhard").val(data.map.veryHard);
		},
		error:function(){
		}
	})
}

//声明全局变量
var i=1;
//通过题目类型得到不同的答案---写死
function getAnswerByQType(){
	$("#quesTypeSelect").change(function(){
		var quesType=$("#quesTypeSelect").val();
		var quesTypeCN=$("#quesTypeSelect").find("option:selected").text();
		console.log("题目类型："+quesType+"  "+quesTypeCN)
		var answer_style='';
		switch(quesTypeCN){
			//选择题
			case "单选题":
				answer_style="<select name='qAnswer'>" +
						"<option value='A'>A</option>" +
						"<option value='B'>B</option>" +
						"<option value='C'>C</option>" +
						"<option value='D'>D</option>" +
						"</select>"
			break;
			case "多选题":
//				alert(quesTypeCN)
				answer_style="<input type='text' name='qAnswer' placeholder='多选答案形如ABCD格式'>"
			break;
			case "选择题":
//				alert(quesTypeCN)
				answer_style="<input type='text' name='qAnswer' style='width:300px' placeholder='单选答案形如A  多选答案形如ABCD格式'>"
			break;
			case "填空题":
//				alert(quesTypeCN)
				answer_style="空"+i+": <input type='text' name='qAnswer'> <img src='../public/media/image/add_s.png' onclick='addqAnswer()'><br>"
			break;
			case "判断题":
				answer_style="<select name='qAnswer'>" +
						"<option value='&#935'>&#935</option>" +
						"<option value='&#8730'>&#8730</option>" +
						"</select>"
			break;
			default:
				answer_style="<textarea name='qAnswer' id='qAnswer1' rows='10' cols='80'>";
		}
		$("#qAnswer_div").html(answer_style)
		if($("#qAnswer1")){
			CKEDITOR.replace('qAnswer1');
		}
	});
}

//填空题答案添加方框
function addqAnswer(){
	i+=1;
	$("#qAnswer_div").append("空"+i+": <input type='text' name='qAnswer'> <img src='../public/media/image/add_s.png' onclick='addqAnswer()'><br>")
}

