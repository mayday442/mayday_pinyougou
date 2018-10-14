package com.pinyougou.search.service.impl.listener;

import com.pinyougou.constant.ActiveMqDestination;
import com.pinyougou.mapper.TbItemMapper;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.pojo.TbItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.List;
import java.util.Map;

/**
 * @author mayday
 */
public class UpdateGoodsListener implements MessageListener {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public void onMessage(Message message) {
        MapMessage mapMessage = (MapMessage) message;
        try {
            List<Long> goodsIds = (List<Long>) mapMessage.getObject(ActiveMqDestination.GOODS_ID_MAP_MESSAGE_KEY);
            String isMarketable = mapMessage.getString(ActiveMqDestination.GOODS_MARKETABLE_MAP_MESSAGE_KEY);


            if ("1".equals(isMarketable)) {
                addSolr(goodsIds);
                return;
            }

            deleteSolr(goodsIds);

        } catch (JMSException e) {
            e.printStackTrace();
        }


    }

    private void addSolr(List<Long> goodsIds) {
        TbItemExample example = new TbItemExample();
        example.createCriteria().andGoodsIdIn(goodsIds).andStatusEqualTo("1").andIsDefaultEqualTo("1");
        List<TbItem> tbItemList = itemMapper.selectByExample(example);
        solrTemplate.saveBeans(tbItemList);
        solrTemplate.commit();

    }

    private void deleteSolr(List<Long> goodsIds) {
        TbItemExample example = new TbItemExample();
        example.createCriteria().andGoodsIdIn(goodsIds);
        List<TbItem> tbItemList = itemMapper.selectByExample(example);
        for (TbItem item : tbItemList) {
            solrTemplate.deleteById(item.getId() + "");
        }
        solrTemplate.commit();
    }


}
