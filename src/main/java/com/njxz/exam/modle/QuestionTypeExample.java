package com.njxz.exam.modle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class QuestionTypeExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public QuestionTypeExample() {
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

		public Criteria andTIdIsNull() {
			addCriterion("t_id is null");
			return (Criteria) this;
		}

		public Criteria andTIdIsNotNull() {
			addCriterion("t_id is not null");
			return (Criteria) this;
		}

		public Criteria andTIdEqualTo(Long value) {
			addCriterion("t_id =", value, "tId");
			return (Criteria) this;
		}

		public Criteria andTIdNotEqualTo(Long value) {
			addCriterion("t_id <>", value, "tId");
			return (Criteria) this;
		}

		public Criteria andTIdGreaterThan(Long value) {
			addCriterion("t_id >", value, "tId");
			return (Criteria) this;
		}

		public Criteria andTIdGreaterThanOrEqualTo(Long value) {
			addCriterion("t_id >=", value, "tId");
			return (Criteria) this;
		}

		public Criteria andTIdLessThan(Long value) {
			addCriterion("t_id <", value, "tId");
			return (Criteria) this;
		}

		public Criteria andTIdLessThanOrEqualTo(Long value) {
			addCriterion("t_id <=", value, "tId");
			return (Criteria) this;
		}

		public Criteria andTIdIn(List<Long> values) {
			addCriterion("t_id in", values, "tId");
			return (Criteria) this;
		}

		public Criteria andTIdNotIn(List<Long> values) {
			addCriterion("t_id not in", values, "tId");
			return (Criteria) this;
		}

		public Criteria andTIdBetween(Long value1, Long value2) {
			addCriterion("t_id between", value1, value2, "tId");
			return (Criteria) this;
		}

		public Criteria andTIdNotBetween(Long value1, Long value2) {
			addCriterion("t_id not between", value1, value2, "tId");
			return (Criteria) this;
		}

		public Criteria andTTitleIsNull() {
			addCriterion("t_title is null");
			return (Criteria) this;
		}

		public Criteria andTTitleIsNotNull() {
			addCriterion("t_title is not null");
			return (Criteria) this;
		}

		public Criteria andTTitleEqualTo(String value) {
			addCriterion("t_title =", value, "tTitle");
			return (Criteria) this;
		}

		public Criteria andTTitleNotEqualTo(String value) {
			addCriterion("t_title <>", value, "tTitle");
			return (Criteria) this;
		}

		public Criteria andTTitleGreaterThan(String value) {
			addCriterion("t_title >", value, "tTitle");
			return (Criteria) this;
		}

		public Criteria andTTitleGreaterThanOrEqualTo(String value) {
			addCriterion("t_title >=", value, "tTitle");
			return (Criteria) this;
		}

		public Criteria andTTitleLessThan(String value) {
			addCriterion("t_title <", value, "tTitle");
			return (Criteria) this;
		}

		public Criteria andTTitleLessThanOrEqualTo(String value) {
			addCriterion("t_title <=", value, "tTitle");
			return (Criteria) this;
		}

		public Criteria andTTitleLike(String value) {
			addCriterion("t_title like", value, "tTitle");
			return (Criteria) this;
		}

		public Criteria andTTitleNotLike(String value) {
			addCriterion("t_title not like", value, "tTitle");
			return (Criteria) this;
		}

		public Criteria andTTitleIn(List<String> values) {
			addCriterion("t_title in", values, "tTitle");
			return (Criteria) this;
		}

		public Criteria andTTitleNotIn(List<String> values) {
			addCriterion("t_title not in", values, "tTitle");
			return (Criteria) this;
		}

		public Criteria andTTitleBetween(String value1, String value2) {
			addCriterion("t_title between", value1, value2, "tTitle");
			return (Criteria) this;
		}

		public Criteria andTTitleNotBetween(String value1, String value2) {
			addCriterion("t_title not between", value1, value2, "tTitle");
			return (Criteria) this;
		}

		public Criteria andTDescIsNull() {
			addCriterion("t_desc is null");
			return (Criteria) this;
		}

		public Criteria andTDescIsNotNull() {
			addCriterion("t_desc is not null");
			return (Criteria) this;
		}

		public Criteria andTDescEqualTo(String value) {
			addCriterion("t_desc =", value, "tDesc");
			return (Criteria) this;
		}

		public Criteria andTDescNotEqualTo(String value) {
			addCriterion("t_desc <>", value, "tDesc");
			return (Criteria) this;
		}

		public Criteria andTDescGreaterThan(String value) {
			addCriterion("t_desc >", value, "tDesc");
			return (Criteria) this;
		}

		public Criteria andTDescGreaterThanOrEqualTo(String value) {
			addCriterion("t_desc >=", value, "tDesc");
			return (Criteria) this;
		}

		public Criteria andTDescLessThan(String value) {
			addCriterion("t_desc <", value, "tDesc");
			return (Criteria) this;
		}

		public Criteria andTDescLessThanOrEqualTo(String value) {
			addCriterion("t_desc <=", value, "tDesc");
			return (Criteria) this;
		}

		public Criteria andTDescLike(String value) {
			addCriterion("t_desc like", value, "tDesc");
			return (Criteria) this;
		}

		public Criteria andTDescNotLike(String value) {
			addCriterion("t_desc not like", value, "tDesc");
			return (Criteria) this;
		}

		public Criteria andTDescIn(List<String> values) {
			addCriterion("t_desc in", values, "tDesc");
			return (Criteria) this;
		}

		public Criteria andTDescNotIn(List<String> values) {
			addCriterion("t_desc not in", values, "tDesc");
			return (Criteria) this;
		}

		public Criteria andTDescBetween(String value1, String value2) {
			addCriterion("t_desc between", value1, value2, "tDesc");
			return (Criteria) this;
		}

		public Criteria andTDescNotBetween(String value1, String value2) {
			addCriterion("t_desc not between", value1, value2, "tDesc");
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