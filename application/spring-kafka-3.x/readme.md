
# Main Docs
- https://docs.spring.io/spring-kafka/reference/kafka.html

# Basic Usage

## Connect to Kafka

## Configuring Topics

- we need to define a `KafkaAdmin` bean (needed). and is auto configured in `org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration`
- define NewTopic.

```java
@Bean
public KafkaAdmin admin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    return new KafkaAdmin(configs);
}
```





# 基础命令

## topic

```shell
# 查看已有的 topic 列表
bin/kafka-topics.sh --bootstrap-server 172.17.239.191:9092 --list

```


## 消费者

```shell
bin/kafka-console-consumer.sh --bootstrap-server 172.17.239.191:9092 --topic si.topic --from-beginning

bin/kafka-console-consumer.sh --bootstrap-server 172.17.239.191:9092 --topic si.new.topic --from-beginning

bin/kafka-console-consumer.sh --bootstrap-server 172.17.239.191:9092 --topic topic1 --from-beginning

```
