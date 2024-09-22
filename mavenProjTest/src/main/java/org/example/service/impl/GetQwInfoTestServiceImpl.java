package org.example.service.impl;

import jakarta.annotation.Resource;
import org.example.getqwinfo.GetQwInfoTest;
import org.example.request.GetQwInfoTestRequest;
import org.example.service.GetQwInfoTestService;
import org.springframework.stereotype.Service;

@Service
public class GetQwInfoTestServiceImpl implements GetQwInfoTestService {

    @Resource
    private GetQwInfoTest getQwInfoTest;

    /*@PostConstruct
    public void init(){
        getQwInfoTest = new GetQwInfoTest();
    }*/

    @Override
    public Boolean getQwInfoTest(GetQwInfoTestRequest request) {
        //getQwInfoTest.getQwTagListTest("wwfea82e388eaa8d08", "1000005");
        return true;
    }
}
