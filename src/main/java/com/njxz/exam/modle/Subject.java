package com.njxz.exam.modle;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
public class Subject implements Serializable{
	
	private static final long serialVersionUID = 6042839088724245719L;

	private Long sId;

	@NotNull(message = "{subject.sTitle.nullOrEmpty}")
	@NotEmpty(message = "{subject.sTitle.nullOrEmpty}")
	private String sTitle;

	private Date sAddTime;

	@NotNull(message = "{subject.sDesc.nullOrEmpty}")
	@NotEmpty(message = "{subject.sDesc.nullOrEmpty}")
	private String sDesc;

	public Long getsId() {
		return sId;
	}

	public void setsId(Long sId) {
		this.sId = sId;
	}

	public String getsTitle() {
		return sTitle;
	}

	public void setsTitle(String sTitle) {
		this.sTitle = sTitle == null ? null : sTitle.trim();
	}

	public Date getsAddTime() {
		return sAddTime;
	}

	public void setsAddTime(Date sAddTime) {
		this.sAddTime = sAddTime;
	}

	public String getsDesc() {
		return sDesc;
	}

	public void setsDesc(String sDesc) {
		this.sDesc = sDesc == null ? null : sDesc.trim();
	}

	@Override
	public String toString() {
		return "Subject [sId=" + sId + ", sTitle=" + sTitle + ", sAddTime=" + sAddTime + ", sDesc=" + sDesc + "]";
	}
	

}