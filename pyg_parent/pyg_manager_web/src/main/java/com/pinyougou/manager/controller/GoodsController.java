package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.manager.service.GoodsService;
import com.pinyougou.pojo.TbGoods;
import entity.PageResult;
import entity.ResultBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Reference
    private GoodsService goodsService;


    @RequestMapping("/updateAuditStatus/{auditStatus}/{selectedIds}")
    public ResultBean updateAuditStatus(@PathVariable String auditStatus,
                                @PathVariable List<Long> selectedIds) {

        try {
            goodsService.updateAuditStatus(auditStatus, selectedIds);
            return new ResultBean(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(false, "操作失败");
        }

    }

    @RequestMapping("search/{pageNum}/{pageSize}")
    public PageResult search(@PathVariable int pageNum,
                             @PathVariable int pageSize,
                             @RequestBody TbGoods goods){

        return goodsService.searchByGoods(pageNum, pageSize, goods);


    }


}
