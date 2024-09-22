package org.example.controll;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.example.request.GetQwInfoTestRequest;
import org.example.response.Response;
import org.example.service.GetQwInfoTestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/getQwinfo", "/inner-api/getQwinfo"})
@Tag(name = "测试企微的一些接口")
public class GetQwInfoController {

    @Resource
    GetQwInfoTestService getQwInfoTestService;

    @ApiOperation("测试企微的一些接口")
    @PostMapping("/test")
    public Response<Boolean> getQwInfo(@RequestBody @Valid GetQwInfoTestRequest request) {
        Boolean response = getQwInfoTestService.getQwInfoTest(request);
        return Response.success(response);

    }
}