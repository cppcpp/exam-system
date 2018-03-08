package com.njxz.exam.serviceImpl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njxz.exam.modle.Questions;
import com.njxz.exam.modle.TempExam;
import com.njxz.exam.service.GeneratePaperService;
import com.njxz.exam.service.QuestionService;
import com.njxz.exam.util.Constants;
import com.njxz.exam.util.StringUtil;

@Service("generatePaperService")
public class GeneratePaperServiceImpl implements GeneratePaperService {
	@Autowired
	QuestionService questionService;
	
	

	/**
	 * @author piperChan count:种群数量 expectExam：期望的试卷 questionsDB：题库
	 *         kpCoverageRate:知识点所占权重 difficultyRate：难度系数所占权重
	 */
	public List<TempExam> cszq(int count, TempExam expectedExam, List<Questions> questionsDB) {
		// 种群
		List<TempExam> unitList = new ArrayList<>();
		// 各种题型信息
		List<Map<String, Object>> qtList = expectedExam.getQtList();
		// 知识点
		Set<Long> kpSet = expectedExam.getKnowPoints();
		TempExam unit;
		Random random = new Random();
		Long qtId;// 题型id
		Map<String, Object> qtMap;// 期望题型信息
		int qtNum, index;
		Questions tempQues;

		for (int j = 0; j < count; j++) {
			System.out.println("第"+j+"次产生种群个体========");
			unit = new TempExam();// 种群个体
			unit.seteId(StringUtil.seqGenerate());
			unit.setAdapterDegree(0.0);
			List<Questions> questionsList = unit.getQuestionList();

			// 总分限制
			while (expectedExam.getTotalScore() != unit.getTotalScore()) {
				System.out.println("总分限制++++"+expectedExam.getTotalScore()+"+++++"+unit.getTotalScore());
				// 清空试题信息
				unit.setQuestionList(null);
				// 各种题型
				for (Map<String, Object> map : qtList) {
					qtId = Long.parseLong(map.get("qtId").toString());

					// 题型信息
					qtMap = getQtById(qtList, qtId);
					qtNum = Integer.parseInt(qtMap.get("qtNum").toString());

					// 题库中该题型题目
					List<Questions> oneTypeQuestions = getOneTypeQuestions(questionsDB, qtId, kpSet);

					for (int i = 0; i < qtNum; i++) {
						// 从[0,oneTypeQuestions.size()-1)中任意取一个数
						//左含又不含
						//如果right<=0抛出异常
						index = random.nextInt(oneTypeQuestions.size());

						questionsList.add(oneTypeQuestions.get(index));

						// 将放入题库的题目排到末尾，避免选择重复题目
						tempQues = oneTypeQuestions.get(qtNum - i - 1);
						oneTypeQuestions.set(qtNum - i - 1, oneTypeQuestions.get(index));
						oneTypeQuestions.set(index, tempQues);
					}
				}
				//将题目放进unit中
				unit.setQuestionList(questionsList);
				//各种题型信息
				unit.setQtList(qtList);
				// 设置种群个体的得分
				unit.setTotalScore1();
				// 设置种群个体的难度系数
				unit.setDifficultyLevel1();
				// 设置种群个体知识点
				unit.setKnowPoints1();
			}
			unitList.add(unit);
		}

		// 设置知识点覆盖率以及适应度
		unitList = getKPCoverage(unitList, expectedExam);
		unitList = getAdaptionDegree(unitList, expectedExam, Constants.KP_COVERAGE_RATE, Constants.DIFFICULTY_RATE);
		return unitList;
	}

