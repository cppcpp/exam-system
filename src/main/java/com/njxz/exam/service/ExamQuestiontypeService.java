package com.njxz.exam.service;


import java.util.List;

import com.njxz.exam.modle.ExamQuestiontype;

public interface ExamQuestiontypeService {
	public int add(ExamQuestiontype examQuestiontype);
	
	//添加多条
	public int add(List<ExamQuestiontype> eqtList);
	
	//试卷题型个数
	public int countByEId(Long eId);
	
	//取出试卷所有题型--按照type_sort升序排列
	public List<ExamQuestiontype> allExamQuestiontypes(Long eId);
	
	//通过试卷id删除试卷的题型
	int deleteExamQuestiontypesByEId(Long eId);
	
	//根据试卷id+试题类型id修改试卷中的题型排序
	int modifyQTOrder(Long eId,Long qtId,int order);
}
