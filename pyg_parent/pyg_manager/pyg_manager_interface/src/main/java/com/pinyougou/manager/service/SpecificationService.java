package com.pinyougou.manager.service;

import entity.PageResult;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationOption;

import java.util.List;
import java.util.Map;

/**
 * @author mayday
 */
public interface SpecificationService {


    /**
     * 分页查询所有规格, 当 spec 不为 null 时,则条件分页查询规格
     * @param pageNum
     * @param pageSize
     * @param spec
     * @return
     */
    PageResult<TbSpecification> searchSpecificationList(int pageNum, int pageSize, TbSpecification spec);

    /**
     * 根据 spec id 查询拥有规格
     * @param id
     * @return
     */
    List<TbSpecificationOption> findSpecificationById(long id);

    /**
     * 存储规格和对应的规格选项
     * @param specification
     */
    void saveSpecification(TbSpecification specification);

    /**
     * 删除规格的方法
     * @param ids
     */
    void deleteSpecification(Long[] ids);

    /**
     * 修改规格的方法
     * @param specification
     */
    void updateSpecification(TbSpecification specification);

    /**
     * 查询所有 specification
     * @return
     */
    List<Map> findAll();

}
