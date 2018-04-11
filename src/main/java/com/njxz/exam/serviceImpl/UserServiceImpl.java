package com.njxz.exam.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.njxz.exam.dao.UserMapper;
import com.njxz.exam.dao.UserSubjectMapper;
import com.njxz.exam.modle.User;
import com.njxz.exam.modle.UserExample;
import com.njxz.exam.modle.UserExample.Criteria;
import com.njxz.exam.modle.UserSubjectExample;
import com.njxz.exam.service.UserService;
import com.njxz.exam.service.UserSubjectService;

//默认名称:类名首字母小写
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserSubjectMapper usMapper;

	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findUsers(int page, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	public User findUser(String uId) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(new Long(uId));
	}

	public User findUser(String userName, String password) {
		// TODO Auto-generated method stub
		UserExample example=new UserExample();
		Criteria criteria= example.createCriteria();
		criteria.andUsernameEqualTo(userName);
		criteria.andPasswordEqualTo(password);
		
		List<User> list= userMapper.selectByExample(example);
		if(list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	public int SaveUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.insert(user);
	}
	
	public int updateUser(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public List<User> findUsers(int power) {
		UserExample example=new UserExample();
		Criteria criteria= example.createCriteria();
		criteria.andPowerEqualTo(new Byte((byte)power));
		return userMapper.selectByExample(example);
	}

	@Override
	@Transactional
	public boolean deleteUser(Long uId) {
		
		try {
			if(userMapper.deleteByPrimaryKey(uId)!=1) {
				return false;
			}
			UserSubjectExample usExample=new UserSubjectExample();
			com.njxz.exam.modle.UserSubjectExample.Criteria criteria=usExample.createCriteria();
			criteria.andUserIdEqualTo(uId);
			usMapper.deleteByExample(usExample);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
