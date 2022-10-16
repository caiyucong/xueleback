package com.caiyucong.eduservice.client;

import com.caiyucong.commonutils.vo.MemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

@FeignClient("service-ucenter")
@Component
public interface MemberClient {
    @GetMapping("/serviceucenter/ucenter-member/getMemberByTokenMemberId/{memberId}")
    MemberVo getMemberByMemberId(@PathVariable("memberId") String memberId);

}
