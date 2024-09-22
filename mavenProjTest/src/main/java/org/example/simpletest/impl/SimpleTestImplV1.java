package org.example.simpletest.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.simpletest.SimpleTest;

@Slf4j
public class SimpleTestImplV1 extends SimpleTestImpl {

    public boolean Test1() {
        log.info("SimpleTestImplV1 exec Test1...");
        return true;
    }
}
