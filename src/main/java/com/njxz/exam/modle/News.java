package com.njxz.exam.modle;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
public class News {
	private Long nId;

	@NotNull(message="news.userId.notNull")
	private Short userId;

	@NotEmpty(message="news.content.notNullOrEmpty")
	@NotNull(message="news.content.notNullOrEmpty")
	private String nContent;

	private Date nAddTime;

	public Long getnId() {
		return nId;
	}

	public void setnId(Long nId) {
		this.nId = nId;
	}

	public Short getUserId() {
		return userId;
	}

	public void setUserId(Short userId) {
		this.userId = userId;
	}

	public String getnContent() {
		return nContent;
	}

	public void setnContent(String nContent) {
		this.nContent = nContent == null ? null : nContent.trim();
	}

	public Date getnAddTime() {
		return nAddTime;
	}

	public void setnAddTime(Date nAddTime) {
		this.nAddTime = nAddTime;
	}

	@Override
	public String toString() {
		return "News [nId=" + nId + ", userId=" + userId + ", nContent=" + nContent + ", nAddTime=" + nAddTime + "]";
	}
}