package com.zayn.bigevent.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zayn
 * * @date 2024/4/4/03:16
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliOssConfig {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
}
