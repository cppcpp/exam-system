package com.njxz.exam.service;


import java.util.List;
import java.util.Map;

import com.njxz.exam.modle.Exam;
import com.njxz.exam.modle.User;

public interface ExamService {
	public int add(Exam exam);
	//试卷信息存入数据库---exam,exam_questons,exam_questiontype
	public boolean inToDB(Long uId,String paperName,int paperTotalScore,double paperDifficutty,Byte eStatus,Long sId,List<Long> qIdList,List<Map<String, Object>> qtList);
	//根据用户管理的科目取出试卷
	public List<Exam> getExamByUserId(User user);
	
	public Exam get(Long eId);
	//是否含有A或者B卷
	public boolean isHasExamType(byte examType);
	
}