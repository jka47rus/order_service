package com.example.order_service.configuration;

import com.example.order_service.model.OrderEvent;
import com.example.order_service.model.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderListener {


    @Value("${app.kafka.orderStatusTopic}")
    private String topicName;
    private final KafkaTemplate<String, OrderStatus> kafkaTemplate;


    @KafkaListener(topics = "${app.kafka.orderTopic}",
            groupId = "$(app.orderStatusGroupId)",
            containerFactory = "orderConcurrentKafkaListenerContainerFactory")
    public void listen(@Payload OrderEvent order,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {

        log.info("Received message: {}", order);
        log.info("Key: {}; Partition: {}; Topic: {}; Timestamp: {}", key, partition, topic, timestamp);


        OrderStatus orderStatus = OrderStatus.builder().status("CREATED").date(Instant.now()).build();
        kafkaTemplate.send(topicName, orderStatus);
        log.info("Send message to kafka! \"{}\", {}; Order: {}, {}!",
                orderStatus.getStatus(), orderStatus.getDate(), order.getProduct(), order.getQuantity());

    }
}
