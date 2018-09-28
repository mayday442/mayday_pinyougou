package com.pinyougou.manager.service;



import entity.PageResult;
import com.pinyougou.pojo.TbBrand;

import java.util.List;
import java.util.Map;

/**
 * @author mayday
 */
public interface BrandService {

    /**
     * 分页查询查询全部产品
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult<Map> listAllBrand(int pageNum, int pageSize);

    /**
     * 保存品牌的方法
     * @param brand
     * @return
     */
    void saveBrand(TbBrand brand);

    /**
     * 根据 id 删除品牌
     * @param ids
     */
    void deleteBrands(long[] ids);

    /**
     * 根据 id 查询单个品牌
     * @param id
     * @return
     */
    TbBrand findBrandById(long id);

    /**
     * 修改品牌
     * @param brand
     */
    void updateBrand(TbBrand brand);

    /**
     * 根据条件分页查询品牌
     * @param pageNum
     * @param pageSize
     * @param searchBrand
     * @return
     */
    PageResult<TbBrand> searchBrandList(int pageNum, int pageSize, TbBrand searchBrand);

    /**
     * 查询全部品牌
     * @return
     */
    List<Map> findAllBrand();

}
