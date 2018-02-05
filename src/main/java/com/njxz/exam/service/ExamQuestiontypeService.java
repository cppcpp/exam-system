package com.njxz.exam.service;


import java.util.List;

import com.njxz.exam.modle.ExamQuestiontype;

public interface ExamQuestiontypeService {
	public int add(ExamQuestiontype examQuestiontype);
	
	//添加多条
	public int add(List<ExamQuestiontype> eqtList);
	
	//试卷题型个数
	public int countByEId(Long eId);
	
}
