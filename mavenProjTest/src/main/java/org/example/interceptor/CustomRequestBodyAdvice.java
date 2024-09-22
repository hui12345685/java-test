package org.example.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.example.request.BaseRequest;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;

@RestControllerAdvice
@Slf4j
public class CustomRequestBodyAdvice extends RequestBodyAdviceAdapter {

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        // 你可以在这里添加条件，决定是否应用这个处理器，如果不是你想处理的类型，其实就是返回false
        return BaseRequest.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
            Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 读取body之后的处理，比如一些公共字段的获取与设置，这个body就是你的controller的入参对应的对象
        log.info("CustomRequestBodyAdvice afterBodyRead");
        if (BaseRequest.class.isAssignableFrom(parameter.getParameterType())) {
            ((BaseRequest) body).setUserId(getUserId()); // 设置公共字段
        }
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }

    private String getUserId() {
        return "testUser";
    }
}
