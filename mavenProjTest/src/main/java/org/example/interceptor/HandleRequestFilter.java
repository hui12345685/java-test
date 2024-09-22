package org.example.interceptor;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HandleRequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        log.info("HandleRequestFilter doFilter before");
        // 可以在你的请求处理之前做一些事情，比如登录校验，权限校验等等，如果不通过，直接return,不用继续调用dofilter
        // 调用filterChain.doFilter才会调用到接口处理的地方
        filterChain.doFilter(servletRequest, servletResponse);
        // 可以在你的请求处理之后做一些事情，比如记录日志等等
        log.info("HandleRequestFilter doFilter after");
    }
}
