package com.caiyucong.smsservice.service.impl;

import com.caiyucong.smsservice.service.SmsService;
import com.caiyucong.smsservice.utils.SmsUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SmsServiceImpl implements SmsService {
    @Override
    public boolean send(String code, String phone) {
        try {
            val cred = new Credential(SmsUtil.SECRET_ID, SmsUtil.SECRET_KEY);
            val client = new SmsClient(cred, SmsUtil.REGION);
            val req = new SendSmsRequest();
            req.setSmsSdkAppId(SmsUtil.SDK_APP_ID);
            req.setSignName(SmsUtil.SIGN_NAME);
            req.setTemplateId(SmsUtil.TEMPLATE_ID);
            req.setTemplateParamSet(new String[]{code, "5"});
            req.setPhoneNumberSet(new String[]{phone});
            val response = client.SendSms(req);
            return response.getSendStatusSet()[0].getCode().equals("Ok");
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return false;
    }
}
