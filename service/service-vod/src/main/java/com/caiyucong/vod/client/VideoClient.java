package com.caiyucong.vod.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-edu")
@Component
public interface VideoClient {

    @GetMapping("/eduservice/video/source/{id}")
    String getBySourceId(@PathVariable String id);

}
