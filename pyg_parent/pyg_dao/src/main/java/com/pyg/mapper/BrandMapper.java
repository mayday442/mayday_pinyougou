package com.pyg.mapper;

import com.pyg.pojo.TbBrand;
import org.apache.ibatis.annotations.*;

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

    /**
     * 保存品牌的方法
     * @param brand
     */
    @Insert("insert into tb_brand values (null, #{name}, #{firstChar})")
    void saveBrand(TbBrand brand);

    /**
     * 根据 id 删除多个品牌, 在 xml 文件中完成
     * @param ids
     */
    void deleteBrands(long[] ids);

    /**
     * 根据 id 查询单个品牌
     * @param id
     * @return
     */
    @Select("select * from tb_brand where id = #{id}")
    @Results({
            @Result(property = "firstChar", column = "first_char")
    })
    TbBrand findBrandById(long id);

    /**
     * 修改品牌
     * @param brand
     */
    @Update("update tb_brand set name = #{name}, first_char = #{firstChar} where id = #{id}")
    void updateBrand(TbBrand brand);

    /**
     * 根据条件查询品牌,在 xml 中完成
     * @param searchBrand
     * @return
     */
    List<TbBrand> searchBrandList(TbBrand searchBrand);

}
