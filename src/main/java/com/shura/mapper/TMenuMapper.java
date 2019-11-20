package com.shura.mapper;

import com.shura.entity.TMenu;
import com.shura.entity.TMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TMenuMapper {
    long countByExample(TMenuExample example);

    int deleteByExample(TMenuExample example);

    int deleteByPrimaryKey(Integer menuId);

    int insert(TMenu record);

    int insertSelective(TMenu record);

    List<TMenu> selectByExample(TMenuExample example);

    TMenu selectByPrimaryKey(Integer menuId);

    int updateByExampleSelective(@Param("record") TMenu record, @Param("example") TMenuExample example);

    int updateByExample(@Param("record") TMenu record, @Param("example") TMenuExample example);

    int updateByPrimaryKeySelective(TMenu record);

    int updateByPrimaryKey(TMenu record);
}
