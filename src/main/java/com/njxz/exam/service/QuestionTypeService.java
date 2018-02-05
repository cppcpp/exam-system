package com.njxz.exam.service;

import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;

import com.njxz.exam.modle.QuestionType;

public interface QuestionTypeService {
	public List<QuestionType> getAllQuestionTypes();
	
	public int insertQuestionType(QuestionType questionType);
	
	public int modify(QuestionType questionType);
	
	public int delete(String qId);
	
	public QuestionType getQuestionTypeById(String qId);
}
