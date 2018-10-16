package com.pinyougou.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.common.util.HttpClient;
import com.pinyougou.mapper.TbUserMapper;
import com.pinyougou.pojo.TbUser;
import com.pinyougou.pojo.TbUserExample;
import com.pinyougou.user.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author mayday
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void userRegister(TbUser user) {

        TbUserExample usernameExample = new TbUserExample();
        usernameExample.createCriteria().andUsernameEqualTo(user.getUsername());
        List<TbUser> tbUsers = userMapper.selectByExample(usernameExample);

        if (tbUsers.size() > 0) {
            throw new RuntimeException("用户名已存在");
        }

        String code = redisTemplate.boundValueOps(user.getPhone()).get(0, -1) + "";

        if (StringUtils.isBlank(code) || "null".equals(code)) {
            throw new RuntimeException("验证码已过期");
        }

        Date date = new Date();
        user.setCreated(date);
        user.setUpdated(date);

        userMapper.insertSelective(user);
    }

    @Override
    public String sendSms(String phoneNumbers)  {

        String code = RandomStringUtils.randomNumeric(4);

        HttpClient httpClient = new HttpClient("http://localhost:7788/sms/sendSms");
        httpClient.addParameter("phoneNumbers", phoneNumbers);
        httpClient.addParameter("signName", "黄果树景区");
        httpClient.addParameter("templateCode", "SMS_147976048");
        httpClient.addParameter("code", code);

        try {
            httpClient.post();
            redisTemplate.boundValueOps(phoneNumbers).set(code, 60 * 1000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return code;
    }
}
