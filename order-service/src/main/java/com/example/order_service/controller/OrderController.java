package com.example.order_service.controller;

import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.model.Order;
import com.example.order_service.model.OrderEvent;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    @Value("${app.kafka.orderTopic}")
    private String topicName;

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<String> sendRequest(@RequestBody Order order) {
        kafkaTemplate.send(topicName, orderMapper.orderToOrderEvent(order));
        return ResponseEntity.ok("Message was sent to kafka");
    }

    @GetMapping("/{name}")
    public ResponseEntity<OrderEvent> getByName(@PathVariable String name) {
        return ResponseEntity.ok(orderService.getByName(name).orElseThrow());
    }

    @GetMapping
    public ResponseEntity<List<OrderEvent>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }
}
