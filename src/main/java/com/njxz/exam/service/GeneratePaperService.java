package com.njxz.exam.service;

import java.util.List;

import com.njxz.exam.modle.Questions;
import com.njxz.exam.modle.TempExam;

public interface GeneratePaperService {
	//初始种群
	public List<TempExam> cszq(int count,TempExam expectedExam,List<Questions> questionsDB);
	//选择算子
	public List<TempExam> select(List<TempExam> unitList,int count);
	//交叉算子
	public List<TempExam> cross(List<TempExam> unitList,int count,TempExam expectedExam);
	//变异算子
	public List<TempExam> change(List<TempExam> unitList,List<Questions> questionDB,TempExam expectedExam);
	//判断结束
	public boolean isEnd(List<TempExam> unitList,double expandAdapterDegree);
	
}
