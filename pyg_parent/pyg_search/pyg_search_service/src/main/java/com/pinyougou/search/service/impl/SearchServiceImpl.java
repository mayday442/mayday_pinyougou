package com.pinyougou.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.pinyougou.constant.CacheKey;
import com.pinyougou.mapper.TbItemCatMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.mapper.TbTypeTemplateMapper;
import com.pinyougou.pojo.*;
import com.pinyougou.search.service.SearchService;
import entity.ResultBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.*;
import org.springframework.jms.core.JmsTemplate;


import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.*;

/**
 * @author mayday
 */

@Service
public class SearchServiceImpl implements SearchService {


    private final String ITEM_KEYWORDS = "item_keywords";

    private final String ITEM_CATEGORY = "item_category";
    private final String SEARCH_CATEGORY = "category";

    private final String ITEM_BRAND = "item_brand";
    private final String SEARCH_BRAND = "brand";

    private final String ITEM_SPEC_ = "item_spec_";
    private final String SEARCH_SPEC = "spec";



    @Autowired
    private SolrTemplate solrTemplate;

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Autowired
    private TbTypeTemplateMapper typeTemplateMapper;

    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResultBean searchItem(Map<String, String> searchMap) {
        Map<String, Object> resultMap = new HashMap<>();
        Criteria criteria = null;

        if (searchMap != null && !StringUtils.isEmpty(searchMap.get(ITEM_KEYWORDS))) {
            criteria = new Criteria(ITEM_KEYWORDS).contains(searchMap.get(ITEM_KEYWORDS));

        } else {
            criteria = new Criteria();
            criteria.expression("*:*");
        }

        highlightQuery(searchMap, resultMap, criteria);

        searchCategory(searchMap, resultMap);
        return new ResultBean<>(resultMap);
    }

    private void searchCategory(Map<String, String> searchMap, Map<String, Object> resultMap) {

        List<String> categoryList = new ArrayList<>();

        Query groupQuery = new SimpleQuery(new Criteria(ITEM_KEYWORDS).is(searchMap.get(ITEM_KEYWORDS)));
        GroupOptions groupOptions = new GroupOptions();
        groupOptions.addGroupByField(ITEM_CATEGORY);

        groupQuery.setGroupOptions(groupOptions);

        GroupPage<TbItem> groupPage = solrTemplate.queryForGroupPage(groupQuery, TbItem.class);
        GroupResult<TbItem> groupResult = groupPage.getGroupResult(ITEM_CATEGORY);
        Page<GroupEntry<TbItem>> groupEntries = groupResult.getGroupEntries();
        for (GroupEntry<TbItem> groupEntry : groupEntries) {
            String groupValue = groupEntry.getGroupValue();
            categoryList.add(groupValue);
        }

        searchTypeTemplate(categoryList, resultMap);

        resultMap.put("categoryList", categoryList);

    }

    private void searchTypeTemplate(List<String> categoryList, Map<String, Object> resultMap) {

        TbItemCatExample itemCatExample = new TbItemCatExample();
        if (categoryList == null || categoryList.size() == 0) {
            return;
        }
        itemCatExample.createCriteria().andNameIn(categoryList);
        List<TbItemCat> tbItemCats = itemCatMapper.selectByExample(itemCatExample);
        if (tbItemCats == null || tbItemCats.size() == 0) {

            return;
        }
        Long typeId = -1L;

        List<List<Map<String, Object>>> brandIdsList = new ArrayList<>();
        List<List<Map<String, Object>>> specIdsList = new ArrayList<>();

        for (TbItemCat tbItemCat : tbItemCats) {
            if (!typeId.equals(tbItemCat.getTypeId())) {
                typeId = tbItemCat.getTypeId();

                // 取出的是缓存当中当前 typeId 对应的 TbTypeTemplate 对象
                String typeTemplateStr = redisTemplate.boundHashOps(CacheKey.SEARCH_TYPE_TEMPLATE_KEY).get(CacheKey.SEARCH_TYPE_TEMPLATE_KEY + typeId) + "";

                TbTypeTemplate tbTypeTemplate = null;

                // 如果为空,则从数据库中查询并放入到缓存当中
                if (StringUtils.isBlank(typeTemplateStr) || "null".equals(typeTemplateStr)) {
                    tbTypeTemplate = typeTemplateMapper.selectByPrimaryKey(typeId);
                    String jsonString = JSON.toJSONString(tbTypeTemplate);
                    redisTemplate.boundHashOps(CacheKey.SEARCH_TYPE_TEMPLATE_KEY).put(CacheKey.SEARCH_TYPE_TEMPLATE_KEY + typeId, jsonString);
                } else {
                    tbTypeTemplate = JSON.parseObject(typeTemplateStr, TbTypeTemplate.class);
                }

                String brandIds = tbTypeTemplate.getBrandIds();
                String specIds = tbTypeTemplate.getSpecIds();

                List<Map<String, Object>> specIdsMapList = JSON.parseObject(specIds, List.class);
                specIdsList.add(specIdsMapList);

                // brandIds 是一个 list 集合,包含多个 map
                List<Map<String, Object>> brandIdsMapList = JSON.parseObject(brandIds, List.class);

                // 把 typeId 不同的 brandIds 装到一个集合中
                brandIdsList.add(brandIdsMapList);
            }
        }
        buildBrandIds(resultMap, brandIdsList);
        buildSpecIds(resultMap, specIdsList);


    }

