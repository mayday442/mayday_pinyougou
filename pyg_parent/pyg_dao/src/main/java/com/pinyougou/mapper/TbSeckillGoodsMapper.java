package com.pinyougou.mapper;

import com.pinyougou.pojo.TbSeckillGoods;
import com.pinyougou.pojo.TbSeckillGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbSeckillGoodsMapper {
    long countByExample(TbSeckillGoodsExample example);

    int deleteByExample(TbSeckillGoodsExample example);

    int insert(TbSeckillGoods record);

    int insertSelective(TbSeckillGoods record);

    List<TbSeckillGoods> selectByExample(TbSeckillGoodsExample example);

    int updateByExampleSelective(@Param("record") TbSeckillGoods record, @Param("example") TbSeckillGoodsExample example);

    int updateByExample(@Param("record") TbSeckillGoods record, @Param("example") TbSeckillGoodsExample example);

    void updateByPrimaryKey(TbSeckillGoods seckillGoods);

    TbSeckillGoods selectByPrimaryKey(Long id);

    void deleteByPrimaryKey(Long id);
}