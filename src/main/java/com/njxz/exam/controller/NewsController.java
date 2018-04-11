package com.njxz.exam.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.fasterxml.jackson.databind.ser.std.MapProperty;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.njxz.exam.modle.News;
import com.njxz.exam.modle.User;
import com.njxz.exam.service.NewsService;
import com.njxz.exam.service.UserService;
import com.njxz.exam.util.ErrorUtil;

import net.sf.ehcache.search.parser.MCriteria.Simple;
import sun.net.www.content.text.plain;

@Controller
@RequestMapping(value="/news")
public class NewsController {
	
	@Autowired
	NewsService ns;
	
	@Autowired
	UserService us;
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String newsAddPage(Model model) {
		model.addAttribute("news", new News());
		return "newsAdd";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ModelAndView newsAdd(@Valid News news,BindingResult bindingResult) {
		ModelAndView mav=new ModelAndView();
		
		if(!ErrorUtil.addErrorToView(bindingResult, mav)) {
			mav.setViewName("newsAdd");
			return mav;
		}
		//添加时间-------
		if(news.getnAddTime()==null) {
			news.setnAddTime(new Date());
		}
		
		
		int count=ns.add(news);
		if(count!=1) {
			mav.addObject("newsAddError","系统异常，公告信息添加失败");
			mav.setViewName("newAdd");
		}else {
			mav.setViewName("redirect:/news/all");
		}
		
		return mav;
	}
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public ModelAndView newsAllPage(@RequestParam(value="pageNum",required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(value="pageSize",required = false, defaultValue = "10") Integer pageSize) {
		ModelAndView mav=new ModelAndView();
		List resultList=new ArrayList<>();
		
		//取出页面信息
		PageHelper.startPage(pageNum, pageSize);
				
		List<News> lists=ns.getAll();
		//将添加人的姓名存入到集合中，返回给前台
		for(News news:lists) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("nId", news.getnId().toString());
			map.put("nContent", news.getnContent());
			map.put("addTime",news.getnAddTime());
			map.put("userId", news.getUserId());
			//添加人姓名
			if(news.getUserId()!=null) {
				User user=us.findUser(news.getUserId().toString());
				if(user!=null) {
					map.put("userName",user.getName());
				}else {
					map.put("userName", "该用户已注销");
				}
			}else {
				map.put("userName","");
			}
			resultList.add(map);
		}
		
		PageInfo<News> page=new PageInfo<News>(lists);
		mav.addObject("newses", resultList);
		mav.addObject("page",page);
		
		mav.setViewName("newses");
		return mav;
	}
	
	@RequestMapping(value="/delete/{id}")
	public String delete(@PathVariable("id")String id,Model model) {
		int count= ns.delete(id);
		if(count!=1) {
			model.addAttribute("newsDeleteError", "系统异常，新闻信息删除失败");
		}
		
		return "redirect:/news/all";
	}
	
	@RequestMapping("/modify")
	public String modify(@RequestParam(name="nId",required=true)String nId,
			@RequestParam(name="nContent",required=true) String nContent,
			@RequestParam(name="userId",required=true)String userId,
			@RequestParam(name="nAddTime",required=true)String nAddTime,Model model) {
		News news=new News();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		if(StringUtil.isNotEmpty(nId)) {
			news.setnId(new Long(nId));
		}else {
			news.setnId(null);
		}
		
		if(StringUtil.isNotEmpty(nContent)) {
			news.setnContent(nContent);
		}else {
			news.setnContent("");
		}
		
		if(StringUtil.isNotEmpty(userId)) {
			news.setUserId(new Short(userId));
		}else {
			news.setUserId(null);
		}
		
		try {
			if(StringUtil.isNotEmpty(nAddTime)) {
			news.setnAddTime(format.parse(nAddTime));
			}else {
				news.setnAddTime(new Date());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int count=ns.modify(news);
		if(count!=1) {
			model.addAttribute("newsModiftError", "系统异常，公告信息修改失败");
		}
		return "redirect:/news/all";
		
	}
	
}
