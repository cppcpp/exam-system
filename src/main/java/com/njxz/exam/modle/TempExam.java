package com.njxz.exam.modle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author piperChan
 *临时的试卷表
 */
public class TempExam {
	private Long eId;
	private int totalScore;
	//试卷的难度系数
	private double difficultyLevel;
	//试卷中所有的知识点id----知识点用set存储--
	private Set<Long> knowPoints; 
	//各种题型的题目数量
	private List<Map<String, Object>> qtList;
	//题目
	private List<Questions> questionList;
	//适应度
	private double adapterDegree;
	//知识点覆盖率
	private double kpCoverage;
	
	public TempExam() {
		this.questionList=new ArrayList<Questions>();
		this.adapterDegree=0.0;
		this.kpCoverage=0.0;
	}
	
	public TempExam(Long eId, int totalScore, double difficultyLevel, Set<Long> knowPoints,
			List<Map<String, Object>> qtList) {
		this.eId = eId;
		this.totalScore = totalScore;
		this.difficultyLevel = difficultyLevel;
		this.knowPoints = knowPoints;
		this.qtList = qtList;
		this.questionList=new ArrayList<Questions>();
		this.adapterDegree=0.0;
		this.kpCoverage=0.0;
	}
	
	
	public TempExam(Long eId, int totalScore, double difficultyLevel, Set<Long> knowPoints,
			List<Map<String, Object>> qtList, List<Questions> questionList, double adapterDegree, double kpCoverage) {
		this.eId = eId;
		this.totalScore = totalScore;
		this.difficultyLevel = difficultyLevel;
		this.knowPoints = knowPoints;
		this.qtList = qtList;
		this.questionList = questionList;
		this.adapterDegree = adapterDegree;
		this.kpCoverage = kpCoverage;
	}


	public Long geteId() {
		return eId;
	}
	public void seteId(Long eId) {
		this.eId = eId;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	
	//设置现有试卷总分
	public void setTotalScore1() {
		Long qtId;
		int sum=0;
		for(Questions questions:questionList) {
			qtId=questions.getQuestionTypeId();
			for(Map<String, Object> map:qtList) {
				if(qtId.toString().equals(map.get("qtId").toString())) {
					sum+=Integer.parseInt(map.get("qtScore").toString());
				}
			}
		}
		this.totalScore=sum;
	}
	
	public double getDifficultyLevel() {
		return difficultyLevel;
	}
	public void setDifficultyLevel(double difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	//设置试卷的难度系数(所有题目分数*难度系数/总分)
	public void setDifficultyLevel1() {
		double difficult=0.0;
		Long qtId;
		for(Questions questions:questionList) {
			qtId=questions.getQuestionTypeId();	
			for(Map<String, Object> map:qtList) {
				if(qtId.toString().equals(map.get("qtId").toString())) {
					difficult+=questions.getqDifficultyLevel()*(Double.parseDouble(map.get("qtScore").toString()));
				}
			}
		}
		this.difficultyLevel = difficult/totalScore;
	}
	
	public Set<Long> getKnowPoints() {
		return knowPoints;
	}

	public void setKnowPoints(Set<Long> knowPoints) {
		this.knowPoints = knowPoints;
	}
	
	//设置试卷的知识点
	public void setKnowPoints1() {
		Set<Long> knowPoints=new HashSet<>();
		for(Questions questions:questionList) {
			knowPoints.add(questions.getKonwledgePointId());
		}
		this.knowPoints=knowPoints;
	}

	public List<Map<String, Object>> getQtList() {
		return qtList;
	}

	public void setQtList(List<Map<String, Object>> qtList) {
		this.qtList = qtList;
	}

	public List<Questions> getQuestionList() {
		return questionList;
	}

	public double getAdapterDegree() {
		return adapterDegree;
	}

	public double getKpCoverage() {
		return kpCoverage;
	}

	public void setQuestionList(List<Questions> questionList) {
		this.questionList = questionList;
	}

	public void setAdapterDegree(double adapterDegree) {
		this.adapterDegree = adapterDegree;
	}

	public void setKpCoverage(double kpCoverage) {
		this.kpCoverage = kpCoverage;
	}
	
	
}
