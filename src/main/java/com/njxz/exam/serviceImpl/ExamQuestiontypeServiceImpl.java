package com.njxz.exam.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njxz.exam.dao.ExamQuestiontypeMapper;
import com.njxz.exam.modle.ExamQuestiontype;
import com.njxz.exam.modle.ExamQuestiontypeExample;
import com.njxz.exam.modle.ExamQuestiontypeExample.Criteria;
import com.njxz.exam.service.ExamQuestiontypeService;

@Service("examQuestiontypeService")
public class ExamQuestiontypeServiceImpl implements ExamQuestiontypeService{

	@Autowired
	ExamQuestiontypeMapper eqtMapper;
	
	@Override
	public int add(ExamQuestiontype examQuestiontype) {
		// TODO Auto-generated method stub
		return eqtMapper.insert(examQuestiontype);
	}

	@Override
	public int add(List<ExamQuestiontype> eqtList) {
		// TODO Auto-generated method stub
		return eqtMapper.insertList(eqtList);
	}

	@Override
	public int countByEId(Long eId) {
		ExamQuestiontypeExample example=new ExamQuestiontypeExample();
		Criteria criteria=example.createCriteria();
		criteria.andExamIdEqualTo(eId);
			
		return (int)eqtMapper.countByExample(example);
	}

	@Override
	public List<ExamQuestiontype> allExamQuestiontypes(Long eId) {
		return eqtMapper.allExamQuestiontypes(eId);
	}

	@Override
	public int deleteExamQuestiontypesByEId(Long eId) {
		return eqtMapper.deleteExamQuestiontypesByEId(eId);
	}

}
