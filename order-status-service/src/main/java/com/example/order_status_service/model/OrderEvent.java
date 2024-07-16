package com.example.order_status_service.model;

import lombok.Data;

@Data
public class OrderEvent {
    private String product;
    private int quantity;
}
