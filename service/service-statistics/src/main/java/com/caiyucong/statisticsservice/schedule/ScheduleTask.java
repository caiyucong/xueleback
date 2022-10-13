package com.caiyucong.statisticsservice.schedule;

import com.caiyucong.statisticsservice.service.StatisticsDailyService;
import com.caiyucong.statisticsservice.utils.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class ScheduleTask {

    @Resource
    private StatisticsDailyService statisticsDailyService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void task() {
        statisticsDailyService.regCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}
