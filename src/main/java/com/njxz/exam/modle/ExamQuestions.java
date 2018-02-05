package com.njxz.exam.modle;

public class ExamQuestions {
    private Long eQId;

    private Long examId;

    private Byte examType;

    private Long questionId;
    
    public ExamQuestions(Long examId, Byte examType, Long questionId) {
		super();
		this.examId = examId;
		this.examType = examType;
		this.questionId = questionId;
	}

	public Long geteQId() {
        return eQId;
    }

    public void seteQId(Long eQId) {
        this.eQId = eQId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Byte getExamType() {
        return examType;
    }

    public void setExamType(Byte examType) {
        this.examType = examType;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}