package com.njxz.exam.modle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public UserExample() {
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

		public Criteria andUIdIsNull() {
			addCriterion("u_id is null");
			return (Criteria) this;
		}

		public Criteria andUIdIsNotNull() {
			addCriterion("u_id is not null");
			return (Criteria) this;
		}

		public Criteria andUIdEqualTo(Long value) {
			addCriterion("u_id =", value, "uId");
			return (Criteria) this;
		}

		public Criteria andUIdNotEqualTo(Long value) {
			addCriterion("u_id <>", value, "uId");
			return (Criteria) this;
		}

		public Criteria andUIdGreaterThan(Long value) {
			addCriterion("u_id >", value, "uId");
			return (Criteria) this;
		}

		public Criteria andUIdGreaterThanOrEqualTo(Long value) {
			addCriterion("u_id >=", value, "uId");
			return (Criteria) this;
		}

		public Criteria andUIdLessThan(Long value) {
			addCriterion("u_id <", value, "uId");
			return (Criteria) this;
		}

		public Criteria andUIdLessThanOrEqualTo(Long value) {
			addCriterion("u_id <=", value, "uId");
			return (Criteria) this;
		}

		public Criteria andUIdIn(List<Long> values) {
			addCriterion("u_id in", values, "uId");
			return (Criteria) this;
		}

		public Criteria andUIdNotIn(List<Long> values) {
			addCriterion("u_id not in", values, "uId");
			return (Criteria) this;
		}

		public Criteria andUIdBetween(Long value1, Long value2) {
			addCriterion("u_id between", value1, value2, "uId");
			return (Criteria) this;
		}

		public Criteria andUIdNotBetween(Long value1, Long value2) {
			addCriterion("u_id not between", value1, value2, "uId");
			return (Criteria) this;
		}

		public Criteria andUsernameIsNull() {
			addCriterion("username is null");
			return (Criteria) this;
		}

		public Criteria andUsernameIsNotNull() {
			addCriterion("username is not null");
			return (Criteria) this;
		}

		public Criteria andUsernameEqualTo(String value) {
			addCriterion("username =", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotEqualTo(String value) {
			addCriterion("username <>", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameGreaterThan(String value) {
			addCriterion("username >", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameGreaterThanOrEqualTo(String value) {
			addCriterion("username >=", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameLessThan(String value) {
			addCriterion("username <", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameLessThanOrEqualTo(String value) {
			addCriterion("username <=", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameLike(String value) {
			addCriterion("username like", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotLike(String value) {
			addCriterion("username not like", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameIn(List<String> values) {
			addCriterion("username in", values, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotIn(List<String> values) {
			addCriterion("username not in", values, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameBetween(String value1, String value2) {
			addCriterion("username between", value1, value2, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotBetween(String value1, String value2) {
			addCriterion("username not between", value1, value2, "username");
			return (Criteria) this;
		}

		public Criteria andPasswordIsNull() {
			addCriterion("password is null");
			return (Criteria) this;
		}

		public Criteria andPasswordIsNotNull() {
			addCriterion("password is not null");
			return (Criteria) this;
		}

		public Criteria andPasswordEqualTo(String value) {
			addCriterion("password =", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordNotEqualTo(String value) {
			addCriterion("password <>", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordGreaterThan(String value) {
			addCriterion("password >", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordGreaterThanOrEqualTo(String value) {
			addCriterion("password >=", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordLessThan(String value) {
			addCriterion("password <", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordLessThanOrEqualTo(String value) {
			addCriterion("password <=", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordLike(String value) {
			addCriterion("password like", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordNotLike(String value) {
			addCriterion("password not like", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordIn(List<String> values) {
			addCriterion("password in", values, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordNotIn(List<String> values) {
			addCriterion("password not in", values, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordBetween(String value1, String value2) {
			addCriterion("password between", value1, value2, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordNotBetween(String value1, String value2) {
			addCriterion("password not between", value1, value2, "password");
			return (Criteria) this;
		}

		public Criteria andNameIsNull() {
			addCriterion("name is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("name is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("name =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("name <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("name >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("name >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("name <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("name <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("name like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("name not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("name in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("name not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("name between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("name not between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andPowerIsNull() {
			addCriterion("power is null");
			return (Criteria) this;
		}

		public Criteria andPowerIsNotNull() {
			addCriterion("power is not null");
			return (Criteria) this;
		}

		public Criteria andPowerEqualTo(Byte value) {
			addCriterion("power =", value, "power");
			return (Criteria) this;
		}

		public Criteria andPowerNotEqualTo(Byte value) {
			addCriterion("power <>", value, "power");
			return (Criteria) this;
		}

		public Criteria andPowerGreaterThan(Byte value) {
			addCriterion("power >", value, "power");
			return (Criteria) this;
		}

		public Criteria andPowerGreaterThanOrEqualTo(Byte value) {
			addCriterion("power >=", value, "power");
			return (Criteria) this;
		}

		public Criteria andPowerLessThan(Byte value) {
			addCriterion("power <", value, "power");
			return (Criteria) this;
		}

		public Criteria andPowerLessThanOrEqualTo(Byte value) {
			addCriterion("power <=", value, "power");
			return (Criteria) this;
		}

		public Criteria andPowerIn(List<Byte> values) {
			addCriterion("power in", values, "power");
			return (Criteria) this;
		}

		public Criteria andPowerNotIn(List<Byte> values) {
			addCriterion("power not in", values, "power");
			return (Criteria) this;
		}

		public Criteria andPowerBetween(Byte value1, Byte value2) {
			addCriterion("power between", value1, value2, "power");
			return (Criteria) this;
		}

		public Criteria andPowerNotBetween(Byte value1, Byte value2) {
			addCriterion("power not between", value1, value2, "power");
			return (Criteria) this;
		}

		public Criteria andParentIdIsNull() {
			addCriterion("parent_id is null");
			return (Criteria) this;
		}

		public Criteria andParentIdIsNotNull() {
			addCriterion("parent_id is not null");
			return (Criteria) this;
		}

		public Criteria andParentIdEqualTo(Long value) {
			addCriterion("parent_id =", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdNotEqualTo(Long value) {
			addCriterion("parent_id <>", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdGreaterThan(Long value) {
			addCriterion("parent_id >", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdGreaterThanOrEqualTo(Long value) {
			addCriterion("parent_id >=", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdLessThan(Long value) {
			addCriterion("parent_id <", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdLessThanOrEqualTo(Long value) {
			addCriterion("parent_id <=", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdIn(List<Long> values) {
			addCriterion("parent_id in", values, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdNotIn(List<Long> values) {
			addCriterion("parent_id not in", values, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdBetween(Long value1, Long value2) {
			addCriterion("parent_id between", value1, value2, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdNotBetween(Long value1, Long value2) {
			addCriterion("parent_id not between", value1, value2, "parentId");
			return (Criteria) this;
		}

		public Criteria andRegistTimeIsNull() {
			addCriterion("regist_time is null");
			return (Criteria) this;
		}

		public Criteria andRegistTimeIsNotNull() {
			addCriterion("regist_time is not null");
			return (Criteria) this;
		}

		public Criteria andRegistTimeEqualTo(Date value) {
			addCriterion("regist_time =", value, "registTime");
			return (Criteria) this;
		}

		public Criteria andRegistTimeNotEqualTo(Date value) {
			addCriterion("regist_time <>", value, "registTime");
			return (Criteria) this;
		}

		public Criteria andRegistTimeGreaterThan(Date value) {
			addCriterion("regist_time >", value, "registTime");
			return (Criteria) this;
		}

		public Criteria andRegistTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("regist_time >=", value, "registTime");
			return (Criteria) this;
		}

		public Criteria andRegistTimeLessThan(Date value) {
			addCriterion("regist_time <", value, "registTime");
			return (Criteria) this;
		}

		public Criteria andRegistTimeLessThanOrEqualTo(Date value) {
			addCriterion("regist_time <=", value, "registTime");
			return (Criteria) this;
		}

		public Criteria andRegistTimeIn(List<Date> values) {
			addCriterion("regist_time in", values, "registTime");
			return (Criteria) this;
		}

		public Criteria andRegistTimeNotIn(List<Date> values) {
			addCriterion("regist_time not in", values, "registTime");
			return (Criteria) this;
		}

		public Criteria andRegistTimeBetween(Date value1, Date value2) {
			addCriterion("regist_time between", value1, value2, "registTime");
			return (Criteria) this;
		}

		public Criteria andRegistTimeNotBetween(Date value1, Date value2) {
			addCriterion("regist_time not between", value1, value2, "registTime");
			return (Criteria) this;
		}

		public Criteria andRecentLoginTimeIsNull() {
			addCriterion("recent_login_time is null");
			return (Criteria) this;
		}

		public Criteria andRecentLoginTimeIsNotNull() {
			addCriterion("recent_login_time is not null");
			return (Criteria) this;
		}

		public Criteria andRecentLoginTimeEqualTo(Date value) {
			addCriterion("recent_login_time =", value, "recentLoginTime");
			return (Criteria) this;
		}

		public Criteria andRecentLoginTimeNotEqualTo(Date value) {
			addCriterion("recent_login_time <>", value, "recentLoginTime");
			return (Criteria) this;
		}

		public Criteria andRecentLoginTimeGreaterThan(Date value) {
			addCriterion("recent_login_time >", value, "recentLoginTime");
			return (Criteria) this;
		}

		public Criteria andRecentLoginTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("recent_login_time >=", value, "recentLoginTime");
			return (Criteria) this;
		}

		public Criteria andRecentLoginTimeLessThan(Date value) {
			addCriterion("recent_login_time <", value, "recentLoginTime");
			return (Criteria) this;
		}

		public Criteria andRecentLoginTimeLessThanOrEqualTo(Date value) {
			addCriterion("recent_login_time <=", value, "recentLoginTime");
			return (Criteria) this;
		}

		public Criteria andRecentLoginTimeIn(List<Date> values) {
			addCriterion("recent_login_time in", values, "recentLoginTime");
			return (Criteria) this;
		}

		public Criteria andRecentLoginTimeNotIn(List<Date> values) {
			addCriterion("recent_login_time not in", values, "recentLoginTime");
			return (Criteria) this;
		}

		public Criteria andRecentLoginTimeBetween(Date value1, Date value2) {
			addCriterion("recent_login_time between", value1, value2, "recentLoginTime");
			return (Criteria) this;
		}

		public Criteria andRecentLoginTimeNotBetween(Date value1, Date value2) {
			addCriterion("recent_login_time not between", value1, value2, "recentLoginTime");
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