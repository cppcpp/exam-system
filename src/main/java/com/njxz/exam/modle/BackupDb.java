package com.njxz.exam.modle;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class BackupDb {
	private Long bId;

	private Short userId;

	private String bFileName;

	private Date bTime;

	public Long getbId() {
		return bId;
	}

	public void setbId(Long bId) {
		this.bId = bId;
	}

	public Short getUserId() {
		return userId;
	}

	public void setUserId(Short userId) {
		this.userId = userId;
	}

	public String getbFileName() {
		return bFileName;
	}

	public void setbFileName(String bFileName) {
		this.bFileName = bFileName == null ? null : bFileName.trim();
	}

	public Date getbTime() {
		return bTime;
	}

	public void setbTime(Date bTime) {
		this.bTime = bTime;
	}
}