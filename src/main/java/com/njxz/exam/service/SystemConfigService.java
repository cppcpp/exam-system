package com.njxz.exam.service;

import java.util.List;

import com.njxz.exam.modle.SystemConfig;

public interface SystemConfigService {
	public List<SystemConfig> getAll();
	public int add(SystemConfig systemConfig);
	public int modify(SystemConfig systemConfig);
	public int delete(String id);
}
