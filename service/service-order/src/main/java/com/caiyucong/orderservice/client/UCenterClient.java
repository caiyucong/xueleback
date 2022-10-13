package com.caiyucong.orderservice.client;

import com.caiyucong.commonutils.vo.MemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-uncenter")
@Component
public interface UCenterClient {

    @GetMapping("/uncenterservice/member/info/{id}")
    MemberOrder getUserInfo(@PathVariable String id);

}
