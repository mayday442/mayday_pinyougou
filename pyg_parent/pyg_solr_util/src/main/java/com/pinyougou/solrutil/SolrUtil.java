package com.pinyougou.solrutil;

import com.alibaba.fastjson.JSON;
import com.pinyougou.mapper.TbItemMapper;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.pojo.TbItemExample;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Map;

/**
 * @author mayday
 */

@Component
public class SolrUtil {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private SolrTemplate solrTemplate;

    private void importItemData() {
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();

        //已审核
        criteria.andStatusEqualTo("1").andIsDefaultEqualTo("1");
        List<TbItem> itemList = itemMapper.selectByExample(example);

        for (TbItem item : itemList) {
            String spec = item.getSpec();
            Map<String, String> specMap = JSON.parseObject(spec, Map.class);
            item.setSpecMap(specMap);

        }


        solrTemplate.saveBeans(itemList);
        solrTemplate.commit();
    }

    private void selectItem() {
        Query query = new SimpleQuery("*:*");
        ScoredPage<TbItem> tbItems = solrTemplate.queryForPage(query, TbItem.class);
        long totalElements = tbItems.getTotalElements();
        System.out.println(totalElements);

        List<TbItem> content = tbItems.getContent();
        for (TbItem item : content) {
            System.out.println(item.getTitle());
        }
    }

    private void delete() {
        Query query = new SimpleQuery("*:*");
        solrTemplate.delete(query);
        solrTemplate.commit();
    }





    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext*.xml");
        SolrUtil solrUtil= (SolrUtil) context.getBean("solrUtil");
       solrUtil.importItemData();
    }

}
