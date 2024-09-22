package org.example.config;

import org.example.simpletest.SimpleTest;
import org.example.simpletest.impl.SimpleTestImplV2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    //这里加了config,@Resource SimpleTest 默认使用该函数返回的
    @Bean
    public SimpleTest simpleTest() {
        return new SimpleTestImplV2();
    }
}
