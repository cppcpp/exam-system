package com.njxz.exam.modle;

import org.springframework.stereotype.Component;

@Component
public class QuestionsWithBLOBs extends Questions {
	private String qTitle;

	private String qAnswer;

	public String getqTitle() {
		return qTitle;
	}

	public void setqTitle(String qTitle) {
		this.qTitle = qTitle == null ? null : qTitle.trim();
	}

	public String getqAnswer() {
		return qAnswer;
	}

	public void setqAnswer(String qAnswer) {
		this.qAnswer = qAnswer == null ? null : qAnswer.trim();
	}
}