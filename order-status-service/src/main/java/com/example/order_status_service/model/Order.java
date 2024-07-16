package com.example.order_status_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Order {
    private String product;
    private int quantity;
}
