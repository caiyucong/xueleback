package com.caiyucong.crmservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;

@SpringBootConfiguration
@MapperScan("com.caiyucong.crmservice.mapper")
public class CrmServiceConfig {
}
