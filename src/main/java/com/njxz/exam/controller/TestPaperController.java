package com.njxz.exam.controller;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njxz.exam.modle.AddException;
import com.njxz.exam.modle.Exam;
import com.njxz.exam.modle.KnowledgePoints;
import com.njxz.exam.modle.QuestionType;
import com.njxz.exam.modle.Questions;
import com.njxz.exam.modle.Result;
import com.njxz.exam.modle.Subject;
import com.njxz.exam.modle.TempExam;
import com.njxz.exam.modle.User;
import com.njxz.exam.service.ExamQuestionsService;
import com.njxz.exam.service.ExamQuestiontypeService;
import com.njxz.exam.service.ExamService;
import com.njxz.exam.service.GeneratePaperService;
import com.njxz.exam.service.KnowledgePointsService;
import com.njxz.exam.service.QuestionService;
import com.njxz.exam.service.QuestionTypeService;
import com.njxz.exam.service.SubjectService;
import com.njxz.exam.service.UserService;
import com.njxz.exam.util.Constants;
import com.njxz.exam.util.ImageConverter;
import com.njxz.exam.util.Logable;
import com.njxz.exam.util.RichHtmlHandler;
import com.njxz.exam.util.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import sun.util.resources.cldr.CalendarData;

@Controller
@RequestMapping(value = "/testPaper")
public class TestPaperController extends Logable{
	@Autowired
	private HttpSession session;

	@Autowired
	private SubjectService SubjectService;

	@Autowired
	private KnowledgePointsService kpService;

	@Autowired
	private QuestionTypeService qtService;

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private GeneratePaperService generatePaperService;
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ExamQuestiontypeService eqtService;

