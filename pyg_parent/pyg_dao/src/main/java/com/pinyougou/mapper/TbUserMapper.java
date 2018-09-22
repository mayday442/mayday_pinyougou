package com.pinyougou.mapper;

import com.pinyougou.pojo.TbUser;
import com.pinyougou.pojo.TbUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbUserMapper {
    long countByExample(TbUserExample example);

    int deleteByExample(TbUserExample example);

    int insert(TbUser record);

    int insertSelective(TbUser record);

    List<TbUser> selectByExample(TbUserExample example);

    int updateByExampleSelective(@Param("record") TbUser record, @Param("example") TbUserExample example);

    int updateByExample(@Param("record") TbUser record, @Param("example") TbUserExample example);

    void updateByPrimaryKey(TbUser user);

    TbUser selectByPrimaryKey(Long id);

    void deleteByPrimaryKey(Long id);
}