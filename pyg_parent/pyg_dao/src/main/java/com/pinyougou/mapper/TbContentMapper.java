package com.pinyougou.mapper;

import com.pinyougou.pojo.TbContent;
import com.pinyougou.pojo.TbContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbContentMapper {
    long countByExample(TbContentExample example);

    int deleteByExample(TbContentExample example);

    int insert(TbContent record);

    int insertSelective(TbContent record);

    List<TbContent> selectByExample(TbContentExample example);

    int updateByExampleSelective(@Param("record") TbContent record, @Param("example") TbContentExample example);

    int updateByExample(@Param("record") TbContent record, @Param("example") TbContentExample example);

    void updateByPrimaryKey(TbContent content);

    TbContent selectByPrimaryKey(Long id);

    void deleteByPrimaryKey(Long id);
}