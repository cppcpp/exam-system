package com.njxz.exam.service;

import java.util.List;


import com.njxz.exam.modle.ExamQuestions;

public interface ExamQuestionsService {
	public int add(ExamQuestions examQuestions);
	//添加多条
	public int add(List<ExamQuestions> eqList);
	
	//通过试卷id删除试卷中的题目信息
	int deleteExamQuestionsByEId(Long eId);
}
