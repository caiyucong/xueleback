package com.caiyucong.vod.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-order")
@Component
public interface OrderClient {
    @GetMapping("/orderservice/order/buy/{courseId}/{memberId}")
    boolean isBuy(@PathVariable String courseId, @PathVariable String memberId);
}
