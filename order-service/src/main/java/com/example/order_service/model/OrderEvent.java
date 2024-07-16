package com.example.order_service.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderEvent {
    private String product;
    private int quantity;
}
