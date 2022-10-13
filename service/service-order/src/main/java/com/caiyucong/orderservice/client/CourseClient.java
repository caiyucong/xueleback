package com.caiyucong.orderservice.client;

import com.caiyucong.commonutils.vo.CourseVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-edu")
@Component
public interface CourseClient {

    @GetMapping("/eduservice/coursefront/info/{id}")
    CourseVoOrder getCourseInfo(@PathVariable String id);

}
