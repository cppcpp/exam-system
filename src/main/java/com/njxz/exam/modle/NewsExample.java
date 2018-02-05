package com.njxz.exam.modle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class NewsExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public NewsExample() {
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

		public Criteria andNIdIsNull() {
			addCriterion("n_id is null");
			return (Criteria) this;
		}

		public Criteria andNIdIsNotNull() {
			addCriterion("n_id is not null");
			return (Criteria) this;
		}

		public Criteria andNIdEqualTo(Long value) {
			addCriterion("n_id =", value, "nId");
			return (Criteria) this;
		}

		public Criteria andNIdNotEqualTo(Long value) {
			addCriterion("n_id <>", value, "nId");
			return (Criteria) this;
		}

		public Criteria andNIdGreaterThan(Long value) {
			addCriterion("n_id >", value, "nId");
			return (Criteria) this;
		}

		public Criteria andNIdGreaterThanOrEqualTo(Long value) {
			addCriterion("n_id >=", value, "nId");
			return (Criteria) this;
		}

		public Criteria andNIdLessThan(Long value) {
			addCriterion("n_id <", value, "nId");
			return (Criteria) this;
		}

		public Criteria andNIdLessThanOrEqualTo(Long value) {
			addCriterion("n_id <=", value, "nId");
			return (Criteria) this;
		}

		public Criteria andNIdIn(List<Long> values) {
			addCriterion("n_id in", values, "nId");
			return (Criteria) this;
		}

		public Criteria andNIdNotIn(List<Long> values) {
			addCriterion("n_id not in", values, "nId");
			return (Criteria) this;
		}

		public Criteria andNIdBetween(Long value1, Long value2) {
			addCriterion("n_id between", value1, value2, "nId");
			return (Criteria) this;
		}

		public Criteria andNIdNotBetween(Long value1, Long value2) {
			addCriterion("n_id not between", value1, value2, "nId");
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

		public Criteria andNContentIsNull() {
			addCriterion("n_content is null");
			return (Criteria) this;
		}

		public Criteria andNContentIsNotNull() {
			addCriterion("n_content is not null");
			return (Criteria) this;
		}

		public Criteria andNContentEqualTo(String value) {
			addCriterion("n_content =", value, "nContent");
			return (Criteria) this;
		}

		public Criteria andNContentNotEqualTo(String value) {
			addCriterion("n_content <>", value, "nContent");
			return (Criteria) this;
		}

		public Criteria andNContentGreaterThan(String value) {
			addCriterion("n_content >", value, "nContent");
			return (Criteria) this;
		}

		public Criteria andNContentGreaterThanOrEqualTo(String value) {
			addCriterion("n_content >=", value, "nContent");
			return (Criteria) this;
		}

		public Criteria andNContentLessThan(String value) {
			addCriterion("n_content <", value, "nContent");
			return (Criteria) this;
		}

		public Criteria andNContentLessThanOrEqualTo(String value) {
			addCriterion("n_content <=", value, "nContent");
			return (Criteria) this;
		}

		public Criteria andNContentLike(String value) {
			addCriterion("n_content like", value, "nContent");
			return (Criteria) this;
		}

		public Criteria andNContentNotLike(String value) {
			addCriterion("n_content not like", value, "nContent");
			return (Criteria) this;
		}

		public Criteria andNContentIn(List<String> values) {
			addCriterion("n_content in", values, "nContent");
			return (Criteria) this;
		}

		public Criteria andNContentNotIn(List<String> values) {
			addCriterion("n_content not in", values, "nContent");
			return (Criteria) this;
		}

		public Criteria andNContentBetween(String value1, String value2) {
			addCriterion("n_content between", value1, value2, "nContent");
			return (Criteria) this;
		}

		public Criteria andNContentNotBetween(String value1, String value2) {
			addCriterion("n_content not between", value1, value2, "nContent");
			return (Criteria) this;
		}

		public Criteria andNAddTimeIsNull() {
			addCriterion("n_add_time is null");
			return (Criteria) this;
		}

		public Criteria andNAddTimeIsNotNull() {
			addCriterion("n_add_time is not null");
			return (Criteria) this;
		}

		public Criteria andNAddTimeEqualTo(Date value) {
			addCriterion("n_add_time =", value, "nAddTime");
			return (Criteria) this;
		}

		public Criteria andNAddTimeNotEqualTo(Date value) {
			addCriterion("n_add_time <>", value, "nAddTime");
			return (Criteria) this;
		}

		public Criteria andNAddTimeGreaterThan(Date value) {
			addCriterion("n_add_time >", value, "nAddTime");
			return (Criteria) this;
		}

		public Criteria andNAddTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("n_add_time >=", value, "nAddTime");
			return (Criteria) this;
		}

		public Criteria andNAddTimeLessThan(Date value) {
			addCriterion("n_add_time <", value, "nAddTime");
			return (Criteria) this;
		}

		public Criteria andNAddTimeLessThanOrEqualTo(Date value) {
			addCriterion("n_add_time <=", value, "nAddTime");
			return (Criteria) this;
		}

		public Criteria andNAddTimeIn(List<Date> values) {
			addCriterion("n_add_time in", values, "nAddTime");
			return (Criteria) this;
		}

		public Criteria andNAddTimeNotIn(List<Date> values) {
			addCriterion("n_add_time not in", values, "nAddTime");
			return (Criteria) this;
		}

		public Criteria andNAddTimeBetween(Date value1, Date value2) {
			addCriterion("n_add_time between", value1, value2, "nAddTime");
			return (Criteria) this;
		}

		public Criteria andNAddTimeNotBetween(Date value1, Date value2) {
			addCriterion("n_add_time not between", value1, value2, "nAddTime");
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