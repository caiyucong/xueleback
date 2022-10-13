package com.caiyucong.uncenterservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.caiyucong")
@MapperScan("com.caiyucong.uncenterservice.mapper")
public class UnCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UnCenterApplication.class, args);
    }
}
