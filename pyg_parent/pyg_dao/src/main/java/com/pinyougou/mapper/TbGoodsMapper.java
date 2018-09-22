package com.pinyougou.mapper;

import com.pinyougou.pojo.TbGoods;
import com.pinyougou.pojo.TbGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbGoodsMapper {
    long countByExample(TbGoodsExample example);

    int deleteByExample(TbGoodsExample example);

    int insert(TbGoods record);

    int insertSelective(TbGoods record);

    List<TbGoods> selectByExample(TbGoodsExample example);

    int updateByExampleSelective(@Param("record") TbGoods record, @Param("example") TbGoodsExample example);

    int updateByExample(@Param("record") TbGoods record, @Param("example") TbGoodsExample example);

    void updateByPrimaryKey(TbGoods goods);

    TbGoods selectByPrimaryKey(Long id);

    void deleteByPrimaryKey(Long id);
}