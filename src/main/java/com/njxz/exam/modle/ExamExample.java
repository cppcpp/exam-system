package com.njxz.exam.modle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamExample() {
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

        public Criteria andEIdIsNull() {
            addCriterion("e_id is null");
            return (Criteria) this;
        }

        public Criteria andEIdIsNotNull() {
            addCriterion("e_id is not null");
            return (Criteria) this;
        }

        public Criteria andEIdEqualTo(Long value) {
            addCriterion("e_id =", value, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdNotEqualTo(Long value) {
            addCriterion("e_id <>", value, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdGreaterThan(Long value) {
            addCriterion("e_id >", value, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdGreaterThanOrEqualTo(Long value) {
            addCriterion("e_id >=", value, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdLessThan(Long value) {
            addCriterion("e_id <", value, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdLessThanOrEqualTo(Long value) {
            addCriterion("e_id <=", value, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdIn(List<Long> values) {
            addCriterion("e_id in", values, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdNotIn(List<Long> values) {
            addCriterion("e_id not in", values, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdBetween(Long value1, Long value2) {
            addCriterion("e_id between", value1, value2, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdNotBetween(Long value1, Long value2) {
            addCriterion("e_id not between", value1, value2, "eId");
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

        public Criteria andSubjectIdEqualTo(Long value) {
            addCriterion("subject_id =", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotEqualTo(Long value) {
            addCriterion("subject_id <>", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdGreaterThan(Long value) {
            addCriterion("subject_id >", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdGreaterThanOrEqualTo(Long value) {
            addCriterion("subject_id >=", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdLessThan(Long value) {
            addCriterion("subject_id <", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdLessThanOrEqualTo(Long value) {
            addCriterion("subject_id <=", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIn(List<Long> values) {
            addCriterion("subject_id in", values, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotIn(List<Long> values) {
            addCriterion("subject_id not in", values, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdBetween(Long value1, Long value2) {
            addCriterion("subject_id between", value1, value2, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotBetween(Long value1, Long value2) {
            addCriterion("subject_id not between", value1, value2, "subjectId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andEAddTimeIsNull() {
            addCriterion("e_add_time is null");
            return (Criteria) this;
        }

        public Criteria andEAddTimeIsNotNull() {
            addCriterion("e_add_time is not null");
            return (Criteria) this;
        }

        public Criteria andEAddTimeEqualTo(Date value) {
            addCriterion("e_add_time =", value, "eAddTime");
            return (Criteria) this;
        }

        public Criteria andEAddTimeNotEqualTo(Date value) {
            addCriterion("e_add_time <>", value, "eAddTime");
            return (Criteria) this;
        }

        public Criteria andEAddTimeGreaterThan(Date value) {
            addCriterion("e_add_time >", value, "eAddTime");
            return (Criteria) this;
        }

        public Criteria andEAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("e_add_time >=", value, "eAddTime");
            return (Criteria) this;
        }

        public Criteria andEAddTimeLessThan(Date value) {
            addCriterion("e_add_time <", value, "eAddTime");
            return (Criteria) this;
        }

        public Criteria andEAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("e_add_time <=", value, "eAddTime");
            return (Criteria) this;
        }

        public Criteria andEAddTimeIn(List<Date> values) {
            addCriterion("e_add_time in", values, "eAddTime");
            return (Criteria) this;
        }

        public Criteria andEAddTimeNotIn(List<Date> values) {
            addCriterion("e_add_time not in", values, "eAddTime");
            return (Criteria) this;
        }

        public Criteria andEAddTimeBetween(Date value1, Date value2) {
            addCriterion("e_add_time between", value1, value2, "eAddTime");
            return (Criteria) this;
        }

        public Criteria andEAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("e_add_time not between", value1, value2, "eAddTime");
            return (Criteria) this;
        }

        public Criteria andETitleIsNull() {
            addCriterion("e_title is null");
            return (Criteria) this;
        }

        public Criteria andETitleIsNotNull() {
            addCriterion("e_title is not null");
            return (Criteria) this;
        }

        public Criteria andETitleEqualTo(String value) {
            addCriterion("e_title =", value, "eTitle");
            return (Criteria) this;
        }

        public Criteria andETitleNotEqualTo(String value) {
            addCriterion("e_title <>", value, "eTitle");
            return (Criteria) this;
        }

        public Criteria andETitleGreaterThan(String value) {
            addCriterion("e_title >", value, "eTitle");
            return (Criteria) this;
        }

        public Criteria andETitleGreaterThanOrEqualTo(String value) {
            addCriterion("e_title >=", value, "eTitle");
            return (Criteria) this;
        }

        public Criteria andETitleLessThan(String value) {
            addCriterion("e_title <", value, "eTitle");
            return (Criteria) this;
        }

        public Criteria andETitleLessThanOrEqualTo(String value) {
            addCriterion("e_title <=", value, "eTitle");
            return (Criteria) this;
        }

        public Criteria andETitleLike(String value) {
            addCriterion("e_title like", value, "eTitle");
            return (Criteria) this;
        }

        public Criteria andETitleNotLike(String value) {
            addCriterion("e_title not like", value, "eTitle");
            return (Criteria) this;
        }

        public Criteria andETitleIn(List<String> values) {
            addCriterion("e_title in", values, "eTitle");
            return (Criteria) this;
        }

        public Criteria andETitleNotIn(List<String> values) {
            addCriterion("e_title not in", values, "eTitle");
            return (Criteria) this;
        }

        public Criteria andETitleBetween(String value1, String value2) {
            addCriterion("e_title between", value1, value2, "eTitle");
            return (Criteria) this;
        }

        public Criteria andETitleNotBetween(String value1, String value2) {
            addCriterion("e_title not between", value1, value2, "eTitle");
            return (Criteria) this;
        }

        public Criteria andEDifficultyLevelIsNull() {
            addCriterion("e_difficulty_level is null");
            return (Criteria) this;
        }

        public Criteria andEDifficultyLevelIsNotNull() {
            addCriterion("e_difficulty_level is not null");
            return (Criteria) this;
        }

        public Criteria andEDifficultyLevelEqualTo(Double value) {
            addCriterion("e_difficulty_level =", value, "eDifficultyLevel");
            return (Criteria) this;
        }

        public Criteria andEDifficultyLevelNotEqualTo(Double value) {
            addCriterion("e_difficulty_level <>", value, "eDifficultyLevel");
            return (Criteria) this;
        }

        public Criteria andEDifficultyLevelGreaterThan(Double value) {
            addCriterion("e_difficulty_level >", value, "eDifficultyLevel");
            return (Criteria) this;
        }

        public Criteria andEDifficultyLevelGreaterThanOrEqualTo(Double value) {
            addCriterion("e_difficulty_level >=", value, "eDifficultyLevel");
            return (Criteria) this;
        }

        public Criteria andEDifficultyLevelLessThan(Double value) {
            addCriterion("e_difficulty_level <", value, "eDifficultyLevel");
            return (Criteria) this;
        }

        public Criteria andEDifficultyLevelLessThanOrEqualTo(Double value) {
            addCriterion("e_difficulty_level <=", value, "eDifficultyLevel");
            return (Criteria) this;
        }

        public Criteria andEDifficultyLevelIn(List<Double> values) {
            addCriterion("e_difficulty_level in", values, "eDifficultyLevel");
            return (Criteria) this;
        }

        public Criteria andEDifficultyLevelNotIn(List<Double> values) {
            addCriterion("e_difficulty_level not in", values, "eDifficultyLevel");
            return (Criteria) this;
        }

        public Criteria andEDifficultyLevelBetween(Double value1, Double value2) {
            addCriterion("e_difficulty_level between", value1, value2, "eDifficultyLevel");
            return (Criteria) this;
        }

        public Criteria andEDifficultyLevelNotBetween(Double value1, Double value2) {
            addCriterion("e_difficulty_level not between", value1, value2, "eDifficultyLevel");
            return (Criteria) this;
        }

        public Criteria andEAddressAIsNull() {
            addCriterion("e_address_a is null");
            return (Criteria) this;
        }

        public Criteria andEAddressAIsNotNull() {
            addCriterion("e_address_a is not null");
            return (Criteria) this;
        }

        public Criteria andEAddressAEqualTo(String value) {
            addCriterion("e_address_a =", value, "eAddressA");
            return (Criteria) this;
        }

        public Criteria andEAddressANotEqualTo(String value) {
            addCriterion("e_address_a <>", value, "eAddressA");
            return (Criteria) this;
        }

        public Criteria andEAddressAGreaterThan(String value) {
            addCriterion("e_address_a >", value, "eAddressA");
            return (Criteria) this;
        }

        public Criteria andEAddressAGreaterThanOrEqualTo(String value) {
            addCriterion("e_address_a >=", value, "eAddressA");
            return (Criteria) this;
        }

        public Criteria andEAddressALessThan(String value) {
            addCriterion("e_address_a <", value, "eAddressA");
            return (Criteria) this;
        }

        public Criteria andEAddressALessThanOrEqualTo(String value) {
            addCriterion("e_address_a <=", value, "eAddressA");
            return (Criteria) this;
        }

        public Criteria andEAddressALike(String value) {
            addCriterion("e_address_a like", value, "eAddressA");
            return (Criteria) this;
        }

        public Criteria andEAddressANotLike(String value) {
            addCriterion("e_address_a not like", value, "eAddressA");
            return (Criteria) this;
        }

        public Criteria andEAddressAIn(List<String> values) {
            addCriterion("e_address_a in", values, "eAddressA");
            return (Criteria) this;
        }

        public Criteria andEAddressANotIn(List<String> values) {
            addCriterion("e_address_a not in", values, "eAddressA");
            return (Criteria) this;
        }

        public Criteria andEAddressABetween(String value1, String value2) {
            addCriterion("e_address_a between", value1, value2, "eAddressA");
            return (Criteria) this;
        }

        public Criteria andEAddressANotBetween(String value1, String value2) {
            addCriterion("e_address_a not between", value1, value2, "eAddressA");
            return (Criteria) this;
        }

        public Criteria andEAddressBIsNull() {
            addCriterion("e_address_b is null");
            return (Criteria) this;
        }

        public Criteria andEAddressBIsNotNull() {
            addCriterion("e_address_b is not null");
            return (Criteria) this;
        }

        public Criteria andEAddressBEqualTo(String value) {
            addCriterion("e_address_b =", value, "eAddressB");
            return (Criteria) this;
        }

        public Criteria andEAddressBNotEqualTo(String value) {
            addCriterion("e_address_b <>", value, "eAddressB");
            return (Criteria) this;
        }

        public Criteria andEAddressBGreaterThan(String value) {
            addCriterion("e_address_b >", value, "eAddressB");
            return (Criteria) this;
        }

        public Criteria andEAddressBGreaterThanOrEqualTo(String value) {
            addCriterion("e_address_b >=", value, "eAddressB");
            return (Criteria) this;
        }

        public Criteria andEAddressBLessThan(String value) {
            addCriterion("e_address_b <", value, "eAddressB");
            return (Criteria) this;
        }

        public Criteria andEAddressBLessThanOrEqualTo(String value) {
            addCriterion("e_address_b <=", value, "eAddressB");
            return (Criteria) this;
        }

        public Criteria andEAddressBLike(String value) {
            addCriterion("e_address_b like", value, "eAddressB");
            return (Criteria) this;
        }

        public Criteria andEAddressBNotLike(String value) {
            addCriterion("e_address_b not like", value, "eAddressB");
            return (Criteria) this;
        }

        public Criteria andEAddressBIn(List<String> values) {
            addCriterion("e_address_b in", values, "eAddressB");
            return (Criteria) this;
        }

        public Criteria andEAddressBNotIn(List<String> values) {
            addCriterion("e_address_b not in", values, "eAddressB");
            return (Criteria) this;
        }

        public Criteria andEAddressBBetween(String value1, String value2) {
            addCriterion("e_address_b between", value1, value2, "eAddressB");
            return (Criteria) this;
        }

        public Criteria andEAddressBNotBetween(String value1, String value2) {
            addCriterion("e_address_b not between", value1, value2, "eAddressB");
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