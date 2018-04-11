package com.njxz.exam.service;

import java.util.List;

import com.njxz.exam.modle.Questions;

public interface QuestionService {
	public Questions get(Long qId);
	public int add(Questions questions);
	public int del(Long qId);
	public int modify(Questions questions);
	public List<Questions> checkAllQuesBySubjectId(Long sId);
	public List<Questions> checkAllQuesBySIdAnd(Long sId,Long qtId,int pageNum,int pageSize);
	
	//根据科目、题型、难易程度查题目
//	public List<Questions> checkAllQuesByQtAndqDL(Long sId,Long questionType,Double qDifficultyLevel);
//	public int countQuesByQtAndqDL(Long sId,Long questionType,Double qDifficultyLevel);
	//public List<Questions> checkAllQuesByQt(Long sId,Long questionType);
	
	//根据科目、题型、知识点、难易程度查题目
	public List<Questions> checkAllQuesByKpsAndQtAndDl(Long sId,List<Long> KnowPoints,Long questionType,Double qDiffiCultyLevel);
	public List<Questions> checkAllQuesByKpsAndQtAndDl(Long sId,List<Long> KnowPoints,Long questionType,Double qDiffiCultyLevel,int pageNum,int pageSize);
	public int countQuesByKpsAndQtAndDl(Long sId,List<Long> KnowPoints,Long questionType,Double qDiffiCultyLevel);
	
	//根据科目、题型（多个）、知识点查询题目
	public List<Questions> checkAllQuesByKpsAndQtAndDl(Long sId,List<Long> konwPoints,List<Long> quesTypes,List<Double> qDiffiCultyLevels);
	
	//题目的抽取次数
	public int countgetBy(Long qId);
	
	//根据试卷和题型和试卷类型取出试题，并根据难易度升序排序--和questions表外连接、
	public List<Questions> getQuestionsByEIdAndEQTId(Long eId,Long eqtId,int eType);
	
}
