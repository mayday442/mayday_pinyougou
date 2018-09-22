package com.pinyougou.mapper;

import com.pinyougou.pojo.TbOrder;
import com.pinyougou.pojo.TbOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbOrderMapper {
    long countByExample(TbOrderExample example);

    int deleteByExample(TbOrderExample example);

    int insert(TbOrder record);

    int insertSelective(TbOrder record);

    List<TbOrder> selectByExample(TbOrderExample example);

    int updateByExampleSelective(@Param("record") TbOrder record, @Param("example") TbOrderExample example);

    int updateByExample(@Param("record") TbOrder record, @Param("example") TbOrderExample example);

    void updateByPrimaryKey(TbOrder order);

    TbOrder selectByPrimaryKey(Long id);

    void deleteByPrimaryKey(Long id);
}