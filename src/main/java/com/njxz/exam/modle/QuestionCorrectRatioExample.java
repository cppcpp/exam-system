package com.njxz.exam.modle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class QuestionCorrectRatioExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public QuestionCorrectRatioExample() {
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

		public Criteria andQResponseIdIsNull() {
			addCriterion("q_response_id is null");
			return (Criteria) this;
		}

		public Criteria andQResponseIdIsNotNull() {
			addCriterion("q_response_id is not null");
			return (Criteria) this;
		}

		public Criteria andQResponseIdEqualTo(Long value) {
			addCriterion("q_response_id =", value, "qResponseId");
			return (Criteria) this;
		}

		public Criteria andQResponseIdNotEqualTo(Long value) {
			addCriterion("q_response_id <>", value, "qResponseId");
			return (Criteria) this;
		}

		public Criteria andQResponseIdGreaterThan(Long value) {
			addCriterion("q_response_id >", value, "qResponseId");
			return (Criteria) this;
		}

		public Criteria andQResponseIdGreaterThanOrEqualTo(Long value) {
			addCriterion("q_response_id >=", value, "qResponseId");
			return (Criteria) this;
		}

		public Criteria andQResponseIdLessThan(Long value) {
			addCriterion("q_response_id <", value, "qResponseId");
			return (Criteria) this;
		}

		public Criteria andQResponseIdLessThanOrEqualTo(Long value) {
			addCriterion("q_response_id <=", value, "qResponseId");
			return (Criteria) this;
		}

		public Criteria andQResponseIdIn(List<Long> values) {
			addCriterion("q_response_id in", values, "qResponseId");
			return (Criteria) this;
		}

		public Criteria andQResponseIdNotIn(List<Long> values) {
			addCriterion("q_response_id not in", values, "qResponseId");
			return (Criteria) this;
		}

		public Criteria andQResponseIdBetween(Long value1, Long value2) {
			addCriterion("q_response_id between", value1, value2, "qResponseId");
			return (Criteria) this;
		}

		public Criteria andQResponseIdNotBetween(Long value1, Long value2) {
			addCriterion("q_response_id not between", value1, value2, "qResponseId");
			return (Criteria) this;
		}

		public Criteria andQuestionIdIsNull() {
			addCriterion("question_id is null");
			return (Criteria) this;
		}

		public Criteria andQuestionIdIsNotNull() {
			addCriterion("question_id is not null");
			return (Criteria) this;
		}

		public Criteria andQuestionIdEqualTo(Integer value) {
			addCriterion("question_id =", value, "questionId");
			return (Criteria) this;
		}

		public Criteria andQuestionIdNotEqualTo(Integer value) {
			addCriterion("question_id <>", value, "questionId");
			return (Criteria) this;
		}

		public Criteria andQuestionIdGreaterThan(Integer value) {
			addCriterion("question_id >", value, "questionId");
			return (Criteria) this;
		}

		public Criteria andQuestionIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("question_id >=", value, "questionId");
			return (Criteria) this;
		}

		public Criteria andQuestionIdLessThan(Integer value) {
			addCriterion("question_id <", value, "questionId");
			return (Criteria) this;
		}

		public Criteria andQuestionIdLessThanOrEqualTo(Integer value) {
			addCriterion("question_id <=", value, "questionId");
			return (Criteria) this;
		}

		public Criteria andQuestionIdIn(List<Integer> values) {
			addCriterion("question_id in", values, "questionId");
			return (Criteria) this;
		}

		public Criteria andQuestionIdNotIn(List<Integer> values) {
			addCriterion("question_id not in", values, "questionId");
			return (Criteria) this;
		}

		public Criteria andQuestionIdBetween(Integer value1, Integer value2) {
			addCriterion("question_id between", value1, value2, "questionId");
			return (Criteria) this;
		}

		public Criteria andQuestionIdNotBetween(Integer value1, Integer value2) {
			addCriterion("question_id not between", value1, value2, "questionId");
			return (Criteria) this;
		}

		public Criteria andQResponseTimeIsNull() {
			addCriterion("q_response_time is null");
			return (Criteria) this;
		}

		public Criteria andQResponseTimeIsNotNull() {
			addCriterion("q_response_time is not null");
			return (Criteria) this;
		}

		public Criteria andQResponseTimeEqualTo(Date value) {
			addCriterion("q_response_time =", value, "qResponseTime");
			return (Criteria) this;
		}

		public Criteria andQResponseTimeNotEqualTo(Date value) {
			addCriterion("q_response_time <>", value, "qResponseTime");
			return (Criteria) this;
		}

		public Criteria andQResponseTimeGreaterThan(Date value) {
			addCriterion("q_response_time >", value, "qResponseTime");
			return (Criteria) this;
		}

		public Criteria andQResponseTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("q_response_time >=", value, "qResponseTime");
			return (Criteria) this;
		}

		public Criteria andQResponseTimeLessThan(Date value) {
			addCriterion("q_response_time <", value, "qResponseTime");
			return (Criteria) this;
		}

		public Criteria andQResponseTimeLessThanOrEqualTo(Date value) {
			addCriterion("q_response_time <=", value, "qResponseTime");
			return (Criteria) this;
		}

		public Criteria andQResponseTimeIn(List<Date> values) {
			addCriterion("q_response_time in", values, "qResponseTime");
			return (Criteria) this;
		}

		public Criteria andQResponseTimeNotIn(List<Date> values) {
			addCriterion("q_response_time not in", values, "qResponseTime");
			return (Criteria) this;
		}

		public Criteria andQResponseTimeBetween(Date value1, Date value2) {
			addCriterion("q_response_time between", value1, value2, "qResponseTime");
			return (Criteria) this;
		}

		public Criteria andQResponseTimeNotBetween(Date value1, Date value2) {
			addCriterion("q_response_time not between", value1, value2, "qResponseTime");
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