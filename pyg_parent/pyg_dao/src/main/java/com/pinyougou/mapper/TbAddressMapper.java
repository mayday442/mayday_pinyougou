package com.pinyougou.mapper;

import com.pinyougou.pojo.TbAddress;
import com.pinyougou.pojo.TbAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbAddressMapper {
    long countByExample(TbAddressExample example);

    int deleteByExample(TbAddressExample example);

    int insert(TbAddress record);

    int insertSelective(TbAddress record);

    List<TbAddress> selectByExample(TbAddressExample example);

    int updateByExampleSelective(@Param("record") TbAddress record, @Param("example") TbAddressExample example);

    int updateByExample(@Param("record") TbAddress record, @Param("example") TbAddressExample example);

    void updateByPrimaryKey(TbAddress address);

    TbAddress selectByPrimaryKey(Long id);

    void deleteByPrimaryKey(Long id);
}