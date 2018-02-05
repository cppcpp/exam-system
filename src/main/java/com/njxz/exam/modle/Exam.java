package com.njxz.exam.modle;

import java.util.Date;

public class Exam {
    private Long eId;

    private Long subjectId;

    private Long userId;

    private Date eAddTime;

    private String eTitle;

    private Double eDifficultyLevel;
    
    public Exam() {}
    
    public Exam(Long eId, Long subjectId, Long userId, Date eAddTime, String eTitle, Double eDifficultyLevel) {
		this.eId = eId;
		this.subjectId = subjectId;
		this.userId = userId;
		this.eAddTime = eAddTime;
		this.eTitle = eTitle;
		this.eDifficultyLevel = eDifficultyLevel;
	}

	public Long geteId() {
        return eId;
    }

    public void seteId(Long eId) {
        this.eId = eId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date geteAddTime() {
        return eAddTime;
    }

    public void seteAddTime(Date eAddTime) {
        this.eAddTime = eAddTime;
    }

    public String geteTitle() {
        return eTitle;
    }

    public void seteTitle(String eTitle) {
        this.eTitle = eTitle == null ? null : eTitle.trim();
    }

    public Double geteDifficultyLevel() {
        return eDifficultyLevel;
    }

    public void seteDifficultyLevel(Double eDifficultyLevel) {
        this.eDifficultyLevel = eDifficultyLevel;
    }
}