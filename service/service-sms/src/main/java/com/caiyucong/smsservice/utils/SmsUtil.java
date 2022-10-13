package com.caiyucong.smsservice.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmsUtil implements InitializingBean {

    @Value("${qcloud.sdk.secretId}")
    private String secretId;

    @Value("${qcloud.sdk.secretKey}")
    private String secretKey;

    @Value("${qcloud.sdk.sdkAppId}")
    private String sdkAppId;

    @Value("${qcloud.sdk.signName}")
    private String signName;

    @Value("${qcloud.sdk.templateId}")
    private String templateId;

    @Value("${qcloud.sdk.region}")
    private String region;

    public static String SECRET_ID;

    public static String SECRET_KEY;

    public static String SDK_APP_ID;

    public static String SIGN_NAME;

    public static String TEMPLATE_ID;

    public static String REGION;

    @Override
    public void afterPropertiesSet() throws Exception {
        SECRET_ID = secretId;
        SECRET_KEY = secretKey;
        SDK_APP_ID = sdkAppId;
        SIGN_NAME = signName;
        TEMPLATE_ID = templateId;
        REGION = region;
    }
}
