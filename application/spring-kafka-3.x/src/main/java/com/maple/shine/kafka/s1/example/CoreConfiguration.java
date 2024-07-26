/*
 * Copyright 2018-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.maple.shine.kafka.s1.example;

import com.maple.shine.kafka.s1.common.Foo2;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.util.backoff.FixedBackOff;


/**
 * Sample shows use of a dead letter topic.
 *
 * @author Gary Russell
 * @since 2.2.1
 */
@Configuration
@Slf4j
public class CoreConfiguration {
  private final TaskExecutor exec = new SimpleAsyncTaskExecutor();


  /*
   * Boot will autowire this into the container factory.
   */
  @Bean
  public CommonErrorHandler errorHandler(KafkaOperations<Object, Object> template) {
    return new DefaultErrorHandler(
        new DeadLetterPublishingRecoverer(template), new FixedBackOff(1000L, 2));
  }

  @Bean
  public RecordMessageConverter converter() {
    return new JsonMessageConverter();
  }

  @KafkaListener(id = "fooGroup", topics = "topic1")
  public void listen(Foo2 foo) {
    log.info("Received: " + foo);
    if (foo.getFoo().startsWith("fail")) {
      throw new RuntimeException("failed");
    }
    this.exec.execute(() -> System.out.println("Hit Enter to terminate..."));
  }

  @KafkaListener(id = "dltGroup", topics = "topic1.DLT")
  public void dltListen(byte[] in) {
    log.info("Received from DLT: " + new String(in));
    this.exec.execute(() -> System.out.println("Hit Enter to terminate..."));
  }

  @Bean
  public NewTopic topic() {
    return new NewTopic("topic1", 1, (short) 1);
  }

  @Bean
  public NewTopic dlt() {
    return new NewTopic("topic1.DLT", 1, (short) 1);
  }

  @Bean
  public NewTopic topic1() {
    return TopicBuilder.name("maple-2024")
        .partitions(1)
        .replicas(1)
        .compact()
        //.config(TopicConfig.COMPRESSION_TYPE_CONFIG, "zstd")
        .build();
  }

  @Bean
  @Profile("default") // Don't run from test(s)
  public ApplicationRunner runner() {
    return args -> {
      System.out.println("Hit Enter to terminate...");
      System.in.read();
    };
  }

}