	/*
	 * 选择算子（轮盘赌选择） 适应度越强被选到的概率越大 unitList:初代种群 count:选择的次数（下一代种群的大小）
	 * 返回值：List<TempExam> 进入下一代的种群
	 * 
	 */
	public List<TempExam> select(List<TempExam> unitList, int count) {
		System.out.println("选择");
		List<TempExam> selectedUnitList = new ArrayList<>();

		// 种群的适应度之和
		double allAdaptionDegree = 0;
		for (TempExam unit : unitList) {
			allAdaptionDegree += unit.getAdapterDegree();
		}

		Random random = new Random();
		int count1=0;
		// 次数限制
		while (selectedUnitList.size() != count) {
			//System.out.println("选择种群的数量："+selectedUnitList.size());
			count1+=1;
			//选择次数超过10000次，直接退出
			if(count1>10000) {
				break;
			}
			
			double degree = 0.00;
			// 随机产生一个概率在0-allAdaptionDegree
			double randomDegree = random.nextInt(100) * 0.01 * allAdaptionDegree;

			for (int i = 0; i < unitList.size(); i++) {
				degree += unitList.get(i).getAdapterDegree();
				if (randomDegree <= degree) {

					// 不重复选择
					if (!selectedUnitList.contains(unitList.get(i))) {
						selectedUnitList.add(unitList.get(i));
					}
					break;
				}
			}

		}

		return selectedUnitList;
	}

	/*
	 * 交叉算子 一套试卷作为染色体，每一道题作为基因 按照题型分段，每种题型单点交叉，整套试卷就表现为分段多点交叉
	 * 交叉点选择：在[0,N-2]（N为题目数量）产生随机数r，交换r位置的两个题目 交叉后很可能存在重复题目而冲突--重新选择题目（知识点相同，类型相同）
	 * 
	 * unitList:初始种群 count:交叉后产生新的种群个体数量 expectedExam：期望试卷
	 * 
	 */
	public List<TempExam> cross(List<TempExam> unitList, int count, TempExam expectedExam) {
		System.out.println("交叉");
		List<TempExam> crossedUnitList = new ArrayList<>();
		Random random = new Random();
		List<Map<String, Object>> qtList = expectedExam.getQtList();// 不同题型的信息
		int count1=0;
		
		// 交叉后种群大小限制
		while (crossedUnitList.size() < count) {
			count1+=1;
			//交叉次数过多，无法产生足够数量的交叉种群个数
			if(count1>10000) {
				break;
			}
			
			
			// 随机选择两个染色体
			int indexOne = random.nextInt(unitList.size());
			int indexTwo = random.nextInt(unitList.size());
			TempExam unitOne, unitTwo;

			if (indexOne != indexTwo) {
				unitOne = unitList.get(indexOne);
				unitTwo = unitList.get(indexTwo);

				// 随机选择交叉位置---种群中题目数量都一样
				int crossPositon = random.nextInt(unitOne.getQuestionList().size() - 1);

				// 保证交叉的分数合相同
				double scoreOne = getScoreById(qtList, unitOne.getQuestionList().get(crossPositon).getQuestionTypeId())
						+ getScoreById(qtList, unitOne.getQuestionList().get(crossPositon + 1).getQuestionTypeId());
				double scoreTwo = getScoreById(qtList, unitTwo.getQuestionList().get(crossPositon).getQuestionTypeId())
						+ getScoreById(qtList, unitTwo.getQuestionList().get(crossPositon + 1).getQuestionTypeId());

				if (scoreOne == scoreTwo) {
					// 产生两个新个体
					TempExam unitNewOne=null;
					TempExam unitNewTwo=null;
					List<Questions> tempList1, tempList2;

					// 交叉位置后的两道题目
					for (int i = crossPositon; i < crossPositon + 2; i++) {
						tempList1 = unitOne.getQuestionList();
						// 确保题目没有重复
						if (!tempList1.contains(unitTwo.getQuestionList().get(i))) {
							//实例化在此处
							unitNewOne= new TempExam();
							tempList1.set(i, unitTwo.getQuestionList().get(i));
							unitNewOne.setQuestionList(tempList1);

							// 设置新个体的其他信息
							unitNewOne.seteId(StringUtil.seqGenerate());
							unitNewOne.setQtList(qtList);
							unitNewOne.setTotalScore1();
							unitNewOne.setDifficultyLevel1();
							unitNewOne.setKnowPoints1();
						}
						tempList2 = unitTwo.getQuestionList();
						if (!tempList2.contains(unitOne.getQuestionList().get(i))) {
							unitNewTwo = new TempExam();
							tempList2.set(i, unitOne.getQuestionList().get(i));
							unitNewTwo.setQuestionList(tempList2);

							// 设置新个体的其他信息
							unitNewTwo.seteId(StringUtil.seqGenerate());
							unitNewTwo.setQtList(qtList);
							unitNewTwo.setTotalScore1();
							unitNewTwo.setDifficultyLevel1();
							unitNewTwo.setKnowPoints1();
						}

					}

					// 将新个体添加到种群中
					if (crossedUnitList.size() < count) {
						if (unitNewOne != null) {
							crossedUnitList.add(unitNewOne);
						}
					}
					if (crossedUnitList.size() < count) {
						if (unitNewTwo != null) {
							crossedUnitList.add(unitNewTwo);
						}
					}

				}
			}
		}
		// 计算知识点覆盖率以及适应度
		crossedUnitList=getKPCoverage(crossedUnitList, expectedExam);
		crossedUnitList=getAdaptionDegree(crossedUnitList, expectedExam, Constants.KP_COVERAGE_RATE, Constants.DIFFICULTY_RATE);
		
		return crossedUnitList;
	}

