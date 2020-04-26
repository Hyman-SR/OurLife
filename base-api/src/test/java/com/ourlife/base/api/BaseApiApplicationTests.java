package com.ourlife.base.api;

import com.ourlife.base.core.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BaseApiApplicationTests {

    @Autowired
    private RedisService redisService;

    @Test
    public void test1() {
//        redisService.set("stringKey1", "stringValue1");
//        redisService.set("stringKey2", "stringValue2", 100);
        String value2=(String)redisService.get("stringKey1");
        System.out.println("set 完成" + value2);
    }

}
