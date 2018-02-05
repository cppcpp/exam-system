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
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njxz.exam.modle.Subject;
import com.njxz.exam.modle.User;
import com.njxz.exam.modle.UserSubject;
import com.njxz.exam.service.SubjectService;
import com.njxz.exam.service.UserService;
import com.njxz.exam.service.UserSubjectService;
import com.njxz.exam.util.ErrorUtil;
import com.njxz.exam.util.Logable;
import com.njxz.exam.util.StringUtil;

@Controller
@RequestMapping("/user")
public class UserController extends Logable {
	@Autowired
	private UserService userService;

	@Autowired
	private HttpSession session;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private UserSubjectService userSubjectService;
	
	// 测试用
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	public UserController() {

	}

	@RequestMapping("/users")
	public String getAllUser(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "count", defaultValue = "10") int count, Model model) {
		info("得到所有用户的控制器---");

		/*
		 * Model其实是一个Map，key-value 这里没有指定键值，可以从value的值判断出来 指定键值：
		 * model.addAttribute("userList",userService.getAllUser());
		 * 可以用java.util.Map代替Model 当视图是jsp时，模型数据已经放在了请求（request）中
		 * 
		 */
		model.addAttribute("userList", userService.findUsers(page, count));

		// 可以直返回userServce.getUser()这样的集合或者对象时。这个值会放在模型中，key可以根据它的值判断出；而逻辑视图名根据请求路径得出，比如此请求的逻辑视图名是getAllUser
		return "users";
	}

	// 面向资源 PathVariable的value可以省略，默认和请求的占位符名称相同
	@RequestMapping(value = "/{uId}", method = RequestMethod.GET)
	public String show(@PathVariable(value = "uId") int uId, Model model) {

		model.addAttribute("user", userService.findUser(uId+""));
		return "show";
	}

	//用户添加页面
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String userAddPage(Model model) {
		List<Subject> subjectList=new ArrayList<Subject>();
		Map<String, String> powerMap=new HashMap<String, String>();
		
		//得到session中的user对象
		User user= (User) session.getAttribute("user");
		
		if(user==null)
			return "redirect:/login";
		
		//根据user的权限配置添加    用户的权限     与    所属科目
		if(user.getPower()==3) {
			subjectList=subjectService.selectAllSubject();
			powerMap.put( "1","试题录入人员");
			powerMap.put( "2","教师");
			powerMap.put( "3","管理员");
			
		}else if(user.getPower()==2) {
			subjectList= subjectService.getSubjectsByUId(user.getuId().toString());
			powerMap.put( "1","试题录入人员");
		}
		
		model.addAttribute("subjects", subjectList);
		
		model.addAttribute("powers", powerMap);
		model.addAttribute("addUser",new User());
		
		return "userAdd";
	}
	
	//添加用户
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ModelAndView userAdd(@Valid User user,BindingResult bindingResult,
			@RequestParam(value="subjects",required=false)String[] subjects) {
		ModelAndView mav=new ModelAndView();
		User sessionUser=(User) session.getAttribute("user");
		if(sessionUser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		
		//表单验证错误，返回用户增加页面
		if(!ErrorUtil.addErrorToView(bindingResult, mav)) {
			mav.setViewName("userAdd");
			return mav;
		}
		
		//设置主键
		if(user.getuId()==null) {
			user.setuId(StringUtil.seqGenerate());
		}
		
		//密码加密
		if(user.getPassword()!=null) {
			user.setPassword(StringUtil.EncoderByMd5(user.getPassword()));
		}
		
		//设置parentId和registTime
		//管理员的parentId为0
		if(user.getPower()==3) {
			user.setParentId(new Long(0));
		}else {
			user.setParentId(sessionUser.getuId());
		}
		
		user.setRegistTime(new Date());
		int count= userService.SaveUser(user);
		if(count!=1) {
			mav.addObject("addUserError","添加用户失败");
			return mav;
		}
		
		//建立user和subject的联系
		for(String subject:subjects) {
			UserSubject userSubject=new UserSubject();
			userSubject.setUserId(user.getuId());
			userSubject.setSubjectId(new Long(subject));
			int count1=userSubjectService.addUserSubject(userSubject);
			if(count1!=1) {
				mav.addObject("userSubjectAddError", "用户科目联系表添加失败");
				mav.setViewName("userAdd");
				return mav;
			}
		}
		
		mav.setViewName("redirect:all/"+user.getPower());
		return mav;
	}
	
	
	@RequestMapping(value="/all/{power}",method=RequestMethod.GET)
	public String userAll(@PathVariable("power")int power,Model model,
			@RequestParam(value="pageNum",required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(value="pageSize",required = false, defaultValue = "10") Integer pageSize) {
		List<Map<String, String>> userList=new ArrayList<>();
		List<Subject> subjectList=null;
		Long intTemp;
		String strTemp="";
		User userTemp=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		//取出页面信息
		PageHelper.startPage(pageNum, pageSize);
		
		User sessionUser= (User) session.getAttribute("user");
		//session中的User的power一定>=power 
		//Filter中已经进行过滤
		/*if(sessionUser.getPower()==1)
			return "redirect:/powerOff";
		if(sessionUser.getPower()==2) {
			if(sessionUser.getPower()<power) {
				return "redirect:/powerOff";
			}
		}*/
		
		List<User> lists= userService.findUsers(power);
		
		PageInfo<User> page=new PageInfo<User>(lists);
		
		for(User user:lists) {
			Map<String, String> tempMap=new HashMap<>();
			tempMap.put("uId", user.getuId().toString());
			tempMap.put("username",user.getUsername());
			tempMap.put("password", user.getPassword());
			tempMap.put("name", user.getName());
			
			tempMap.put("power", String.valueOf(user.getPower()));
			
			intTemp=(long)user.getPower();
			//int 默认 0
			if(intTemp!=0) {
				strTemp=intTemp==1?"录入人员":intTemp==2?"教师":intTemp==3?"管理员":"";
			}else {
				strTemp="";
			}
			tempMap.put("powerChinese",strTemp);
			tempMap.put("parentId", String.valueOf(user.getParentId()));
			
			intTemp=user.getParentId();
			//int 默认 0,管理员parentId=9999
			if(intTemp!=0) {
				userTemp=userService.findUser(intTemp.toString());
				if(userTemp!=null) {
					strTemp=userTemp.getName();
				}else {
					strTemp="";
				}
			}else {
				strTemp="";
			}
			tempMap.put("parentName", strTemp);
			if(user.getRegistTime()!=null) {
				tempMap.put("registTime", sdf.format(user.getRegistTime()));
			}else {
				tempMap.put("registTime", "");
			}
			if(user.getRecentLoginTime()!=null) {
				tempMap.put("recentLoginTime", sdf.format(user.getRecentLoginTime()));
			}else {
				tempMap.put("recentLoginTime", "");
			}
			
			//人员管理科目
			if(user.getPower()==3) {
				subjectList=subjectService.selectAllSubject();
			}else {
				subjectList=subjectService.getSubjectsByUId(user.getuId().toString());
			}
			
			strTemp="";
			for(Subject subject:subjectList) {
				strTemp+=subject.getsTitle()+"、";
			}
			tempMap.put("subjects", strTemp);
		
			
			userList.add(tempMap);
		}
		
		model.addAttribute("page",page);
		model.addAttribute("users",userList);
		model.addAttribute("power",power);
		return "users";
	}
	
	@RequestMapping(value="/modify/{uId}",method=RequestMethod.GET)
	public ModelAndView modifyPage(@PathVariable(name="uId")Long uId) {
		ModelAndView mav=new ModelAndView();
		List<Map<String, Object>> subjectsResult=new ArrayList<>();
		List<Map<String, Object>> powerResult=new ArrayList<>();
		List<Subject> subjectsAll=null;
		
		User user=userService.findUser(uId.toString());
		User sessionUser=(User) session.getAttribute("user");
		
		//根据session_id找到展示的所有科目信息
		//根据user_subject中的联系找到所有自己相关的科目
		//如果是，设计flag=true
		if(sessionUser.getPower()==3) {
			subjectsAll=subjectService.selectAllSubject();
		}else {
			subjectsAll=subjectService.getSubjectsByUId(sessionUser.getParentId().toString());
		}
		for(Subject subject:subjectsAll) {
			Map<String, Object> tempMap=new HashMap<>();
			tempMap.put("sId", subject.getsId());
			tempMap.put("sTitle", subject.getsTitle());
			if(userSubjectService.isExist(user.getuId(), subject.getsId())) {
				tempMap.put("exits",true);
			}else {
				tempMap.put("exits",false);
			}
			subjectsResult.add(tempMap);
		}
		
		//根据sessionUser_power展示权限
		User parentUser=userService.findUser(sessionUser.getuId().toString());
		
		if(parentUser.getPower()==3) {
			Map<String,Object> tempMap1=new HashMap<>();
			Map<String,Object> tempMap2=new HashMap<>();
			Map<String,Object> tempMap3=new HashMap<>();
			
			tempMap1.put("power", 1);
			tempMap1.put("powerChinese", "试题录入人员");
			tempMap1.put("exits",1==user.getPower());
			
			tempMap2.put("power",2);
			tempMap2.put("powerChinese", "教师");
			tempMap2.put("exits", 2==user.getPower());
			
			tempMap3.put("power",3);
			tempMap3.put("powerChinese", "管理员");
			tempMap3.put("exits", 3==user.getPower());
			
			powerResult.add(tempMap1);
			powerResult.add(tempMap2);
			powerResult.add(tempMap3);
		}
		
		if(parentUser.getPower()==2) {
			Map<String,Object> tempMap1=new HashMap<>();
			tempMap1.put("power", 1);
			tempMap1.put("powerChinese", "试题录入人员");
			tempMap1.put("exits",1==user.getPower());
			powerResult.add(tempMap1);
		}
		
		mav.addObject("subjectsResult", subjectsResult);
		mav.addObject("powerResult", powerResult);
		mav.addObject("modifyUser",user);
		mav.addObject("power", user.getPower());
		mav.setViewName("userModify");
		return mav;
	}
	
	@RequestMapping(value="/modify/{uId}",method=RequestMethod.POST)
	public ModelAndView modify(@PathVariable(name="uId",required=true)Long uId,
			@Valid User user, BindingResult bindingResult,
			@RequestParam(value="subjects",required=false)String[] subjects
			) {
		ModelAndView mav=new ModelAndView();
		if(ErrorUtil.addErrorToView(bindingResult, mav)) {
			mav.setViewName("userModify");
			return mav;
		}
		
		//更新user-subject
		//先删再插
		int count= userSubjectService.deleteByUId(uId.toString());
		int count1;
		if(count>0) {
			for(String sId:subjects) {
				UserSubject us=new UserSubject();
				us.setSubjectId(new Long(sId));
				us.setUserId(uId);
				count1= userSubjectService.addUserSubject(us);
				if(count1!=1) {
					mav.addObject("addUserSubjectError", "插入用户-科目关系表失败");
				}
			}
		}else {
			mav.addObject("deleteUserSubjectError", "删除用户-科目关系表失败");
		}
		
		
		userService.updateUser(user);
		mav.setViewName("redirect:/user/all/"+user.getPower());
		return mav;
	}
	
	
	
	@RequestMapping(value="/personalInfo",method=RequestMethod.GET)
	public String userPersonalInfo() {
		
		return "userPersonalInfo";
	}
	
	@RequestMapping(value="/modifyPassword",method=RequestMethod.GET)
	public String modifyPasswordPage() {
		
		return "userModifyPassword";
	}
	
	@RequestMapping(value="logout")
	public String logout() {
		if(session.getAttribute("user")!=null) {
			session.removeAttribute("user");
		}
		return "redirect:/login";
	}
}
