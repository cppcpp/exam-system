package com.njxz.exam.intercept;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.njxz.exam.modle.User;

public class SecurityFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse) response;
		String uri= req.getRequestURI();//项目名+请求路径名：/exam-system/login
		String path=uri.substring(12);
		System.out.println("path:::"+path);
		User user= (User) req.getSession().getAttribute("user");
		System.out.println("use:"+user);
		
		//登录页面，放行---以及放行静态文件
		if(path.equals("/login")||path.contains("public")) {
			filterChain.doFilter(request, response);
			return;
		}
		//未登录，重定向到登录页面
		if(user==null&&!path.equals("/login")) {
			res.sendRedirect("/exam-system/login");
			return;
		}
		
		
		if(user!=null) {
			//权限控制--管理员，所有操作
			if(user.getPower()==3);
			
			//权限控制--教师
			if(user.getPower()==2) {
				//有权访问页面
				if(path.equals("/index")||
						path.equals("/logout")||
						path.equals("/powerOff")||
						path.equals("/user/personalInfo")||
						path.equals("/user/modifyPassword")||
						Pattern.compile("/testPaper/*").matcher(path).find()||
						Pattern.compile("/knowledgePoints/*").matcher(path).find()||
						path.equals("/questions/add")||
						path.equals("/questions/subjects")||
						path.equals("/questions/questionTypes")||
						Pattern.compile("/questions/knowledgPoints/*").matcher(path).find()||
						path.equals("/questions/qDiffLevel")||
						path.equals("/user/add")||
						path.equals("/user/all/1")) {
					filterChain.doFilter(request, response);
					return ;
				}
				
				//无权访问页面
				if(Pattern.compile("/subject/*").matcher(path).find()||
						Pattern.compile("/questionType/*").matcher(path).find()||
						path.equals("/user/all/2")||
						path.equals("/user/all/3")||
						Pattern.compile("/systemConfig/*").matcher(path).find()||
						Pattern.compile("/news/*").matcher(path).find()||
						Pattern.compile("/databaseBabckup/*").matcher(path).find()||
						path.equals("/databaseBabckup/*")) {
					
					res.sendRedirect("/exam-system/powerOff");
				}
			}
			
			//权限控制--学生
			if(user.getPower()==1) {
				//有权访问页面
				if(path.equals("/index")||
						path.equals("/logout")||
						path.equals("/powerOff")||
						path.equals("/user/personalInfo")||
						path.equals("/user/modifyPassword")||
						path.equals("/questions/add")||
						path.equals("/questions/all")
						//试题的修改删除功能未写
						) {
					
					filterChain.doFilter(request, response);
				}
				
				//无权访问页面
				if(Pattern.compile("/subject/*").matcher(path).find()||
						Pattern.compile("/questionType/*").matcher(path).find()||
						Pattern.compile("/user/all/*").matcher(path).find()||
						path.equals("/user/add")||
						Pattern.compile("/systemConfig/*").matcher(path).find()||
						Pattern.compile("/news/*").matcher(path).find()||
						Pattern.compile("/databaseBabckup/*").matcher(path).find()||
						Pattern.compile("/testPaper/*").matcher(path).find()||
						Pattern.compile("/knowledgePoints/*").matcher(path).find()||
						path.equals("/questions/print")
						) {
					
					res.sendRedirect("/exam-system/powerOff");
				}
			}
			
		}
			
		filterChain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
