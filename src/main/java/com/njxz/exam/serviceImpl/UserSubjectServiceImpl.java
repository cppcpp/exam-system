package com.njxz.exam.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.njxz.exam.dao.UserSubjectMapper;
import com.njxz.exam.modle.UserSubject;
import com.njxz.exam.modle.UserSubjectExample;
import com.njxz.exam.modle.UserSubjectExample.Criteria;
import com.njxz.exam.service.UserSubjectService;

@Service("userSubjectService")
public class UserSubjectServiceImpl implements UserSubjectService{

	@Autowired
	public UserSubjectMapper usMapper;
	
	public List<UserSubject> getSubjects(String uId) {
		UserSubjectExample example=new UserSubjectExample();
		Criteria criteria= example.createCriteria();
		criteria.andUserIdEqualTo(new Long(uId));
		List<UserSubject> lists=usMapper.selectByExample(example);
		return lists;
	}

	@Override
	public int addUserSubject(UserSubject userSubject) {

		return usMapper.insert(userSubject);
	}

	@Override
	public boolean isExist(Long userId, Long subjectId) {
		UserSubjectExample example=new UserSubjectExample();
		Criteria criteria= example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andSubjectIdEqualTo(subjectId);
		
		//其实userId+subjectId相当于联合主键
		List<UserSubject> list=usMapper.selectByExample(example);
		
		if(list.size()>0) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public int deleteByUId(String uId) {
		UserSubjectExample example=new UserSubjectExample();
		Criteria criteria= example.createCriteria();
		criteria.andUserIdEqualTo(new Long(uId));
		return usMapper.deleteByExample(example);
	}

}
