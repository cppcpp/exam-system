package com.njxz.exam.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njxz.exam.dao.SubjectMapper;
import com.njxz.exam.dao.UserMapper;
import com.njxz.exam.modle.Subject;
import com.njxz.exam.modle.SubjectExample;
import com.njxz.exam.modle.User;
import com.njxz.exam.modle.UserSubject;
import com.njxz.exam.service.SubjectService;
import com.njxz.exam.service.UserSubjectService;

@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	public SubjectMapper subjectMapper;
	
	@Autowired
	public UserSubjectService userSubjectService;
	
	@Autowired
	public UserMapper userMapper;
	
	public List<Subject> getAllSubjects(SubjectExample example) {
		return subjectMapper.selectByExample(example);
	}

	public int insert(Subject subject) {
		return subjectMapper.insert(subject);
	}

	public List<Subject> selectAllSubject() {
		return subjectMapper.selectAllSubject();
	}

	public int deleteBySId(Long sId) {
		return subjectMapper.deleteByPrimaryKey(sId);
	}

	public Subject getSubjectById(Long sId) {
		return subjectMapper.selectByPrimaryKey(sId);
	}

	public int modifySubject(Subject subject) {
		return subjectMapper.updateByPrimaryKeySelective(subject);
	}

	//将科目信息缓存，避免多次向数据库中查找
	//根据入参为键缓存，如果新增一条数据，也不会重新查找
	//@Cacheable("subjects")
	public List<Subject> getSubjectsByUId(String uId) {
		List<UserSubject> listUS = null;
		List<Subject> listS = new ArrayList<Subject>();
		
		User user=userMapper.selectByPrimaryKey(Long.parseLong(uId));
		
		if(user.getPower()==3) {
			listS=subjectMapper.selectAllSubject();
		}else {
			//非管理员，查user-subject表
			listUS = userSubjectService.getSubjects(uId);
			// 根据得到的listUS，将所有科目表查出来
			for (UserSubject us : listUS) {
				Subject subject = getSubjectById((long) us.getSubjectId());
				listS.add(subject);
			}
		}
		
		return listS;
	}

}
