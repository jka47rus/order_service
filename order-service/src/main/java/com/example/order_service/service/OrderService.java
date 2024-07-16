package com.example.order_service.service;

import com.example.order_service.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Optional<Order> getByName(String name) {
        return orders.stream().filter(n -> n.getProduct().equals(name)).findFirst();
    }

    public List<Order> findAll() {
        return orders;
    }

}