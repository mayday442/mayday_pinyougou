package com.pinyougou.shop.controller;

import com.pinyougou.common.unit.FastDFSClient;
import entity.ResultBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author mayday
 */
@RestController
@RequestMapping("upload")
public class UploadController {

    private final String FILE_SERVER_URL = "http://192.168.25.133";

    private FastDFSClient fastDFSClient;

    @RequestMapping("upload")
    public ResultBean uploadFile(MultipartFile file){

        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".")
                + 1);

        try {
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:shop/config/fdfs_client.conf");

            String path = fastDFSClient.uploadFile(file.getBytes(), extName);

            String url = FILE_SERVER_URL + "/" +  path;

            return new ResultBean(true, url);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultBean<>(false, "上传失败");
        }


    }

}
