package com.pinyougou.page.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.page.service.ItemPageService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mayday
 */
@RequestMapping("/page")
@RestController
public class PageController {

    @Reference(timeout = 5000)
    private ItemPageService itemPageService;

    @RequestMapping("/makePageById/{id}")
    public String makePageById(@PathVariable Long id) {
        boolean genItemHtml = itemPageService.getItemHtml(id);

        if (!genItemHtml) {
            return "not success";
        }
        return "success";
    }


    @RequestMapping("/makeAllPage")
    public String makeAllPage() {
        boolean genItemHtml = itemPageService.getAllItemHtml();

        if (!genItemHtml) {
            return "not success";
        }
        return "success";
    }

}
