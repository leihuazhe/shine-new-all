package com.maple.shine.kafka.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;

/**
 * MixUtils
 *
 * @author leihz
 * @version 1.0.0
 * @since 2024/07/26 18:07
 */
@Slf4j
public class MixUtils {

  public static <K, V> void logSendResult(SendResult<K, V> sendResult) {
    ProducerRecord<K, V> producerRecord = sendResult.getProducerRecord();
    RecordMetadata recordMetadata = sendResult.getRecordMetadata();
    try {

      log.info("producerRecord: \n{}", GsonUtils.toJson(producerRecord));
      log.info("recordMetadata: \n{}", GsonUtils.toJson(recordMetadata));
    } catch (Exception e) {
      log.error("logSendResult error", e);
    }
  }
}
