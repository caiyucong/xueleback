package com.caiyucong.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstYmlUtils implements InitializingBean {


    @Value("${qcloud.cos.secretId}")
    private String secretId;

    @Value("${qcloud.cos.secretKey}")
    private String secretKey;

    @Value("${qcloud.cos.bucket}")
    private String bucket;

    @Value("${qcloud.cos.region}")
    private String region;

    public static String SECRET_ID;

    public static String SECRET_KEY;

    public static String BUCKET;

    public static String REGION;

    @Override
    public void afterPropertiesSet() throws Exception {
        SECRET_ID = secretId;
        SECRET_KEY = secretKey;
        BUCKET = bucket;
        REGION = region;
    }

    public static String getUrl(String key) {
        return "https://" + BUCKET + ".cos." + REGION + ".myqcloud.com/" + key;
    }
}
