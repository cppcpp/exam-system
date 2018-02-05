package com.njxz.exam.modle;

import java.util.List;
import java.util.Map;

/*
 *封装的返回的结果类
 * */
public class Result {
	public Map<String,Object> map;
	public String rtnCode;
	public String rtnMessage;
	public List list;
	
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public String getRtnCode() {
		return rtnCode;
	}
	public void setRtnCode(String rtnCode) {
		this.rtnCode = rtnCode;
	}
	public String getRtnMessage() {
		return rtnMessage;
	}
	public void setRtnMessage(String rtnMessage) {
		this.rtnMessage = rtnMessage;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "Result [map=" + map + ", rtnCode=" + rtnCode + ", rtnMessage=" + rtnMessage + "]";
	}

	
}
