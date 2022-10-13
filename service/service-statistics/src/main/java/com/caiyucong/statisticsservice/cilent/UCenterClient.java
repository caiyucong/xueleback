package com.caiyucong.statisticsservice.cilent;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("service-uncenter")
@Component
public interface UCenterClient {

    @PostMapping("/uncenterservice/member/{date}")
    Integer getCount(@PathVariable String date);

}
