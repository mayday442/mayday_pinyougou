package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.manager.service.GoodsService;
import com.pinyougou.pojo.TbGoods;
import entity.GoodsEditBean;
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


    @RequestMapping("/save")
    public ResultBean saveGoods(@RequestBody GoodsEditBean goods) {
        try {
            User seller = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String sellerId = seller.getUsername();
            goods.getGoods().setSellerId(sellerId);
            goodsService.saveGoods(goods);
            return new ResultBean(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(false, "增加失败");
        }
    }

    @RequestMapping("search/{pageNum}/{pageSize}")
    public PageResult search(@PathVariable int pageNum,
                             @PathVariable int pageSize,
                             @RequestBody TbGoods goods){

        User seller = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String sellerId = seller.getUsername();
        goods.setSellerId(sellerId);

        return goodsService.searchByGoods(pageNum, pageSize, goods);


    }

    @RequestMapping("/updateIsMarketable/{isMarketable}/{selectedIds}")
    public ResultBean updateIsMarketable(@PathVariable String isMarketable,
                                        @PathVariable List<Long> selectedIds) {

        try {
            goodsService.updateIsMarketable(isMarketable, selectedIds);
            return new ResultBean(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(false, "操作失败");
        }

    }


}
