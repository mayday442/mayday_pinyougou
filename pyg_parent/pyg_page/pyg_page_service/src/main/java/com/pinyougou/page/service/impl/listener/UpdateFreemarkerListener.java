package com.pinyougou.page.service.impl.listener;

import com.pinyougou.constant.ActiveMqDestination;
import com.pinyougou.mapper.TbGoodsDescMapper;
import com.pinyougou.mapper.TbGoodsMapper;
import com.pinyougou.mapper.TbItemMapper;
import com.pinyougou.pojo.*;
import entity.GoodsEditBean;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mayday
 */
public class UpdateFreemarkerListener implements MessageListener {
    @Autowired
    private TbItemMapper itemMapper;

    @Value("${pagedir}")
    private String pagedir;

    private final String IS_USE = "1";

    @Autowired
    private FreeMarkerConfig freeMarkerConfig;

    @Autowired
    private TbGoodsMapper goodsMapper;

    @Autowired
    private TbGoodsDescMapper goodsDescMapper;

    @Override
    public void onMessage(Message message) {
        MapMessage mapMessage = (MapMessage) message;
        try {
            List<Long> goodsIds = (List<Long>) mapMessage.getObject(ActiveMqDestination.GOODS_ID_MAP_MESSAGE_KEY);
            String isMarketable = mapMessage.getString(ActiveMqDestination.GOODS_MARKETABLE_MAP_MESSAGE_KEY);


            if (IS_USE.equals(isMarketable)) {
                addFreemarker(goodsIds);
                return;
            }
            deleteFreemarker(goodsIds);

        } catch (JMSException | IOException | TemplateException e) {
            e.printStackTrace();
        }


    }

    private void deleteFreemarker(List<Long> goodsIds) {

        TbItemExample itemExample = new TbItemExample();
        itemExample.createCriteria().andGoodsIdIn(goodsIds);
        List<TbItem> itemList = itemMapper.selectByExample(itemExample);

        for (TbItem item : itemList) {
            File file = new File(pagedir + item.getId() + ".html");
            boolean delete = file.delete();
            if (delete) {
                System.out.println("删除 ---" + pagedir + item.getId() + ".html" + " --- 成功");
            }
        }


    }

    private void addFreemarker(List<Long> goodsIds) throws IOException, TemplateException {
        Configuration configuration = freeMarkerConfig.getConfiguration();
        Template template = configuration.getTemplate("item.ftl");

        TbGoodsExample example = new TbGoodsExample();
        example.createCriteria().andAuditStatusEqualTo(IS_USE).andIsMarketableEqualTo(IS_USE).andIdIn(goodsIds);
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
    }
}
