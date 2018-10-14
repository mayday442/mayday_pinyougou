package com.pinyougou.manager.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.pagehelper.PageHelper;
import com.pinyougou.constant.ActiveMqDestination;
import com.pinyougou.manager.service.GoodsService;
import com.pinyougou.mapper.*;
import com.pinyougou.pojo.*;
import entity.GoodsEditBean;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.jms.*;

/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private TbGoodsMapper goodsMapper;

    @Autowired
    private TbGoodsDescMapper goodsDescMapper;

    @Autowired
    private TbBrandMapper brandMapper;

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Autowired
    private TbSellerMapper sellerMapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("toSolrQueue")
    private Destination toSolrQueue;


    @Autowired
    @Qualifier("toFreemarkerQueue")
    private Destination toFreemarkerQueue;

    @Override
    public void saveGoods(GoodsEditBean goodsEditBean) {
        TbGoods goods = goodsEditBean.getGoods();
        TbGoodsDesc goodsDesc = goodsEditBean.getGoodsDesc();
        List<TbItem> itemList = goodsEditBean.getItemList();

        goods.setIsMarketable("0");
        goods.setAuditStatus("0");
        goodsMapper.insertSelective(goods);
        goodsDesc.setGoodsId(goods.getId());
        goodsDescMapper.insertSelective(goodsDesc);


        Date date = new Date();

        if ("1".equals(goods.getIsEnableSpec())) {
            for (TbItem item : itemList) {
                String title = goodsEditBean.getGoods().getGoodsName();
                Map<String, Object> specMap = JSON.parseObject(item.getSpec());
                for (String key : specMap.keySet()) {
                    title += " " + specMap.get(key);
                }
                item.setTitle(title);
                setItemVales(goods, goodsDesc, item);
                itemMapper.insert(item);
            }
        } else {
            TbItem item = new TbItem();
            item.setTitle(goods.getGoodsName());
            item.setPrice(goods.getPrice());
            item.setStatus("1");
            item.setIsDefault("1");
            item.setNum(99999);
            item.setSpec("{}");


            setItemVales(goods, goodsDesc, item);

            itemMapper.insert(item);
        }
    }

    @Override
    public PageResult<TbGoods> searchByGoods(int pageNum, int pageSize, TbGoods goods) {
        PageHelper.startPage(pageNum, pageSize);

        TbGoodsExample example = new TbGoodsExample();

        TbGoodsExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(goods.getAuditStatus())){
            criteria.andAuditStatusEqualTo(goods.getAuditStatus());
        }

        if (!StringUtils.isEmpty(goods.getSellerId())){
            criteria.andSellerIdEqualTo(goods.getSellerId());
        }

        if (!StringUtils.isEmpty(goods.getGoodsName())){
        criteria.andGoodsNameLike(goods.getGoodsName());
        }

        List<TbGoods> goodsList = goodsMapper.selectByExample(example);

        return new PageResult<>(goodsList);
    }

    @Override
    public void updateAuditStatus(String auditStatus, List<Long> selectedIds) {
        TbGoods tbGoods = new TbGoods();
        tbGoods.setAuditStatus(auditStatus);


        TbGoodsExample example = new TbGoodsExample();
        example.createCriteria().andIdIn(selectedIds);
        goodsMapper.updateByExampleSelective(tbGoods, example);
    }


    /**
     * 商品上下架方法
     * @param isMarketable
     * @param selectedIds
     */
    @Override
    public void updateIsMarketable(final String isMarketable,final List<Long> selectedIds) {
        TbGoods tbGoods = new TbGoods();
        tbGoods.setIsMarketable(isMarketable);
        TbGoodsExample example = new TbGoodsExample();
        example.createCriteria().andIdIn(selectedIds);
        goodsMapper.updateByExampleSelective(tbGoods, example);

        try {
            sendMessage(toSolrQueue,isMarketable, selectedIds);
            sendMessage(toFreemarkerQueue,isMarketable, selectedIds);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(Destination destination, String isMarketable, List<Long> selectedIds) throws JMSException {
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage mapMessage = session.createMapMessage();
                mapMessage.setObject(ActiveMqDestination.GOODS_ID_MAP_MESSAGE_KEY, selectedIds);
                mapMessage.setString(ActiveMqDestination.GOODS_MARKETABLE_MAP_MESSAGE_KEY, isMarketable);
                return mapMessage;
            }
        });
    }

    private void setItemVales(TbGoods goods, TbGoodsDesc goodsDesc, TbItem item) {
        item.setGoodsId(goods.getId());
        item.setSellerId(goods.getSellerId());

        item.setCategoryid(goods.getCategory3Id());
        item.setCreateTime(new Date());
        item.setUpdateTime(new Date());


        TbBrandExample example = new TbBrandExample();
        example.createCriteria().andIdEqualTo(goods.getBrandId());
        List<TbBrand> brandList = brandMapper.selectByExample(example);
        item.setBrand(brandList.get(0).getName());

        TbItemCat itemCat = itemCatMapper.selectByPrimaryKey(goods.getCategory3Id());
        item.setCategory(itemCat.getName());
        TbSeller seller = sellerMapper.selectByPrimaryKey(goods.getSellerId());
        item.setSeller(seller.getNickName());
        List<Map> imageList = JSON.parseArray(goodsDesc.getItemImages(),
                Map.class);
        if (imageList.size() > 0) {
            item.setImage((String) imageList.get(0).get("url"));
        }
    }


}
