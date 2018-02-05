package com.njxz.exam.service;

import java.util.List;

import com.njxz.exam.modle.Subject;
import com.njxz.exam.modle.SubjectExample;

public interface SubjectService {
	public List<Subject> getAllSubjects(SubjectExample example);

	public int insert(Subject subject);
	
	public List<Subject> selectAllSubject();
	
	public int deleteBySId(Long sId);
	
	public Subject getSubjectById(Long sId);
	
	public int modifySubject(Subject subject);
	//通过userId获得所有的科目信息
	public List<Subject> getSubjectsByUId(String uId); 
	

}
