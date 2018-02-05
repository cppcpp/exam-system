package com.njxz.exam.modle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author piperChan
 *初始种群的类
 */
public class Unit {
	private Long id;
	//适应度
	private double adapterDegree;
	//知识点覆盖率
	private double kpCoverage;
	//难度系数
	private double difficultyLevel;
	//题目
	private List<Questions> questionList;
	
	public Unit() {
		this.adapterDegree=0.00;
		this.kpCoverage=0.00;
		this.questionList=new ArrayList<Questions>();
	}
	
	
	public Unit(Long id, double adapterDegree, double kpCoverage, List<Questions> questionList) {
		super();
		this.id = id;
		this.adapterDegree = adapterDegree;
		this.kpCoverage = kpCoverage;
		this.questionList = questionList;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getAdapterDegree() {
		return adapterDegree;
	}
	public void setAdapterDegree(double adapterDegree) {
		this.adapterDegree = adapterDegree;
	}
	public double getKpCoverage() {
		return kpCoverage;
	}

	public void setKpCoverage(double kpCoverage) {
		this.kpCoverage = kpCoverage;
	}

	public double getDifficultyLevel() {
		return difficultyLevel;
	}


	public void setDifficultyLevel(double difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}


	public List<Questions> getQestionList() {
		return questionList;
	}
	public void setQestionList(List<Questions> qestionList) {
		this.questionList = qestionList;
	}

}
