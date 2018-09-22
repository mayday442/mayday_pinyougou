package com.pinyougou.mapper;

import com.pinyougou.pojo.TbFreightTemplate;
import com.pinyougou.pojo.TbFreightTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbFreightTemplateMapper {
    long countByExample(TbFreightTemplateExample example);

    int deleteByExample(TbFreightTemplateExample example);

    int insert(TbFreightTemplate record);

    int insertSelective(TbFreightTemplate record);

    List<TbFreightTemplate> selectByExample(TbFreightTemplateExample example);

    int updateByExampleSelective(@Param("record") TbFreightTemplate record, @Param("example") TbFreightTemplateExample example);

    int updateByExample(@Param("record") TbFreightTemplate record, @Param("example") TbFreightTemplateExample example);

    void updateByPrimaryKey(TbFreightTemplate freightTemplate);

    TbFreightTemplate selectByPrimaryKey(Long id);

    void deleteByPrimaryKey(Long id);
}