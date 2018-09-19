package com.pyg.mapper;

import com.pyg.pojo.TbSpecification;
import com.pyg.pojo.TbSpecificationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbSpecificationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_specification
     *
     * @mbg.generated Wed Sep 19 15:49:32 CST 2018
     */
    long countByExample(TbSpecificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_specification
     *
     * @mbg.generated Wed Sep 19 15:49:32 CST 2018
     */
    int deleteByExample(TbSpecificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_specification
     *
     * @mbg.generated Wed Sep 19 15:49:32 CST 2018
     */
    int insert(TbSpecification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_specification
     *
     * @mbg.generated Wed Sep 19 15:49:32 CST 2018
     */
    int insertSelective(TbSpecification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_specification
     *
     * @mbg.generated Wed Sep 19 15:49:32 CST 2018
     */
    List<TbSpecification> selectByExample(TbSpecificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_specification
     *
     * @mbg.generated Wed Sep 19 15:49:32 CST 2018
     */
    int updateByExampleSelective(@Param("record") TbSpecification record, @Param("example") TbSpecificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_specification
     *
     * @mbg.generated Wed Sep 19 15:49:32 CST 2018
     */
    int updateByExample(@Param("record") TbSpecification record, @Param("example") TbSpecificationExample example);
}