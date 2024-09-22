package org.example.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.example.common.addname.AddName;

@Data
@ApiModel("测试返回响应类")
@ToString
public class SimpleTestResponse {
    @ApiModelProperty("返回的id")
    @AddName
    private String id ;
}
