package com.caiyucong.smsservice.controller;

import com.caiyucong.commonutils.R;
import com.caiyucong.smsservice.service.SmsService;
import com.caiyucong.smsservice.utils.RandomUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/smsservice/sms")
public class SmsController {

    @Resource
    private SmsService smsService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("{phone}")
    public R sendSms(@PathVariable String phone) {
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return R.ok();
        }
        code = RandomUtil.getFourBitRandom();
        if (smsService.send(code, phone)) {
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return R.ok();
        }
        return R.error();
    }
}
