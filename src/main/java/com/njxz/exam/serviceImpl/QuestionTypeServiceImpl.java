package com.njxz.exam.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njxz.exam.dao.QuestionTypeMapper;
import com.njxz.exam.modle.QuestionType;
import com.njxz.exam.service.QuestionTypeService;

@Service("questionTypeService")
public class QuestionTypeServiceImpl implements QuestionTypeService{

	@Autowired
	QuestionTypeMapper mapper;
	
	public List<QuestionType> getAllQuestionTypes() {
		
		return mapper.selectAllQuestionType();
	}

	public int insertQuestionType(QuestionType questionType) {
		
		return mapper.insertSelective(questionType);
	}

	public int modify(QuestionType questionType) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(questionType);
	}

	public int delete(String qId) {
		return mapper.deleteByPrimaryKey(Long.parseLong(qId));
	}

	public QuestionType getQuestionTypeById(String qId) {
		return mapper.selectByPrimaryKey(Long.parseLong(qId));
	}

}
