package com.lpq.mail.entity;

import java.util.ArrayList;
import java.util.List;

public class MailAccountInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MailAccountInfoExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
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

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andMailAccountIsNull() {
            addCriterion("mail_account is null");
            return (Criteria) this;
        }

        public Criteria andMailAccountIsNotNull() {
            addCriterion("mail_account is not null");
            return (Criteria) this;
        }

        public Criteria andMailAccountEqualTo(String value) {
            addCriterion("mail_account =", value, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailAccountNotEqualTo(String value) {
            addCriterion("mail_account <>", value, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailAccountGreaterThan(String value) {
            addCriterion("mail_account >", value, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailAccountGreaterThanOrEqualTo(String value) {
            addCriterion("mail_account >=", value, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailAccountLessThan(String value) {
            addCriterion("mail_account <", value, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailAccountLessThanOrEqualTo(String value) {
            addCriterion("mail_account <=", value, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailAccountLike(String value) {
            addCriterion("mail_account like", value, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailAccountNotLike(String value) {
            addCriterion("mail_account not like", value, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailAccountIn(List<String> values) {
            addCriterion("mail_account in", values, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailAccountNotIn(List<String> values) {
            addCriterion("mail_account not in", values, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailAccountBetween(String value1, String value2) {
            addCriterion("mail_account between", value1, value2, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailAccountNotBetween(String value1, String value2) {
            addCriterion("mail_account not between", value1, value2, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailPasswordIsNull() {
            addCriterion("mail_password is null");
            return (Criteria) this;
        }

        public Criteria andMailPasswordIsNotNull() {
            addCriterion("mail_password is not null");
            return (Criteria) this;
        }

        public Criteria andMailPasswordEqualTo(String value) {
            addCriterion("mail_password =", value, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPasswordNotEqualTo(String value) {
            addCriterion("mail_password <>", value, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPasswordGreaterThan(String value) {
            addCriterion("mail_password >", value, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("mail_password >=", value, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPasswordLessThan(String value) {
            addCriterion("mail_password <", value, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPasswordLessThanOrEqualTo(String value) {
            addCriterion("mail_password <=", value, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPasswordLike(String value) {
            addCriterion("mail_password like", value, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPasswordNotLike(String value) {
            addCriterion("mail_password not like", value, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPasswordIn(List<String> values) {
            addCriterion("mail_password in", values, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPasswordNotIn(List<String> values) {
            addCriterion("mail_password not in", values, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPasswordBetween(String value1, String value2) {
            addCriterion("mail_password between", value1, value2, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPasswordNotBetween(String value1, String value2) {
            addCriterion("mail_password not between", value1, value2, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPopAddressIsNull() {
            addCriterion("mail_pop_address is null");
            return (Criteria) this;
        }

        public Criteria andMailPopAddressIsNotNull() {
            addCriterion("mail_pop_address is not null");
            return (Criteria) this;
        }

        public Criteria andMailPopAddressEqualTo(String value) {
            addCriterion("mail_pop_address =", value, "mailPopAddress");
            return (Criteria) this;
        }

        public Criteria andMailPopAddressNotEqualTo(String value) {
            addCriterion("mail_pop_address <>", value, "mailPopAddress");
            return (Criteria) this;
        }

        public Criteria andMailPopAddressGreaterThan(String value) {
            addCriterion("mail_pop_address >", value, "mailPopAddress");
            return (Criteria) this;
        }

        public Criteria andMailPopAddressGreaterThanOrEqualTo(String value) {
            addCriterion("mail_pop_address >=", value, "mailPopAddress");
            return (Criteria) this;
        }

        public Criteria andMailPopAddressLessThan(String value) {
            addCriterion("mail_pop_address <", value, "mailPopAddress");
            return (Criteria) this;
        }

        public Criteria andMailPopAddressLessThanOrEqualTo(String value) {
            addCriterion("mail_pop_address <=", value, "mailPopAddress");
            return (Criteria) this;
        }

        public Criteria andMailPopAddressLike(String value) {
            addCriterion("mail_pop_address like", value, "mailPopAddress");
            return (Criteria) this;
        }

        public Criteria andMailPopAddressNotLike(String value) {
            addCriterion("mail_pop_address not like", value, "mailPopAddress");
            return (Criteria) this;
        }

        public Criteria andMailPopAddressIn(List<String> values) {
            addCriterion("mail_pop_address in", values, "mailPopAddress");
            return (Criteria) this;
        }

        public Criteria andMailPopAddressNotIn(List<String> values) {
            addCriterion("mail_pop_address not in", values, "mailPopAddress");
            return (Criteria) this;
        }

        public Criteria andMailPopAddressBetween(String value1, String value2) {
            addCriterion("mail_pop_address between", value1, value2, "mailPopAddress");
            return (Criteria) this;
        }

        public Criteria andMailPopAddressNotBetween(String value1, String value2) {
            addCriterion("mail_pop_address not between", value1, value2, "mailPopAddress");
            return (Criteria) this;
        }

        public Criteria andMailPopPortIsNull() {
            addCriterion("mail_pop_port is null");
            return (Criteria) this;
        }

        public Criteria andMailPopPortIsNotNull() {
            addCriterion("mail_pop_port is not null");
            return (Criteria) this;
        }

        public Criteria andMailPopPortEqualTo(String value) {
            addCriterion("mail_pop_port =", value, "mailPopPort");
            return (Criteria) this;
        }

        public Criteria andMailPopPortNotEqualTo(String value) {
            addCriterion("mail_pop_port <>", value, "mailPopPort");
            return (Criteria) this;
        }

        public Criteria andMailPopPortGreaterThan(String value) {
            addCriterion("mail_pop_port >", value, "mailPopPort");
            return (Criteria) this;
        }

        public Criteria andMailPopPortGreaterThanOrEqualTo(String value) {
            addCriterion("mail_pop_port >=", value, "mailPopPort");
            return (Criteria) this;
        }

        public Criteria andMailPopPortLessThan(String value) {
            addCriterion("mail_pop_port <", value, "mailPopPort");
            return (Criteria) this;
        }

        public Criteria andMailPopPortLessThanOrEqualTo(String value) {
            addCriterion("mail_pop_port <=", value, "mailPopPort");
            return (Criteria) this;
        }

        public Criteria andMailPopPortLike(String value) {
            addCriterion("mail_pop_port like", value, "mailPopPort");
            return (Criteria) this;
        }

        public Criteria andMailPopPortNotLike(String value) {
            addCriterion("mail_pop_port not like", value, "mailPopPort");
            return (Criteria) this;
        }

        public Criteria andMailPopPortIn(List<String> values) {
            addCriterion("mail_pop_port in", values, "mailPopPort");
            return (Criteria) this;
        }

        public Criteria andMailPopPortNotIn(List<String> values) {
            addCriterion("mail_pop_port not in", values, "mailPopPort");
            return (Criteria) this;
        }

        public Criteria andMailPopPortBetween(String value1, String value2) {
            addCriterion("mail_pop_port between", value1, value2, "mailPopPort");
            return (Criteria) this;
        }

        public Criteria andMailPopPortNotBetween(String value1, String value2) {
            addCriterion("mail_pop_port not between", value1, value2, "mailPopPort");
            return (Criteria) this;
        }

        public Criteria andMailSmtpAddressIsNull() {
            addCriterion("mail_smtp_address is null");
            return (Criteria) this;
        }

        public Criteria andMailSmtpAddressIsNotNull() {
            addCriterion("mail_smtp_address is not null");
            return (Criteria) this;
        }

        public Criteria andMailSmtpAddressEqualTo(String value) {
            addCriterion("mail_smtp_address =", value, "mailSmtpAddress");
            return (Criteria) this;
        }

        public Criteria andMailSmtpAddressNotEqualTo(String value) {
            addCriterion("mail_smtp_address <>", value, "mailSmtpAddress");
            return (Criteria) this;
        }

        public Criteria andMailSmtpAddressGreaterThan(String value) {
            addCriterion("mail_smtp_address >", value, "mailSmtpAddress");
            return (Criteria) this;
        }

        public Criteria andMailSmtpAddressGreaterThanOrEqualTo(String value) {
            addCriterion("mail_smtp_address >=", value, "mailSmtpAddress");
            return (Criteria) this;
        }

        public Criteria andMailSmtpAddressLessThan(String value) {
            addCriterion("mail_smtp_address <", value, "mailSmtpAddress");
            return (Criteria) this;
        }

        public Criteria andMailSmtpAddressLessThanOrEqualTo(String value) {
            addCriterion("mail_smtp_address <=", value, "mailSmtpAddress");
            return (Criteria) this;
        }

        public Criteria andMailSmtpAddressLike(String value) {
            addCriterion("mail_smtp_address like", value, "mailSmtpAddress");
            return (Criteria) this;
        }

        public Criteria andMailSmtpAddressNotLike(String value) {
            addCriterion("mail_smtp_address not like", value, "mailSmtpAddress");
            return (Criteria) this;
        }

        public Criteria andMailSmtpAddressIn(List<String> values) {
            addCriterion("mail_smtp_address in", values, "mailSmtpAddress");
            return (Criteria) this;
        }

        public Criteria andMailSmtpAddressNotIn(List<String> values) {
            addCriterion("mail_smtp_address not in", values, "mailSmtpAddress");
            return (Criteria) this;
        }

        public Criteria andMailSmtpAddressBetween(String value1, String value2) {
            addCriterion("mail_smtp_address between", value1, value2, "mailSmtpAddress");
            return (Criteria) this;
        }

        public Criteria andMailSmtpAddressNotBetween(String value1, String value2) {
            addCriterion("mail_smtp_address not between", value1, value2, "mailSmtpAddress");
            return (Criteria) this;
        }

        public Criteria andMailSmtpPortIsNull() {
            addCriterion("mail_smtp_port is null");
            return (Criteria) this;
        }

        public Criteria andMailSmtpPortIsNotNull() {
            addCriterion("mail_smtp_port is not null");
            return (Criteria) this;
        }

        public Criteria andMailSmtpPortEqualTo(String value) {
            addCriterion("mail_smtp_port =", value, "mailSmtpPort");
            return (Criteria) this;
        }

        public Criteria andMailSmtpPortNotEqualTo(String value) {
            addCriterion("mail_smtp_port <>", value, "mailSmtpPort");
            return (Criteria) this;
        }

        public Criteria andMailSmtpPortGreaterThan(String value) {
            addCriterion("mail_smtp_port >", value, "mailSmtpPort");
            return (Criteria) this;
        }

        public Criteria andMailSmtpPortGreaterThanOrEqualTo(String value) {
            addCriterion("mail_smtp_port >=", value, "mailSmtpPort");
            return (Criteria) this;
        }

        public Criteria andMailSmtpPortLessThan(String value) {
            addCriterion("mail_smtp_port <", value, "mailSmtpPort");
            return (Criteria) this;
        }

        public Criteria andMailSmtpPortLessThanOrEqualTo(String value) {
            addCriterion("mail_smtp_port <=", value, "mailSmtpPort");
            return (Criteria) this;
        }

        public Criteria andMailSmtpPortLike(String value) {
            addCriterion("mail_smtp_port like", value, "mailSmtpPort");
            return (Criteria) this;
        }

        public Criteria andMailSmtpPortNotLike(String value) {
            addCriterion("mail_smtp_port not like", value, "mailSmtpPort");
            return (Criteria) this;
        }

        public Criteria andMailSmtpPortIn(List<String> values) {
            addCriterion("mail_smtp_port in", values, "mailSmtpPort");
            return (Criteria) this;
        }

        public Criteria andMailSmtpPortNotIn(List<String> values) {
            addCriterion("mail_smtp_port not in", values, "mailSmtpPort");
            return (Criteria) this;
        }

        public Criteria andMailSmtpPortBetween(String value1, String value2) {
            addCriterion("mail_smtp_port between", value1, value2, "mailSmtpPort");
            return (Criteria) this;
        }

        public Criteria andMailSmtpPortNotBetween(String value1, String value2) {
            addCriterion("mail_smtp_port not between", value1, value2, "mailSmtpPort");
            return (Criteria) this;
        }
    }

    /**
     */
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