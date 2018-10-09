package com.pinyougou.manager.service;
import java.util.List;
import com.pinyougou.pojo.TbGoods;

import entity.GoodsEditBean;
import entity.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface GoodsService {


    /**
     * 保存商品的方法
     * @param goods
     */
    void saveGoods(GoodsEditBean goods);

    /**
     * 分页查询商品
     * @param pageNum
     * @param pageSize
     * @param goods
     * @return
     */
    PageResult<TbGoods> searchByGoods(int pageNum, int pageSize, TbGoods goods);

    /**
     * 修改商品状态
     * @param auditStatus
     * @param selectedIds
     */
    void updateAuditStatus(String auditStatus, List<Long> selectedIds);


    void updateIsMarketable(String isMarketable, List<Long> selectedIds);
}
