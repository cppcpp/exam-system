package com.njxz.exam.serviceImpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njxz.exam.dao.KnowledgePointsMapper;
import com.njxz.exam.modle.KnowledgePoints;
import com.njxz.exam.modle.KnowledgePointsExample;
import com.njxz.exam.modle.KnowledgePointsExample.Criteria;
import com.njxz.exam.service.KnowledgePointsService;

@Service("KnowledgePointsService")
public class KnowledgePointsServiceImpl implements KnowledgePointsService{
	
	@Autowired
	public KnowledgePointsMapper mapper;
	
	public int add(KnowledgePoints knowledgePoints) {
		return mapper.insert(knowledgePoints);
	}
	
	//根据subjectId查出所有的知识点----按照num,subNum对数据库排序
	public List<KnowledgePoints> getKnowledgePointsBySId(String SId){
		
		return mapper.getKnowledgePointsBySId(Short.valueOf(SId));
	}

	public int deleteByKId(String kId) {
		return  mapper.deleteByPrimaryKey(Long.parseLong(kId));
	}
	
	public KnowledgePoints getKnowledgePointsByKId(String KId) {
		return mapper.selectByPrimaryKey(Long.parseLong(KId));
	}

	public int updateByPrimaryKey(KnowledgePoints knowledgePoints) {
		return mapper.updateByPrimaryKey(knowledgePoints);
	}

	public boolean isExitKnowPoint(Byte num, Byte subNum, short sId) {
		KnowledgePointsExample example=new KnowledgePointsExample();
		Criteria criteria= example.createCriteria();
		criteria.andSubjectIdEqualTo(sId);
		criteria.andKNumEqualTo(num);
		criteria.andKSubNumEqualTo(subNum);
		List<KnowledgePoints> list=mapper.selectByExample(example);
		return list.size()>0;
	}

	
}
