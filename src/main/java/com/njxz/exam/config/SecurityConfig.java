package com.njxz.exam.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


//@Configuration
//@EnableWebMvcSecurity弃用
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource mysqlSource;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		/*http.authorizeRequests()
		.anyRequest().authenticated()//要求所有进入应用的http请求都要进行验证
		.and()
		.formLogin()
		.and()
		.httpBasic();//配置spring Security支持基于表单的验证和HTTP Basic方式的认证*/	
		
		http.formLogin().loginPage("/login").and()
		.httpBasic().and()
		.authorizeRequests()
		//对网站静态资源的无授权访问
		.antMatchers("/public/**").permitAll()
		.antMatchers("/login").permitAll()
		//除登录页面之外的所有的请求都必须验证
		.anyRequest().authenticated();
		
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication()
//		.dataSource(mysqlSource)
//		.usersByUsernameQuery(
//				"select username,password from user where username=?")
//		.authoritiesByUsernameQuery(
//				"select username,'ROLE_USER' from user where username=?");
//	}
	
	
	
}
