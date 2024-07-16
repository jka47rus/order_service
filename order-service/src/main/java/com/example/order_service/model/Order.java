package com.example.order_service.model;

import lombok.Data;

@Data
public class Order {
    private String product;
    private int quantity;
}
