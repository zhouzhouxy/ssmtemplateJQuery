package com.shura.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TTaskExample() {
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

        public Criteria andTaskNameIsNull() {
            addCriterion("task_name is null");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNotNull() {
            addCriterion("task_name is not null");
            return (Criteria) this;
        }

        public Criteria andTaskNameEqualTo(String value) {
            addCriterion("task_name =", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotEqualTo(String value) {
            addCriterion("task_name <>", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThan(String value) {
            addCriterion("task_name >", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThanOrEqualTo(String value) {
            addCriterion("task_name >=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThan(String value) {
            addCriterion("task_name <", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThanOrEqualTo(String value) {
            addCriterion("task_name <=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLike(String value) {
            addCriterion("task_name like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotLike(String value) {
            addCriterion("task_name not like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameIn(List<String> values) {
            addCriterion("task_name in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotIn(List<String> values) {
            addCriterion("task_name not in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameBetween(String value1, String value2) {
            addCriterion("task_name between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotBetween(String value1, String value2) {
            addCriterion("task_name not between", value1, value2, "taskName");
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

        public Criteria andRealBeginDateIsNull() {
            addCriterion("real_begin_date is null");
            return (Criteria) this;
        }

        public Criteria andRealBeginDateIsNotNull() {
            addCriterion("real_begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andRealBeginDateEqualTo(Date value) {
            addCriterion("real_begin_date =", value, "realBeginDate");
            return (Criteria) this;
        }

        public Criteria andRealBeginDateNotEqualTo(Date value) {
            addCriterion("real_begin_date <>", value, "realBeginDate");
            return (Criteria) this;
        }

        public Criteria andRealBeginDateGreaterThan(Date value) {
            addCriterion("real_begin_date >", value, "realBeginDate");
            return (Criteria) this;
        }

        public Criteria andRealBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("real_begin_date >=", value, "realBeginDate");
            return (Criteria) this;
        }

        public Criteria andRealBeginDateLessThan(Date value) {
            addCriterion("real_begin_date <", value, "realBeginDate");
            return (Criteria) this;
        }

        public Criteria andRealBeginDateLessThanOrEqualTo(Date value) {
            addCriterion("real_begin_date <=", value, "realBeginDate");
            return (Criteria) this;
        }

        public Criteria andRealBeginDateIn(List<Date> values) {
            addCriterion("real_begin_date in", values, "realBeginDate");
            return (Criteria) this;
        }

        public Criteria andRealBeginDateNotIn(List<Date> values) {
            addCriterion("real_begin_date not in", values, "realBeginDate");
            return (Criteria) this;
        }

        public Criteria andRealBeginDateBetween(Date value1, Date value2) {
            addCriterion("real_begin_date between", value1, value2, "realBeginDate");
            return (Criteria) this;
        }

        public Criteria andRealBeginDateNotBetween(Date value1, Date value2) {
            addCriterion("real_begin_date not between", value1, value2, "realBeginDate");
            return (Criteria) this;
        }

        public Criteria andRealEndDateIsNull() {
            addCriterion("real_end_date is null");
            return (Criteria) this;
        }

        public Criteria andRealEndDateIsNotNull() {
            addCriterion("real_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andRealEndDateEqualTo(Date value) {
            addCriterion("real_end_date =", value, "realEndDate");
            return (Criteria) this;
        }

        public Criteria andRealEndDateNotEqualTo(Date value) {
            addCriterion("real_end_date <>", value, "realEndDate");
            return (Criteria) this;
        }

        public Criteria andRealEndDateGreaterThan(Date value) {
            addCriterion("real_end_date >", value, "realEndDate");
            return (Criteria) this;
        }

        public Criteria andRealEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("real_end_date >=", value, "realEndDate");
            return (Criteria) this;
        }

        public Criteria andRealEndDateLessThan(Date value) {
            addCriterion("real_end_date <", value, "realEndDate");
            return (Criteria) this;
        }

        public Criteria andRealEndDateLessThanOrEqualTo(Date value) {
            addCriterion("real_end_date <=", value, "realEndDate");
            return (Criteria) this;
        }

        public Criteria andRealEndDateIn(List<Date> values) {
            addCriterion("real_end_date in", values, "realEndDate");
            return (Criteria) this;
        }

        public Criteria andRealEndDateNotIn(List<Date> values) {
            addCriterion("real_end_date not in", values, "realEndDate");
            return (Criteria) this;
        }

        public Criteria andRealEndDateBetween(Date value1, Date value2) {
            addCriterion("real_end_date between", value1, value2, "realEndDate");
            return (Criteria) this;
        }

        public Criteria andRealEndDateNotBetween(Date value1, Date value2) {
            addCriterion("real_end_date not between", value1, value2, "realEndDate");
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

        public Criteria andImplementorIdIsNull() {
            addCriterion("implementor_id is null");
            return (Criteria) this;
        }

        public Criteria andImplementorIdIsNotNull() {
            addCriterion("implementor_id is not null");
            return (Criteria) this;
        }

        public Criteria andImplementorIdEqualTo(Integer value) {
            addCriterion("implementor_id =", value, "implementorId");
            return (Criteria) this;
        }

        public Criteria andImplementorIdNotEqualTo(Integer value) {
            addCriterion("implementor_id <>", value, "implementorId");
            return (Criteria) this;
        }

        public Criteria andImplementorIdGreaterThan(Integer value) {
            addCriterion("implementor_id >", value, "implementorId");
            return (Criteria) this;
        }

        public Criteria andImplementorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("implementor_id >=", value, "implementorId");
            return (Criteria) this;
        }

        public Criteria andImplementorIdLessThan(Integer value) {
            addCriterion("implementor_id <", value, "implementorId");
            return (Criteria) this;
        }

        public Criteria andImplementorIdLessThanOrEqualTo(Integer value) {
            addCriterion("implementor_id <=", value, "implementorId");
            return (Criteria) this;
        }

        public Criteria andImplementorIdIn(List<Integer> values) {
            addCriterion("implementor_id in", values, "implementorId");
            return (Criteria) this;
        }

        public Criteria andImplementorIdNotIn(List<Integer> values) {
            addCriterion("implementor_id not in", values, "implementorId");
            return (Criteria) this;
        }

        public Criteria andImplementorIdBetween(Integer value1, Integer value2) {
            addCriterion("implementor_id between", value1, value2, "implementorId");
            return (Criteria) this;
        }

        public Criteria andImplementorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("implementor_id not between", value1, value2, "implementorId");
            return (Criteria) this;
        }

        public Criteria andAssignerIdIsNull() {
            addCriterion("assigner_id is null");
            return (Criteria) this;
        }

        public Criteria andAssignerIdIsNotNull() {
            addCriterion("assigner_id is not null");
            return (Criteria) this;
        }

        public Criteria andAssignerIdEqualTo(Integer value) {
            addCriterion("assigner_id =", value, "assignerId");
            return (Criteria) this;
        }

        public Criteria andAssignerIdNotEqualTo(Integer value) {
            addCriterion("assigner_id <>", value, "assignerId");
            return (Criteria) this;
        }

        public Criteria andAssignerIdGreaterThan(Integer value) {
            addCriterion("assigner_id >", value, "assignerId");
            return (Criteria) this;
        }

        public Criteria andAssignerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("assigner_id >=", value, "assignerId");
            return (Criteria) this;
        }

        public Criteria andAssignerIdLessThan(Integer value) {
            addCriterion("assigner_id <", value, "assignerId");
            return (Criteria) this;
        }

        public Criteria andAssignerIdLessThanOrEqualTo(Integer value) {
            addCriterion("assigner_id <=", value, "assignerId");
            return (Criteria) this;
        }

        public Criteria andAssignerIdIn(List<Integer> values) {
            addCriterion("assigner_id in", values, "assignerId");
            return (Criteria) this;
        }

        public Criteria andAssignerIdNotIn(List<Integer> values) {
            addCriterion("assigner_id not in", values, "assignerId");
            return (Criteria) this;
        }

        public Criteria andAssignerIdBetween(Integer value1, Integer value2) {
            addCriterion("assigner_id between", value1, value2, "assignerId");
            return (Criteria) this;
        }

        public Criteria andAssignerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("assigner_id not between", value1, value2, "assignerId");
            return (Criteria) this;
        }

        public Criteria andTaskDescIsNull() {
            addCriterion("task_desc is null");
            return (Criteria) this;
        }

        public Criteria andTaskDescIsNotNull() {
            addCriterion("task_desc is not null");
            return (Criteria) this;
        }

        public Criteria andTaskDescEqualTo(String value) {
            addCriterion("task_desc =", value, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskDescNotEqualTo(String value) {
            addCriterion("task_desc <>", value, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskDescGreaterThan(String value) {
            addCriterion("task_desc >", value, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskDescGreaterThanOrEqualTo(String value) {
            addCriterion("task_desc >=", value, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskDescLessThan(String value) {
            addCriterion("task_desc <", value, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskDescLessThanOrEqualTo(String value) {
            addCriterion("task_desc <=", value, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskDescLike(String value) {
            addCriterion("task_desc like", value, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskDescNotLike(String value) {
            addCriterion("task_desc not like", value, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskDescIn(List<String> values) {
            addCriterion("task_desc in", values, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskDescNotIn(List<String> values) {
            addCriterion("task_desc not in", values, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskDescBetween(String value1, String value2) {
            addCriterion("task_desc between", value1, value2, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskDescNotBetween(String value1, String value2) {
            addCriterion("task_desc not between", value1, value2, "taskDesc");
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