	/*
	 * 变异算子
	 * unitList:初始种群
	 * questionDB：题库
	 * expectedExam:期望试卷
	 * 
	 * 随机试卷中的一道题目进行变异（要求：题目题型相同、题号不同、知识点最好为Ue-(Ue ∩ Un)  Ue:期望试卷  Un：现有试卷
	 * */
	public List<TempExam> change(List<TempExam> unitList, List<Questions> questionDB, TempExam expectedExam) {
		System.out.println("变异");
		Random random=new Random();
		int index,index1;Questions tempQues;
		Set<Long> expectedKPSet=expectedExam.getKnowPoints();
		Set<Long> unitKPSet;//种群个体知识点
		Set<Long> resultKPSet;
		List<Questions> expectedQuestionsList;
		List<Questions> temp;
		
		for(TempExam unit:unitList) {
			//随机变异的试题位置
			index=random.nextInt(unit.getQuestionList().size());
			tempQues=new Questions();
			//变异的试题
			tempQues=unit.getQuestionList().get(index);
			unitKPSet=unit.getKnowPoints();
			
			//期望新试题所在的知识点范围
			resultKPSet=getResultKPList(expectedKPSet,unitKPSet);
			
			//期望试题列表---如果resultKPSet为空，就不限制知识点
			expectedQuestionsList=getOneTypeQuestions(questionDB,tempQues.getQuestionTypeId(),resultKPSet);
			
			//将新的试题替换掉原来的试题
			if(expectedQuestionsList.size()>0) {
				index1=random.nextInt(expectedQuestionsList.size());
				temp=unit.getQuestionList();
				temp.set(index, expectedQuestionsList.get(index1));
				unit.setQuestionList(temp);
				
				//重新计算  试卷的几个信息
				unit.setKnowPoints1();
				unit.setTotalScore1();
				unit.setDifficultyLevel1();
				unit.setKnowPoints1();
			}else {
				System.out.println(unit.geteId()+"种群个体没有可变异的待选试题");
			}
			
		}
		
		// 计算知识点覆盖率以及适应度
		unitList=getKPCoverage(unitList, expectedExam);
		unitList=getAdaptionDegree(unitList, expectedExam, Constants.KP_COVERAGE_RATE, Constants.DIFFICULTY_RATE);
		return unitList;
	}

