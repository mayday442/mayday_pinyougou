package com.pinyougou.sms.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author mayday
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    static final String PRODUCT = "Dysmsapi";

    static final String DOMAIN = "dysmsapi.aliyuncs.com";

    @Value("${access.AccessKeyID}")
    private String ACCESS_KEY_ID;

    @Value("${access.AccessKeySecret}")
    private String ACCESS_KEY_SECRET;

    @RequestMapping("/sendSms")
    public void sendSms(String phoneNumbers, String signName, String templateCode, String code) {
        try {
            SendSmsResponse response = send(phoneNumbers, signName, templateCode, code);
        } catch (ClientException e) {
            e.printStackTrace();
        }


    }

    private SendSmsResponse send(String phoneNumbers, String signName, String templateCode, String code) throws ClientException {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, DOMAIN);

        IAcsClient acsClient = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();

        request.setPhoneNumbers(phoneNumbers);
        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        request.setOutId("yourOutId");

        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        return sendSmsResponse;
    }
}
