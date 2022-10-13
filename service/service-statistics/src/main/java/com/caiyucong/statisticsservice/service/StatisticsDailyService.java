package com.caiyucong.statisticsservice.service;

import com.caiyucong.statisticsservice.domain.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author caiyucong
 * @since 2022-10-11
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    boolean regCount(String day);

    Map<String, Object> getShowDate(String begin, String end, String type);
}
