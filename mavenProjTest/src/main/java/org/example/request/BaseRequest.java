package org.example.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BaseRequest {
    @ApiModelProperty(value = "登录的用户id,前端勿填，后台从登录态获取", hidden = true)
    private String userId;
}
