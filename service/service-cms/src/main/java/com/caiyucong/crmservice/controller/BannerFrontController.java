package com.caiyucong.crmservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caiyucong.commonutils.R;
import com.caiyucong.crmservice.domain.Banner;
import com.caiyucong.crmservice.service.BannerService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-24
 */
@RestController
@RequestMapping("/crmservice/bannerfront")
public class BannerFrontController {

    @Resource
    public BannerService bannerService;

    @Cacheable(value = "banner", key = "'getBannerList'")
    @GetMapping
    public R getAll() {
        return R.ok().putData("list", bannerService.list(new QueryWrapper<Banner>()
                .orderByDesc(Banner.ID)
                .last("limit 2"))
        );
    }


}

