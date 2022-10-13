package com.caiyucong.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.caiyucong")
@EnableFeignClients
public class EduServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(EduServiceApp.class, args);
    }
}
