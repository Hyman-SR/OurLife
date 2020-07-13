package com.ourlife.base.api.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangchao
 * @createdOn 2020/7/6
 */
@Component
public class TestFactory {

    @Autowired
    public void testAutowireMethod(List<Test> tests) {
        System.out.println(111);
    }

}
