package com.pyg.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.pyg.pojo.TbBrand;
import com.pyg.service.BrandService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mayday
 */

@RestController
@RequestMapping("brand")
public class BrandController {

    @Reference
    private BrandService brandService;


    /**
     * 返回全部列表 * @return
     */
    @RequestMapping("/listAllBrand")
    public List<TbBrand> listAllBrand() {


        return brandService.listAllBrand();
    }

}
