package com.njxz.exam.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njxz.exam.modle.KnowledgePoints;
import com.njxz.exam.modle.QuestionType;
import com.njxz.exam.modle.Questions;
import com.njxz.exam.modle.Result;
import com.njxz.exam.modle.Subject;
import com.njxz.exam.modle.User;
import com.njxz.exam.service.KnowledgePointsService;
import com.njxz.exam.service.QuestionService;
import com.njxz.exam.service.QuestionTypeService;
import com.njxz.exam.service.SubjectService;
import com.njxz.exam.util.Constants;
import com.njxz.exam.util.ErrorUtil;
import com.njxz.exam.util.ImageUploadUtil;

/**
 * @author piperChan
 *
 */
@Controller
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	public HttpServletResponse response;

	@Autowired
	public SubjectService subjectService;

	@Autowired
	public QuestionTypeService qTService;

	@Autowired
	public KnowledgePointsService kpService;

	@Autowired
	public QuestionService questionService;

	@Autowired
	public HttpSession session;

	// 展示试题录入的页面，初始化科目和题型信息
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String questionAddPage(Model model) {
		Questions questions = new Questions();
		
		// 增加科目和题型对象
		// 根据用户获得科目信息
//		 User user=(User) session.getAttribute("user");
//		 List<Subject> sLists=subjectService.getSubjectsByUId(user.getuId().toString());
		 
//		 model.addAttribute("subjects",sLists);
		  
//		 List<QuestionType> qTLists=qTService.getAllQuestionTypes();
//		 model.addAttribute("questionTypes", qTLists);
		 model.addAttribute(questions);
		return "questionAdd";
	}

	// 添加试题
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(@Valid Questions question, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		// spring将qAnswer以逗号分隔存入string中
		System.out.println(question.toString());

		// 校验输入的科目信息--如果有错，直接返回增加页面
		if (!ErrorUtil.addErrorToView(bindingResult, mav)) {
			mav.setViewName("questionAdd");
			return mav;
		}

		if (question.getqAddTime() == null) {
			question.setqAddTime(new Date());
		}

		if (question.getqUserId() == null) {
			User user = (User) session.getAttribute("user");
			question.setqUserId(user.getuId());
		}

		int count = questionService.add(question);
		if (count == 1) {
			mav.setViewName("redirect:/questions/all/"+question.getsId()+"/"+question.getQuestionTypeId());
		} else {
			mav.addObject("questionAddError", "系统异常，题目添加失败");
			mav.setViewName("questionAdd");
		}
		return mav;
	}

	// 进入试题详情界面1
	/*@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView allQuestions1() {
		ModelAndView mav = new ModelAndView();

		// 初始化科目信息
		User user = (User) session.getAttribute("user");
		List<Subject> sLists = subjectService.getSubjectsByUId(user.getuId().toString());
		mav.addObject("subjects", sLists);

		// 初始化题型信息
		List<QuestionType> questionTypeLists = qTService.getAllQuestionTypes();
		mav.addObject("questionTypes", questionTypeLists);

		mav.addObject("sId", null);
		mav.addObject("qtId",null);
		mav.setViewName("questions");
		return mav;
	}*/

	// 进入试题详情界面2
	//设计当没有sid、qtId时传入-1，-1
	//当sid=-1时，试题列表为空，因为数据库中没有sid=-1的试题。只当qtId=-1时，查所有试题
	@RequestMapping(value = "/all/{sId}/{qtId}", method = RequestMethod.GET)
	public ModelAndView allQuestions(@PathVariable("sId") String sId, @PathVariable("qtId") String qtId,
			@RequestParam(value="pageNum",required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(value="pageSize",required = false, defaultValue = "10") Integer pageSize) {
		ModelAndView mav = new ModelAndView();
		List<Map<String, String>> questionMapList=new ArrayList<>();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		// 初始化科目信息
		User user = (User) session.getAttribute("user");
		List<Subject> sLists = subjectService.getSubjectsByUId(user.getuId().toString());
		mav.addObject("subjects", sLists);

		// 初始化题型信息
		List<QuestionType> questionTypeLists = qTService.getAllQuestionTypes();
		mav.addObject("questionTypes", questionTypeLists);

		//将sId和qtId传给前台
		mav.addObject("sId", sId);
		mav.addObject("qtId",qtId);
		
		List<Questions> questionList=questionService.checkAllQuesBySIdAnd(Long.parseLong(sId), Long.parseLong(qtId),pageNum,pageSize);
		
		
		//将questionList转换成List<map<>>
		for(Questions questions:questionList) {
			Map<String, String> tempMap=new HashMap<>();
			if(questions.getqId()!=null) {
				tempMap.put("qId", questions.getqId().toString());
			}
			if(questions.getQuestionTypeId()!=null) {
				tempMap.put("questionTypeId", questions.getQuestionTypeId().toString());
				tempMap.put("questionTypeCN", qTService.getQuestionTypeById(questions.getQuestionTypeId().toString()).gettTitle());
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
				tempMap.put("qDifficultyLevelEN",Constants.getDiffLevelStrEN(questions.getqDifficultyLevel()));
			}
			if(questions.getsId()!=null) {
				tempMap.put("sId", questions.getsId().toString());
				tempMap.put("sIdCN", subjectService.getSubjectById(questions.getsId()).getsTitle());
			}
			questionMapList.add(tempMap);
		}
		
		//分页的信息
		PageInfo<Questions> page=new PageInfo<Questions>(questionList);
		mav.addObject("page",page);
				
		mav.addObject("questions", questionMapList);
		
		mav.setViewName("questions");
		return mav;
	}

	//修改试题信息
	@RequestMapping(value="/modify/{qId}",method=RequestMethod.GET)
	public ModelAndView modifyPage(@PathVariable(name="qId",required=true)Long qId) {
		ModelAndView mav=new ModelAndView();
		Questions questions= questionService.get(qId);
		/*Map<String,Object> questionMap=new HashMap<>();
		questionMap.put("qId", questions.getqId());
		questionMap.put("konwledgePointId", questions.getKonwledgePointId());
		questionMap.put("qTitle", questions.getqTitle());
		
		List<String> list=new ArrayList<>();
		list.add(questions.getqAnswer());
		questionMap.put("aAnswer", list);
		questionMap.put("qAddTime", questions.getqAddTime());
		questionMap.put("qUserId", questions.getqUserId());
		questionMap.put("qDifficultyLevel", questions.getqDifficultyLevel());
		questionMap.put("sId", questions.getsId());
		questionMap.put("qDidNum", questions.getqDidNum());
		questionMap.put("qCorrectNum", questions.getqCorrectNum());*/
		
		mav.addObject("questions", questions);
		
		
		//初始化科目
		User user = (User) session.getAttribute("user");
		List<Subject> sLists = subjectService.getSubjectsByUId(user.getuId().toString());
		mav.addObject("subjects", sLists);
		
		//知识点---根据questions.getsId()
		List<KnowledgePoints> kpList=kpService.getKnowledgePointsBySId(questions.getsId().toString());
		mav.addObject("knowledgePoints", kpList);
		
		//题型
		List<QuestionType> qtLists = qTService.getAllQuestionTypes();
		mav.addObject("questionTypes", qtLists);
		
		//难易度
		Map<String,Object> diffcultyLevelMap=new HashMap<>();
		diffcultyLevelMap.put("veryEasy",Constants.DIFFICULTY_LEVEL_VERYEASY);
		diffcultyLevelMap.put("easy", Constants.DIFFICULTY_LEVEL_EASY);
		diffcultyLevelMap.put("medium", Constants.DIFFICULTY_LEVEL_MEDIUM);
		diffcultyLevelMap.put("hard", Constants.DIFFICULTY_LEVEL_HARD);
		diffcultyLevelMap.put("veryHard", Constants.DIFFICULTY_LEVEL_VERYHARD);
		mav.addObject("diffcultyLevel", diffcultyLevelMap);
		
		mav.setViewName("questionModify");
		return mav;
	}
	
	@RequestMapping(value="/modify/{qId}",method=RequestMethod.POST)
	public ModelAndView moidify(@Valid Questions question) {
		ModelAndView mav=new ModelAndView();
		
		int count=questionService.modify(question);
		if(count==1) {
			mav.setViewName("redirect:/questions/all/"+question.getsId()+"/"+question.getQuestionTypeId());
		}else {
			mav.addObject("questionModifyError","系统异常，题目修改失败");
			mav.setViewName("questionModify");
		}
		
		return mav;
	}

	@RequestMapping(value="/delete/{sId}/{qtId}/{qId}",method=RequestMethod.GET)	
	public ModelAndView delete(@PathVariable(name="qId")Long qId,
			@PathVariable("sId")String sId,
			@PathVariable("qtId")String qtId) {
		ModelAndView mav=new ModelAndView();
		int count= questionService.del(qId);
		if(count!=1) {
			mav.addObject("questionDelError","题目删除失败");
		}
		mav.setViewName("redirect:/questions/all/"+sId+"/"+qtId);
		
		return mav;
	}
	
	// 进入打印题库界面
	@RequestMapping(value = "/print", method = RequestMethod.GET)
	public String print() {
		return "questionsPrint";
	}

	// @responseBody注解，不再走返回视图处理器路径，而是将数据直接写到输出流中，等同于response.getWriter().print()
	// 初始化科目信息
	@RequestMapping(value = "/subjects", method = RequestMethod.GET)
	@ResponseBody
	public Result getAllSubjects() {
		Map<String, Object> map = new HashMap<String, Object>();
		Result result = new Result();
		// 增加科目和题型对象
		// 根据用户获得科目信息
		User user = (User) session.getAttribute("user");
		List<Subject> sLists = subjectService.getSubjectsByUId(user.getuId().toString());

		map.put("subjects", sLists);
		result.setMap(map);
		result.setRtnCode("0");
		result.setRtnMessage("科目信息查询成功");
		return result;
		// 因为格式化时间的问题，先将List<Subject>转成List<Map>
		/*
		 * for(Subject subject:sLists) { Map<String,String> tempMap=new HashMap<String,
		 * String>(); tempMap.put("sId", subject.getsId().toString());
		 * tempMap.put("sTitle",subject.getsTitle());
		 * tempMap.put("sAddTime",simpleDateFormat.format(subject.getsAddTime()));
		 * tempMap.put("sDesc",subject.getsDesc()); resultLists.add(tempMap); }
		 * 
		 * String data= JSON.toJSONString(resultLists);
		 * System.out.println("json数据：：：：：：：：：：：："); System.out.println(data);
		 * ResponseUtil.renderData(response, data);
		 */

	}

	// 初始化题型信息
	@RequestMapping(value = "/questionTypes", method = RequestMethod.GET)
	@ResponseBody
	public Result getAllQuesTypes() {
		Result result = new Result();
		List<QuestionType> resultLists = qTService.getAllQuestionTypes();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quesTypes", resultLists);
		result.setMap(map);
		result.setRtnCode("0");
		result.setRtnMessage("题型信息查询成功");
		return result;
	}

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
	
	//题目的难易程度值设值
	@RequestMapping(value="qDiffLevel",method=RequestMethod.GET)
	@ResponseBody
	public Result getQuestionDifficultyLevel() {
		Result result=new Result();
		Map<String,Object> resultMap=new HashMap<>();
		resultMap.put("veryEasy",Constants.DIFFICULTY_LEVEL_VERYEASY);
		resultMap.put("easy", Constants.DIFFICULTY_LEVEL_EASY);
		resultMap.put("medium", Constants.DIFFICULTY_LEVEL_MEDIUM);
		resultMap.put("hard", Constants.DIFFICULTY_LEVEL_HARD);
		resultMap.put("veryHard", Constants.DIFFICULTY_LEVEL_VERYHARD);
		result.setMap(resultMap);
		result.setRtnCode("0");
		result.setRtnMessage("题目难易程度查询成功");
		return result;
	}

	//上传文件到服务器
	@RequestMapping("uploadFile")
	public void imageUpload(HttpServletRequest request,HttpServletResponse response) {
		//String rootPath=request.getSession().getServletContext().getRealPath("/");
		String DirectoryName = Constants.PHOTO_DIRECTORY_NAME;
	        try {
	            ImageUploadUtil.ckeditor(request, response, DirectoryName);
	        } catch (IllegalStateException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
}
