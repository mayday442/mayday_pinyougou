package com.pinyougou.page.service;

/**
 * @author mayday
 */
public interface ItemPageService {


    /**
     * 生成商品详细页
     * @param goodsId
     * @return
     */
    public boolean getItemHtml(Long goodsId);

    public boolean getAllItemHtml();

}
