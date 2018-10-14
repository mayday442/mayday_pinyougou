package com.pinyougou.page.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.mapper.TbGoodsDescMapper;
import com.pinyougou.mapper.TbGoodsMapper;
import com.pinyougou.mapper.TbItemMapper;
import com.pinyougou.page.service.ItemPageService;
import com.pinyougou.pojo.*;
import entity.Goods;
import entity.GoodsEditBean;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mayday
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ItemPageServiceImpl implements ItemPageService {

    @Value("${pagedir}")
    private String pagedir;

    private final String IS_USE = "1";

    @Autowired
    private FreeMarkerConfig freeMarkerConfig;

    @Autowired
    private TbGoodsMapper goodsMapper;

    @Autowired
    private TbGoodsDescMapper goodsDescMapper;

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public boolean getAllItemHtml() {
        try {
            Configuration configuration = freeMarkerConfig.getConfiguration();
            Template template = configuration.getTemplate("item.ftl");

            TbGoodsExample example = new TbGoodsExample();
            example.createCriteria().andAuditStatusEqualTo(IS_USE).andIsMarketableEqualTo(IS_USE);
            List<TbGoods> goodsList = goodsMapper.selectByExample(example);

            for (TbGoods tbGoods : goodsList) {
                TbGoodsDesc tbGoodsDesc = goodsDescMapper.selectByPrimaryKey(tbGoods.getId());

                GoodsEditBean goods = new GoodsEditBean();
                goods.setGoods(tbGoods);
                goods.setGoodsDesc(tbGoodsDesc);

                TbItemExample itemExample = new TbItemExample();
                itemExample.createCriteria().andGoodsIdEqualTo(tbGoods.getId()).andStatusEqualTo(IS_USE);
                List<TbItem> tbItemList = itemMapper.selectByExample(itemExample);
                Map templateMap = new HashMap();
                goods.setItemList(tbItemList);
                templateMap.put("good", goods);
                for (TbItem item : tbItemList) {
                    Long id = item.getId();
                    templateMap.put("item", item);
                    Writer writer = new FileWriter(pagedir + id + ".html");
                    template.process(templateMap, writer);
                    writer.close();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }


    @Override
    public boolean getItemHtml(Long goodsId) {
        try {
            Configuration configuration = freeMarkerConfig.getConfiguration();
            Template template = configuration.getTemplate("item.ftl");
            TbGoods tbGoods = goodsMapper.selectByPrimaryKey(goodsId);

            if (!IS_USE.equals(tbGoods.getAuditStatus()) || !IS_USE.equals(tbGoods.getIsMarketable())){
                return false;
            }

            TbGoodsDesc tbGoodsDesc = goodsDescMapper.selectByPrimaryKey(goodsId);

            GoodsEditBean goods = new GoodsEditBean();
            goods.setGoods(tbGoods);
            goods.setGoodsDesc(tbGoodsDesc);

            TbItemExample example = new TbItemExample();
            example.createCriteria().andGoodsIdEqualTo(goodsId).andStatusEqualTo(IS_USE);
            List<TbItem> tbItemList = itemMapper.selectByExample(example);
            Map templateMap = new HashMap();
            goods.setItemList(tbItemList);
            templateMap.put("good", goods);

            for (TbItem item : tbItemList) {
                Long id = item.getId();
                templateMap.put("item", item);
                Writer writer = new FileWriter(pagedir + id + ".html");
                template.process(templateMap, writer);
                writer.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }
}
