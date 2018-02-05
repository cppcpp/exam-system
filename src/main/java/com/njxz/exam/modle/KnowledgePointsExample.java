package com.njxz.exam.modle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KnowledgePointsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public KnowledgePointsExample() {
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

        public Criteria andKIdIsNull() {
            addCriterion("k_id is null");
            return (Criteria) this;
        }

        public Criteria andKIdIsNotNull() {
            addCriterion("k_id is not null");
            return (Criteria) this;
        }

        public Criteria andKIdEqualTo(Long value) {
            addCriterion("k_id =", value, "kId");
            return (Criteria) this;
        }

        public Criteria andKIdNotEqualTo(Long value) {
            addCriterion("k_id <>", value, "kId");
            return (Criteria) this;
        }

        public Criteria andKIdGreaterThan(Long value) {
            addCriterion("k_id >", value, "kId");
            return (Criteria) this;
        }

        public Criteria andKIdGreaterThanOrEqualTo(Long value) {
            addCriterion("k_id >=", value, "kId");
            return (Criteria) this;
        }

        public Criteria andKIdLessThan(Long value) {
            addCriterion("k_id <", value, "kId");
            return (Criteria) this;
        }

        public Criteria andKIdLessThanOrEqualTo(Long value) {
            addCriterion("k_id <=", value, "kId");
            return (Criteria) this;
        }

        public Criteria andKIdIn(List<Long> values) {
            addCriterion("k_id in", values, "kId");
            return (Criteria) this;
        }

        public Criteria andKIdNotIn(List<Long> values) {
            addCriterion("k_id not in", values, "kId");
            return (Criteria) this;
        }

        public Criteria andKIdBetween(Long value1, Long value2) {
            addCriterion("k_id between", value1, value2, "kId");
            return (Criteria) this;
        }

        public Criteria andKIdNotBetween(Long value1, Long value2) {
            addCriterion("k_id not between", value1, value2, "kId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIsNull() {
            addCriterion("subject_id is null");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIsNotNull() {
            addCriterion("subject_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectIdEqualTo(Short value) {
            addCriterion("subject_id =", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotEqualTo(Short value) {
            addCriterion("subject_id <>", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdGreaterThan(Short value) {
            addCriterion("subject_id >", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdGreaterThanOrEqualTo(Short value) {
            addCriterion("subject_id >=", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdLessThan(Short value) {
            addCriterion("subject_id <", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdLessThanOrEqualTo(Short value) {
            addCriterion("subject_id <=", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIn(List<Short> values) {
            addCriterion("subject_id in", values, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotIn(List<Short> values) {
            addCriterion("subject_id not in", values, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdBetween(Short value1, Short value2) {
            addCriterion("subject_id between", value1, value2, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotBetween(Short value1, Short value2) {
            addCriterion("subject_id not between", value1, value2, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectTitleIsNull() {
            addCriterion("subject_title is null");
            return (Criteria) this;
        }

        public Criteria andSubjectTitleIsNotNull() {
            addCriterion("subject_title is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectTitleEqualTo(String value) {
            addCriterion("subject_title =", value, "subjectTitle");
            return (Criteria) this;
        }

        public Criteria andSubjectTitleNotEqualTo(String value) {
            addCriterion("subject_title <>", value, "subjectTitle");
            return (Criteria) this;
        }

        public Criteria andSubjectTitleGreaterThan(String value) {
            addCriterion("subject_title >", value, "subjectTitle");
            return (Criteria) this;
        }

        public Criteria andSubjectTitleGreaterThanOrEqualTo(String value) {
            addCriterion("subject_title >=", value, "subjectTitle");
            return (Criteria) this;
        }

        public Criteria andSubjectTitleLessThan(String value) {
            addCriterion("subject_title <", value, "subjectTitle");
            return (Criteria) this;
        }

        public Criteria andSubjectTitleLessThanOrEqualTo(String value) {
            addCriterion("subject_title <=", value, "subjectTitle");
            return (Criteria) this;
        }

        public Criteria andSubjectTitleLike(String value) {
            addCriterion("subject_title like", value, "subjectTitle");
            return (Criteria) this;
        }

        public Criteria andSubjectTitleNotLike(String value) {
            addCriterion("subject_title not like", value, "subjectTitle");
            return (Criteria) this;
        }

        public Criteria andSubjectTitleIn(List<String> values) {
            addCriterion("subject_title in", values, "subjectTitle");
            return (Criteria) this;
        }

        public Criteria andSubjectTitleNotIn(List<String> values) {
            addCriterion("subject_title not in", values, "subjectTitle");
            return (Criteria) this;
        }

        public Criteria andSubjectTitleBetween(String value1, String value2) {
            addCriterion("subject_title between", value1, value2, "subjectTitle");
            return (Criteria) this;
        }

        public Criteria andSubjectTitleNotBetween(String value1, String value2) {
            addCriterion("subject_title not between", value1, value2, "subjectTitle");
            return (Criteria) this;
        }

        public Criteria andKNumIsNull() {
            addCriterion("k_num is null");
            return (Criteria) this;
        }

        public Criteria andKNumIsNotNull() {
            addCriterion("k_num is not null");
            return (Criteria) this;
        }

        public Criteria andKNumEqualTo(Byte value) {
            addCriterion("k_num =", value, "kNum");
            return (Criteria) this;
        }

        public Criteria andKNumNotEqualTo(Byte value) {
            addCriterion("k_num <>", value, "kNum");
            return (Criteria) this;
        }

        public Criteria andKNumGreaterThan(Byte value) {
            addCriterion("k_num >", value, "kNum");
            return (Criteria) this;
        }

        public Criteria andKNumGreaterThanOrEqualTo(Byte value) {
            addCriterion("k_num >=", value, "kNum");
            return (Criteria) this;
        }

        public Criteria andKNumLessThan(Byte value) {
            addCriterion("k_num <", value, "kNum");
            return (Criteria) this;
        }

        public Criteria andKNumLessThanOrEqualTo(Byte value) {
            addCriterion("k_num <=", value, "kNum");
            return (Criteria) this;
        }

        public Criteria andKNumIn(List<Byte> values) {
            addCriterion("k_num in", values, "kNum");
            return (Criteria) this;
        }

        public Criteria andKNumNotIn(List<Byte> values) {
            addCriterion("k_num not in", values, "kNum");
            return (Criteria) this;
        }

        public Criteria andKNumBetween(Byte value1, Byte value2) {
            addCriterion("k_num between", value1, value2, "kNum");
            return (Criteria) this;
        }

        public Criteria andKNumNotBetween(Byte value1, Byte value2) {
            addCriterion("k_num not between", value1, value2, "kNum");
            return (Criteria) this;
        }

        public Criteria andKSubNumIsNull() {
            addCriterion("k_sub_num is null");
            return (Criteria) this;
        }

        public Criteria andKSubNumIsNotNull() {
            addCriterion("k_sub_num is not null");
            return (Criteria) this;
        }

        public Criteria andKSubNumEqualTo(Byte value) {
            addCriterion("k_sub_num =", value, "kSubNum");
            return (Criteria) this;
        }

        public Criteria andKSubNumNotEqualTo(Byte value) {
            addCriterion("k_sub_num <>", value, "kSubNum");
            return (Criteria) this;
        }

        public Criteria andKSubNumGreaterThan(Byte value) {
            addCriterion("k_sub_num >", value, "kSubNum");
            return (Criteria) this;
        }

        public Criteria andKSubNumGreaterThanOrEqualTo(Byte value) {
            addCriterion("k_sub_num >=", value, "kSubNum");
            return (Criteria) this;
        }

        public Criteria andKSubNumLessThan(Byte value) {
            addCriterion("k_sub_num <", value, "kSubNum");
            return (Criteria) this;
        }

        public Criteria andKSubNumLessThanOrEqualTo(Byte value) {
            addCriterion("k_sub_num <=", value, "kSubNum");
            return (Criteria) this;
        }

        public Criteria andKSubNumIn(List<Byte> values) {
            addCriterion("k_sub_num in", values, "kSubNum");
            return (Criteria) this;
        }

        public Criteria andKSubNumNotIn(List<Byte> values) {
            addCriterion("k_sub_num not in", values, "kSubNum");
            return (Criteria) this;
        }

        public Criteria andKSubNumBetween(Byte value1, Byte value2) {
            addCriterion("k_sub_num between", value1, value2, "kSubNum");
            return (Criteria) this;
        }

        public Criteria andKSubNumNotBetween(Byte value1, Byte value2) {
            addCriterion("k_sub_num not between", value1, value2, "kSubNum");
            return (Criteria) this;
        }

        public Criteria andKTitleIsNull() {
            addCriterion("k_title is null");
            return (Criteria) this;
        }

        public Criteria andKTitleIsNotNull() {
            addCriterion("k_title is not null");
            return (Criteria) this;
        }

        public Criteria andKTitleEqualTo(String value) {
            addCriterion("k_title =", value, "kTitle");
            return (Criteria) this;
        }

        public Criteria andKTitleNotEqualTo(String value) {
            addCriterion("k_title <>", value, "kTitle");
            return (Criteria) this;
        }

        public Criteria andKTitleGreaterThan(String value) {
            addCriterion("k_title >", value, "kTitle");
            return (Criteria) this;
        }

        public Criteria andKTitleGreaterThanOrEqualTo(String value) {
            addCriterion("k_title >=", value, "kTitle");
            return (Criteria) this;
        }

        public Criteria andKTitleLessThan(String value) {
            addCriterion("k_title <", value, "kTitle");
            return (Criteria) this;
        }

        public Criteria andKTitleLessThanOrEqualTo(String value) {
            addCriterion("k_title <=", value, "kTitle");
            return (Criteria) this;
        }

        public Criteria andKTitleLike(String value) {
            addCriterion("k_title like", value, "kTitle");
            return (Criteria) this;
        }

        public Criteria andKTitleNotLike(String value) {
            addCriterion("k_title not like", value, "kTitle");
            return (Criteria) this;
        }

        public Criteria andKTitleIn(List<String> values) {
            addCriterion("k_title in", values, "kTitle");
            return (Criteria) this;
        }

        public Criteria andKTitleNotIn(List<String> values) {
            addCriterion("k_title not in", values, "kTitle");
            return (Criteria) this;
        }

        public Criteria andKTitleBetween(String value1, String value2) {
            addCriterion("k_title between", value1, value2, "kTitle");
            return (Criteria) this;
        }

        public Criteria andKTitleNotBetween(String value1, String value2) {
            addCriterion("k_title not between", value1, value2, "kTitle");
            return (Criteria) this;
        }

        public Criteria andKAddtimeIsNull() {
            addCriterion("k_addtime is null");
            return (Criteria) this;
        }

        public Criteria andKAddtimeIsNotNull() {
            addCriterion("k_addtime is not null");
            return (Criteria) this;
        }

        public Criteria andKAddtimeEqualTo(Date value) {
            addCriterion("k_addtime =", value, "kAddtime");
            return (Criteria) this;
        }

        public Criteria andKAddtimeNotEqualTo(Date value) {
            addCriterion("k_addtime <>", value, "kAddtime");
            return (Criteria) this;
        }

        public Criteria andKAddtimeGreaterThan(Date value) {
            addCriterion("k_addtime >", value, "kAddtime");
            return (Criteria) this;
        }

        public Criteria andKAddtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("k_addtime >=", value, "kAddtime");
            return (Criteria) this;
        }

        public Criteria andKAddtimeLessThan(Date value) {
            addCriterion("k_addtime <", value, "kAddtime");
            return (Criteria) this;
        }

        public Criteria andKAddtimeLessThanOrEqualTo(Date value) {
            addCriterion("k_addtime <=", value, "kAddtime");
            return (Criteria) this;
        }

        public Criteria andKAddtimeIn(List<Date> values) {
            addCriterion("k_addtime in", values, "kAddtime");
            return (Criteria) this;
        }

        public Criteria andKAddtimeNotIn(List<Date> values) {
            addCriterion("k_addtime not in", values, "kAddtime");
            return (Criteria) this;
        }

        public Criteria andKAddtimeBetween(Date value1, Date value2) {
            addCriterion("k_addtime between", value1, value2, "kAddtime");
            return (Criteria) this;
        }

        public Criteria andKAddtimeNotBetween(Date value1, Date value2) {
            addCriterion("k_addtime not between", value1, value2, "kAddtime");
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