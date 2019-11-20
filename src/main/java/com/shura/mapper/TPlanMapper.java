package com.shura.mapper;

import com.shura.entity.TPlan;
import com.shura.entity.TPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPlanMapper {
    long countByExample(TPlanExample example);

    int deleteByExample(TPlanExample example);

    int deleteByPrimaryKey(Integer planId);

    int insert(TPlan record);

    int insertSelective(TPlan record);

    List<TPlan> selectByExample(TPlanExample example);

    TPlan selectByPrimaryKey(Integer planId);

    int updateByExampleSelective(@Param("record") TPlan record, @Param("example") TPlanExample example);

    int updateByExample(@Param("record") TPlan record, @Param("example") TPlanExample example);

    int updateByPrimaryKeySelective(TPlan record);

    int updateByPrimaryKey(TPlan record);
}
