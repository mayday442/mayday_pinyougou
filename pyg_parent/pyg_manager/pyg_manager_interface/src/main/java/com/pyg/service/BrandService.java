package com.pyg.service;



import com.pyg.pojo.TbBrand;

import java.util.List;

/**
 * @author mayday
 */
public interface BrandService {

    /**
     * 查询全部产品
     * @return
     */
    List<TbBrand> listAllBrand();

}
