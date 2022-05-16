package com.sole.saas.supplier;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.sole.saas.**")
@MapperScan(value = {"com.sole.saas.organization.mappers"})
@EnableFeignClients("com.sole.saas.**.apis")
public class SupplierApplication {
    public static void main(String[] args) {
        SpringApplication.run(SupplierApplication.class, args);
    }
}
