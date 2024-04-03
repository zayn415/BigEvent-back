package com.zayn.bigevent.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.zayn.bigevent.config.AliOssConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;

@Component
public class AliOssUtil {
    @Autowired
    private AliOssConfig aliOssConfig;
    
    public String uploadFile(String objectName, InputStream in) throws Exception {
        String ENDPOINT = aliOssConfig.getEndpoint();
        String ACCESS_KEY_ID = aliOssConfig.getAccessKeyId();
        String ACCESS_KEY_SECRET = aliOssConfig.getAccessKeySecret();
        String BUCKET_NAME = aliOssConfig.getBucketName();
        
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        String url = "";
        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, objectName, in);
            
            // 上传
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            
            //url组成: https://bucket名称.区域节点/objectName
            url = "https://" + BUCKET_NAME + "." + ENDPOINT + "/" + objectName;
            
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        
        return url;
    }
}
