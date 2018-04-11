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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njxz.exam.modle.Exam;
import com.njxz.exam.modle.ExamQuestiontype;
import com.njxz.exam.modle.KnowledgePoints;
import com.njxz.exam.modle.QuestionType;
import com.njxz.exam.modle.Questions;
import com.njxz.exam.modle.Result;
import com.njxz.exam.modle.Subject;
import com.njxz.exam.modle.TempExam;
import com.njxz.exam.modle.User;
import com.njxz.exam.service.ExamQuestiontypeService;
import com.njxz.exam.service.ExamService;
import com.njxz.exam.service.GeneratePaperService;
import com.njxz.exam.service.KnowledgePointsService;
import com.njxz.exam.service.QuestionService;
import com.njxz.exam.service.QuestionTypeService;
import com.njxz.exam.service.SubjectService;
import com.njxz.exam.service.UserService;
import com.njxz.exam.util.Constants;
import com.njxz.exam.util.Logable;
import com.njxz.exam.util.RichHtmlHandler;
import com.njxz.exam.util.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

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
	public ModelAndView getAllPaperPage(@RequestParam(value="pageNum",required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(value="pageSize",required = false, defaultValue = "10") Integer pageSize) {
		ModelAndView mav=new ModelAndView();
		List<Map<String, String>> examResultList=new ArrayList<>();
		SimpleDateFormat sdFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		DecimalFormat dformat=new DecimalFormat("#0.00");
		
		User user=(User) session.getAttribute("user");
		
		PageHelper.startPage(pageNum, pageSize);
		List<Exam> examList= examService.getExamByUserId(user,pageNum, pageSize);
		for(Exam exam:examList) {
			Map<String, String> tempMap=new HashMap<>();
			tempMap.put("eId", exam.geteId().toString());
			tempMap.put("addTime", sdFormat.format(exam.geteAddTime()));
			tempMap.put("eTitle", exam.geteTitle());
			tempMap.put("eDifficultyA", dformat.format(exam.geteDifficultyLevelA()));
			if(exam.geteDifficultyLevelB()!=null) {
				tempMap.put("eDifficultyB", dformat.format(exam.geteDifficultyLevelB()));
			}else {
				tempMap.put("eDifficultyB", "");
			}
			tempMap.put("eAddressA", exam.geteAddressA());
			tempMap.put("eAddressB",exam.geteAddressB());
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
				User user2=userService.findUser(exam.getUserId().toString());
				if(user2!=null) {
					tempMap.put("userName", user2.getName());
				}else {
					tempMap.put("userName", "该用户已注销");
				}
			}
			
			
			examResultList.add(tempMap);
		}
		
		PageInfo<Exam> pageInfo=new PageInfo<Exam>(examList);
		
		mav.addObject("page", pageInfo);
		mav.addObject("examList", examResultList);
		
		mav.setViewName("testPaperGet");
		
		return mav;
	}

	//根据试卷Id生成word
	//@RequestMapping(value="/exportWord/{eId}/{eType}",method=RequestMethod.GET)
	//return  true：生成word成功    false：失败
	public boolean exportWord(Long eId,int eType) throws IOException, TemplateException {
		String directoryName=Constants.WORD_TEMPLETE_DIRECTORY_NAME;
		Map<String,Object> resultMap=new HashMap<>();
		
		//模板存放的实际地址
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
		
		//题型个数--试题头部打分的表格--scoreThList   scoreList
		int eqtCount=eqtService.countByEId(eId);
		
		List<String> scoreThList=new ArrayList<>();
		scoreThList.add("序号");
		for(int i=1;i<=eqtCount;i++) {
			scoreThList.add(Constants.numGetChinese(i));
		}
		scoreThList.add("总分");
		resultMap.put("scoreThList", scoreThList);
		
		List<String> scoreList=new ArrayList<>();
		scoreList.add("得分");
		for(int i=1;i<=eqtCount;i++) {
			scoreList.add("");
		}
		scoreList.add("");
		resultMap.put("scoreList", scoreList);
		
		//写入所有题目
		//试卷题型信息--已经按照题型排列顺序排列
		List<ExamQuestiontype> eqtList= eqtService.allExamQuestiontypes(eId);
		List<Map<String,Object>> quesList=new ArrayList<>();
		List<Map<String, String>> answerList=new ArrayList<>();
		List<Map<String, Object>> question;
		Map<String, String> answer;
		StringBuilder tempAnswerContent;
		
		ExamQuestiontype tempEqt;//试卷中题型
		QuestionType tempQt;//题型实体
		List<Questions> qList;//试题列表
		String tempContent;String tempImagesXmlHref;String tempImagesBase64;
		Map<String, Object> tmepResultMap=null;
		
		StringBuilder sb = new StringBuilder();
		StringBuilder imagesXmlHrefString=new StringBuilder();
		StringBuilder imagesBase64=new StringBuilder();
		Map<String, Object> tempQuestionMap;//临时存放问题详情Map
		Map<String, Object> tempMap;//临时存放试卷中所有题型
		
		for(int i=1;i<=eqtCount;i++) {
			question=new ArrayList<>();
			answer=new HashMap<>();
			tempAnswerContent=new StringBuilder();
			
			tempMap = new HashMap<>();
			tempEqt=eqtList.get(i-1);//当前试题类型
			tempQt=qtService.getQuestionTypeById(tempEqt.getQuestionTypeId().toString());
			//tempMap.put("fQId",Constants.numGetChinese(i));
			
			qList=questionService.getQuestionsByEIdAndEQTId(eId, tempEqt.getQuestionTypeId(),eType);//当前试卷-题型下的题目（按照难易度排序）
			
			String title=Constants.numGetChinese(i)+"、"+tempQt.gettTitle()+"共"+qList.size()+"题，每题"+tempEqt.getTypeScore()+"分，共计"+qList.size()*tempEqt.getTypeScore()+"分";
			tempMap.put("title",title);
			answer.put("title", title);
			
			for(int j=1;j<=qList.size();j++) {
				tempQuestionMap=new HashMap<>();
				
				tempAnswerContent.append(j+"."+qList.get(j-1).getqAnswer());
				if(!qList.get(j-1).getqAnswer().contains("<p>")) {
					tempAnswerContent.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				}
				//处理的图片信息（总）
				sb.append(qList.get(j-1).getqTitle());
				
				StringBuilder sbTemp=new StringBuilder();
				sbTemp.append(qList.get(j-1).getqTitle());
				
				//处理数据库中富文本
				tmepResultMap=getHandleredInfo(sbTemp);
				tempContent=tmepResultMap.get("docBodyBlock").toString();
				tempImagesXmlHref=tmepResultMap.get("imagesXmlHrefString").toString();
				tempImagesBase64=tmepResultMap.get("iamgebase64").toString();
				
				//所有的图片信息以及图片链接信息
				imagesXmlHrefString.append(tempImagesXmlHref);
				imagesBase64.append(tempImagesBase64);
				
				System.out.println("======tempContent==============");
				System.out.println(tempContent);
				//--绑定题目序号和题目
				String content=bindTitleAndNum(j+"",tempContent);
				tempQuestionMap.put("content",content);
				question.add(tempQuestionMap);
			}
			tempMap.put("question",question);
			quesList.add(tempMap);
			answer.put("content", tempAnswerContent.toString());
			answerList.add(answer);
		}
		
		resultMap.put("quesList", quesList);
		resultMap.put("answerList", answerList);
		
		//题型-题目-答案
//		contentMap.put("title","题目");
//		contentMap.put("question", "选择的是");
//		contentMap.put("answer", "答案");
//		
//		resultMap.put("content", contentMap);
		
	
		//Map<String, Object> handlerMap=getHandleredInfo(sb);
		//String imagesBase64=handlerMap.get("iamgebase64").toString();
		//String imagesXmlHrefString=handlerMap.get("imagesXmlHrefString").toString();

		
		resultMap.put("imagesXmlHrefString",imagesXmlHrefString);
		resultMap.put("imagesBase64",imagesBase64);
		
		//Configuration用于读取ftl文件
		Configuration configuration=new Configuration();
		configuration.setDefaultEncoding("utf-8");
        
		configuration.setDirectoryForTemplateLoading(new File(realPath));
		
		File outFile=null;
		if(eType==0) {
			outFile=new File(realPath+"\\"+eId+"A.doc");
		}else if(eType==1) {
			outFile=new File(realPath+"\\"+eId+"B.doc");
		}
		
		//以utf-8编码读取ftl文件
		Template template=configuration.getTemplate("examTest.ftl","utf-8");
		
		Writer out=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8"),10240);
		
		template.process(resultMap, out);
		out.close();
		
		//将生成的word地址存入Exam中
		if(eType==0) {
			exam.seteAddressA("http://localhost:8080/wordTemplete/"+eId+"A.doc");
		}
		if(eType==1) {
			exam.seteAddressB("http://localhost:8080/wordTemplete/"+eId+"B.doc");
		}
		
		//更新
		if( examService.updateByPrimaryKey(exam)==1) {
			return true;
		}else {
			return false;
		}
			
	}
	
	//绑定题目序号和题目--找到第一个<p>标签，将序号加入
	public String bindTitleAndNum(String num,String title) {
		StringBuilder tempString=new StringBuilder();
		if(title.contains("<p>")) {
			int startIndex= title.indexOf("<p>");
			tempString.append(title.substring(startIndex, startIndex+3));
			tempString.append(num+".");
			tempString.append(title.substring(startIndex+3));
		}else {
			tempString.append(num+"."+title);
		}
		return tempString.toString();
	}
	
	//得到处理过的富文本信息
	public Map<String, Object> getHandleredInfo(StringBuilder sb){
		Map<String, Object> resultMap=new HashMap<>();
		
		try {
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
			
			//System.out.println("===========StringBuilder=========");
			//System.out.println(sb.toString());
			
			//System.out.println("=========iamgebase64（图片的Base64编码）=========");
			//System.out.println(imagesBase64.toString());
			
			StringBuilder imagesXmlHrefString=new StringBuilder();
			if (handler.getXmlImgRefs() != null
					&& handler.getXmlImgRefs().size() > 0) {
				for (String item : handler.getXmlImgRefs()) {
					imagesXmlHrefString.append(item);
				}
			}
			
			//System.out.println("============imagesXmlHrefString（图片链接）==============");
			//System.out.println(imagesXmlHrefString.toString());
			
			//System.out.println("==========docBodyBlock（处理过的主体部分）=================");
			//System.out.println(handler.getHandledDocBodyBlock());
			
			//存入结果
			resultMap.put("docBodyBlock", handler.getHandledDocBodyBlock());
			resultMap.put("iamgebase64", imagesBase64.toString());
			resultMap.put("imagesXmlHrefString", imagesXmlHrefString.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return resultMap;
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
				"</ol><p><img alt=\"\" src=\"/upload/1514443556468655192.png\" style=\"height:40px; width:40px\" /></p>\r\n" + 
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
				"</ol><p><img alt=\"\" src=\"/upload/1514443556468655192.png\" style=\"height:40px; width:40px\" /></p>\r\n" + 
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
				"</ol><p><img alt=\"\" src=\"/upload/1514443556468655192.png\" style=\"height:40px; width:40px\" /></p>\r\n" + 
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
				"</ol><p><img alt=\"\" src=\"/upload/1514443556468655192.png\" style=\"height:40px; width:40px\" /></p>\r\n" + 
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
				"</ol><p><img alt=\"\" src=\"/upload/1514443556468655192.png\" style=\"height:40px; width:40px\" /></p>\r\n" + 
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
		File file;
		try {
			handler.handledHtml(request);
			
			String logFile="D:\\log.txt";
			
			file=new File(logFile);
			//FileOutputStream out=new FileOutputStream(file);
			FileWriter fw=new FileWriter(file);
			
			
			fw.write("======handledDocBody block（主体信息）==========\n");
			fw.write(handler.getHandledDocBodyBlock());
			
			fw.write("======handledBase64Block（图片的base64编码）==========\n");
			if (handler.getDocBase64BlockResults() != null
					&& handler.getDocBase64BlockResults().size() > 0) {
				for (String item : handler.getDocBase64BlockResults()) {
					fw.write(item + "\n");
				}
			}
			if (handler.getXmlImgRefs() != null
					&& handler.getXmlImgRefs().size() > 0) {
				fw.write("======xmlimaHref（从主体部分链接到Base64图片部分）==========\n");
				for (String item : handler.getXmlImgRefs()) {
					fw.write(item + "\n");
				}
			}
			
			fw.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			
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
	@ResponseBody
	public Result generatePaperAuto(@RequestBody Map<String,Object> request) {
		Result result=new Result();
		List<Long> qtList2=new ArrayList<>();//存储前台题型
		
		String paperName=request.get("paperName").toString();
		double paperDiffLevA=Double.parseDouble(request.get("paperDiffLevA").toString());
		double paperDiffLevB=Double.parseDouble(request.get("paperDiffLevB").toString());
		int totalScore=Integer.parseInt(request.get("totalScore").toString());
		Long sId=Long.parseLong(request.get("sId").toString());
		List<Object> kpsList = (List<Object>) request.get("kps");//知识点的list--知识点不重复
		List<Long> kpsList1 = (List<Long>) request.get("kps");//知识点的list--知识点不重复
		
		//不允许知识点重复----hashSet根据值的hashCode值判断是否重复，放到HashMap中
		Set<Long> kpsSet=new HashSet<>();  
		for(int i=0;i<kpsList.size();i++) {
			kpsSet.add(Long.parseLong(kpsList.get(i).toString()));
		}   
	
		List<Map<String, Object>> qtList=(List<Map<String, Object>>) request.get("qtList");	//各种题型的题目信息
		
		//存储各题型题目数量信息
		for(Map<String, Object> map:qtList) {
			qtList2.add(Long.parseLong(map.get("qtId").toString()));
		}
		
		//取出题库中  当前科目-知识点-题型下的题目012
		List<Questions> questionsDB=questionService.checkAllQuesByKpsAndQtAndDl(sId,kpsList1,qtList2,null);
		//实例化期望的试卷信息
		TempExam expectedExamA=new TempExam(StringUtil.seqGenerate(), totalScore, paperDiffLevA, kpsSet,qtList);
		TempExam expectedExamB=new TempExam(StringUtil.seqGenerate(), totalScore, paperDiffLevB, kpsSet,qtList);
		
		//遗传算法
		TempExam resultUnitA= geneticAlgorithm(questionsDB,expectedExamA);
		TempExam resultUnitB= geneticAlgorithm(questionsDB,expectedExamB);
		
		Long eId=StringUtil.seqGenerate();
		User user=(User) session.getAttribute("user");
		int paperTotalScoreA=resultUnitA.getTotalScore();
		int paperTotalScoreB=resultUnitB.getTotalScore();
		double paperDifficuttyA=resultUnitA.getDifficultyLevel();
		double paperDifficuttyB=resultUnitB.getDifficultyLevel();
		List<Long> qIdListA=new ArrayList<>();//A卷所有试题Id
		List<Long> qIdListB=new ArrayList<>();//B卷所有试题Id
		for(Questions questions:resultUnitA.getQuestionList()) {
			qIdListA.add(questions.getqId());
		}
		for(Questions questions:resultUnitB.getQuestionList()) {
			qIdListB.add(questions.getqId());
		}

		try {
			//TODO 将产生的最终试卷存入数据库--A卷  B卷
			if(!examService.inToDB(eId,user.getuId(),paperName,paperTotalScoreA,paperDifficuttyA,(byte)0,sId,qIdListA,qtList)||
					!examService.inToDB(eId,user.getuId(),paperName,paperTotalScoreB,paperDifficuttyB,(byte)1,sId,qIdListB,qtList)) {
				error("服务器崩溃，添加试卷信息失败，请重新尝试");
				result.setRtnMessage("服务器崩溃，添加试卷信息失败，请重新尝试");
				result.setRtnCode("-9999");
				return result ;
			}
		}catch(Exception e) {
			error("服务器崩溃，添加试卷信息失败，请重新尝试");
			result.setRtnMessage("服务器崩溃，添加试卷信息失败，请重新尝试");
			result.setRtnCode("-9999");
			//打印错误栈日志
			e.printStackTrace();
			return result ;
		}
		
		//生成word文档
		try {
			 if(!exportWord(eId,0)||!exportWord(eId,1)) {
				 error("服务器崩溃，生成word格式失败，请重新尝试");
				 result.setRtnMessage("服务器崩溃，生成word格式失败，请重新尝试");
				 result.setRtnCode("-9999");
				 return result;
			 }
			 
		} catch (IOException e) {
			e.printStackTrace();
			error("服务器崩溃，生成试卷word格式失败，请重新尝试");
			result.setRtnMessage("服务器崩溃，生成word格式失败，请重新尝试");
			result.setRtnCode("-9999");
			return result;
		} catch (TemplateException e) {
			e.printStackTrace();
			error("服务器崩溃，生成试卷word格式失败，请重新尝试");
			result.setRtnMessage("服务器崩溃，生成word格式失败，请重新尝试");
			result.setRtnCode("-9999");
			return result;
		}
		
		result.setRtnMessage("试卷生成成功，前往下载试卷");
		result.setRtnCode("0");
		return result;
	}
	
	//手动生成试卷---试卷都是A卷
	@RequestMapping("/generatePaperSelf")
	@ResponseBody
	public Result generatePaperSelf(@RequestBody Map<String, Object> request){
		Result result=new Result();
		List<Long> qIdList=new ArrayList<>();//所有试题Id
		double paperDifficutty=0;Questions tempQuestions;
		Long eId=StringUtil.seqGenerate();
		
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
			if(!examService.inToDB(eId,user.getuId(),paperName,paperTotalScore,paperDifficutty,(byte)0,sId,qIdList,qtList)) {
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
		
		//生成word文档
		try {
			 if(!exportWord(eId,0)) {
				 error("服务器崩溃，生成word格式失败，请重新尝试");
				 result.setRtnMessage("服务器崩溃，生成word格式失败，请重新尝试");
				 result.setRtnCode("-9999");
				 return result;
			 }
			 
		} catch (IOException e) {
			e.printStackTrace();
			error("服务器崩溃，生成试卷word格式失败，请重新尝试");
			result.setRtnMessage("服务器崩溃，生成word格式失败，请重新尝试");
			result.setRtnCode("-9999");
			return result;
		} catch (TemplateException e) {
			e.printStackTrace();
			error("服务器崩溃，生成试卷word格式失败，请重新尝试");
			result.setRtnMessage("服务器崩溃，生成word格式失败，请重新尝试");
			result.setRtnCode("-9999");
			return result;
		}
		
		result.setRtnMessage("试卷生成成功，前往下载试卷");
		result.setRtnCode("0");
		return result;
	}
	
	//删除试卷--exam--exam_questiontype--exam_questions
	@RequestMapping(value="/deleteExam/{eId}",method=RequestMethod.GET)
	public String deleteExam(@PathVariable("eId")Long eId,Model model) {
		if(examService.deleteExamByExamId(eId)==false) {
			model.addAttribute("deleteExamError", "删除试卷信息失败");
		}
		
		return "redirect:/testPaper/get";
	}
	
	//修改试卷Page
	@RequestMapping(value="/modify/{eId}",method=RequestMethod.GET)
	public ModelAndView modifyExamPage(@PathVariable("eId")Long eId) {
		ModelAndView mav=new ModelAndView();
		Exam exam=examService.get(eId);
		List<ExamQuestiontype> eqtList=eqtService.allExamQuestiontypes(eId);
		mav.addObject("exam",exam);
		mav.addObject("totalEQT",eqtList.size());
		List<Map<String,String>> orderList=new ArrayList<>();
		Map<String, String> tempMap;
		QuestionType tempQuestiontype;
		for(ExamQuestiontype eqt:eqtList) {
			tempMap=new HashMap<>();
			tempQuestiontype=qtService.getQuestionTypeById(eqt.getQuestionTypeId().toString());
			
			tempMap.put("qtTitle",tempQuestiontype.gettTitle());
			tempMap.put("qtId",tempQuestiontype.gettId().toString());
			tempMap.put("qtOrder", eqt.getTypeSort().toString());
			orderList.add(tempMap);
		}
		mav.addObject("orderList", orderList);
		mav.setViewName("testPaperModify");
		return mav;
	}
	
	@RequestMapping(value="/modify/{eId}",method=RequestMethod.POST)
	@ResponseBody
	public String modifyExam(@PathVariable("eId")Long eId,
			@RequestBody Map<String,Object> req) {
		String eTitle=req.get("eTitle").toString();
		List<Map<String, String>> orderList=(List<Map<String, String>>) req.get("eOrder");
		
		Exam exam=examService.get(eId);
		exam.seteTitle(eTitle);
		if(examService.updateByPrimaryKey(exam)!=1) {
			return "modifyExamError";
		}
		
		for(Map<String, String> map:orderList) {
			Long qtId=Long.parseLong(map.get("qtId"));
			int order=Integer.parseInt(map.get("qtOrder"));
			int count=eqtService.modifyQTOrder(eId, qtId, order);
			if(count!=1) {
				return "modifyEQTError";
			}
			
		}
		
		return "success";
	}
	
	/*遗传算法
	 * 入参：
	 * questionsDB题库
	 * expectedExam：期望试题
	 * 出参：
	 * TempExam resultUnit最终试题
	 * */
	public TempExam geneticAlgorithm(List<Questions> questionsDB,TempExam expectedExam) {
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
		if(resultUnit!=null) {
			System.out.println("试卷id："+resultUnit.geteId());
			System.out.println("题目数量\t知识点分布\t\t难度系数\t\t适应度");
			System.out.println(resultUnit.getQuestionList().size()+"\t"+resultUnit.getKpCoverage()+"\t"+resultUnit.getDifficultyLevel()+"\t"+resultUnit.getAdapterDegree());
		}	
		
		return resultUnit;
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
