package com.pinyougou.mapper;

import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbSpecificationOptionMapper {
    long countByExample(TbSpecificationOptionExample example);

    int deleteByExample(TbSpecificationOptionExample example);

    int insert(TbSpecificationOption record);

    int insertSelective(TbSpecificationOption record);

    List<TbSpecificationOption> selectByExample(TbSpecificationOptionExample example);

    int updateByExampleSelective(@Param("record") TbSpecificationOption record, @Param("example") TbSpecificationOptionExample example);

    int updateByExample(@Param("record") TbSpecificationOption record, @Param("example") TbSpecificationOptionExample example);

    void updateByPrimaryKey(TbSpecificationOption specificationOption);

    TbSpecificationOption selectByPrimaryKey(Long id);

    void deleteByPrimaryKey(Long id);
}