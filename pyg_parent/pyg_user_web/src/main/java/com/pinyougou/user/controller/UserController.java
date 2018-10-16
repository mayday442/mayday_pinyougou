package com.pinyougou.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbUser;
import com.pinyougou.user.service.UserService;
import entity.ResultBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mayday
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;


    @RequestMapping("/userRegister")
    public ResultBean userRegister(@RequestBody TbUser user){

        try {
            userService.userRegister(user);

            return new ResultBean(true);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResultBean(false, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(false, "未知错误, 注册失败");
        }
    }

    @RequestMapping("/sendSms/{phoneNumbers}")
    public ResultBean sendSms(@PathVariable String phoneNumbers){

        try {
            String code = userService.sendSms(phoneNumbers);
            return new ResultBean(true, code);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResultBean(false, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(false, "未知错误, 发送短信失败");
        }
    }


}
