package com.njxz.exam.modle;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Questions {
    private Long qId;

    @NotNull(message="{question.questionTypeId.notNull}")
    private Long questionTypeId;

    @NotNull(message="{question.knowledgePointId.notNull}")
    private Long konwledgePointId;

    @NotNull(message="{question.qTitle.notNullOrEmpty}")
    @NotEmpty(message="{question.qTitle.notNullOrEmpty}")
    private String qTitle;

    @NotNull(message="{question.qAnswer.notNullOrEmpty}")
    @NotEmpty(message="{question.qAnswer.notNullOrEmpty}")
    private String qAnswer;

    private Date qAddTime;

    private Long qUserId;

    @NotNull(message="{question.qDifficultyLevel.notNullOrEmpty}")
    private Double qDifficultyLevel;

    @NotNull(message="{question.sId.notNullOrEmpty}")
    private Long sId;

    private Integer qGetTimes;

    private Integer qDidNum;

    private Integer qCorrectNum;

    public Long getqId() {
        return qId;
    }

    public void setqId(Long qId) {
        this.qId = qId;
    }

    public Long getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Long questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public Long getKonwledgePointId() {
        return konwledgePointId;
    }

    public void setKonwledgePointId(Long konwledgePointId) {
        this.konwledgePointId = konwledgePointId;
    }

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

    public Date getqAddTime() {
        return qAddTime;
    }

    public void setqAddTime(Date qAddTime) {
        this.qAddTime = qAddTime;
    }

    public Long getqUserId() {
        return qUserId;
    }

    public void setqUserId(Long qUserId) {
        this.qUserId = qUserId;
    }

    public Double getqDifficultyLevel() {
        return qDifficultyLevel;
    }

    public void setqDifficultyLevel(Double qDifficultyLevel) {
        this.qDifficultyLevel = qDifficultyLevel;
    }

    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public Integer getqGetTimes() {
        return qGetTimes;
    }

    public void setqGetTimes(Integer qGetTimes) {
        this.qGetTimes = qGetTimes;
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