package org.example.simpletest.impl;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleTestImplV2 extends SimpleTestImpl {

    public boolean Test1() {
        Map<String, TestClass> map = new HashMap<>();
        TestClass test1 = new TestClass();
        test1.setAaa("aaa");
        test1.setBbb("bbb");
        map.put("key1", test1);
        Object vv = map.get("key1");
        log.info("SimpleTestImplV2 exec Test1, map:{} val:{}", map, vv.getClass().getName());
        return true;
    }

    // 静态内部类
    @Data
    @ToString
    private static class TestClass{
        String aaa;
        String bbb;
    }
}
