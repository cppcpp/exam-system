package com.njxz.exam.modle;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
public class QuestionType {
	private Long tId;

	@NotEmpty(message="{questionType.tTitle.notNullOrEmpty}")
	@NotNull(message="{questionType.tTitle.notNullOrEmpty}")
	private String tTitle;

	private String tDesc;

	public Long gettId() {
		return tId;
	}

	public void settId(Long tId) {
		this.tId = tId;
	}

	public String gettTitle() {
		return tTitle;
	}

	public void settTitle(String tTitle) {
		this.tTitle = tTitle == null ? null : tTitle.trim();
	}

	public String gettDesc() {
		return tDesc;
	}

	public void settDesc(String tDesc) {
		this.tDesc = tDesc == null ? null : tDesc.trim();
	}

	@Override
	public String toString() {
		return "QuestionType [tId=" + tId + ", tTitle=" + tTitle + ", tDesc=" + tDesc + "]";
	}
	
	
}