package com.njxz.exam.modle;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class QuestionCorrectRatio {
	private Long qResponseId;

	private Integer questionId;

	private Date qResponseTime;

	private Integer qDidNum;

	private Integer qCorrectNum;

	public Long getqResponseId() {
		return qResponseId;
	}

	public void setqResponseId(Long qResponseId) {
		this.qResponseId = qResponseId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Date getqResponseTime() {
		return qResponseTime;
	}

	public void setqResponseTime(Date qResponseTime) {
		this.qResponseTime = qResponseTime;
	}

	public Integer getqDidNum() {
		return qDidNum;
	}

	public void setqDidNum(Integer qDidNum) {
		this.qDidNum = qDidNum;
	}

	public Integer getqCorrectNum() {
		return qCorrectNum;
	}

	public void setqCorrectNum(Integer qCorrectNum) {
		this.qCorrectNum = qCorrectNum;
	}
}