package com.pyg.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pyg.mapper.BrandMapper;

import com.pyg.pojo.TbBrand;
import com.pyg.service.BrandService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author mayday
 */

@Service
@MapperScan("com.pyg.mapper")
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<TbBrand> listAllBrand() {
        return brandMapper.listAllBrand();
    }
}
