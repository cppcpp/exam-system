package com.njxz.exam.modle;

import java.util.ArrayList;
import java.util.List;

public class ExamQuestiontypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamQuestiontypeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andETIdIsNull() {
            addCriterion("e_t_id is null");
            return (Criteria) this;
        }

        public Criteria andETIdIsNotNull() {
            addCriterion("e_t_id is not null");
            return (Criteria) this;
        }

        public Criteria andETIdEqualTo(Long value) {
            addCriterion("e_t_id =", value, "eTId");
            return (Criteria) this;
        }

        public Criteria andETIdNotEqualTo(Long value) {
            addCriterion("e_t_id <>", value, "eTId");
            return (Criteria) this;
        }

        public Criteria andETIdGreaterThan(Long value) {
            addCriterion("e_t_id >", value, "eTId");
            return (Criteria) this;
        }

        public Criteria andETIdGreaterThanOrEqualTo(Long value) {
            addCriterion("e_t_id >=", value, "eTId");
            return (Criteria) this;
        }

        public Criteria andETIdLessThan(Long value) {
            addCriterion("e_t_id <", value, "eTId");
            return (Criteria) this;
        }

        public Criteria andETIdLessThanOrEqualTo(Long value) {
            addCriterion("e_t_id <=", value, "eTId");
            return (Criteria) this;
        }

        public Criteria andETIdIn(List<Long> values) {
            addCriterion("e_t_id in", values, "eTId");
            return (Criteria) this;
        }

        public Criteria andETIdNotIn(List<Long> values) {
            addCriterion("e_t_id not in", values, "eTId");
            return (Criteria) this;
        }

        public Criteria andETIdBetween(Long value1, Long value2) {
            addCriterion("e_t_id between", value1, value2, "eTId");
            return (Criteria) this;
        }

        public Criteria andETIdNotBetween(Long value1, Long value2) {
            addCriterion("e_t_id not between", value1, value2, "eTId");
            return (Criteria) this;
        }

        public Criteria andExamIdIsNull() {
            addCriterion("exam_id is null");
            return (Criteria) this;
        }

        public Criteria andExamIdIsNotNull() {
            addCriterion("exam_id is not null");
            return (Criteria) this;
        }

        public Criteria andExamIdEqualTo(Long value) {
            addCriterion("exam_id =", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotEqualTo(Long value) {
            addCriterion("exam_id <>", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdGreaterThan(Long value) {
            addCriterion("exam_id >", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdGreaterThanOrEqualTo(Long value) {
            addCriterion("exam_id >=", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdLessThan(Long value) {
            addCriterion("exam_id <", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdLessThanOrEqualTo(Long value) {
            addCriterion("exam_id <=", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdIn(List<Long> values) {
            addCriterion("exam_id in", values, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotIn(List<Long> values) {
            addCriterion("exam_id not in", values, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdBetween(Long value1, Long value2) {
            addCriterion("exam_id between", value1, value2, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotBetween(Long value1, Long value2) {
            addCriterion("exam_id not between", value1, value2, "examId");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdIsNull() {
            addCriterion("question_type_id is null");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdIsNotNull() {
            addCriterion("question_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdEqualTo(Long value) {
            addCriterion("question_type_id =", value, "questionTypeId");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdNotEqualTo(Long value) {
            addCriterion("question_type_id <>", value, "questionTypeId");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdGreaterThan(Long value) {
            addCriterion("question_type_id >", value, "questionTypeId");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("question_type_id >=", value, "questionTypeId");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdLessThan(Long value) {
            addCriterion("question_type_id <", value, "questionTypeId");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("question_type_id <=", value, "questionTypeId");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdIn(List<Long> values) {
            addCriterion("question_type_id in", values, "questionTypeId");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdNotIn(List<Long> values) {
            addCriterion("question_type_id not in", values, "questionTypeId");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdBetween(Long value1, Long value2) {
            addCriterion("question_type_id between", value1, value2, "questionTypeId");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("question_type_id not between", value1, value2, "questionTypeId");
            return (Criteria) this;
        }

        public Criteria andQuestionNumIsNull() {
            addCriterion("question_num is null");
            return (Criteria) this;
        }

        public Criteria andQuestionNumIsNotNull() {
            addCriterion("question_num is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionNumEqualTo(Short value) {
            addCriterion("question_num =", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumNotEqualTo(Short value) {
            addCriterion("question_num <>", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumGreaterThan(Short value) {
            addCriterion("question_num >", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumGreaterThanOrEqualTo(Short value) {
            addCriterion("question_num >=", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumLessThan(Short value) {
            addCriterion("question_num <", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumLessThanOrEqualTo(Short value) {
            addCriterion("question_num <=", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumIn(List<Short> values) {
            addCriterion("question_num in", values, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumNotIn(List<Short> values) {
            addCriterion("question_num not in", values, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumBetween(Short value1, Short value2) {
            addCriterion("question_num between", value1, value2, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumNotBetween(Short value1, Short value2) {
            addCriterion("question_num not between", value1, value2, "questionNum");
            return (Criteria) this;
        }

        public Criteria andTypeScoreIsNull() {
            addCriterion("type_score is null");
            return (Criteria) this;
        }

        public Criteria andTypeScoreIsNotNull() {
            addCriterion("type_score is not null");
            return (Criteria) this;
        }

        public Criteria andTypeScoreEqualTo(Short value) {
            addCriterion("type_score =", value, "typeScore");
            return (Criteria) this;
        }

        public Criteria andTypeScoreNotEqualTo(Short value) {
            addCriterion("type_score <>", value, "typeScore");
            return (Criteria) this;
        }

        public Criteria andTypeScoreGreaterThan(Short value) {
            addCriterion("type_score >", value, "typeScore");
            return (Criteria) this;
        }

        public Criteria andTypeScoreGreaterThanOrEqualTo(Short value) {
            addCriterion("type_score >=", value, "typeScore");
            return (Criteria) this;
        }

        public Criteria andTypeScoreLessThan(Short value) {
            addCriterion("type_score <", value, "typeScore");
            return (Criteria) this;
        }

        public Criteria andTypeScoreLessThanOrEqualTo(Short value) {
            addCriterion("type_score <=", value, "typeScore");
            return (Criteria) this;
        }

        public Criteria andTypeScoreIn(List<Short> values) {
            addCriterion("type_score in", values, "typeScore");
            return (Criteria) this;
        }

        public Criteria andTypeScoreNotIn(List<Short> values) {
            addCriterion("type_score not in", values, "typeScore");
            return (Criteria) this;
        }

        public Criteria andTypeScoreBetween(Short value1, Short value2) {
            addCriterion("type_score between", value1, value2, "typeScore");
            return (Criteria) this;
        }

        public Criteria andTypeScoreNotBetween(Short value1, Short value2) {
            addCriterion("type_score not between", value1, value2, "typeScore");
            return (Criteria) this;
        }

        public Criteria andTypeSortIsNull() {
            addCriterion("type_sort is null");
            return (Criteria) this;
        }

        public Criteria andTypeSortIsNotNull() {
            addCriterion("type_sort is not null");
            return (Criteria) this;
        }

        public Criteria andTypeSortEqualTo(Byte value) {
            addCriterion("type_sort =", value, "typeSort");
            return (Criteria) this;
        }

        public Criteria andTypeSortNotEqualTo(Byte value) {
            addCriterion("type_sort <>", value, "typeSort");
            return (Criteria) this;
        }

        public Criteria andTypeSortGreaterThan(Byte value) {
            addCriterion("type_sort >", value, "typeSort");
            return (Criteria) this;
        }

        public Criteria andTypeSortGreaterThanOrEqualTo(Byte value) {
            addCriterion("type_sort >=", value, "typeSort");
            return (Criteria) this;
        }

        public Criteria andTypeSortLessThan(Byte value) {
            addCriterion("type_sort <", value, "typeSort");
            return (Criteria) this;
        }

        public Criteria andTypeSortLessThanOrEqualTo(Byte value) {
            addCriterion("type_sort <=", value, "typeSort");
            return (Criteria) this;
        }

        public Criteria andTypeSortIn(List<Byte> values) {
            addCriterion("type_sort in", values, "typeSort");
            return (Criteria) this;
        }

        public Criteria andTypeSortNotIn(List<Byte> values) {
            addCriterion("type_sort not in", values, "typeSort");
            return (Criteria) this;
        }

        public Criteria andTypeSortBetween(Byte value1, Byte value2) {
            addCriterion("type_sort between", value1, value2, "typeSort");
            return (Criteria) this;
        }

        public Criteria andTypeSortNotBetween(Byte value1, Byte value2) {
            addCriterion("type_sort not between", value1, value2, "typeSort");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}