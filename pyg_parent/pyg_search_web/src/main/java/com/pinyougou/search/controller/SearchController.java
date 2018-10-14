package com.pinyougou.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.pinyougou.search.service.SearchService;
import entity.ResultBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author mayday
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    @Reference
    private SearchService searchService;

    @RequestMapping("/searchItem")
    public ResultBean searchItem(@RequestBody Map<String, String> searchMap){
        return searchService.searchItem(searchMap);
    }

}
