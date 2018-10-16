package com.pinyougou.user.service;

import com.pinyougou.pojo.TbUser;

/**
 * @author mayday
 */
public interface UserService {
    void userRegister(TbUser user);

    String sendSms(String phoneNumbers);
}
