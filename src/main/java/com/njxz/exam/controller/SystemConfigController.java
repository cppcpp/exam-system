package com.njxz.exam.controller;

import java.util.List;

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

import com.github.pagehelper.StringUtil;
import com.njxz.exam.modle.SystemConfig;
import com.njxz.exam.service.SystemConfigService;
import com.njxz.exam.util.ErrorUtil;

@Controller
@RequestMapping("/systemConfig")
public class SystemConfigController {

	@Autowired
	SystemConfigService scs;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String systemConfigAddPage(Model model) {
		model.addAttribute("systemConfig", new SystemConfig());

		return "systemConfigAdd";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView systemConfigAdd(@Valid SystemConfig systemConfig, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();

		if (!ErrorUtil.addErrorToView(bindingResult, mav)) {
			mav.setViewName("systemConfigAdd");
			return mav;
		}

		int count = scs.add(systemConfig);

		if (count != 1) {
			mav.addObject("systemConfigAddError", "系统异常，系统信息添加失败");
			mav.setViewName("systemConfigAdd");
		} else {
			mav.setViewName("redirect:/systemConfig/all");
		}

		return mav;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView systemConfigAllPage() {
		ModelAndView mav = new ModelAndView();
		List<SystemConfig> list = scs.getAll();
		mav.addObject("systemConfigs", list);
		mav.setViewName("systemConfigs");
		return mav;
	}

	@RequestMapping(value = "/modify")
	public String modify(@RequestParam("id") String id, @RequestParam("key") String key,
			@RequestParam("value") String value, Model model) {
		SystemConfig systemConfig = new SystemConfig();
		if (StringUtil.isNotEmpty(id)) {
			systemConfig.setId(new Byte(id));
		}
		if (StringUtil.isNotEmpty(key)) {
			systemConfig.setKey(key);
		} else {
			systemConfig.setKey("");
		}
		if (StringUtil.isNotEmpty(value)) {
			systemConfig.setValue(value);
		} else {
			systemConfig.setValue("");
		}
		int count = scs.modify(systemConfig);
		if (count != 1) {
			model.addAttribute("systemConfigModifyError", "系统异常，系统信息修改异常");
		}
		return "redirect:/systemConfig/all";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable(name = "id", required = true) String id, Model model) {
		int count = scs.delete(id);
		if (count != 1) {
			model.addAttribute("systemConfigDeleteError", "系统异常，系统信息删除异常");
		}
		return "redirect:/systemConfig/all";
	}
}
