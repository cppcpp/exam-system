package com.njxz.exam.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njxz.exam.dao.SystemConfigMapper;
import com.njxz.exam.modle.SystemConfig;
import com.njxz.exam.service.SystemConfigService;

@Service("systemConfigService")
public class SystemConfigServiceImpl implements SystemConfigService{

	@Autowired
	public SystemConfigMapper mapper;
	
	@Override
	public List<SystemConfig> getAll() {
		
		return mapper.getAll();
	}

	@Override
	public int add(SystemConfig systemConfig) {

		return mapper.insert(systemConfig);
	}

	@Override
	public int modify(SystemConfig systemConfig) {
		// TODO Auto-generated method stub
		return mapper.modify(systemConfig);
	}

	@Override
	public int delete(String id) {
		return mapper.deleteByPrimaryKey(new Byte(id));
	}
	
}
