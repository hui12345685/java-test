package org.example.controll;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.example.request.SimpleTestRequest;
import org.example.response.Response;
import org.example.response.SimpleTestResponse;
import org.example.service.SimpleTestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/simple", "/inner-api/simple"})
@Tag(name = "测试一些接口")
public class GetSimpleTestController {

    @Resource
    SimpleTestService simpleTestService;

    @ApiOperation("测试一些接口")
    @PostMapping("/test")
    public Response<SimpleTestResponse> getSimpleTest(@RequestBody @Valid SimpleTestRequest request) {
        SimpleTestResponse response = simpleTestService.getSimpleTest(request);
        return Response.success(response);

    }
}