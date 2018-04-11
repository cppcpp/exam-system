package com.njxz.exam.modle;

import java.util.Date;

public class Exam {
    private Long eId;

    private Long subjectId;

    private Long userId;

    private Date eAddTime;

    private String eTitle;

    private Double eDifficultyLevelA;

    private Double eDifficultyLevelB;

    private String eAddressA;

    private String eAddressB;
    
    public Exam() {
    	
    }
    public Exam(Long eId, Long subjectId, Long userId, Date eAddTime, String eTitle, Double eDifficultyLevelA,
			Double eDifficultyLevelB) {
		super();
		this.eId = eId;
		this.subjectId = subjectId;
		this.userId = userId;
		this.eAddTime = eAddTime;
		this.eTitle = eTitle;
		this.eDifficultyLevelA = eDifficultyLevelA;
		this.eDifficultyLevelB = eDifficultyLevelB;
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

    public Double geteDifficultyLevelA() {
        return eDifficultyLevelA;
    }

    public void seteDifficultyLevelA(Double eDifficultyLevelA) {
        this.eDifficultyLevelA = eDifficultyLevelA;
    }

    public Double geteDifficultyLevelB() {
        return eDifficultyLevelB;
    }

    public void seteDifficultyLevelB(Double eDifficultyLevelB) {
        this.eDifficultyLevelB = eDifficultyLevelB;
    }

    public String geteAddressA() {
        return eAddressA;
    }

    public void seteAddressA(String eAddressA) {
        this.eAddressA = eAddressA == null ? null : eAddressA.trim();
    }

    public String geteAddressB() {
        return eAddressB;
    }

    public void seteAddressB(String eAddressB) {
        this.eAddressB = eAddressB == null ? null : eAddressB.trim();
    }
}