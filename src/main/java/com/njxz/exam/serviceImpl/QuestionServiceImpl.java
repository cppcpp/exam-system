package com.njxz.exam.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.njxz.exam.dao.QuestionsMapper;
import com.njxz.exam.modle.Questions;
import com.njxz.exam.modle.QuestionsExample;
import com.njxz.exam.modle.QuestionsExample.Criteria;
import com.njxz.exam.service.QuestionService;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionsMapper mapper;

	@Override
	public int add(Questions questions) {
		return mapper.insert(questions);
	}

	@Override
	public int del(Long qId) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(qId);
	}

	@Override
	public int modify(Questions questions) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(questions);
	}

	@Override
	public List<Questions> checkAllQuesBySubjectId(Long sId) {
		QuestionsExample example = new QuestionsExample();
		Criteria criteria = example.createCriteria();
		criteria.andSIdEqualTo(sId);
		return mapper.selectByExample(example);
	}

	@Override
	public List<Questions> checkAllQuesBySIdAnd(Long sId, Long qtId,int pageNum,int pageSize) {
		//取出页面信息
		PageHelper.startPage(pageNum, pageSize);
				
		QuestionsExample example = new QuestionsExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andSIdEqualTo(sId);
		if (qtId!= -1) {
			criteria.andQuestionTypeIdEqualTo(qtId);
		}		
		List<Questions> lists= mapper.selectByExample(example);
		return lists;
		
	}

	@Override
	public Questions get(Long qId) {
		return mapper.selectByPrimaryKey(qId);
	}

	/*@Override
	public List<Questions> checkAllQuesByQtAndqDL(Long sId,Long questionType, Double qDifficultyLevel) {
		QuestionsExample example=new QuestionsExample();
		Criteria criteria= example.createCriteria();
		criteria.andSIdEqualTo(sId);
		if(questionType!=null) {
			criteria.andQuestionTypeIdEqualTo(questionType);
		}
		if(qDifficultyLevel!=null) {
			criteria.andQDifficultyLevelEqualTo(qDifficultyLevel);
		}
		return mapper.selectByExample(example);
	}
	
	@Override
	public int countQuesByQtAndqDL(Long sId, Long questionType, Double qDifficultyLevel) {
		QuestionsExample example=new QuestionsExample();
		Criteria criteria= example.createCriteria();
		criteria.andSIdEqualTo(sId);
		if(questionType!=null) {
			criteria.andQuestionTypeIdEqualTo(questionType);
		}
		if(qDifficultyLevel!=null) {
			criteria.andQDifficultyLevelEqualTo(qDifficultyLevel);
		}
		return (int)mapper.countByExample(example);
	}
*/
	
	/*@Override
	public List<Questions> checkAllQuesByQt(Long sId, Long questionType) {
		QuestionsExample example=new QuestionsExample();
		Criteria criteria= example.createCriteria();
		criteria.andSIdEqualTo(sId);
		criteria.andQuestionTypeIdEqualTo(questionType);
		return mapper.selectByExample(example);
	}*/

	@Override
	public List<Questions> checkAllQuesByKpsAndQtAndDl(Long sId, List<Long> KnowPoints, Long questionType,
			Double qDiffiCultyLevel) {
		QuestionsExample example=new QuestionsExample();
		Criteria criteria=example.createCriteria();
		
		if (sId!=null) {
			criteria.andSIdEqualTo(sId);
		}
		//知识点不为空
		if(KnowPoints!=null&&KnowPoints.size()>0) {
			criteria.andKonwledgePointIdIn(KnowPoints);
		}
		if(questionType!=null) {
			criteria.andQuestionTypeIdEqualTo(questionType);
		}
		if(qDiffiCultyLevel!=null) {
			criteria.andQDifficultyLevelEqualTo(qDiffiCultyLevel);
		}
		return mapper.selectByExample(example);
	}
	
	@Override
	public List<Questions> checkAllQuesByKpsAndQtAndDl(Long sId, List<Long> KnowPoints, Long questionType,
			Double qDiffiCultyLevel, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		QuestionsExample example=new QuestionsExample();
		Criteria criteria=example.createCriteria();
		
		if (sId!=null) {
			criteria.andSIdEqualTo(sId);
		}
		//知识点不为空
		if(KnowPoints!=null&&KnowPoints.size()>0) {
			criteria.andKonwledgePointIdIn(KnowPoints);
		}
		if(questionType!=null) {
			criteria.andQuestionTypeIdEqualTo(questionType);
		}
		if(qDiffiCultyLevel!=null) {
			criteria.andQDifficultyLevelEqualTo(qDiffiCultyLevel);
		}
		return mapper.selectByExample(example);
	}

	@Override
	public int countQuesByKpsAndQtAndDl(Long sId, List<Long> KnowPoints, Long questionType, Double qDiffiCultyLevel) {
		QuestionsExample example=new QuestionsExample();
		Criteria criteria=example.createCriteria();
		criteria.andSIdEqualTo(sId);
		if(KnowPoints!=null) {
			criteria.andKonwledgePointIdIn(KnowPoints);
		}
		if(questionType!=null) {
			criteria.andQuestionTypeIdEqualTo(questionType);
		}
		if(qDiffiCultyLevel!=null) {
			criteria.andQDifficultyLevelEqualTo(qDiffiCultyLevel);
		}
		return (int)mapper.countByExample(example);
	}

	@Override
	public List<Questions> checkAllQuesByKpsAndQtAndDl(Long sId, List<Long> konwPoints, List<Long> quesTypes,
			List<Double> qDiffiCultyLevels) {
		QuestionsExample example=new QuestionsExample();
		Criteria criteria=example.createCriteria();
		criteria.andSIdEqualTo(sId);
		if(konwPoints!=null) {
			criteria.andKonwledgePointIdIn(konwPoints);
		}
		if(quesTypes!=null) {
			criteria.andQuestionTypeIdIn(quesTypes);
		}
		if(qDiffiCultyLevels!=null) {
			criteria.andQDifficultyLevelIn(qDiffiCultyLevels);
		}
		return mapper.selectByExample(example);
	}

	@Override
	public int countgetBy(Long qId) {
		return mapper.countgetBy(qId);
	}

	@Override
	public List<Questions> getQuestionsByEIdAndEQTId(Long eId, Long eqtId,int eType) {
		// TODO Auto-generated method stub
		return mapper.getQuestionsByEIdAndEQTId(eId, eqtId,eType);
	}

}
