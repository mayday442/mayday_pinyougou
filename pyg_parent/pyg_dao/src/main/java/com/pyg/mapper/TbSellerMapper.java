package com.pyg.mapper;

import com.pyg.pojo.TbSeller;
import com.pyg.pojo.TbSellerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbSellerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seller
     *
     * @mbg.generated Wed Sep 19 15:49:32 CST 2018
     */
    long countByExample(TbSellerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seller
     *
     * @mbg.generated Wed Sep 19 15:49:32 CST 2018
     */
    int deleteByExample(TbSellerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seller
     *
     * @mbg.generated Wed Sep 19 15:49:32 CST 2018
     */
    int insert(TbSeller record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seller
     *
     * @mbg.generated Wed Sep 19 15:49:32 CST 2018
     */
    int insertSelective(TbSeller record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seller
     *
     * @mbg.generated Wed Sep 19 15:49:32 CST 2018
     */
    List<TbSeller> selectByExample(TbSellerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seller
     *
     * @mbg.generated Wed Sep 19 15:49:32 CST 2018
     */
    int updateByExampleSelective(@Param("record") TbSeller record, @Param("example") TbSellerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seller
     *
     * @mbg.generated Wed Sep 19 15:49:32 CST 2018
     */
    int updateByExample(@Param("record") TbSeller record, @Param("example") TbSellerExample example);
}