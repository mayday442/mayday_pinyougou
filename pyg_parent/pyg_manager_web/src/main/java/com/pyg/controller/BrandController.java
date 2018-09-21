package com.pyg.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.pyg.entity.PageResult;
import com.pyg.entity.PygResult;
import com.pyg.pojo.TbBrand;
import com.pyg.service.BrandService;

import org.springframework.web.bind.annotation.*;

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

        System.err.println(pageNum);
        System.err.println(pageSize);
        System.err.println(searchBrand);
        PageResult<TbBrand> pageResult = brandService.searchBrandList(pageNum, pageSize, searchBrand);
        return pageResult;
    }

    @RequestMapping("saveBrand")
    public PygResult saveBrand(@RequestBody TbBrand brand) {

        try {
            brandService.saveBrand(brand);
            return new PygResult(true);
        }catch (Exception e) {
            return new PygResult(false, "新建品牌失败");
        }
    }

    @RequestMapping("deleteBrands")
    public PygResult deleteBrands(long[] ids) {
        brandService.deleteBrands(ids);
        return new PygResult(true);
    }

    @RequestMapping("findBrandById/{id}")
    public TbBrand findBrandById(@PathVariable long id){
        return brandService.findBrandById(id);
    }


    @RequestMapping("updateBrand")
    public PygResult findBrandById(@RequestBody TbBrand brand){
        brandService.updateBrand(brand);

        return new PygResult(true);
    }

}
