package org.example.mq.producer;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class KafkaProducer {
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String message) {
        try {
            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.debug("[KafkaProducer]-send message:{} with offset:{}",
                            message, result.getRecordMetadata().offset());
                } else {
                    log.error("[KafkaProducer]-send message failure", ex);
                }
            });
        } catch (Exception e) {
            log.error("[KafkaProducer]-send message exception", e);
        }
    }

    public void send(String topic, String key, String message) {
        try {
            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, message);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.debug("[KafkaProducer]-send message:{} with offset:{}",
                            message, result.getRecordMetadata().offset());
                } else {
                    log.error("[KafkaProducer]-send message failure", ex);
                }
            });
        } catch (Exception e) {
            log.error("[KafkaProducer]-send message exception", e);
        }
    }

    public void sendSync(String topic, String key, String message) throws ExecutionException, InterruptedException {
        try {
            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, message);
            future.get();
        } catch (ExecutionException | InterruptedException e) {
            log.error("[KafkaProducer]-sendSync message exception, {}, {}, {}", e, key, message);
            throw e;
        }
    }
}
