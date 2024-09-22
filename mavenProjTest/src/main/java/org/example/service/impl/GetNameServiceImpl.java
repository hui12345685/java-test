package org.example.service.impl;

import org.example.service.GetNameService;
import org.springframework.stereotype.Service;

@Service
public class GetNameServiceImpl implements GetNameService {

    @Override
    public String getNameById(String id) {
        return id + "_name";
    }
}
