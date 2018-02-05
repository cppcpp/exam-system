package com.njxz.exam.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njxz.exam.modle.Subject;
import com.njxz.exam.modle.User;
import com.njxz.exam.modle.UserSubject;
import com.njxz.exam.service.SubjectService;
import com.njxz.exam.util.ErrorUtil;

@Controller
@RequestMapping("/subject")
public class SubjectController {
	@Autowired
	public SubjectService subjectService;

	@Autowired
	public HttpSession session;
	
	//分页测试
	@RequestMapping(value="/test",method=RequestMethod.GET)
	 public void testSelectAll() {
        Page<Subject> page = PageHelper.startPage(1, 3);
        
        //selectAll查询出的List即为上面定义的page
        subjectService.selectAllSubject();
        //注意：
        //使用PageHelper.startPage只是针对接下来的一条查询语句，
        //如果又查询了一次数据，则还需要使用一次PageHelper.startPage
        System.out.println("获取所有Doctor信息，获得记录数："+page.size());
        System.out.println("获取所有Doctor信息，获得记录："+page);
        //使用PageInfo封装
        PageInfo<Subject> info = new PageInfo<Subject>(page);
        System.out.println("info.getPages:"+info.getPages());
    }
	
	//展示所有科目信息------------错---应该展现某用户下的科目信息，如果是管理员，才展示所有信息--------完成
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView getAllSubjects(@RequestParam(value="pageNum",required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(value="pageSize",required = false, defaultValue = "10") Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView();
		List<Subject> lists=null;
		//int pageNum = Integer.parseInt(page);
		//int pageSizeNum = Integer.parseInt(pageSize);
		
		//取出页面信息
		PageHelper.startPage(pageNum, pageSize);
		
		//List<Subject> lists =  subjectService.selectAllSubject();------wrong
		User user= (User) session.getAttribute("user");
		
		lists=subjectService.getSubjectsByUId(user.getuId().toString());
		
		//如果科目信息为空，直接返回
		if(lists==null||lists.size()<1) {
			modelAndView.addObject("nullSubjects", "还没有科目信息，请添加");
			modelAndView.setViewName("subjects");
			return modelAndView;
		}
		
		
		System.out.println("取出"+lists.size()+"条数据");
		for (Subject s : lists)
			System.out.println(s.toString());
		
		if (lists == null||lists.size()<1) {
			modelAndView.addObject("EmptySubject", "还没有科目，添加科目");
		}
		modelAndView.addObject("subjects", lists);
		
		
		//分页的信息
		PageInfo<Subject> page=new PageInfo<Subject>(lists);
		modelAndView.addObject("page",page);
		
		modelAndView.setViewName("subjects");
		return modelAndView;
	}

	//找到subjectAdd的jsp页面
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addPage(Model model) {
		model.addAttribute("subject", new Subject());
		
		//jsp页面名
		return "subjectAdd";
	}

	//增加科目信息--------------------待完善---------过
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addSubject(@Valid Subject subject, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		// 校验输入的科目信息--如果有错，直接返回增加页面
		if(!ErrorUtil.addErrorToView(bindingResult, mav)) {
			mav.setViewName("subjectAdd");
			return mav;
		}
		
		//增加添加时间
		subject.setsAddTime(new Date());
		
		int count = subjectService.insert(subject);
		
		//-------------------还需要向user_subject表中插入数据（用户关联的科目）---不需要，因为只有管理员有权限操作此方法
		
		if (count < 1) {
			mav.setViewName("subjectAdd");
			mav.addObject("subjectAddError", "服务器问题，科目添加失败");
		} else {
			mav.setViewName("redirect:/subject/all");
		}
		return mav;
	}
	
	//删除科目信息
	@RequestMapping("/delete/{id}")
	public ModelAndView deleteSubject(@PathVariable(value="id",required=true) String id) {
		ModelAndView mav=new ModelAndView();
		Long sId=Long.parseLong(id);
		int count= subjectService.deleteBySId(sId);
		if(count<1) {
			mav.addObject("subjectDeleteErr", "系统异常，科目删除失败");
		}
		mav.setViewName("redirect:/subject/all");
		
		return mav;
	}
	
	//跳到修改科目信息页面
	@RequestMapping(value="/modify/{id}",method=RequestMethod.GET)
	public ModelAndView modifySubjectPage(@PathVariable(value="id",required=true) String id) {
		ModelAndView mav=new ModelAndView();
		Long sId=Long.parseLong(id);
		Subject subject= subjectService.getSubjectById(sId);
		mav.addObject("subject", subject);
		mav.setViewName("subjectModify");
		return mav;
	}
	
	//修改科目信息
	@RequestMapping(value="/modify/{id}",method=RequestMethod.POST)
	public ModelAndView modifySubject(@PathVariable(value="id",required=true)String id,@Valid Subject subject, BindingResult bindingResult) {
		ModelAndView mav=new ModelAndView();

		// 校验输入的科目信息--如果有错，直接返回增加页面
		if(!ErrorUtil.addErrorToView(bindingResult, mav)) {
			mav.setViewName("subjectModify");
			return mav;
		}

		subject.setsId(Long.parseLong(id));
		int count = subjectService.modifySubject(subject);
		
		if (count < 1) {
			mav.setViewName("subjectModify");
			mav.addObject("subjectModifyError", "服务器问题，科目修改失败");
		} else {
			mav.setViewName("redirect:/subject/all");
		}
		return mav;
	}
	
}
