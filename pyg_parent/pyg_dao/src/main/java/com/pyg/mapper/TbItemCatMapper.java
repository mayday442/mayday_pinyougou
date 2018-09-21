package com.pyg.mapper;

import com.pyg.pojo.TbItemCat;
import com.pyg.pojo.TbItemCatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbItemCatMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_cat
     *
     * @mbg.generated Wed Sep 19 15:49:32 CST 2018
     */
    long countByExample(TbItemCatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_cat
     *
     * @mbg.generated Wed Sep 19 15:49:32 CST 2018
     */
    int deleteByExample(TbItemCatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_cat
     *
     * @mbg.generated Wed Sep 19 15:49:32 CST 2018
     */
    int insert(TbItemCat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_cat
     *
     * @mbg.generated Wed Sep 19 15:49:32 CST 2018
     */
    int insertSelective(TbItemCat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_cat
     *
     * @mbg.generated Wed Sep 19 15:49:32 CST 2018
     */
    List<TbItemCat> selectByExample(TbItemCatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_cat
     *
     * @mbg.generated Wed Sep 19 15:49:32 CST 2018
     */
    int updateByExampleSelective(@Param("record") TbItemCat record, @Param("example") TbItemCatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_cat
     *
     * @mbg.generated Wed Sep 19 15:49:32 CST 2018
     */
    int updateByExample(@Param("record") TbItemCat record, @Param("example") TbItemCatExample example);
}