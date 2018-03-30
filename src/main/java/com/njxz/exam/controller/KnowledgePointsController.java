package com.njxz.exam.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njxz.exam.modle.KnowledgePoints;
import com.njxz.exam.modle.Subject;
import com.njxz.exam.modle.User;
import com.njxz.exam.service.KnowledgePointsService;
import com.njxz.exam.service.SubjectService;
import com.njxz.exam.service.UserSubjectService;
import com.njxz.exam.util.ErrorUtil;

/*
 * piperChan 2017/11/9
 * 
 * 知识点管理
 * 
 * 涉及用户：教师、管理员
 * 
 * 教师--管理自己科目下的知识点
 * 管理员--管理所有科目下的知识点
 * 
 * 
 * 
 * */
@Controller
@RequestMapping("/knowledgePoints")
public class KnowledgePointsController {

	@Autowired
	public UserSubjectService userSubjectService;
	@Autowired
	public SubjectService subjectService;
	@Autowired
	public KnowledgePointsService knowledgePointsService;

	@Autowired
	public HttpSession session;

	@Autowired
	public HttpServletRequest request;

	// 初始化知识点页面
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String init(Model model) {
		List<Subject> listS = null;

		// 从session中取用户信息，目前没有，模拟uId=2的用户（session中存放的是user类）-----完成
		// 从user-subject表中查出该用户的所有科目
		User user= (User) session.getAttribute("user");
		listS = subjectService.getSubjectsByUId(user.getuId().toString());

		// 分页-----
		session.setAttribute("sId", null);
		model.addAttribute("subjects", listS);

		return "knowledgePoints";
	}

	// 根据科目查出知识点信息----分页
	@RequestMapping(value = "/{sId}", method = RequestMethod.GET)
	public String getAllKonwledgePointsBySubjectId(@PathVariable(name = "sId", required = true) String sId,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize, Model model) {
		// 将sId保存在session中，重复使用（分页）
		session.setAttribute("sId", sId);
		// 开始分页
		PageHelper.startPage(pageNum, pageSize);

		User user=(User) session.getAttribute("user");
		// 需要将所有科目信息重新插一遍，为了减少查询，用缓存
		List<Subject> listS = subjectService.getSubjectsByUId(user.getuId().toString());
		List<KnowledgePoints> listKP = knowledgePointsService.getKnowledgePointsBySId(sId);
		model.addAttribute("subjects", listS);
		model.addAttribute("knowledgePoints", listKP);

		PageInfo<KnowledgePoints> page = new PageInfo<KnowledgePoints>(listKP);
		model.addAttribute("page", page);

		
		return "knowledgePoints";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addKnowledgePointsPage(KnowledgePoints konwledgePoints, Model model) {
		// --------------------2---------虚拟数据--从session中取--完成
		User user= (User) session.getAttribute("user");
		List<Subject> listS = subjectService.getSubjectsByUId(user.getuId().toString());
//		model.addAttribute("subjects", listS);//--------如果输入数据失败，重新请求，失效。因此存入session中
		session.setAttribute("subjects", listS);
		model.addAttribute("konwledgePoint", new KnowledgePoints());
		return "knowledgePointAdd";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addKnowledgePoints(@Valid KnowledgePoints knowledgePoints, BindingResult bindingResult,
			Model model) {
		ModelAndView mav = new ModelAndView();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		if (!ErrorUtil.addErrorToView(bindingResult, mav)) {
			mav.setViewName("knowledgePointAdd");
			return mav;
		}
		
		//如果(kNum,kSubNum)在数据库中已经有的话，返回增加页面
		
		

		//String的构造函数和包装原语的对象不应该被使用--原因：表意不明、占用更多内存
		Subject subject = subjectService.getSubjectById(Long.valueOf(knowledgePoints.getSubjectId()));

		// 增加科目标题
		knowledgePoints.setSubjectTitle(subject.getsTitle());

		// 增加添加时间
		knowledgePoints.setkAddtime(new Date());

		int count = knowledgePointsService.add(knowledgePoints);

		if (count < 1) {
			mav.setViewName("knowledgePointAdd");
			mav.addObject("knowledgePointAddError", "服务器问题，科目添加失败");
		} else {
			// 查当前科目下的所有知识点
			mav.setViewName("redirect:/knowledgePoints/" + knowledgePoints.getSubjectId());
		}
		return mav;
	}

	@RequestMapping("/delete/{sId}/{kId}")
	public ModelAndView delete(@PathVariable(name = "kId") String kId, @PathVariable(name = "sId") String sId) {
		ModelAndView mav = new ModelAndView();
		int count = knowledgePointsService.deleteByKId(kId);
		if (count < 1) {
			mav.addObject("kPDeleteErr", "系统异常，科目删除失败");
		}
		mav.setViewName("redirect:/knowledgePoints/" + sId);

		return mav;
	}

	@RequestMapping(value = "/modify/{kId}", method = RequestMethod.GET)
	public ModelAndView modifyPage(@PathVariable(name = "kId") String kId) {
		ModelAndView modelAndView = new ModelAndView();

		KnowledgePoints kp = knowledgePointsService.getKnowledgePointsByKId(kId);
		modelAndView.addObject("knowledgePoints", kp);

		// --------------------2---------虚拟数据--从session中取
		User user=(User) session.getAttribute("user");
		List<Subject> listS = subjectService.getSubjectsByUId(user.getuId().toString());
		
		List<Object> listSTitle=new ArrayList<Object>();
		for(Subject subject:listS) {
			listSTitle.add(subject.getsTitle());
		}
		
		session.setAttribute("sTitles", listSTitle);

		modelAndView.setViewName("knowledgePointsModify");
		return modelAndView;
	}

	@RequestMapping(value = "/modify/{kId}", method = RequestMethod.POST)
	public ModelAndView modify(@PathVariable(name = "kId") String kId, @Valid KnowledgePoints knowledgePoints,
			BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();

		if (!ErrorUtil.addErrorToView(bindingResult, mav)) {
			mav.setViewName("knowledgePointsModify");
			return mav;
		}

		knowledgePoints.setkId(Long.parseLong(kId));
		int count= knowledgePointsService.updateByPrimaryKey(knowledgePoints);
		if(count<1) {
			System.out.println("=========异常======");
			mav.addObject("KPModifyError", "系统异常，修改知识点失败");
			mav.setViewName("knowledgePointsModify");
		}else {
			mav.setViewName("redirect:/knowledgePoints/" + knowledgePoints.getSubjectId());
		}
		return mav;
	}
}
