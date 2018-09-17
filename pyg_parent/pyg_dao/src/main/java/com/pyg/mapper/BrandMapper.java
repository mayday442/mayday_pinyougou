package com.pyg.mapper;

import com.pyg.pojo.TbBrand;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author mayday
 */

public interface BrandMapper {
    /**
     * 查询所有品牌
     * @return
     */
    @Select("select * from tb_brand")
    @Results({
            @Result(property = "firstChar", column = "first_char")
    })
    List<TbBrand> listAllBrand();

}
