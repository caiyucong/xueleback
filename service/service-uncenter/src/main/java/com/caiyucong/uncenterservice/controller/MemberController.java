package com.caiyucong.uncenterservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caiyucong.commonutils.JwtUtils;
import com.caiyucong.commonutils.R;
import com.caiyucong.commonutils.vo.MemberOrder;
import com.caiyucong.uncenterservice.domain.Member;
import com.caiyucong.uncenterservice.domain.vo.LoginVo;
import com.caiyucong.uncenterservice.domain.vo.RegisterVo;
import com.caiyucong.uncenterservice.service.MemberService;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-27
 */
@RestController
@RequestMapping("/uncenterservice/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    @PostMapping
    public R loginUser(@RequestBody LoginVo loginVo) {
        return R.ok().putData("token", memberService.login(loginVo));
    }

    @PutMapping
    public R registerUser(@RequestBody RegisterVo registerVo) {
        return memberService.register(registerVo) ? R.ok() : R.error();
    }

    @GetMapping
    public R getMemberInfo(HttpServletRequest request) {
        return R.ok().putData("userInfo", memberService.getById(JwtUtils.getMemberIdByJwtToken(request)));
    }

    @GetMapping("{phone}")
    public R checkPhone(@PathVariable String phone) {
        return memberService.count(new QueryWrapper<Member>().eq(Member.MOBILE, phone)) > 0 ? R.ok() : R.error();
    }

    @GetMapping("info/{id}")
    public MemberOrder getUserInfo(@PathVariable String id) {
        val member = memberService.getById(id);
        val memberOrder = new MemberOrder();
        BeanUtils.copyProperties(member, memberOrder);
        return memberOrder;
    }

    @PostMapping("{date}")
    public Integer getCount(@PathVariable String date) {
        return memberService.getCount(date);
    }

}

