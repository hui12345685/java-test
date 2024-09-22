package org.example.service;

import org.example.request.BaseRequest;
import org.example.response.SimpleTestResponse;

public interface SimpleTestService {

    SimpleTestResponse getSimpleTest(BaseRequest request);
}
