package com.njxz.exam.service;

import java.util.List;

import com.njxz.exam.modle.UserSubject;

public interface UserSubjectService {
	public List<UserSubject> getSubjects(String uId);
	public int addUserSubject(UserSubject userSubject);
	public boolean isExist(Long userId,Long subjectId);
	public int deleteByUId(String uId);
	
}
