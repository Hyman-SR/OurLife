package com.ourlife.base.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 解决问题的方法论
 * 1.故障现象
 * 2.导致原因
 * 3.解决方案
 * 4.优化建议(同样的错误不犯第二次)
 */
@ComponentScan(basePackages = {"com.ourlife.base"})
@SpringBootApplication
public class BaseApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseApiApplication.class, args);
    }

}
