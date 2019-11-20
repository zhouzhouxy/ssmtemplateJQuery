package com.shura.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TPlanExample() {
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

        public Criteria andPlanIdIsNull() {
            addCriterion("plan_id is null");
            return (Criteria) this;
        }

        public Criteria andPlanIdIsNotNull() {
            addCriterion("plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlanIdEqualTo(Integer value) {
            addCriterion("plan_id =", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotEqualTo(Integer value) {
            addCriterion("plan_id <>", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdGreaterThan(Integer value) {
            addCriterion("plan_id >", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("plan_id >=", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLessThan(Integer value) {
            addCriterion("plan_id <", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLessThanOrEqualTo(Integer value) {
            addCriterion("plan_id <=", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdIn(List<Integer> values) {
            addCriterion("plan_id in", values, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotIn(List<Integer> values) {
            addCriterion("plan_id not in", values, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdBetween(Integer value1, Integer value2) {
            addCriterion("plan_id between", value1, value2, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotBetween(Integer value1, Integer value2) {
            addCriterion("plan_id not between", value1, value2, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanNameIsNull() {
            addCriterion("plan_name is null");
            return (Criteria) this;
        }

        public Criteria andPlanNameIsNotNull() {
            addCriterion("plan_name is not null");
            return (Criteria) this;
        }

        public Criteria andPlanNameEqualTo(String value) {
            addCriterion("plan_name =", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotEqualTo(String value) {
            addCriterion("plan_name <>", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameGreaterThan(String value) {
            addCriterion("plan_name >", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameGreaterThanOrEqualTo(String value) {
            addCriterion("plan_name >=", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLessThan(String value) {
            addCriterion("plan_name <", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLessThanOrEqualTo(String value) {
            addCriterion("plan_name <=", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLike(String value) {
            addCriterion("plan_name like", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotLike(String value) {
            addCriterion("plan_name not like", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameIn(List<String> values) {
            addCriterion("plan_name in", values, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotIn(List<String> values) {
            addCriterion("plan_name not in", values, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameBetween(String value1, String value2) {
            addCriterion("plan_name between", value1, value2, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotBetween(String value1, String value2) {
            addCriterion("plan_name not between", value1, value2, "planName");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andIsFeedbackIsNull() {
            addCriterion("is_feedback is null");
            return (Criteria) this;
        }

        public Criteria andIsFeedbackIsNotNull() {
            addCriterion("is_feedback is not null");
            return (Criteria) this;
        }

        public Criteria andIsFeedbackEqualTo(String value) {
            addCriterion("is_feedback =", value, "isFeedback");
            return (Criteria) this;
        }

        public Criteria andIsFeedbackNotEqualTo(String value) {
            addCriterion("is_feedback <>", value, "isFeedback");
            return (Criteria) this;
        }

        public Criteria andIsFeedbackGreaterThan(String value) {
            addCriterion("is_feedback >", value, "isFeedback");
            return (Criteria) this;
        }

        public Criteria andIsFeedbackGreaterThanOrEqualTo(String value) {
            addCriterion("is_feedback >=", value, "isFeedback");
            return (Criteria) this;
        }

        public Criteria andIsFeedbackLessThan(String value) {
            addCriterion("is_feedback <", value, "isFeedback");
            return (Criteria) this;
        }

        public Criteria andIsFeedbackLessThanOrEqualTo(String value) {
            addCriterion("is_feedback <=", value, "isFeedback");
            return (Criteria) this;
        }

        public Criteria andIsFeedbackLike(String value) {
            addCriterion("is_feedback like", value, "isFeedback");
            return (Criteria) this;
        }

        public Criteria andIsFeedbackNotLike(String value) {
            addCriterion("is_feedback not like", value, "isFeedback");
            return (Criteria) this;
        }

        public Criteria andIsFeedbackIn(List<String> values) {
            addCriterion("is_feedback in", values, "isFeedback");
            return (Criteria) this;
        }

        public Criteria andIsFeedbackNotIn(List<String> values) {
            addCriterion("is_feedback not in", values, "isFeedback");
            return (Criteria) this;
        }

        public Criteria andIsFeedbackBetween(String value1, String value2) {
            addCriterion("is_feedback between", value1, value2, "isFeedback");
            return (Criteria) this;
        }

        public Criteria andIsFeedbackNotBetween(String value1, String value2) {
            addCriterion("is_feedback not between", value1, value2, "isFeedback");
            return (Criteria) this;
        }

        public Criteria andBeginDateIsNull() {
            addCriterion("begin_date is null");
            return (Criteria) this;
        }

        public Criteria andBeginDateIsNotNull() {
            addCriterion("begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andBeginDateEqualTo(Date value) {
            addCriterion("begin_date =", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotEqualTo(Date value) {
            addCriterion("begin_date <>", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateGreaterThan(Date value) {
            addCriterion("begin_date >", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("begin_date >=", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLessThan(Date value) {
            addCriterion("begin_date <", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLessThanOrEqualTo(Date value) {
            addCriterion("begin_date <=", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateIn(List<Date> values) {
            addCriterion("begin_date in", values, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotIn(List<Date> values) {
            addCriterion("begin_date not in", values, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateBetween(Date value1, Date value2) {
            addCriterion("begin_date between", value1, value2, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotBetween(Date value1, Date value2) {
            addCriterion("begin_date not between", value1, value2, "beginDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterion("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterion("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterion("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterion("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterion("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterion("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterion("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterion("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterion("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("task_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("task_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(Integer value) {
            addCriterion("task_id =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(Integer value) {
            addCriterion("task_id <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(Integer value) {
            addCriterion("task_id >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_id >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(Integer value) {
            addCriterion("task_id <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(Integer value) {
            addCriterion("task_id <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<Integer> values) {
            addCriterion("task_id in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<Integer> values) {
            addCriterion("task_id not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(Integer value1, Integer value2) {
            addCriterion("task_id between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(Integer value1, Integer value2) {
            addCriterion("task_id not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoIsNull() {
            addCriterion("feedback_info is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoIsNotNull() {
            addCriterion("feedback_info is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoEqualTo(String value) {
            addCriterion("feedback_info =", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoNotEqualTo(String value) {
            addCriterion("feedback_info <>", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoGreaterThan(String value) {
            addCriterion("feedback_info >", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoGreaterThanOrEqualTo(String value) {
            addCriterion("feedback_info >=", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoLessThan(String value) {
            addCriterion("feedback_info <", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoLessThanOrEqualTo(String value) {
            addCriterion("feedback_info <=", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoLike(String value) {
            addCriterion("feedback_info like", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoNotLike(String value) {
            addCriterion("feedback_info not like", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoIn(List<String> values) {
            addCriterion("feedback_info in", values, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoNotIn(List<String> values) {
            addCriterion("feedback_info not in", values, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoBetween(String value1, String value2) {
            addCriterion("feedback_info between", value1, value2, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoNotBetween(String value1, String value2) {
            addCriterion("feedback_info not between", value1, value2, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andPlanDescIsNull() {
            addCriterion("plan_desc is null");
            return (Criteria) this;
        }

        public Criteria andPlanDescIsNotNull() {
            addCriterion("plan_desc is not null");
            return (Criteria) this;
        }

        public Criteria andPlanDescEqualTo(String value) {
            addCriterion("plan_desc =", value, "planDesc");
            return (Criteria) this;
        }

        public Criteria andPlanDescNotEqualTo(String value) {
            addCriterion("plan_desc <>", value, "planDesc");
            return (Criteria) this;
        }

        public Criteria andPlanDescGreaterThan(String value) {
            addCriterion("plan_desc >", value, "planDesc");
            return (Criteria) this;
        }

        public Criteria andPlanDescGreaterThanOrEqualTo(String value) {
            addCriterion("plan_desc >=", value, "planDesc");
            return (Criteria) this;
        }

        public Criteria andPlanDescLessThan(String value) {
            addCriterion("plan_desc <", value, "planDesc");
            return (Criteria) this;
        }

        public Criteria andPlanDescLessThanOrEqualTo(String value) {
            addCriterion("plan_desc <=", value, "planDesc");
            return (Criteria) this;
        }

        public Criteria andPlanDescLike(String value) {
            addCriterion("plan_desc like", value, "planDesc");
            return (Criteria) this;
        }

        public Criteria andPlanDescNotLike(String value) {
            addCriterion("plan_desc not like", value, "planDesc");
            return (Criteria) this;
        }

        public Criteria andPlanDescIn(List<String> values) {
            addCriterion("plan_desc in", values, "planDesc");
            return (Criteria) this;
        }

        public Criteria andPlanDescNotIn(List<String> values) {
            addCriterion("plan_desc not in", values, "planDesc");
            return (Criteria) this;
        }

        public Criteria andPlanDescBetween(String value1, String value2) {
            addCriterion("plan_desc between", value1, value2, "planDesc");
            return (Criteria) this;
        }

        public Criteria andPlanDescNotBetween(String value1, String value2) {
            addCriterion("plan_desc not between", value1, value2, "planDesc");
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