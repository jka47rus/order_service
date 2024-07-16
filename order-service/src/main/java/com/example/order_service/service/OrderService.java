package com.example.order_service.service;

import com.example.order_service.model.Order;
import com.example.order_service.model.OrderEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final List<OrderEvent> orders = new ArrayList<>();

    public void addOrder(OrderEvent order) {
        orders.add(order);
    }

    public Optional<OrderEvent> getByName(String name) {
        return orders.stream().filter(n -> n.getProduct().equals(name)).findFirst();
    }

    public List<OrderEvent> findAll() {
        return orders;
    }

}
