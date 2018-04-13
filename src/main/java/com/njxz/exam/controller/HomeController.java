package com.njxz.exam.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.njxz.exam.modle.News;
import com.njxz.exam.modle.SystemConfig;
import com.njxz.exam.modle.User;
import com.njxz.exam.service.NewsService;
import com.njxz.exam.service.SystemConfigService;
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
	@Autowired
	private NewsService newsService;
	@Autowired
	private SystemConfigService scs;

	@RequestMapping(value="/",method=RequestMethod.GET)
	public String test(Model model) {
		model.addAttribute("user", new User());
		return "redirect:/login";
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

		String a=StringUtil.EncoderByMd5(user.getPassword());
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
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String, Object>> resultList=new ArrayList<>();
		
		User user=(User) session.getAttribute("user");
		if(user==null) {
			return "login";
		}
		
		model.addAttribute("user", user);
		String power=user.getPower()==1?"录入人员":user.getPower()==2?"教师":user.getPower()==3?"管理员":"";
		model.addAttribute("power", power);
		
		//公告信息---取最近的五条
		List<News> lists=newsService.latelyNews(5);
		for(News news:lists) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("nId", news.getnId().toString());
			map.put("nContent", news.getnContent());
			map.put("addTime",format.format(news.getnAddTime()));
			map.put("userId", news.getUserId());
			//添加人姓名
			if(news.getUserId()!=null) {
				User user2=userService.findUser(news.getUserId().toString());
				if(user2!=null) {
					map.put("userName",user2.getName());
				}else {
					map.put("userName", "该用户已注销");
				}
			}else {
				map.put("userName","");
			}
			resultList.add(map);
		}
		model.addAttribute("newsList", resultList);
		
		//系统信息
		List<SystemConfig> list = scs.getAll();
		model.addAttribute("systemConfigs", list);
		
		return "index";
	}
	
	//无权限页面
	@RequestMapping(value="/powerOff",method=RequestMethod.GET)
	public String powerOffPage() {
		return "powerOff";
	}
}
