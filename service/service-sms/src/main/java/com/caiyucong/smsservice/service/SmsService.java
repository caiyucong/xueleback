package com.caiyucong.smsservice.service;

public interface SmsService {
    boolean send(String code, String phone);
}
