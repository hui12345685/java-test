package org.example.schedule;

import lombok.extern.slf4j.Slf4j;
import org.example.aspect.RdLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// @Component必需加的，否则程序启动的时候会提示找不到TestTask这个bean
@Slf4j
@Component
public class TestXxxTask {

    // 设置每个小时执行一次的任务
    @Scheduled(cron = "0 0 */1 * * ?")
    @RdLock(keyExpireTime = 60, key = "TestTaskxxx_hour")
    public void run() {
        long startTime = System.currentTimeMillis();
        log.info("TestXxxTask run start job:{}", startTime);

        // 这里执行具体的业务逻辑

        long timeEnd = System.currentTimeMillis();
        log.info("TestXxxTask finish start job time:{} cost time:{}", timeEnd, timeEnd - startTime);
    }
}
