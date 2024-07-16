package com.example.order_service.mapper;

import com.example.order_service.model.Order;
import com.example.order_service.model.OrderEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderEvent orderToOrderEvent(Order order) {
        if (order == null) return null;
        return OrderEvent.builder()
                .product(order.getProduct())
                .quantity(order.getQuantity())
                .build();
    }


    public List<OrderEvent> ordersToOrderEventList(List<Order> orders) {
        return orders.stream().map(this::orderToOrderEvent).collect(Collectors.toList());
    }
}