	// 抽取现有试卷
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ModelAndView getPaperPage(@RequestParam(value="pageNum",required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(value="pageSize",required = false, defaultValue = "10") Integer pageSize) {
		ModelAndView mav=new ModelAndView();
		List<Map<String, String>> examResultList=new ArrayList<>();
		SimpleDateFormat sdFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		DecimalFormat dformat=new DecimalFormat("#0.00");
		
		User user=(User) session.getAttribute("user");
		
		PageHelper.startPage(pageNum, pageSize);
		List<Exam> examList= examService.getExamByUserId(user);
		for(Exam exam:examList) {
			Map<String, String> tempMap=new HashMap<>();
			tempMap.put("eId", exam.geteId().toString());
			tempMap.put("addTime", sdFormat.format(exam.geteAddTime()));
			tempMap.put("eTitle", exam.geteTitle());
			tempMap.put("eDifficulty", dformat.format(exam.geteDifficultyLevel()));
			//A卷B卷
			if(examService.isHasExamType((byte)0)) {
				tempMap.put("examTypeA", "A卷");
			}
			if(examService.isHasExamType((byte)1)){
				tempMap.put("examTypeB", "B卷");
			}
			
			tempMap.put("subjectId", exam.getSubjectId().toString());
			if(exam.getSubjectId()!=null) {
				tempMap.put("subjectTitle", SubjectService.getSubjectById(exam.getSubjectId()).getsTitle());
			}
			if(exam.getUserId()!=null) {
				tempMap.put("userName", userService.findUser(exam.getUserId().toString()).getName());
			}
			
			
			examResultList.add(tempMap);
		}
		
		PageInfo<Exam> page=new PageInfo<Exam>(examList);
		
		mav.addObject("page", page);
		mav.addObject("examList", examResultList);
		
		mav.setViewName("testPaperGet");
		
		return mav;
	}

	//根据试卷Id生成word
	@RequestMapping(value="/exportWord/${eId}/${eType}",method=RequestMethod.GET)
	public void exportWord(@PathVariable(name="eId",required=true)Long eId,
			@PathVariable(name="eType",required=true)int eType) throws IOException {
		String directoryName=Constants.WORD_TEMPLETE_DIRECTORY_NAME;
		Map<String,Object> resultMap=new HashMap<>();
		Map<String, String> contentMap=new HashMap<>();
		
		String t=request.getSession().getServletContext().getRealPath("");
        String realPath=t.substring(0, t.lastIndexOf('\\'))+"\\"+directoryName;
        System.out.println("wordTemplete_realPath:-------------"+realPath);
		
        //试题信息
        Exam exam=examService.get(eId);
        String examTitle=exam.geteTitle();
        String eStatus=eType==0?"A":eType==1?"B":"";
        Calendar calandar=Calendar.getInstance();
        int nowYear=calandar.get(Calendar.YEAR);
        int nowMonth=calandar.get(Calendar.MONTH);
        int semester,startYear=nowYear,stopYear=nowYear+1;
        if(nowMonth<9) {
        	semester=1;
        }else {
        	semester=2;
        }
		
		resultMap.put("examTitle", examTitle);
		resultMap.put("examStatus", eStatus);
		resultMap.put("startYear", startYear);
		resultMap.put("stopYear", stopYear);
		resultMap.put("semester",semester);
		
		//题型个数
		int count=eqtService.countByEId(eId);
		
		List<String> scoreThList=new ArrayList<>();
		scoreThList.add("序号");
		for(int i=0;i<count;i++) {
			scoreThList.add(Constants.numGetChinese(i));
		}
		scoreThList.add("总分");
		resultMap.put("scoreThList", scoreThList);
		
		List<String> scoreList=new ArrayList<>();
		scoreList.add("得分");
		for(int i=0;i<count;i++) {
			scoreList.add("");
		}
		scoreList.add("");
		resultMap.put("scoreList", scoreList);
		
		//resultMap.put("scoreTh", "题目");
		//resultMap.put("score","得分");
		
		//题型-题目-答案
		
		
		StringBuilder sb = new StringBuilder();
		

		RichHtmlHandler handler = new RichHtmlHandler(sb.toString());

		
		handler.setDocSrcLocationPrex("file:///C:/C8FBA2D4");
		handler.setDocSrcParent("examTest.files");
		handler.setNextPartId("01D395FD.81B8E900");
		
		handler.handledHtml(request);
		
		StringBuilder imagesBase64=new StringBuilder();
		if (handler.getDocBase64BlockResults() != null
				&& handler.getDocBase64BlockResults().size() > 0) {
			for (String item : handler.getDocBase64BlockResults()) {
				imagesBase64.append(item);
			}
		}
		
		System.out.println("=========iamgebase64=========");
		System.out.println(imagesBase64.toString());
		
		StringBuilder imagesXmlHrefString=new StringBuilder();
		if (handler.getXmlImgRefs() != null
				&& handler.getXmlImgRefs().size() > 0) {
			for (String item : handler.getXmlImgRefs()) {
				imagesXmlHrefString.append(item);
			}
		}
		
		System.out.println("============imagesXmlHrefString==============");
		System.out.println(imagesXmlHrefString.toString());
		
		System.out.println("==========title=================");
		System.out.println(handler.getHandledDocBodyBlock());
		
		contentMap.put("title",handler.getHandledDocBodyBlock());
		contentMap.put("question", "选择的是");
		contentMap.put("answer", "答案");
		resultMap.put("content", contentMap);
		
		resultMap.put("imagesXmlHrefString",imagesXmlHrefString);
		resultMap.put("imagesBase64",imagesBase64);
		
		//Configuration用于读取ftl文件
		Configuration configuration=new Configuration();
		configuration.setDefaultEncoding("utf-8");
		
          try {
			configuration.setDirectoryForTemplateLoading(new File(realPath));
			File outFile=new File(realPath+"\\test.doc");
			
			//以utf-8编码读取ftl文件
			Template template=configuration.getTemplate("examTest.ftl","utf-8");
			
			Writer out=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8"),10240);
			
			template.process(resultMap, out);
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="test")
	public void test() {
		StringBuilder sb = new StringBuilder();
		sb.append("<p><img alt=\"\" src=\"/upload/1514443556468655192.png\" style=\"height:40px; width:40px\" /></p>\r\n" + 
				"\r\n" + 
				"<ol>\r\n" + 
				"	<li>/**&nbsp;</li>\r\n" + 
				"	<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;通过其后缀名判断其是否是图片&nbsp;</li>\r\n" + 
				"	<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;@param&nbsp;String&nbsp;后缀名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>\r\n" + 
				"	<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;@return&nbsp;合法返回true，不合法返回false&nbsp;</li>\r\n" + 
				"	<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*/&nbsp;&nbsp;</li>\r\n" + 
				"	<li>&nbsp;&nbsp;&nbsp;&nbsp;public&nbsp;boolean&nbsp;isPic(String&nbsp;suffix){&nbsp;&nbsp;</li>\r\n" + 
				"	<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;suffix=suffix.toLowerCase();&nbsp;&nbsp;</li>\r\n" + 
				"	<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if(suffix.equals(&quot;jpg&quot;)||suffix.equals(&quot;gif&quot;)||suffix.equals(&quot;jpeg&quot;)||suffix.equals(&quot;png&quot;))&nbsp;&nbsp;</li>\r\n" + 
				"	<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{&nbsp;&nbsp;</li>\r\n" + 
				"	<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return&nbsp;true;&nbsp;&nbsp;</li>\r\n" + 
				"	<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}else{&nbsp;&nbsp;</li>\r\n" + 
				"	<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>\r\n" + 
				"	<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return&nbsp;false;&nbsp;&nbsp;</li>\r\n" + 
				"	<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}&nbsp;&nbsp;</li>\r\n" + 
				"	<li>&nbsp;&nbsp;</li>\r\n" + 
				"	<li>&nbsp;&nbsp;&nbsp;&nbsp;}&nbsp;&nbsp;</li>\r\n" + 
				"</ol>");

			RichHtmlHandler handler = new RichHtmlHandler(sb.toString());

		
			handler.setDocSrcLocationPrex("file:///C:/C8FBA2D4");
			handler.setDocSrcParent("examTest.files");
			handler.setNextPartId("01D395FD.81B8E900");
		
		//写入文件中，
		try {
			handler.handledHtml(request);
			
			String logFile="D:\\log.txt";
			
			File file=new File(logFile);
			//FileOutputStream out=new FileOutputStream(file);
			FileWriter fw=new FileWriter(file);
			
			
			fw.write("======handledDocBody block==========\n");
			fw.write(handler.getHandledDocBodyBlock());
			
			fw.write("======handledBase64Block==========\n");
			if (handler.getDocBase64BlockResults() != null
					&& handler.getDocBase64BlockResults().size() > 0) {
				for (String item : handler.getDocBase64BlockResults()) {
					fw.write(item + "\n");
				}
			}
			if (handler.getXmlImgRefs() != null
					&& handler.getXmlImgRefs().size() > 0) {
				fw.write("======xmlimaHref==========\n");
				for (String item : handler.getXmlImgRefs()) {
					fw.write(item + "\n");
				}
			}
			
			fw.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	
	// 自动生成试卷
	@RequestMapping(value = "generateAuto", method = RequestMethod.GET)
	public ModelAndView generatePaperAutoPage() {
		ModelAndView mav = new ModelAndView();
		// 初始化科目信息----该用户下的所有科目信息
		User user = (User) session.getAttribute("user");
		List<Subject> subjectList = SubjectService.getSubjectsByUId(user.getuId().toString());
		mav.addObject("subjects", subjectList);

		// 初始化题型信息
		List<QuestionType> qtList = qtService.getAllQuestionTypes();
		mav.addObject("questionTypes", qtList);

		mav.setViewName("testPaperGenerateAuto");
		return mav;
	}

	// 手动生成试卷
	@RequestMapping(value = "generateSelf", method = RequestMethod.GET)
	public ModelAndView generatePaperSelfPage() {
		ModelAndView mav = new ModelAndView();
		// 初始化科目信息----该用户下的所有科目信息
		User user = (User) session.getAttribute("user");
		List<Subject> subjectList = SubjectService.getSubjectsByUId(user.getuId().toString());
		mav.addObject("subjects", subjectList);

		// 初始化题型信息
		List<QuestionType> qtList = qtService.getAllQuestionTypes();
		mav.addObject("questionTypes", qtList);

		mav.setViewName("testPaperGenerateSelf");
		return mav;
	}

	// 根据科目得到知识点信息
	@RequestMapping(value = "/knowledgPoints/{sId}", method = RequestMethod.GET)
	@ResponseBody
	public Result getAllKonwsBySId(@PathVariable(name = "sId", required = true) String sId) {
		Result result = new Result();
		List<KnowledgePoints> resultLists = kpService.getKnowledgePointsBySId(sId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("knows", resultLists);
		result.setMap(map);
		result.setRtnCode("0");
		result.setRtnMessage(sId + "下的知识点信息查询成功");

		return result;
	}

	// 根据科目得到各种题型的数量信息
	@RequestMapping(value = "/amount/{sId}", method = RequestMethod.GET)
	@ResponseBody
	public Result getQuesAmountBySId(@PathVariable(name = "sId", required = true) Long sId) {
		Result result = new Result();
		List<Map<String, Object>> list = new LinkedList<>();
		Map<String, Object> map1 = new HashMap<>();
		Map<String, Object> map2 = new HashMap<>();

		// 题型
		List<QuestionType> qtList = qtService.getAllQuestionTypes();
		for (QuestionType qt : qtList) {
			map1.put(qt.gettId() + "_veryEasy",
					questionService.countQuesByKpsAndQtAndDl(sId,null, qt.gettId(), Constants.DIFFICULTY_LEVEL_VERYEASY));
			map1.put(qt.gettId() + "_easy",
					questionService.countQuesByKpsAndQtAndDl(sId,null, qt.gettId(), Constants.DIFFICULTY_LEVEL_EASY));
			map1.put(qt.gettId() + "_medium",
					questionService.countQuesByKpsAndQtAndDl(sId, null,qt.gettId(), Constants.DIFFICULTY_LEVEL_MEDIUM));
			map1.put(qt.gettId() + "_hard",
					questionService.countQuesByKpsAndQtAndDl(sId, null,qt.gettId(), Constants.DIFFICULTY_LEVEL_HARD));
			map1.put(qt.gettId() + "_veryHard",
					questionService.countQuesByKpsAndQtAndDl(sId,null, qt.gettId(), Constants.DIFFICULTY_LEVEL_VERYHARD));
			map2.put(qt.gettId() + "", questionService.countQuesByKpsAndQtAndDl(sId,null, qt.gettId(), null));
		}
		list.add(map1);
		list.add(map2);
		result.setList(list);
		result.setRtnCode("0");
		result.setRtnMessage("题目信息查询成功");
		return result;
	}

	// 根据科目得到各种题型的数量信息--增加知识点限制
	@RequestMapping(value = "/amount/{sId}", method = RequestMethod.POST)
	@ResponseBody
	public Result getQuesAmountBySIdAndQt(@PathVariable(name = "sId",required=true) Long sId,
			@RequestBody Map<String, Object> request) {
		Result result = new Result();
		List<Long> kpsList = (List<Long>) request.get("kps");
		List<Map<String, Object>> list = new LinkedList<>();
		Map<String, Object> map1 = new HashMap<>();
		Map<String, Object> map2 = new HashMap<>();
		/*
		 * List<String> list=(List<String>)request.get("kps"); String string="";
		 * 
		 * //list不能为空 if(list.size()<1) { result.setRtnCode("-9999");
		 * result.setRtnMessage("知识点不能为空"); return result; }
		 * 
		 * for(String kp:list) { string+=kp+","; } String resultStr=string.substring(0,
		 * string.length()-1);
		 */

		// 题型
		List<QuestionType> qtList = qtService.getAllQuestionTypes();
		for (QuestionType qt : qtList) {
			map1.put(qt.gettId() + "_veryEasy",
					questionService.countQuesByKpsAndQtAndDl(sId, kpsList,qt.gettId(),Constants.DIFFICULTY_LEVEL_VERYEASY));
			map1.put(qt.gettId() + "_easy",
					questionService.countQuesByKpsAndQtAndDl(sId, kpsList,qt.gettId(), Constants.DIFFICULTY_LEVEL_EASY));
			map1.put(qt.gettId() + "_medium",
					questionService.countQuesByKpsAndQtAndDl(sId, kpsList,qt.gettId(), Constants.DIFFICULTY_LEVEL_MEDIUM));
			map1.put(qt.gettId() + "_hard",
					questionService.countQuesByKpsAndQtAndDl(sId, kpsList,qt.gettId(), Constants.DIFFICULTY_LEVEL_HARD));
			map1.put(qt.gettId() + "_veryHard",
					questionService.countQuesByKpsAndQtAndDl(sId, kpsList,qt.gettId(), Constants.DIFFICULTY_LEVEL_VERYHARD));
			map2.put(qt.gettId() + "", questionService.countQuesByKpsAndQtAndDl(sId, kpsList,qt.gettId(), null));
		}
		list.add(map1);
		list.add(map2);
		result.setList(list);
		result.setRtnCode("0");
		result.setRtnMessage("题目信息查询成功");
		return result;
	}
	
	//根据科目Id+知识点+问题Id查出所有题目
	@RequestMapping(value="/getQuesBySIdAndKnowsAndQt",method=RequestMethod.POST)
	@ResponseBody
	public Result getQuesBySIdAndKnowsAndQt(@RequestBody Map<String, Object> request) {
		Result result=new Result();
		List<Map<String, String>> questionMapList=new ArrayList<>();//存放科目结果
		Map<String, Object> resultMap=new HashMap<>();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Long sId=null,qtId=null;List<Long> kps=null;int pageNum,pageSize;
		int count;//题目的提取次数
		
		//前台信息
		if(request.get("sId")!=null&&request.get("sId")!="") {
			sId=Long.parseLong(request.get("sId").toString());
		}
		if(request.get("qtId")!=null&&request.get("qtId")!="") {
			qtId=Long.parseLong(request.get("qtId").toString());
		}
		kps= (List<Long>) request.get("kps");     
		
		if(request.get("pageNum")!=null&&request.get("pageNum")!="") {
			pageNum=Integer.parseInt(request.get("pageNum").toString());
		}else {
			pageNum=1;
		}
		if(request.get("pageSize")!=null&&request.get("pageSize")!="") {
			pageSize=Integer.parseInt(request.get("pageSize").toString());
		}else {
			pageSize=10;
		}
		System.out.println("pageNum   PageSize");
		System.out.println(pageNum);
		System.out.println(pageSize);
		
		//取出页面信息
		//PageHelper.startPage(pageNum, pageSize);
		
		//根据科目Id+知识点+问题Id查出所有题目
		List<Questions> questionsList=questionService.checkAllQuesByKpsAndQtAndDl(sId,kps,qtId,null,pageNum,pageSize);
		//将questionList转换成List<map<>>
		for(Questions questions:questionsList) {
			Map<String, String> tempMap=new HashMap<>();
			if(questions.getqId()!=null) {
				tempMap.put("qId", questions.getqId().toString());
				
				//题目的抽取次数
				count= questionService.countgetBy(questions.getqId());
				tempMap.put("count", count+"");
			}
			if(questions.getQuestionTypeId()!=null) {
				tempMap.put("questionTypeId", questions.getQuestionTypeId().toString());
				tempMap.put("questionTypeCN", qtService.getQuestionTypeById(questions.getQuestionTypeId().toString()).gettTitle());
			}
			
			if(questions.getKonwledgePointId()!=null) {
				tempMap.put("konwledgePointId", questions.getKonwledgePointId().toString());
			}
			if(questions.getqTitle()!=null) {
				tempMap.put("qTitle", questions.getqTitle());
			}
			if(questions.getqAnswer()!=null) {
				tempMap.put("qAnswer", questions.getqAnswer());
			}
			if(questions.getqAddTime()!=null) {
				tempMap.put("qAddTime", format.format(questions.getqAddTime()));
			}
			if(questions.getqUserId()!=null) {
				tempMap.put("qUserId", questions.getqUserId().toString());
			}
			if(questions.getqDifficultyLevel()!=null) {
				tempMap.put("qDifficultyLevel", questions.getqDifficultyLevel().toString());
				tempMap.put("qDifficultyLevelCN",Constants.getDiffLevelStrCN(questions.getqDifficultyLevel()));
				tempMap.put("qDiffocultyLevelEN", Constants.getDiffLevelStrEN(questions.getqDifficultyLevel()));
			}
			if(questions.getsId()!=null) {
				tempMap.put("sId", questions.getsId().toString());
			}
			questionMapList.add(tempMap);
		}
		
		//分页的信息
		PageInfo<Questions> page=new PageInfo<Questions>(questionsList);
		//page中有所有的试题信息
		resultMap.put("page", page);
		
		resultMap.put("questionsList", questionMapList);
		
		result.setMap(resultMap);
		result.setRtnCode("9999");
		result.setRtnMessage("题目信息查询成功");
		return result;
	}
	
	
	//自动生成试卷
	@RequestMapping(value="/generatePaperAuto",method=RequestMethod.POST)
	public Result generatePaperAuto(@RequestBody Map<String,Object> request) {
		Result result=new Result();
		List<Long> qtList2=new ArrayList<>();
		//List<Long> allKpsList=new ArrayList<>();//存储所有的知识点
		
		String paperName=request.get("paperName").toString();
		double paperDiffLev=Double.parseDouble(request.get("paperDiffLev").toString());
		int totalScore=Integer.parseInt(request.get("totalScore").toString());
		Long sId=Long.parseLong(request.get("sId").toString());
		List<Object> kpsList = (List<Object>) request.get("kps");//知识点的list--知识点不重复
		List<Long> kpsList1 = (List<Long>) request.get("kps");//知识点的list--知识点不重复
		
		//不允许知识点重复----hashSet根据值的hashCode值判断是否重复，放到HashMap中
		Set<Long> kpsSet=new HashSet<>();  
		//---------------强制类型转换错---------------
		for(int i=0;i<kpsList.size();i++) {
			//System.out.println(kpsList.get(i));
			//System.out.println(kpsList.get(i).getClass().toSt ring());
			kpsSet.add(Long.parseLong(kpsList.get(i).toString()));
		}   
		
		//如果知识点为空的话,存储当前科目下所有的知识点信息
		/*if(kpsList==null) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
			List<KnowledgePoints> kps=kpService.getKnowledgePointsBySId(sId.toString());
			for(KnowledgePoints kp:kps) {
				allKpsList.add(kp.getkId());
			}
		}
	*/
		List<Map<String, Object>> qtList=(List<Map<String, Object>>) request.get("qtList");	//各种题型的题目信息
		/*for(Map<String, Object> map:list) {
			System.out.println("qtId:"+map.get("qtId")+"  "+"qtNum:"+map.get("qtNum")+"  "+"qtScore:"+map.get("qtScore")+"  "+"qtOrder:"+map.get("qtOrder"));
		}*/
		//存储各题型题目数量信息
		for(Map<String, Object> map:qtList) {
			qtList2.add(Long.parseLong(map.get("qtId").toString()));
		}
		
		//取出题库中  当前科目-知识点-题型下的题目012
		List<Questions> questionsDB=questionService.checkAllQuesByKpsAndQtAndDl(sId,kpsList1,qtList2,null);
		//实例化期望的试卷信息
		TempExam expectedExam=new TempExam(StringUtil.seqGenerate(), totalScore, paperDiffLev, kpsSet,qtList);
		
		//最终试题
		TempExam resultUnit=null,resultUnitTemp=null;
		
		//迭代次数计数器
		int count=1;
		
		//初始化种群
		List<TempExam> unitList=generatePaperService.cszq(20, expectedExam, questionsDB);
		resultUnitTemp=getMaxAdapterUnit(unitList);
		System.out.println("----------------遗传算法组卷---------------------------------------");
		System.out.println("初始种群-------------");
		showUnit(unitList);
		System.out.println("-----------------------开始迭代-------------------------");
		
		boolean flag=false;
		while(!generatePaperService.isEnd(unitList, Constants.EXPAND_ADATPER)) {
			System.out.println("在"+(count++)+"代未得到结果----------"+unitList.size()+"------------------");
			
			if(count>Constants.RUN_Count) {
				System.out.println("计算"+Constants.RUN_Count+"代仍没有结果，请重新设置条件");
				break;
			}
			
			//经过选择、交叉后种群数量只剩1或只剩0退出
			if(unitList.size()<=1) {
				System.out.println("没有结果");
				flag=true;
				break;
			}
			
			//选择--个数一定小于初始化种群
			unitList=generatePaperService.select(unitList, 10);
			
			//交叉
			unitList=generatePaperService.cross(unitList, 20, expectedExam);
			
			//判断是否可以结束
			if(generatePaperService.isEnd(unitList, Constants.EXPAND_ADATPER)) {
				break;
			}
			
			//变异
			unitList=generatePaperService.change(unitList, questionsDB, expectedExam);
		}
		
		if(count<=Constants.RUN_Count&&flag==false) {
			System.out.println("在第"+count+"代得到结果，结果为：**********************************");
			System.out.println("期望难度系数："+expectedExam.getDifficultyLevel());
			
			showResult(unitList,Constants.EXPAND_ADATPER);
			
			//如果有多个，取适应度最大的
			if(unitList.size()>=1) {
				resultUnit=getMaxAdapterUnit(unitList);
			}
			
		}else {
			//没有得到结果，取初始群种中适应度最大的
			resultUnit=resultUnitTemp;
		}
		
		//最终结果试题
		System.out.println("!!!!!!!!!!!!!!!!!!!!!最终试题！！！！！！！！！！！！！！！！！！！！！！！");
		System.out.println("试卷id："+resultUnit.geteId());
		System.out.println("题目数量\t知识点分布\t\t难度系数\t\t适应度");
		System.out.println(resultUnit.getQuestionList().size()+"\t"+resultUnit.getKpCoverage()+"\t"+resultUnit.getDifficultyLevel()+"\t"+resultUnit.getAdapterDegree());
		
		
		//将产生的最终试卷存入数据库
		
		
		
		return result;
	}
	
	//手动生成试卷
	@RequestMapping("/generatePaperSelf")
	@ResponseBody
	public Result generatePaperSelf(@RequestBody Map<String, Object> request) throws AddException{
		Result result=new Result();
		List<Long> qIdList=new ArrayList<>();//所有试题Id
		double paperDifficutty=0;Questions tempQuestions;
		
		List<Map<String, Object>> qtList=(List<Map<String, Object>>) request.get("qtInfo");//题型
		List<Object> qIdListTemp=(List<Object>) request.get("qIds"); //临时存放所有题目Id
		String paperName=request.get("paperName").toString();//试卷名称
		int paperTotalScore=Integer.parseInt(request.get("paperTotalScore").toString());//试卷总分
		Long sId=Long.parseLong(request.get("sId").toString());
				
		//试卷难度系数
		for(Object qId:qIdListTemp) {
			qIdList.add(Long.parseLong(qId.toString()));
			//临时题目    题目分数*题目难易度/总分数
			tempQuestions=questionService.get(Long.parseLong(qId.toString()));
			for(Map<String, Object> qtMap:qtList) {
				if(qtMap.get("qtId").toString().equals(tempQuestions.getQuestionTypeId().toString())) {
					paperDifficutty+=tempQuestions.getqDifficultyLevel()*Integer.parseInt(qtMap.get("qtScore").toString());
				}
			}
		}
		paperDifficutty=paperDifficutty/paperTotalScore;
		
		User user= (User) session.getAttribute("user");
		//将试卷信息存入数据库
		try {
			if(!examService.inToDB(user.getuId(),paperName,paperTotalScore,paperDifficutty,(byte)0,sId,qIdList,qtList)) {
				error("服务器崩溃，添加试卷信息失败，请重新尝试");
				result.setRtnMessage("服务器崩溃，添加试卷信息失败，请重新尝试");
				result.setRtnCode("-9999");
				return result ;
			}
		}catch (Exception e) {
			error("服务器崩溃，添加试卷信息失败，请重新尝试");
			result.setRtnMessage("服务器崩溃，添加试卷信息失败，请重新尝试");
			result.setRtnCode("-9999");
			//打印错误栈日志
			e.printStackTrace();
			return result ;
		}
		
		result.setRtnMessage("试卷生成成功，前往下载试卷");
		result.setRtnCode("0");
		return result ;
	}
	
	
	//展示种群个体信息
	public void showUnit(List<TempExam> unitList) {
		for(TempExam unit:unitList) {
			System.out.println("编号\t\t\t知识点分布\t\t\t难度系数\t\t\t适应度");
			System.out.println(unit.geteId()+"\t"+unit.getKpCoverage()+"\t"+unit.getDifficultyLevel()+"\t"+unit.getAdapterDegree());
			List<Long> quesIdList=new ArrayList<>();
			for(Questions questions:unit.getQuestionList()) {
				quesIdList.add(questions.getqId());
			}
			System.out.println("问题列表："+quesIdList);
			System.out.println();
			System.out.println();
		}
		
	}
	
	//展示结果
	public void showResult(List<TempExam> unitList,double expand) {
		for(TempExam unit:unitList) {
			if(unit.getAdapterDegree()>=expand) {
				System.out.println("试卷id："+unit.geteId());
				System.out.println("题目数量\t知识点分布\t\t难度系数\t\t适应度");
				System.out.println(unit.getQuestionList().size()+"\t"+unit.getKpCoverage()+"\t"+unit.getDifficultyLevel()+"\t"+unit.getAdapterDegree());
			}
		}
	}
	
	//适应度最大的种群个体
	public TempExam getMaxAdapterUnit(List<TempExam> unitLists) {
		TempExam maxUnit=new TempExam();
		for(TempExam unit:unitLists) {
			if(unit.getAdapterDegree()>=maxUnit.getAdapterDegree()) {
				maxUnit=unit;
			}
		}
		return maxUnit;
	}
	
}
