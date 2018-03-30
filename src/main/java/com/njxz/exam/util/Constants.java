package com.njxz.exam.util;


/*
 * 将难易程度做成可配置（但是只能配置一次）
 * 常量类
 * */
public class Constants {
	//difficulty_level难度系数取值
	public static Double DIFFICULTY_LEVEL_VERYEASY=(double) 0.1;
	public static Double DIFFICULTY_LEVEL_EASY=(double) 0.3;
	public static Double DIFFICULTY_LEVEL_MEDIUM=(double) 0.5;
	public static Double DIFFICULTY_LEVEL_HARD=(double) 0.7;
	public static Double DIFFICULTY_LEVEL_VERYHARD=(double) 0.9;
	

	public static double KP_COVERAGE_RATE = 0;//知识点覆盖率 占 适应度 比率
	public static double DIFFICULTY_RATE = 1;//难度系数  占 适应度比率
	
	//期望适应度
	public static double EXPAND_ADATPER=0.95;
	//最大迭代次数
	public static int RUN_Count=500;
	
	
	//上传图片存放位置
	public static String PHOTO_DIRECTORY_NAME="upload";
	//word模板存放位置
	public static String WORD_TEMPLETE_DIRECTORY_NAME="wordTemplete";
	
	
	public static String CHINESE_1="一";
	
	//得到1-20之间的中文数字
	public static String numGetChinese(int i) {
		if(i<=0||i>20) {
			return "";
		}
		switch (i) {
		case 1:
			return "一";
		case 2:
			return "二";
		case 3:
			return "三";
		case 4:
			return "四";
		case 5:
			return "五";
		case 6:
			return "六";
		case 7:
			return "七";
		case 8:
			return "八";
		case 9:
			return "九";
		case 10:
			return "十";
		case 11:
			return "十一";
		case 12:
			return "十二";
		case 13:
			return "十三";
		case 14:
			return "十四";
		case 15:
			return "十五";
		case 16:
			return "十六";
		case 17:
			return "十七";
		case 18:
			return "十八";
		case 19:
			return "十九";
		case 20:
			return "二十";
		default:
			return "";
		}
		
	}
	
	
	
	//得到困难度的中文
	public static String getDiffLevelStrCN(Double level) {
		String levelCN="";
		if((level-DIFFICULTY_LEVEL_VERYEASY)<0.0001&&(level-DIFFICULTY_LEVEL_VERYEASY)>-0.0001) {
			levelCN="很容易";
		}
		if((level-DIFFICULTY_LEVEL_EASY)<0.0001&&(level-DIFFICULTY_LEVEL_EASY)>-0.0001) {
			levelCN="容易";
		}
		if((level-DIFFICULTY_LEVEL_MEDIUM)<0.0001&&(level-DIFFICULTY_LEVEL_MEDIUM)>-0.0001) {
			levelCN="中等";
		}
		if((level-DIFFICULTY_LEVEL_HARD)<0.0001&&(level-DIFFICULTY_LEVEL_HARD)>-0.0001) {
			levelCN="困难";
		}
		if((level-DIFFICULTY_LEVEL_VERYHARD)<0.0001&&(level-DIFFICULTY_LEVEL_VERYHARD)>-0.0001) {
			levelCN="很困难";
		}
		return levelCN;
	}
	
	public static String getDiffLevelStrEN(Double level) {
		String levelEN="";
		if((level-DIFFICULTY_LEVEL_VERYEASY)<0.0001&&(level-DIFFICULTY_LEVEL_VERYEASY)>-0.0001) {
			levelEN="veryEasy";
		}
		if((level-DIFFICULTY_LEVEL_EASY)<0.0001&&(level-DIFFICULTY_LEVEL_EASY)>-0.0001) {
			levelEN="easy";
		}
		if((level-DIFFICULTY_LEVEL_MEDIUM)<0.0001&&(level-DIFFICULTY_LEVEL_MEDIUM)>-0.0001) {
			levelEN="medium";
		}
		if((level-DIFFICULTY_LEVEL_HARD)<0.0001&&(level-DIFFICULTY_LEVEL_HARD)>-0.0001) {
			levelEN="hard";
		}
		if((level-DIFFICULTY_LEVEL_VERYHARD)<0.0001&&(level-DIFFICULTY_LEVEL_VERYHARD)>-0.0001) {
			levelEN="veryHard";
		}
		return levelEN;
	}
}
