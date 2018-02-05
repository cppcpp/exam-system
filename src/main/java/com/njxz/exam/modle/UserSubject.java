package com.njxz.exam.modle;

import org.springframework.stereotype.Component;

@Component
public class UserSubject {
	private Long uSId;

	private Long userId;

	private Long subjectId;

	public Long getuSId() {
		return uSId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public void setuSId(Long uSId) {
		this.uSId = uSId;
	}

	
}