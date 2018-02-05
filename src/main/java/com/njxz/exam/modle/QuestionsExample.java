package com.njxz.exam.modle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public QuestionsExample() {
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

        public Criteria andQIdIsNull() {
            addCriterion("q_id is null");
            return (Criteria) this;
        }

        public Criteria andQIdIsNotNull() {
            addCriterion("q_id is not null");
            return (Criteria) this;
        }

        public Criteria andQIdEqualTo(Long value) {
            addCriterion("q_id =", value, "qId");
            return (Criteria) this;
        }

        public Criteria andQIdNotEqualTo(Long value) {
            addCriterion("q_id <>", value, "qId");
            return (Criteria) this;
        }

        public Criteria andQIdGreaterThan(Long value) {
            addCriterion("q_id >", value, "qId");
            return (Criteria) this;
        }

        public Criteria andQIdGreaterThanOrEqualTo(Long value) {
            addCriterion("q_id >=", value, "qId");
            return (Criteria) this;
        }

        public Criteria andQIdLessThan(Long value) {
            addCriterion("q_id <", value, "qId");
            return (Criteria) this;
        }

        public Criteria andQIdLessThanOrEqualTo(Long value) {
            addCriterion("q_id <=", value, "qId");
            return (Criteria) this;
        }

        public Criteria andQIdIn(List<Long> values) {
            addCriterion("q_id in", values, "qId");
            return (Criteria) this;
        }

        public Criteria andQIdNotIn(List<Long> values) {
            addCriterion("q_id not in", values, "qId");
            return (Criteria) this;
        }

        public Criteria andQIdBetween(Long value1, Long value2) {
            addCriterion("q_id between", value1, value2, "qId");
            return (Criteria) this;
        }

        public Criteria andQIdNotBetween(Long value1, Long value2) {
            addCriterion("q_id not between", value1, value2, "qId");
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

        public Criteria andKonwledgePointIdIsNull() {
            addCriterion("konwledge_point_id is null");
            return (Criteria) this;
        }

        public Criteria andKonwledgePointIdIsNotNull() {
            addCriterion("konwledge_point_id is not null");
            return (Criteria) this;
        }

        public Criteria andKonwledgePointIdEqualTo(Long value) {
            addCriterion("konwledge_point_id =", value, "konwledgePointId");
            return (Criteria) this;
        }

        public Criteria andKonwledgePointIdNotEqualTo(Long value) {
            addCriterion("konwledge_point_id <>", value, "konwledgePointId");
            return (Criteria) this;
        }

        public Criteria andKonwledgePointIdGreaterThan(Long value) {
            addCriterion("konwledge_point_id >", value, "konwledgePointId");
            return (Criteria) this;
        }

        public Criteria andKonwledgePointIdGreaterThanOrEqualTo(Long value) {
            addCriterion("konwledge_point_id >=", value, "konwledgePointId");
            return (Criteria) this;
        }

        public Criteria andKonwledgePointIdLessThan(Long value) {
            addCriterion("konwledge_point_id <", value, "konwledgePointId");
            return (Criteria) this;
        }

        public Criteria andKonwledgePointIdLessThanOrEqualTo(Long value) {
            addCriterion("konwledge_point_id <=", value, "konwledgePointId");
            return (Criteria) this;
        }

        public Criteria andKonwledgePointIdIn(List<Long> values) {
            addCriterion("konwledge_point_id in", values, "konwledgePointId");
            return (Criteria) this;
        }

        public Criteria andKonwledgePointIdNotIn(List<Long> values) {
            addCriterion("konwledge_point_id not in", values, "konwledgePointId");
            return (Criteria) this;
        }

        public Criteria andKonwledgePointIdBetween(Long value1, Long value2) {
            addCriterion("konwledge_point_id between", value1, value2, "konwledgePointId");
            return (Criteria) this;
        }

        public Criteria andKonwledgePointIdNotBetween(Long value1, Long value2) {
            addCriterion("konwledge_point_id not between", value1, value2, "konwledgePointId");
            return (Criteria) this;
        }

        public Criteria andQTitleIsNull() {
            addCriterion("q_title is null");
            return (Criteria) this;
        }

        public Criteria andQTitleIsNotNull() {
            addCriterion("q_title is not null");
            return (Criteria) this;
        }

        public Criteria andQTitleEqualTo(String value) {
            addCriterion("q_title =", value, "qTitle");
            return (Criteria) this;
        }

        public Criteria andQTitleNotEqualTo(String value) {
            addCriterion("q_title <>", value, "qTitle");
            return (Criteria) this;
        }

        public Criteria andQTitleGreaterThan(String value) {
            addCriterion("q_title >", value, "qTitle");
            return (Criteria) this;
        }

        public Criteria andQTitleGreaterThanOrEqualTo(String value) {
            addCriterion("q_title >=", value, "qTitle");
            return (Criteria) this;
        }

        public Criteria andQTitleLessThan(String value) {
            addCriterion("q_title <", value, "qTitle");
            return (Criteria) this;
        }

        public Criteria andQTitleLessThanOrEqualTo(String value) {
            addCriterion("q_title <=", value, "qTitle");
            return (Criteria) this;
        }

        public Criteria andQTitleLike(String value) {
            addCriterion("q_title like", value, "qTitle");
            return (Criteria) this;
        }

        public Criteria andQTitleNotLike(String value) {
            addCriterion("q_title not like", value, "qTitle");
            return (Criteria) this;
        }

        public Criteria andQTitleIn(List<String> values) {
            addCriterion("q_title in", values, "qTitle");
            return (Criteria) this;
        }

        public Criteria andQTitleNotIn(List<String> values) {
            addCriterion("q_title not in", values, "qTitle");
            return (Criteria) this;
        }

        public Criteria andQTitleBetween(String value1, String value2) {
            addCriterion("q_title between", value1, value2, "qTitle");
            return (Criteria) this;
        }

        public Criteria andQTitleNotBetween(String value1, String value2) {
            addCriterion("q_title not between", value1, value2, "qTitle");
            return (Criteria) this;
        }

        public Criteria andQAnswerIsNull() {
            addCriterion("q_answer is null");
            return (Criteria) this;
        }

        public Criteria andQAnswerIsNotNull() {
            addCriterion("q_answer is not null");
            return (Criteria) this;
        }

        public Criteria andQAnswerEqualTo(String value) {
            addCriterion("q_answer =", value, "qAnswer");
            return (Criteria) this;
        }

        public Criteria andQAnswerNotEqualTo(String value) {
            addCriterion("q_answer <>", value, "qAnswer");
            return (Criteria) this;
        }

        public Criteria andQAnswerGreaterThan(String value) {
            addCriterion("q_answer >", value, "qAnswer");
            return (Criteria) this;
        }

        public Criteria andQAnswerGreaterThanOrEqualTo(String value) {
            addCriterion("q_answer >=", value, "qAnswer");
            return (Criteria) this;
        }

        public Criteria andQAnswerLessThan(String value) {
            addCriterion("q_answer <", value, "qAnswer");
            return (Criteria) this;
        }

        public Criteria andQAnswerLessThanOrEqualTo(String value) {
            addCriterion("q_answer <=", value, "qAnswer");
            return (Criteria) this;
        }

        public Criteria andQAnswerLike(String value) {
            addCriterion("q_answer like", value, "qAnswer");
            return (Criteria) this;
        }

        public Criteria andQAnswerNotLike(String value) {
            addCriterion("q_answer not like", value, "qAnswer");
            return (Criteria) this;
        }

        public Criteria andQAnswerIn(List<String> values) {
            addCriterion("q_answer in", values, "qAnswer");
            return (Criteria) this;
        }

        public Criteria andQAnswerNotIn(List<String> values) {
            addCriterion("q_answer not in", values, "qAnswer");
            return (Criteria) this;
        }

        public Criteria andQAnswerBetween(String value1, String value2) {
            addCriterion("q_answer between", value1, value2, "qAnswer");
            return (Criteria) this;
        }

        public Criteria andQAnswerNotBetween(String value1, String value2) {
            addCriterion("q_answer not between", value1, value2, "qAnswer");
            return (Criteria) this;
        }

        public Criteria andQAddTimeIsNull() {
            addCriterion("q_add_time is null");
            return (Criteria) this;
        }

        public Criteria andQAddTimeIsNotNull() {
            addCriterion("q_add_time is not null");
            return (Criteria) this;
        }

        public Criteria andQAddTimeEqualTo(Date value) {
            addCriterion("q_add_time =", value, "qAddTime");
            return (Criteria) this;
        }

        public Criteria andQAddTimeNotEqualTo(Date value) {
            addCriterion("q_add_time <>", value, "qAddTime");
            return (Criteria) this;
        }

        public Criteria andQAddTimeGreaterThan(Date value) {
            addCriterion("q_add_time >", value, "qAddTime");
            return (Criteria) this;
        }

        public Criteria andQAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("q_add_time >=", value, "qAddTime");
            return (Criteria) this;
        }

        public Criteria andQAddTimeLessThan(Date value) {
            addCriterion("q_add_time <", value, "qAddTime");
            return (Criteria) this;
        }

        public Criteria andQAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("q_add_time <=", value, "qAddTime");
            return (Criteria) this;
        }

        public Criteria andQAddTimeIn(List<Date> values) {
            addCriterion("q_add_time in", values, "qAddTime");
            return (Criteria) this;
        }

        public Criteria andQAddTimeNotIn(List<Date> values) {
            addCriterion("q_add_time not in", values, "qAddTime");
            return (Criteria) this;
        }

        public Criteria andQAddTimeBetween(Date value1, Date value2) {
            addCriterion("q_add_time between", value1, value2, "qAddTime");
            return (Criteria) this;
        }

        public Criteria andQAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("q_add_time not between", value1, value2, "qAddTime");
            return (Criteria) this;
        }

        public Criteria andQUserIdIsNull() {
            addCriterion("q_user_id is null");
            return (Criteria) this;
        }

        public Criteria andQUserIdIsNotNull() {
            addCriterion("q_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andQUserIdEqualTo(Long value) {
            addCriterion("q_user_id =", value, "qUserId");
            return (Criteria) this;
        }

        public Criteria andQUserIdNotEqualTo(Long value) {
            addCriterion("q_user_id <>", value, "qUserId");
            return (Criteria) this;
        }

        public Criteria andQUserIdGreaterThan(Long value) {
            addCriterion("q_user_id >", value, "qUserId");
            return (Criteria) this;
        }

        public Criteria andQUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("q_user_id >=", value, "qUserId");
            return (Criteria) this;
        }

        public Criteria andQUserIdLessThan(Long value) {
            addCriterion("q_user_id <", value, "qUserId");
            return (Criteria) this;
        }

        public Criteria andQUserIdLessThanOrEqualTo(Long value) {
            addCriterion("q_user_id <=", value, "qUserId");
            return (Criteria) this;
        }

        public Criteria andQUserIdIn(List<Long> values) {
            addCriterion("q_user_id in", values, "qUserId");
            return (Criteria) this;
        }

        public Criteria andQUserIdNotIn(List<Long> values) {
            addCriterion("q_user_id not in", values, "qUserId");
            return (Criteria) this;
        }

        public Criteria andQUserIdBetween(Long value1, Long value2) {
            addCriterion("q_user_id between", value1, value2, "qUserId");
            return (Criteria) this;
        }

        public Criteria andQUserIdNotBetween(Long value1, Long value2) {
            addCriterion("q_user_id not between", value1, value2, "qUserId");
            return (Criteria) this;
        }

        public Criteria andQDifficultyLevelIsNull() {
            addCriterion("q_difficulty_level is null");
            return (Criteria) this;
        }

        public Criteria andQDifficultyLevelIsNotNull() {
            addCriterion("q_difficulty_level is not null");
            return (Criteria) this;
        }

        public Criteria andQDifficultyLevelEqualTo(Double value) {
            addCriterion("q_difficulty_level =", value, "qDifficultyLevel");
            return (Criteria) this;
        }

        public Criteria andQDifficultyLevelNotEqualTo(Double value) {
            addCriterion("q_difficulty_level <>", value, "qDifficultyLevel");
            return (Criteria) this;
        }

        public Criteria andQDifficultyLevelGreaterThan(Double value) {
            addCriterion("q_difficulty_level >", value, "qDifficultyLevel");
            return (Criteria) this;
        }

        public Criteria andQDifficultyLevelGreaterThanOrEqualTo(Double value) {
            addCriterion("q_difficulty_level >=", value, "qDifficultyLevel");
            return (Criteria) this;
        }

        public Criteria andQDifficultyLevelLessThan(Double value) {
            addCriterion("q_difficulty_level <", value, "qDifficultyLevel");
            return (Criteria) this;
        }

        public Criteria andQDifficultyLevelLessThanOrEqualTo(Double value) {
            addCriterion("q_difficulty_level <=", value, "qDifficultyLevel");
            return (Criteria) this;
        }

        public Criteria andQDifficultyLevelIn(List<Double> values) {
            addCriterion("q_difficulty_level in", values, "qDifficultyLevel");
            return (Criteria) this;
        }

        public Criteria andQDifficultyLevelNotIn(List<Double> values) {
            addCriterion("q_difficulty_level not in", values, "qDifficultyLevel");
            return (Criteria) this;
        }

        public Criteria andQDifficultyLevelBetween(Double value1, Double value2) {
            addCriterion("q_difficulty_level between", value1, value2, "qDifficultyLevel");
            return (Criteria) this;
        }

        public Criteria andQDifficultyLevelNotBetween(Double value1, Double value2) {
            addCriterion("q_difficulty_level not between", value1, value2, "qDifficultyLevel");
            return (Criteria) this;
        }

        public Criteria andSIdIsNull() {
            addCriterion("s_id is null");
            return (Criteria) this;
        }

        public Criteria andSIdIsNotNull() {
            addCriterion("s_id is not null");
            return (Criteria) this;
        }

        public Criteria andSIdEqualTo(Long value) {
            addCriterion("s_id =", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdNotEqualTo(Long value) {
            addCriterion("s_id <>", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdGreaterThan(Long value) {
            addCriterion("s_id >", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdGreaterThanOrEqualTo(Long value) {
            addCriterion("s_id >=", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdLessThan(Long value) {
            addCriterion("s_id <", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdLessThanOrEqualTo(Long value) {
            addCriterion("s_id <=", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdIn(List<Long> values) {
            addCriterion("s_id in", values, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdNotIn(List<Long> values) {
            addCriterion("s_id not in", values, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdBetween(Long value1, Long value2) {
            addCriterion("s_id between", value1, value2, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdNotBetween(Long value1, Long value2) {
            addCriterion("s_id not between", value1, value2, "sId");
            return (Criteria) this;
        }

        public Criteria andQGetTimesIsNull() {
            addCriterion("q_get_times is null");
            return (Criteria) this;
        }

        public Criteria andQGetTimesIsNotNull() {
            addCriterion("q_get_times is not null");
            return (Criteria) this;
        }

        public Criteria andQGetTimesEqualTo(Integer value) {
            addCriterion("q_get_times =", value, "qGetTimes");
            return (Criteria) this;
        }

        public Criteria andQGetTimesNotEqualTo(Integer value) {
            addCriterion("q_get_times <>", value, "qGetTimes");
            return (Criteria) this;
        }

        public Criteria andQGetTimesGreaterThan(Integer value) {
            addCriterion("q_get_times >", value, "qGetTimes");
            return (Criteria) this;
        }

        public Criteria andQGetTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("q_get_times >=", value, "qGetTimes");
            return (Criteria) this;
        }

        public Criteria andQGetTimesLessThan(Integer value) {
            addCriterion("q_get_times <", value, "qGetTimes");
            return (Criteria) this;
        }

        public Criteria andQGetTimesLessThanOrEqualTo(Integer value) {
            addCriterion("q_get_times <=", value, "qGetTimes");
            return (Criteria) this;
        }

        public Criteria andQGetTimesIn(List<Integer> values) {
            addCriterion("q_get_times in", values, "qGetTimes");
            return (Criteria) this;
        }

        public Criteria andQGetTimesNotIn(List<Integer> values) {
            addCriterion("q_get_times not in", values, "qGetTimes");
            return (Criteria) this;
        }

        public Criteria andQGetTimesBetween(Integer value1, Integer value2) {
            addCriterion("q_get_times between", value1, value2, "qGetTimes");
            return (Criteria) this;
        }

        public Criteria andQGetTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("q_get_times not between", value1, value2, "qGetTimes");
            return (Criteria) this;
        }

        public Criteria andQDidNumIsNull() {
            addCriterion("q_did_num is null");
            return (Criteria) this;
        }

        public Criteria andQDidNumIsNotNull() {
            addCriterion("q_did_num is not null");
            return (Criteria) this;
        }

        public Criteria andQDidNumEqualTo(Integer value) {
            addCriterion("q_did_num =", value, "qDidNum");
            return (Criteria) this;
        }

        public Criteria andQDidNumNotEqualTo(Integer value) {
            addCriterion("q_did_num <>", value, "qDidNum");
            return (Criteria) this;
        }

        public Criteria andQDidNumGreaterThan(Integer value) {
            addCriterion("q_did_num >", value, "qDidNum");
            return (Criteria) this;
        }

        public Criteria andQDidNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("q_did_num >=", value, "qDidNum");
            return (Criteria) this;
        }

        public Criteria andQDidNumLessThan(Integer value) {
            addCriterion("q_did_num <", value, "qDidNum");
            return (Criteria) this;
        }

        public Criteria andQDidNumLessThanOrEqualTo(Integer value) {
            addCriterion("q_did_num <=", value, "qDidNum");
            return (Criteria) this;
        }

        public Criteria andQDidNumIn(List<Integer> values) {
            addCriterion("q_did_num in", values, "qDidNum");
            return (Criteria) this;
        }

        public Criteria andQDidNumNotIn(List<Integer> values) {
            addCriterion("q_did_num not in", values, "qDidNum");
            return (Criteria) this;
        }

        public Criteria andQDidNumBetween(Integer value1, Integer value2) {
            addCriterion("q_did_num between", value1, value2, "qDidNum");
            return (Criteria) this;
        }

        public Criteria andQDidNumNotBetween(Integer value1, Integer value2) {
            addCriterion("q_did_num not between", value1, value2, "qDidNum");
            return (Criteria) this;
        }

        public Criteria andQCorrectNumIsNull() {
            addCriterion("q_correct_num is null");
            return (Criteria) this;
        }

        public Criteria andQCorrectNumIsNotNull() {
            addCriterion("q_correct_num is not null");
            return (Criteria) this;
        }

        public Criteria andQCorrectNumEqualTo(Integer value) {
            addCriterion("q_correct_num =", value, "qCorrectNum");
            return (Criteria) this;
        }

        public Criteria andQCorrectNumNotEqualTo(Integer value) {
            addCriterion("q_correct_num <>", value, "qCorrectNum");
            return (Criteria) this;
        }

        public Criteria andQCorrectNumGreaterThan(Integer value) {
            addCriterion("q_correct_num >", value, "qCorrectNum");
            return (Criteria) this;
        }

        public Criteria andQCorrectNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("q_correct_num >=", value, "qCorrectNum");
            return (Criteria) this;
        }

        public Criteria andQCorrectNumLessThan(Integer value) {
            addCriterion("q_correct_num <", value, "qCorrectNum");
            return (Criteria) this;
        }

        public Criteria andQCorrectNumLessThanOrEqualTo(Integer value) {
            addCriterion("q_correct_num <=", value, "qCorrectNum");
            return (Criteria) this;
        }

        public Criteria andQCorrectNumIn(List<Integer> values) {
            addCriterion("q_correct_num in", values, "qCorrectNum");
            return (Criteria) this;
        }

        public Criteria andQCorrectNumNotIn(List<Integer> values) {
            addCriterion("q_correct_num not in", values, "qCorrectNum");
            return (Criteria) this;
        }

        public Criteria andQCorrectNumBetween(Integer value1, Integer value2) {
            addCriterion("q_correct_num between", value1, value2, "qCorrectNum");
            return (Criteria) this;
        }

        public Criteria andQCorrectNumNotBetween(Integer value1, Integer value2) {
            addCriterion("q_correct_num not between", value1, value2, "qCorrectNum");
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