package com.pinyougou.mapper;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.pojo.TbSellerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbSellerMapper {
    long countByExample(TbSellerExample example);

    int deleteByExample(TbSellerExample example);

    int insert(TbSeller record);

    int insertSelective(TbSeller record);

    List<TbSeller> selectByExample(TbSellerExample example);

    int updateByExampleSelective(@Param("record") TbSeller record, @Param("example") TbSellerExample example);

    int updateByExample(@Param("record") TbSeller record, @Param("example") TbSellerExample example);

    void updateByPrimaryKey(TbSeller seller);

    TbSeller selectByPrimaryKey(String id);

    void deleteByPrimaryKey(Long id);
}