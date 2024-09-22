package org.example.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Data
public class Response<T> {
    /**
     * 结果码
     */
    @ApiModelProperty("结果码")
    private int code;

    /**
     * 错误消息
     */
    @ApiModelProperty("错误消息")
    private String msg;

    /**
     * 结果数据
     */
    @ApiModelProperty("结果数据")
    private T data;

    @ApiModelProperty("请求id")
    private String requestId;

    public static <T> Response<T> success() {
        Response<T> response = new Response<>();
        response.setCode(0);
        response.setMsg("Success");
        return response;
    }

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setCode(0);
        response.setData(data);
        response.setMsg("Success");
        return response;
    }


    public static <T> Response<T> fail(int resultCode, String msg, Object extra) {
        Response<T> response = new Response<>();
        response.setCode(resultCode);
        response.setMsg(msg);
        return response;
    }
}
