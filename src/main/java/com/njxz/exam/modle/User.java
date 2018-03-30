package com.njxz.exam.modle;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

/*
 * spring 提供对java validation API的支持
 * 使用java校验api的实现--Hibernate Validation(与Hibernate没有关系)
 * */
@Alias("User")
@Component
public class User implements Serializable{
	//生成唯一的序列化Id，考虑兼容性要求
	private static final long serialVersionUID = 5656989856162309746L;
	// 用户id，自增
	private Long uId;
	// 用户名
	@NotNull(message = "{user.username.nullOrEmpty}")
	@NotEmpty(message = "{user.username.nullOrEmpty}")
	private String username;
	// 用户密码
	@NotNull(message = "{user.password.nullOrEmpty}")
	@NotEmpty(message = "{user.password.nullOrEmpty}")
	private String password;
	// 用户姓名
	
	private String name;
	
	// 用户权限：三种：1普通用户 2教师 3管理员
	@NotNull(message="{user.power.notNull}")
	private int power;
	// 直接上级：管理员的上级为0
	private Long parentId;
	// 注册时间
	private Date registTime;
	// 最近登录时间
	private Date recentLoginTime;

	public User() {

	}

	public User(Long uId, String username, String password, String name, int power, Long parentId, Date registTime) {
		super();
		this.uId = uId;
		this.username = username;
		this.password = password;
		this.name = name;
		this.power = power;
		this.parentId = parentId;
		this.registTime = registTime;
	}

	public Long getuId() {
		return uId;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public Long getParentId() {
		return parentId;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public Date getRecentLoginTime() {
		return recentLoginTime;
	}

	public void setuId(Long uId) {
		this.uId = uId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public void setRecentLoginTime(Date recentLoginTime) {
		this.recentLoginTime = recentLoginTime;
	}

	@Override
	public String toString() {
		return "User [uId=" + uId + ", username=" + username + ", password=" + password + ", name=" + name + ", power="
				+ power + ", parentId=" + parentId + ", registTime=" + registTime + ", recentLoginTime="
				+ recentLoginTime + "]";
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

}
