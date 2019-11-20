package com.shura.mapper;

import com.shura.entity.TEmployee;
import com.shura.entity.TEmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TEmployeeMapper {
    long countByExample(TEmployeeExample example);

    int deleteByExample(TEmployeeExample example);

    int deleteByPrimaryKey(Integer employeeId);

    int insert(TEmployee record);

    int insertSelective(TEmployee record);

    List<TEmployee> selectByExample(TEmployeeExample example);

    TEmployee selectByPrimaryKey(Integer employeeId);

    int updateByExampleSelective(@Param("record") TEmployee record, @Param("example") TEmployeeExample example);

    int updateByExample(@Param("record") TEmployee record, @Param("example") TEmployeeExample example);

    int updateByPrimaryKeySelective(TEmployee record);

    int updateByPrimaryKey(TEmployee record);

    TEmployee selectByEmployee(TEmployee employee);
}
