package com.pinyougou.mapper;

import com.pinyougou.pojo.TbGoodsDesc;
import com.pinyougou.pojo.TbGoodsDescExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbGoodsDescMapper {
    long countByExample(TbGoodsDescExample example);

    int deleteByExample(TbGoodsDescExample example);

    int insert(TbGoodsDesc record);

    int insertSelective(TbGoodsDesc record);

    List<TbGoodsDesc> selectByExample(TbGoodsDescExample example);

    int updateByExampleSelective(@Param("record") TbGoodsDesc record, @Param("example") TbGoodsDescExample example);

    int updateByExample(@Param("record") TbGoodsDesc record, @Param("example") TbGoodsDescExample example);

    void updateByPrimaryKey(TbGoodsDesc goodsDesc);

    TbGoodsDesc selectByPrimaryKey(Long id);

    void deleteByPrimaryKey(Long id);
}