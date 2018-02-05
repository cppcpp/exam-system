package com.njxz.exam.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njxz.exam.dao.ExamQuestionsMapper;
import com.njxz.exam.modle.ExamQuestions;
import com.njxz.exam.service.ExamQuestionsService;

@Service("examQuestionsService")
public class ExamQuestionsServiceImpl implements ExamQuestionsService{

	@Autowired
	ExamQuestionsMapper examQuestionsMapper;
	
	@Override
	public int add(ExamQuestions examQuestions) {
		// TODO Auto-generated method stub
		return examQuestionsMapper.insert(examQuestions);
	}

	@Override
	public int add(List<ExamQuestions> eqList) {
		// TODO Auto-generated method stub
		return examQuestionsMapper.insertList(eqList);
	}

}
