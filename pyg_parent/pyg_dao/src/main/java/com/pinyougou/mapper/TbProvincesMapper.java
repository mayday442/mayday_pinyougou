package com.pinyougou.mapper;

import com.pinyougou.pojo.TbProvinces;
import com.pinyougou.pojo.TbProvincesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbProvincesMapper {
    long countByExample(TbProvincesExample example);

    int deleteByExample(TbProvincesExample example);

    int insert(TbProvinces record);

    int insertSelective(TbProvinces record);

    List<TbProvinces> selectByExample(TbProvincesExample example);

    int updateByExampleSelective(@Param("record") TbProvinces record, @Param("example") TbProvincesExample example);

    int updateByExample(@Param("record") TbProvinces record, @Param("example") TbProvincesExample example);

    void updateByPrimaryKey(TbProvinces provinces);

    TbProvinces selectByPrimaryKey(Long id);

    void deleteByPrimaryKey(Long id);
}