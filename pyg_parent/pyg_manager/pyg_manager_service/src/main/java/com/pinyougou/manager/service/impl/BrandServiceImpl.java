package com.pinyougou.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.BrandMapper;
import entity.PageResult;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.manager.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author mayday
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;



    @Override
    public PageResult<Map> listAllBrand(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map> tbBrandList = brandMapper.listAllBrand();
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
        PageHelper.startPage(pageNum, pageSize);
        List<TbBrand> tbBrandList = brandMapper.searchBrandList(searchBrand);
        return new PageResult<>(tbBrandList);
    }

    @Override
    public List<Map> findAllBrand() {
        return brandMapper.listAllBrand();
    }
}
