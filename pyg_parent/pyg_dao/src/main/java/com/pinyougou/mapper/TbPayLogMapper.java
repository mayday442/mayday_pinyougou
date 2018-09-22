package com.pinyougou.mapper;

import com.pinyougou.pojo.TbPayLog;
import com.pinyougou.pojo.TbPayLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbPayLogMapper {
    long countByExample(TbPayLogExample example);

    int deleteByExample(TbPayLogExample example);

    int insert(TbPayLog record);

    int insertSelective(TbPayLog record);

    List<TbPayLog> selectByExample(TbPayLogExample example);

    int updateByExampleSelective(@Param("record") TbPayLog record, @Param("example") TbPayLogExample example);

    int updateByExample(@Param("record") TbPayLog record, @Param("example") TbPayLogExample example);

    void updateByPrimaryKey(TbPayLog payLog);

    TbPayLog selectByPrimaryKey(Long id);

    void deleteByPrimaryKey(Long id);
}