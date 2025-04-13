package com.sparta.limited.preuser_service;

import com.sparta.limited.preuser_service.preuser_product.infrastructure.client.ProductClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackageClasses = ProductClient.class)
public class PreuserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreuserServiceApplication.class, args);
    }

}
