package com.njxz.exam.modle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SubjectExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public SubjectExample() {
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

		public Criteria andSTitleIsNull() {
			addCriterion("s_title is null");
			return (Criteria) this;
		}

		public Criteria andSTitleIsNotNull() {
			addCriterion("s_title is not null");
			return (Criteria) this;
		}

		public Criteria andSTitleEqualTo(String value) {
			addCriterion("s_title =", value, "sTitle");
			return (Criteria) this;
		}

		public Criteria andSTitleNotEqualTo(String value) {
			addCriterion("s_title <>", value, "sTitle");
			return (Criteria) this;
		}

		public Criteria andSTitleGreaterThan(String value) {
			addCriterion("s_title >", value, "sTitle");
			return (Criteria) this;
		}

		public Criteria andSTitleGreaterThanOrEqualTo(String value) {
			addCriterion("s_title >=", value, "sTitle");
			return (Criteria) this;
		}

		public Criteria andSTitleLessThan(String value) {
			addCriterion("s_title <", value, "sTitle");
			return (Criteria) this;
		}

		public Criteria andSTitleLessThanOrEqualTo(String value) {
			addCriterion("s_title <=", value, "sTitle");
			return (Criteria) this;
		}

		public Criteria andSTitleLike(String value) {
			addCriterion("s_title like", value, "sTitle");
			return (Criteria) this;
		}

		public Criteria andSTitleNotLike(String value) {
			addCriterion("s_title not like", value, "sTitle");
			return (Criteria) this;
		}

		public Criteria andSTitleIn(List<String> values) {
			addCriterion("s_title in", values, "sTitle");
			return (Criteria) this;
		}

		public Criteria andSTitleNotIn(List<String> values) {
			addCriterion("s_title not in", values, "sTitle");
			return (Criteria) this;
		}

		public Criteria andSTitleBetween(String value1, String value2) {
			addCriterion("s_title between", value1, value2, "sTitle");
			return (Criteria) this;
		}

		public Criteria andSTitleNotBetween(String value1, String value2) {
			addCriterion("s_title not between", value1, value2, "sTitle");
			return (Criteria) this;
		}

		public Criteria andSAddTimeIsNull() {
			addCriterion("s_add_time is null");
			return (Criteria) this;
		}

		public Criteria andSAddTimeIsNotNull() {
			addCriterion("s_add_time is not null");
			return (Criteria) this;
		}

		public Criteria andSAddTimeEqualTo(Date value) {
			addCriterion("s_add_time =", value, "sAddTime");
			return (Criteria) this;
		}

		public Criteria andSAddTimeNotEqualTo(Date value) {
			addCriterion("s_add_time <>", value, "sAddTime");
			return (Criteria) this;
		}

		public Criteria andSAddTimeGreaterThan(Date value) {
			addCriterion("s_add_time >", value, "sAddTime");
			return (Criteria) this;
		}

		public Criteria andSAddTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("s_add_time >=", value, "sAddTime");
			return (Criteria) this;
		}

		public Criteria andSAddTimeLessThan(Date value) {
			addCriterion("s_add_time <", value, "sAddTime");
			return (Criteria) this;
		}

		public Criteria andSAddTimeLessThanOrEqualTo(Date value) {
			addCriterion("s_add_time <=", value, "sAddTime");
			return (Criteria) this;
		}

		public Criteria andSAddTimeIn(List<Date> values) {
			addCriterion("s_add_time in", values, "sAddTime");
			return (Criteria) this;
		}

		public Criteria andSAddTimeNotIn(List<Date> values) {
			addCriterion("s_add_time not in", values, "sAddTime");
			return (Criteria) this;
		}

		public Criteria andSAddTimeBetween(Date value1, Date value2) {
			addCriterion("s_add_time between", value1, value2, "sAddTime");
			return (Criteria) this;
		}

		public Criteria andSAddTimeNotBetween(Date value1, Date value2) {
			addCriterion("s_add_time not between", value1, value2, "sAddTime");
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