package com.njxz.exam.service;

import java.util.List;

import com.njxz.exam.modle.KnowledgePoints;

public interface KnowledgePointsService {
	public int add(KnowledgePoints knowledgePoints);
	public List<KnowledgePoints> getKnowledgePointsBySIdPaging(String SId,int pageNum,int pageSize);
	public List<KnowledgePoints> getKnowledgePointsBySId(String SId);
	public int deleteByKId(String kId);
	public KnowledgePoints getKnowledgePointsByKId(String KId);
	public int updateByPrimaryKey(KnowledgePoints knowledgePoints);
	//（科目id,num,subNum）作为联合主键
	public boolean isExitKnowPoint(Byte num,Byte subNum,short sId);
	
}
