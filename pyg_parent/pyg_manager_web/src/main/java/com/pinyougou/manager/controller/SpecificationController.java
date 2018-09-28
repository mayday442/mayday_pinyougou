package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.ResultBean;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.manager.service.SpecificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author mayday
 */
@RestController
@RequestMapping("specification")
public class SpecificationController {

    @Reference
    private SpecificationService specService;


    @RequestMapping("searchSpecificationList")
    public PageResult<TbSpecification> searchSpecificationList(int pageNum,
                                                               int pageSize,
                                                               @RequestBody TbSpecification spec){
        return specService.searchSpecificationList(pageNum, pageSize, spec);
    }

    @RequestMapping("findSpecById/{id}")
    public List<TbSpecificationOption> findSpecificationById(@PathVariable long id){
        return specService.findSpecificationById(id);
    }

    @RequestMapping("saveSpecification")
    public ResultBean saveSpecification(@RequestBody TbSpecification specification) {
        System.err.println(specification);

        try {
            specService.saveSpecification(specification);
            return new ResultBean(true);
        } catch (Exception e) {
            return new ResultBean(false, "操作失败");
        }

    }

    @RequestMapping("updateSpecification")
    public ResultBean updateSpecification(@RequestBody TbSpecification specification) {
        try {
            specService.updateSpecification(specification);
            return new ResultBean(true);
        } catch (Exception e) {
            return new ResultBean(false, "操作失败");
        }
    }

    @RequestMapping("deleteSpecification")
    public ResultBean deleteSpecification(Long[] ids) {
        try {
            specService.deleteSpecification(ids);
            return new ResultBean(true);
        } catch (Exception e) {
            return new ResultBean(false, "操作失败");
        }
    }

    @RequestMapping("findAll")
    public ResultBean findAll() {
       List<Map> specMapList =  specService.findAll();

       return new ResultBean<>(specMapList);
    }

}
