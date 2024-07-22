package com.maple.shine.integration;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties for the kafka sample app.
 *
 * @author Gary Russell
 * @since 5.0
 * @since 2024/07/22 11:43
 */
@Getter
@ConfigurationProperties("kafka")
public class KafkaAppProperties {

  private String topic;

  private String newTopic;

  private String messageKey;

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public void setNewTopic(String newTopic) {
    this.newTopic = newTopic;
  }

  public void setMessageKey(String messageKey) {
    this.messageKey = messageKey;
  }

}
