package com.maple.shine.kafka.s1.sender;

import com.maple.shine.kafka.s1.common.Foo2;
import com.maple.shine.kafka.utils.MixUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * ASender
 *
 * @author leihz
 * @version 1.0.0
 * @since 2024/07/26 16:44
 */
@Component
@Slf4j
public class ASender {

  private final KafkaTemplate<String, Object> kafkaTemplate;

  public ASender(KafkaTemplate<String, Object> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void send(String topic, Foo2 foo) {
    CompletableFuture<SendResult<String, Object>> future = this.kafkaTemplate.send(topic, foo);
    future.whenComplete((result, ex) -> {
      if (ex == null) {
        MixUtils.logSendResult(result);
      } else {
        log.info("ASender send error.", ex);
      }
    });
  }
}
