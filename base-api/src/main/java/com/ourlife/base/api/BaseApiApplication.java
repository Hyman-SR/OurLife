package com.ourlife.base.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.ourlife.base"})
@SpringBootApplication
public class BaseApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseApiApplication.class, args);
    }

}
