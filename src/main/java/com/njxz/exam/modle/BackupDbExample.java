package com.njxz.exam.modle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BackupDbExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public BackupDbExample() {
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

		public Criteria andBIdIsNull() {
			addCriterion("b_id is null");
			return (Criteria) this;
		}

		public Criteria andBIdIsNotNull() {
			addCriterion("b_id is not null");
			return (Criteria) this;
		}

		public Criteria andBIdEqualTo(Long value) {
			addCriterion("b_id =", value, "bId");
			return (Criteria) this;
		}

		public Criteria andBIdNotEqualTo(Long value) {
			addCriterion("b_id <>", value, "bId");
			return (Criteria) this;
		}

		public Criteria andBIdGreaterThan(Long value) {
			addCriterion("b_id >", value, "bId");
			return (Criteria) this;
		}

		public Criteria andBIdGreaterThanOrEqualTo(Long value) {
			addCriterion("b_id >=", value, "bId");
			return (Criteria) this;
		}

		public Criteria andBIdLessThan(Long value) {
			addCriterion("b_id <", value, "bId");
			return (Criteria) this;
		}

		public Criteria andBIdLessThanOrEqualTo(Long value) {
			addCriterion("b_id <=", value, "bId");
			return (Criteria) this;
		}

		public Criteria andBIdIn(List<Long> values) {
			addCriterion("b_id in", values, "bId");
			return (Criteria) this;
		}

		public Criteria andBIdNotIn(List<Long> values) {
			addCriterion("b_id not in", values, "bId");
			return (Criteria) this;
		}

		public Criteria andBIdBetween(Long value1, Long value2) {
			addCriterion("b_id between", value1, value2, "bId");
			return (Criteria) this;
		}

		public Criteria andBIdNotBetween(Long value1, Long value2) {
			addCriterion("b_id not between", value1, value2, "bId");
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

		public Criteria andUserIdEqualTo(Short value) {
			addCriterion("user_id =", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotEqualTo(Short value) {
			addCriterion("user_id <>", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThan(Short value) {
			addCriterion("user_id >", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThanOrEqualTo(Short value) {
			addCriterion("user_id >=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThan(Short value) {
			addCriterion("user_id <", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThanOrEqualTo(Short value) {
			addCriterion("user_id <=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdIn(List<Short> values) {
			addCriterion("user_id in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotIn(List<Short> values) {
			addCriterion("user_id not in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdBetween(Short value1, Short value2) {
			addCriterion("user_id between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotBetween(Short value1, Short value2) {
			addCriterion("user_id not between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andBFileNameIsNull() {
			addCriterion("b_file_name is null");
			return (Criteria) this;
		}

		public Criteria andBFileNameIsNotNull() {
			addCriterion("b_file_name is not null");
			return (Criteria) this;
		}

		public Criteria andBFileNameEqualTo(String value) {
			addCriterion("b_file_name =", value, "bFileName");
			return (Criteria) this;
		}

		public Criteria andBFileNameNotEqualTo(String value) {
			addCriterion("b_file_name <>", value, "bFileName");
			return (Criteria) this;
		}

		public Criteria andBFileNameGreaterThan(String value) {
			addCriterion("b_file_name >", value, "bFileName");
			return (Criteria) this;
		}

		public Criteria andBFileNameGreaterThanOrEqualTo(String value) {
			addCriterion("b_file_name >=", value, "bFileName");
			return (Criteria) this;
		}

		public Criteria andBFileNameLessThan(String value) {
			addCriterion("b_file_name <", value, "bFileName");
			return (Criteria) this;
		}

		public Criteria andBFileNameLessThanOrEqualTo(String value) {
			addCriterion("b_file_name <=", value, "bFileName");
			return (Criteria) this;
		}

		public Criteria andBFileNameLike(String value) {
			addCriterion("b_file_name like", value, "bFileName");
			return (Criteria) this;
		}

		public Criteria andBFileNameNotLike(String value) {
			addCriterion("b_file_name not like", value, "bFileName");
			return (Criteria) this;
		}

		public Criteria andBFileNameIn(List<String> values) {
			addCriterion("b_file_name in", values, "bFileName");
			return (Criteria) this;
		}

		public Criteria andBFileNameNotIn(List<String> values) {
			addCriterion("b_file_name not in", values, "bFileName");
			return (Criteria) this;
		}

		public Criteria andBFileNameBetween(String value1, String value2) {
			addCriterion("b_file_name between", value1, value2, "bFileName");
			return (Criteria) this;
		}

		public Criteria andBFileNameNotBetween(String value1, String value2) {
			addCriterion("b_file_name not between", value1, value2, "bFileName");
			return (Criteria) this;
		}

		public Criteria andBTimeIsNull() {
			addCriterion("b_time is null");
			return (Criteria) this;
		}

		public Criteria andBTimeIsNotNull() {
			addCriterion("b_time is not null");
			return (Criteria) this;
		}

		public Criteria andBTimeEqualTo(Date value) {
			addCriterion("b_time =", value, "bTime");
			return (Criteria) this;
		}

		public Criteria andBTimeNotEqualTo(Date value) {
			addCriterion("b_time <>", value, "bTime");
			return (Criteria) this;
		}

		public Criteria andBTimeGreaterThan(Date value) {
			addCriterion("b_time >", value, "bTime");
			return (Criteria) this;
		}

		public Criteria andBTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("b_time >=", value, "bTime");
			return (Criteria) this;
		}

		public Criteria andBTimeLessThan(Date value) {
			addCriterion("b_time <", value, "bTime");
			return (Criteria) this;
		}

		public Criteria andBTimeLessThanOrEqualTo(Date value) {
			addCriterion("b_time <=", value, "bTime");
			return (Criteria) this;
		}

		public Criteria andBTimeIn(List<Date> values) {
			addCriterion("b_time in", values, "bTime");
			return (Criteria) this;
		}

		public Criteria andBTimeNotIn(List<Date> values) {
			addCriterion("b_time not in", values, "bTime");
			return (Criteria) this;
		}

		public Criteria andBTimeBetween(Date value1, Date value2) {
			addCriterion("b_time between", value1, value2, "bTime");
			return (Criteria) this;
		}

		public Criteria andBTimeNotBetween(Date value1, Date value2) {
			addCriterion("b_time not between", value1, value2, "bTime");
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