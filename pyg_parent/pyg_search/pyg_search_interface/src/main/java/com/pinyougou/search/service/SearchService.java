package com.pinyougou.search.service;

import entity.ResultBean;

import java.util.Map;

/**
 * @author mayday
 */
public interface SearchService {


    /**
     * solr 查询的方法
     * @param searchMap
     * @return
     */
    ResultBean searchItem(Map<String, String> searchMap);
}
