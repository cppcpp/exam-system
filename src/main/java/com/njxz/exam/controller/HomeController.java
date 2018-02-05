package com.njxz.exam.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.njxz.exam.modle.User;
import com.njxz.exam.service.UserService;
import com.njxz.exam.util.Logable;
import com.njxz.exam.util.StringUtil;

@Controller
@RequestMapping("/")
public class HomeController extends Logable {
	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;

	//测试
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String test() {
		return "test";
	}
	
	
	// 请求登录页面
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginForm(Model model) {
		// 构建一个空对象，否则前台表单不能正常渲染（）
		model.addAttribute("user", new User());
		return "login";
	}

	// 处理登录的表单请求 valid是java validation API提供的校验支持
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid User user, BindingResult bindingResult, Model model) {

		// 登录的用户信息
		info("login post user:" + user.toString());
		// 如果校验有错，重回登录页面
		if (bindingResult.hasErrors()) {
			// 输出错误信息
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for (ObjectError objectError : allErrors) {
				// 输出错误信息
				error(objectError.getDefaultMessage());
			}
			model.addAttribute("allErrors", allErrors);
			return "login";
		}

		User findUser = userService.findUser(user.getUsername(), StringUtil.EncoderByMd5(user.getPassword()));
		System.out.println("----------user:--------------------------------" + findUser);

		if (findUser == null) {
			System.out.println("用户名或密码错误");
			model.addAttribute("loginError", "用户名或密码错误");
			return "login";
		}
		
		//验证登录用户存在，设置最近登录时间
		findUser.setRecentLoginTime(new Date());
		userService.updateUser(findUser);
		
		//将user类存在session中
		session.setAttribute("user", findUser);
		session.setMaxInactiveInterval(-1);
		// 还有forward:
		// redirect:/user/user.getUserId()
		return "redirect:/index";
	}

	// 处理对首页--index发起的请求
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("user", session.getAttribute("user"));
		return "index";
	}
	
	//无权限页面
	@RequestMapping(value="/powerOff",method=RequestMethod.GET)
	public String powerOffPage() {
		return "powerOff";
	}
}
