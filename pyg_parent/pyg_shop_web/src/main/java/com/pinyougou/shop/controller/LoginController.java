package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.manager.service.SellerService;
import entity.ResultBean;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mayday
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Reference
    private SellerService sellerService;

    @RequestMapping("loadLoginName")
    public ResultBean loadLoginName(){
        SecurityContext context = SecurityContextHolder.getContext();
        User principal = (User) context.getAuthentication().getPrincipal();
        String loadLoginName = principal.getUsername();
        return new  ResultBean<>(loadLoginName);
    }

}