    private void highlightQuery(Map<String, String> searchMap, Map<String, Object> resultMap, Criteria criteria) {
        HighlightQuery query = new SimpleHighlightQuery();
        HighlightOptions highlightOptions = new HighlightOptions().addField("item_title");
        highlightOptions.setSimplePrefix("<em style='color:red'>");
        highlightOptions.setSimplePostfix("</em>");
        query.setHighlightOptions(highlightOptions);

        if (StringUtils.isNotBlank(searchMap.get(SEARCH_BRAND))) {
            query.addFilterQuery(new SimpleFacetQuery(new Criteria(ITEM_BRAND).is(searchMap.get(SEARCH_BRAND))));
        }

        if (StringUtils.isNotBlank(searchMap.get(SEARCH_CATEGORY))) {
            query.addFilterQuery(new SimpleFacetQuery(new Criteria(ITEM_CATEGORY).is(searchMap.get(SEARCH_CATEGORY))));
        }

        if (searchMap.get(SEARCH_SPEC) != null) {

            Map<String, String> specMap = JSON.parseObject(searchMap.get(SEARCH_SPEC), Map.class);
            Set<String> keySet = specMap.keySet();

            for (String specKey : keySet) {
                String specValue = specMap.get(specKey);
                query.addFilterQuery(new SimpleFacetQuery(new Criteria(ITEM_SPEC_ + specKey).is(specValue)));
            }

        }



        Integer pageSize = Integer.valueOf(searchMap.get("pageSize") + "");
        query.setOffset((Integer.valueOf(searchMap.get("page") + "") -1) * pageSize);
        query.setRows(pageSize);

        query.addCriteria(criteria);

        HighlightPage<TbItem> highlightPage = solrTemplate.queryForHighlightPage(query, TbItem.class);
        List<HighlightEntry<TbItem>> highlighted = highlightPage.getHighlighted();
        for (HighlightEntry<TbItem> entry : highlighted) {
            TbItem item = entry.getEntity();

            if (entry.getHighlights().size() > 0 &&
                    entry.getHighlights().get(0).getSnipplets().size() > 0) {

                item.setTitle(entry.getHighlights().get(0).getSnipplets().get(0));
            }
        }
        List<TbItem> content = highlightPage.getContent();
        long totalElements = highlightPage.getTotalElements();
        int totalPages = highlightPage.getTotalPages();


        resultMap.put("totalPages", totalPages);
        resultMap.put("total", totalElements);
        resultMap.put("itemList", content);
    }

    /**
     * 构建品牌列表的方法
     *
     * @param resultMap    结果集
     * @param brandIdsList 包含多个或一个模板的品牌集合
     */
    private void buildBrandIds(Map<String, Object> resultMap, List<List<Map<String, Object>>> brandIdsList) {
        List<Map<String, Object>> brandIds = getMaps(brandIdsList);

        // 将 brandIds 放入 resultMap 中
        resultMap.put("brand", brandIds);
    }

    /**
     * 构建规格选项的方法
     *
     * @param resultMap   结果集
     * @param specIdsList 包含多个或一个模板的规格集合
     */
    private void buildSpecIds(Map<String, Object> resultMap, List<List<Map<String, Object>>> specIdsList) {

        List<Map<String, Object>> specIds = getMaps(specIdsList);
        List<Map<String, Object>> optionsMap = new ArrayList<>();

        if (specIds.size() > 1) {
            for (Map<String, Object> specMap : specIds) {
                TbSpecificationOptionExample example = new TbSpecificationOptionExample();
                Long specId = Long.valueOf(specMap.get("id") + "");

                example.createCriteria().andSpecIdEqualTo(specId);
                List<TbSpecificationOption> tbSpecificationOptions = specificationOptionMapper.selectByExample(example);

                if (tbSpecificationOptions != null && tbSpecificationOptions.size() > 0) {
                    specMap.put("options", tbSpecificationOptions);
                }
            }
            resultMap.put("spec", specIds);
            return;
        }

        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        example.createCriteria().andSpecIdEqualTo(Long.valueOf(specIds.get(0).get("id") + ""));
        List<TbSpecificationOption> tbSpecificationOptions = specificationOptionMapper.selectByExample(example);
        if (tbSpecificationOptions != null && tbSpecificationOptions.size() > 0) {
            specIds.get(0).put("options", tbSpecificationOptions);
        }


        resultMap.put("spec", specIds);
    }

    /**
     * 构建品牌列表的方法和构建规格选项的方法的通用方法
     *
     * @param idsList
     * @return
     */
    private List<Map<String, Object>> getMaps(List<List<Map<String, Object>>> idsList) {
        List<Map<String, Object>> maps = new ArrayList<>();




        if (idsList.size() > 1) {
            for (List<Map<String, Object>> mapList : idsList) {
                for (Map<String, Object> map : mapList) {
                    Set<Map.Entry<String, Object>> entries = map.entrySet();
                    Map<String, Object> tempMap = new HashMap<>();
                    for (Map.Entry<String, Object> entry : entries) {
                        tempMap.put(entry.getKey(), entry.getValue());
                    }
                    maps.add(tempMap);
                }
            }

        } else {
            maps = idsList.get(0);
        }
        return maps;
    }


}
