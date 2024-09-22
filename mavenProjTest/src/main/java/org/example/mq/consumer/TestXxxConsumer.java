package org.example.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestXxxConsumer {
    @KafkaListener(id = "TestXxxListener",
            topics = "test-xxx-topic",
            groupId = "test-xxx-groupId",
            concurrency = "${kafka.consumer.qw.tag.event.concurrency:1}")
    public void consume(String json, Acknowledgment ack) {
        try {
            log.debug("TestXxxConsumer handle message:{}", json);
            // 将json解析为对象，然后执行业务逻辑
        } catch (Exception e) {
            log.error("TestXxxConsumer error occurred while process:{}  {}", json, e);
        } finally {
            ack.acknowledge();
        }
    }
}
