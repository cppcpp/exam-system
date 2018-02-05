package com.njxz.exam.modle;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.njxz.exam.validation.ValidNumSubNum;


@ValidNumSubNum()
public class KnowledgePoints {
    private Long kId;

    @NotNull(message="{knowledgePoints.subjectId.nullOrEmpty}")
    private Short subjectId;

    private String subjectTitle;

    @NotNull(message="{knowledgePoints.kNum.nullOrEmpty}")
    private Byte kNum;

    @NotNull(message="{knowledgePoints.kSubNum.nullOrEmpty}")
    private Byte kSubNum;

    @NotEmpty(message="{knowledgePoints.kTitle.nullOrEmpty}")
    @NotNull(message="{knowledgePoints.kTitle.nullOrEmpty}")
    private String kTitle;

    private Date kAddtime;

    public Long getkId() {
        return kId;
    }

    public void setkId(Long kId) {
        this.kId = kId;
    }

    public Short getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Short subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle == null ? null : subjectTitle.trim();
    }

    public Byte getkNum() {
        return kNum;
    }

    public void setkNum(Byte kNum) {
        this.kNum = kNum;
    }

    public Byte getkSubNum() {
        return kSubNum;
    }

    public void setkSubNum(Byte kSubNum) {
        this.kSubNum = kSubNum;
    }

    public String getkTitle() {
        return kTitle;
    }

    public void setkTitle(String kTitle) {
        this.kTitle = kTitle == null ? null : kTitle.trim();
    }

    public Date getkAddtime() {
        return kAddtime;
    }

    public void setkAddtime(Date kAddtime) {
        this.kAddtime = kAddtime;
    }

	@Override
	public String toString() {
		return "KnowledgePoints [kId=" + kId + ", subjectId=" + subjectId + ", subjectTitle=" + subjectTitle + ", kNum="
				+ kNum + ", kSubNum=" + kSubNum + ", kTitle=" + kTitle + ", kAddtime=" + kAddtime + "]";
	}
    
    
}