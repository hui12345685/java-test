package org.example.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.request.BaseRequest;
import org.example.response.SimpleTestResponse;
import org.example.service.SimpleTestService;
import org.example.simpletest.SimpleTest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SimpleTestServiceImpl implements SimpleTestService {

    @Resource
    private SimpleTest simpleTest;

    static int sequence = 0;

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public SimpleTestResponse getSimpleTest(BaseRequest request) {
        S3Test.S3InfoTest();

        simpleTest.Test1();

        SimpleTestResponse rsp = new SimpleTestResponse();
        rsp.setId(String.valueOf(sequence++));

        try {
        String jsonData = objectMapper.writeValueAsString(rsp);
            log.info("getSimpleTest response:{} request:{}", jsonData, request);
        } catch (Exception e) {
            log.warn("getSimpleTest convert to json error response:{} request:{}, e:", rsp, request, e);
        }
        return rsp;
    }
}
