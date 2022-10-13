package com.caiyucong.statisticsservice.controller;


import com.caiyucong.commonutils.R;
import com.caiyucong.statisticsservice.service.StatisticsDailyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author caiyucong
 * @since 2022-10-11
 */
@RestController
@RequestMapping("/statisticsservice/statistics-daily")
public class StatisticsDailyController {

    @Resource
    private StatisticsDailyService statisticsDailyService;

    @PostMapping("{day}")
    public R regCount(@PathVariable String day) {
        return statisticsDailyService.regCount(day) ? R.ok() : R.error();
    }

    @GetMapping("{type}/{begin}/{end}")
    public R showDate(@PathVariable String begin, @PathVariable String end, @PathVariable String type) {
        return R.ok().data(statisticsDailyService.getShowDate(begin, end, type));
    }
}

