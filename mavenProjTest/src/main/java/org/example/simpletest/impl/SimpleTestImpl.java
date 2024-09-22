package org.example.simpletest.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.simpletest.SimpleTest;

@Slf4j
public class SimpleTestImpl implements SimpleTest {
    public boolean Test1() {
        log.info("SimpleTestImpl exec Test1...");
        return true;
    }
}
