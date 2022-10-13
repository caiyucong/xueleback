package com.caiyucong.eduservice.client;

import com.caiyucong.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-vod")
@Component
public interface VideoClient {

    @DeleteMapping("/eduvod/vod/{ids}")
    R deleteVideo(@PathVariable("ids") String ids);

}
