package com.caiyucong.statisticsservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.caiyucong")
@EnableFeignClients
@MapperScan("com.caiyucong.statisticsservice.mapper")
@EnableScheduling
public class StaServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaServiceApplication.class, args);
    }
}
