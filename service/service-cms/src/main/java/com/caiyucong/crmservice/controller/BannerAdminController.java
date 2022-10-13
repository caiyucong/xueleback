package com.caiyucong.crmservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caiyucong.commonutils.R;
import com.caiyucong.crmservice.domain.Banner;
import com.caiyucong.crmservice.service.BannerService;
import lombok.var;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/crmservice/banneradmin")
public class BannerAdminController {

    @Resource
    private BannerService bannerService;

    @GetMapping("{current}/{pageSize}")
    public R getPage(@PathVariable Integer current, @PathVariable Integer pageSize) {
        var bannerPage = new Page<Banner>(current, pageSize);
        bannerService.page(bannerPage, null);
        return R.ok().putData("records", bannerPage.getRecords())
                .putData("total", bannerPage.getTotal());
    }

    @PostMapping
    public R add(@RequestBody Banner banner) {
        return bannerService.save(banner) ? R.ok() : R.error();
    }

    @PutMapping
    public R update(@RequestBody Banner banner) {
        return bannerService.updateById(banner) ? R.ok() : R.error();
    }

    @DeleteMapping("{id}")
    public R deleteById(@PathVariable String id) {
        return bannerService.removeById(id) ? R.ok() : R.error();
    }

}

