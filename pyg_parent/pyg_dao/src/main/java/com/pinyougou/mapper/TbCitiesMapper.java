package com.pinyougou.mapper;

import com.pinyougou.pojo.TbCities;
import com.pinyougou.pojo.TbCitiesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCitiesMapper {
    long countByExample(TbCitiesExample example);

    int deleteByExample(TbCitiesExample example);

    int insert(TbCities record);

    int insertSelective(TbCities record);

    List<TbCities> selectByExample(TbCitiesExample example);

    int updateByExampleSelective(@Param("record") TbCities record, @Param("example") TbCitiesExample example);

    int updateByExample(@Param("record") TbCities record, @Param("example") TbCitiesExample example);

    void updateByPrimaryKey(TbCities cities);

    TbCities selectByPrimaryKey(Long id);

    void deleteByPrimaryKey(Long id);
}