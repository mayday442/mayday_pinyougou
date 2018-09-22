package com.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.ResultBean;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.service.BrandService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author mayday
 */

@RestController
@RequestMapping("brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    @RequestMapping("searchBrandList")
    public PageResult<TbBrand> searchBrandList(int pageNum,
                                               int pageSize,
                                               @RequestBody TbBrand searchBrand) {

        return brandService.searchBrandList(pageNum, pageSize, searchBrand);
    }

    @RequestMapping("saveBrand")
    public ResultBean saveBrand(@RequestBody TbBrand brand) {

        try {
            brandService.saveBrand(brand);
            return new ResultBean(true);
        }catch (Exception e) {
            return new ResultBean(false, "新建品牌失败");
        }
    }

    @RequestMapping("deleteBrands")
    public ResultBean deleteBrands(long[] ids) {
        brandService.deleteBrands(ids);
        return new ResultBean(true);
    }

    @RequestMapping("findBrandById/{id}")
    public TbBrand findBrandById(@PathVariable long id){
        return brandService.findBrandById(id);
    }


    @RequestMapping("updateBrand")
    public ResultBean findBrandById(@RequestBody TbBrand brand){
        brandService.updateBrand(brand);

        return new ResultBean(true);
    }

    @RequestMapping("listAllBrand")
    public ResultBean listAllBrand(){
        List<Map> brandList = brandService.findAllBrand();

        return new ResultBean<>(brandList);
    }

}
