package com.njxz.exam.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
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
import com.njxz.exam.modle.QuestionType;
import com.njxz.exam.modle.Subject;
import com.njxz.exam.service.QuestionTypeService;
import com.njxz.exam.util.ErrorUtil;

@Controller
@RequestMapping("/questionType")
public class QuestionTypeController {

	@Autowired
	public QuestionTypeService questionTypeService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView getQuestionTypes(
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
		ModelAndView mav = new ModelAndView();

		PageHelper.startPage(pageNum, pageSize);

		List<QuestionType> listsQT = questionTypeService.getAllQuestionTypes();
		mav.addObject("questionTypes", listsQT);

		PageInfo<QuestionType> page = new PageInfo<QuestionType>(listsQT);
		mav.addObject("page", page);

		mav.setViewName("questionTypes");
		return mav;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addQuestionTypePage(Model model) {
		QuestionType questionType = new QuestionType();
		model.addAttribute("questionType", questionType);
		return "questionTypeAdd";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addQuestionType(@Valid QuestionType questionType, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();

		if (!ErrorUtil.addErrorToView(bindingResult, mav)) {
			mav.setViewName("questionTypeAdd");
			return mav;
		}

		int count = questionTypeService.insertQuestionType(questionType);
		if (count < 1) {
			mav.addObject("questionTypeAddError", "服务器错误，题型添加失败");
			mav.setViewName("questionTypeAdd");
		} else {
			mav.setViewName("redirect:/questionType/all");
		}

		return mav;
	}

	// 删除题型信息
	@RequestMapping("/delete/{id}")
	public ModelAndView deleteSubject(@PathVariable(value = "id", required = true) String id) {
		ModelAndView mav = new ModelAndView();
		Long sId = Long.parseLong(id);
		int count = questionTypeService.delete(id);
		if (count < 1) {
			mav.addObject("questionTypeDeleteErr", "系统异常，题型删除失败");
		}
		mav.setViewName("redirect:/questionType/all");

		return mav;
	}

	// 跳到修改题型信息页面
	@RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
	public ModelAndView modifySubjectPage(@PathVariable(value = "id", required = true) String id) {
		ModelAndView mav = new ModelAndView();
		Long sId = Long.parseLong(id);
		QuestionType questionType = questionTypeService.getQuestionTypeById(id);
		mav.addObject("questionType", questionType);
		mav.setViewName("questionTypeModify");
		return mav;
	}

	// 修改题型信息
	@RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
	public ModelAndView modifySubject(@PathVariable(value = "id", required = true) String id,
			@Valid QuestionType questionType, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();

		// 校验输入的科目信息--如果有错，直接返回增加页面
		if (!ErrorUtil.addErrorToView(bindingResult, mav)) {
			mav.setViewName("questionTypeModify");
			return mav;
		}

		questionType.settId(Long.parseLong(id));
		int count = questionTypeService.modify(questionType);

		if (count < 1) {
			mav.setViewName("questionTypeModify");
			mav.addObject("questionTypeModifyError", "服务器问题，科目修改失败");
		} else {
			mav.setViewName("redirect:/questionType/all");
		}
		return mav;
	}

}
