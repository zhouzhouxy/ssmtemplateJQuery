package com.shura.mapper;

import com.shura.entity.TPlan;

import java.util.List;

public interface PlanMapper {
    List<TPlan> queryPlanByTaskId(Integer taskId);
}