	//Ue-(Ue ∩ Un)  U试e:期望卷  Un：现有试卷
	public Set<Long> getResultKPList(Set<Long> expectedKPSet,Set<Long> unitKPSet) {
		List<Long> intersectionKPList=new ArrayList<>();//交集
		Set<Long> tempSet=new HashSet<>(expectedKPSet);
		Set<Long> resultKPSet=new HashSet<>();
		
		//Ue ∩ Un
		for(Long id:unitKPSet) {
			if(tempSet.add(id)==false) {
				intersectionKPList.add(id);
			}
		}
		
		//Ue-(Ue ∩ Un)
		for(Long id:expectedKPSet) {
			if(!intersectionKPList.contains(id)) {
				resultKPSet.add(id);
			}
		}
		
		return resultKPSet;
	}
	
	// 从题库中选出x题型的题目--以及知识点符合
	public List<Questions> getOneTypeQuestions(List<Questions> questionsDB, Long qtId, Set<Long> kpSet) {
		List<Questions> oneTypeQuestions = new ArrayList<>();
		for (Questions questions : questionsDB) {
			// 题型符合
			if (questions.getQuestionTypeId().equals(qtId)) {
				// 知识点符合
				if (kpSet != null) {
					if (kpSet.contains(questions.getKonwledgePointId())) {
						oneTypeQuestions.add(questions);
					}
					/*if(kpSet.add(questions.getKonwledgePointId())==false) {
						oneTypeQuestions.add(questions);
					}*/
					
				} else {
					oneTypeQuestions.add(questions);
				}
			}
		}

		return oneTypeQuestions;
	}

	// 根据qtId获得该题型数量
	public Map<String, Object> getQtById(List<Map<String, Object>> qtList, Long qtId) {
		Map<String, Object> qtMap = new HashMap<>();
		for (Map<String, Object> map : qtList) {
			if (map.get("qtId").toString().equals(qtId.toString())) {
				qtMap = map;
			}
		}
		return qtMap;
	}

	// 根据qtId得到题型的分数
	public int getScoreById(List<Map<String, Object>> qtList, Long qtId) {
		for (Map<String, Object> map : qtList) {
			if (map.get("qtId").toString().equals(qtId.toString())) {
				return Integer.parseInt(map.get("qtScore").toString());
			}
		}
		return 0;
	}

	// 计算知识点覆盖率
	public List<TempExam> getKPCoverage(List<TempExam> unitList, TempExam expectedExam) {
		int kpNum, expectedKpNum;
		for (TempExam unit : unitList) {
			kpNum = unit.getKnowPoints().size();
			expectedKpNum = expectedExam.getKnowPoints().size();
			unit.setKpCoverage(kpNum * 1.00 / expectedKpNum);
		}
		return unitList;
	}

	/*
	 * 计算适应度 公式：f=1-(1-M/N)*f1-|Ep-P|*f2 M/N为知识点覆盖率 Ep：期望难度系数 P:实际难度系数
	 * 
	 * unitList:种群 expectedExam：期望试卷 kpCoverage：知识点覆盖率所占权重 difficulty：难度系数所占权重
	 */
	public List<TempExam> getAdaptionDegree(List<TempExam> unitList, TempExam expectedExam, double kpCoverage,
			double difficulty) {
		double adapterDegree;
		for (TempExam unit : unitList) {
			adapterDegree = 1 - (1 - unit.getKpCoverage()) * kpCoverage
					- Math.abs(expectedExam.getDifficultyLevel() - unit.getDifficultyLevel()) * difficulty;
			unit.setAdapterDegree(adapterDegree);
		}
		return unitList;
	}

	@Override
	public boolean isEnd(List<TempExam> unitList, double expandAdapterDegree) {
		for(TempExam unit:unitList) {
			if(unit.getAdapterDegree()>=expandAdapterDegree) {
				return true;
			}
		}
		return false;
	}
}
