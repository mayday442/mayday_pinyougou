package com.pyg.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.pyg.mapper.BrandMapper;
import com.pyg.entity.PageResult;
import com.pyg.pojo.TbBrand;
import com.pyg.service.BrandService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author mayday
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;



    @Override
    public PageResult<TbBrand> listAllBrand(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TbBrand> tbBrandList = brandMapper.listAllBrand();
        return new PageResult<>(tbBrandList);
    }

    @Override
    public void saveBrand(TbBrand brand) {
        brandMapper.saveBrand(brand);
    }

    @Override
    public void deleteBrands(long[] ids) {
        brandMapper.deleteBrands(ids);
    }

    @Override
    public TbBrand findBrandById(long id) {
        return brandMapper.findBrandById(id);
    }

    @Override
    public void updateBrand(TbBrand brand) {
        brandMapper.updateBrand(brand);
    }

    @Override
    public PageResult<TbBrand> searchBrandList(int pageNum, int pageSize, TbBrand searchBrand) {

        System.out.println(pageNum);
        System.out.println(pageSize);
        System.out.println(searchBrand);

        PageHelper.startPage(pageNum, pageSize);
        List<TbBrand> tbBrandList = brandMapper.searchBrandList(searchBrand);
        return new PageResult<>(tbBrandList);
    }
}
