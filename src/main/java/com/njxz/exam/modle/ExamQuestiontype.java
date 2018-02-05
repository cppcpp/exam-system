package com.njxz.exam.modle;

public class ExamQuestiontype {
    private Long eTId;

    private Long examId;

    private Long questionTypeId;

    private Short questionNum;

    private Short typeScore;

    private Byte typeSort;
    
    public ExamQuestiontype() {
	}

	public ExamQuestiontype( Long examId, Long questionTypeId, Short questionNum, Short typeScore,
			Byte typeSort) {
		super();
		this.examId = examId;
		this.questionTypeId = questionTypeId;
		this.questionNum = questionNum;
		this.typeScore = typeScore;
		this.typeSort = typeSort;
	}

	public Long geteTId() {
        return eTId;
    }

    public void seteTId(Long eTId) {
        this.eTId = eTId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Long questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public Short getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Short questionNum) {
        this.questionNum = questionNum;
    }

    public Short getTypeScore() {
        return typeScore;
    }

    public void setTypeScore(Short typeScore) {
        this.typeScore = typeScore;
    }

    public Byte getTypeSort() {
        return typeSort;
    }

    public void setTypeSort(Byte typeSort) {
        this.typeSort = typeSort;
    }
}