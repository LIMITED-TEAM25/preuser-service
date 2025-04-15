package com.sparta.limited.preuser_service;

import com.sparta.limited.common_module.common.EnableCommonModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableCommonModule
@EnableFeignClients
public class PreuserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreuserServiceApplication.class, args);
    }

}
